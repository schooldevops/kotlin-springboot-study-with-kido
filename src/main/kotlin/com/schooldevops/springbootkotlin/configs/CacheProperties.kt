package com.schooldevops.springbootkotlin.configs

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration


@ConfigurationProperties(
    prefix = "cache"
)
@Configuration
class CacheProperties(
    var storage: String = "memory",
    var cacheNames: List<String> = mutableListOf()
)