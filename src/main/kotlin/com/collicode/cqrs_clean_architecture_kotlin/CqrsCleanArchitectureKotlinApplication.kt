package com.collicode.cqrs_clean_architecture_kotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableR2dbcAuditing
@EnableScheduling
@ConfigurationPropertiesScan
@ComponentScan(
    basePackages = [
        "com.collicode.cqrs_clean_architecture_kotlin",
        "com.collicode.common"
    ]
)
class CqrsCleanArchitectureKotlinApplication

fun main(args: Array<String>) {
    runApplication<CqrsCleanArchitectureKotlinApplication>(*args)
}