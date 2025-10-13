package com.collicode.cqrs_clean_architecture_kotlin.api.configuration.kafka

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.annotation.PostConstruct
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.kafka.core.KafkaAdmin
import org.springframework.stereotype.Component


@Component
class KafkaTopicsInitializer(
    private val kafkaTopics: KafkaTopics,
    private val kafkaAdmin: KafkaAdmin
) {

    @PostConstruct
    fun init() {
        runCatching {
            kafkaTopics.getTopics()
                .map { NewTopic(it.name, it.partitions, it.replication.toShort()) }
                .forEach {
                    kafkaAdmin.createOrModifyTopics(it)
                    log.info { "created or modified topic: $it" }
                }
        }
            .onSuccess { log.info { "kafka topics created successfully" } }
            .onFailure { log.error(it) { "error while creating kafka topics: ${it.message}" } }
        // Don't call .getOrThrow() - allow app to continue even if Kafka fails
    }

    private companion object {
        private val log = KotlinLogging.logger { }
    }
}