package com.collicode.cqrs_clean_architecture_kotlin.infrastructure.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "mongo")
data class MongoProperties(val mongoURI: String)
