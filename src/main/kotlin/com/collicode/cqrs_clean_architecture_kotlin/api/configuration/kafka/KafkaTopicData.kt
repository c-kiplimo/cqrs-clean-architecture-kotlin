package com.collicode.cqrs_clean_architecture_kotlin.api.configuration.kafka

data class KafkaTopicData(var name: String = "", var partitions: Int = 1, var replication: Int = 1)
