package com.schooldevops.springbootkotlin.configs

import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component


@Component
@ConfigurationProperties(
    prefix = "cache"
)
class CacheProperties : InitializingBean {
    var storage: String = "default"
    var cacheInfos: List<CacheDetails> = listOf()

    override fun afterPropertiesSet() {
        println("Initialized ------------------- ${storage} / ${cacheInfos}")
    }

    fun getCacheNames() : List<String> {
        return if (cacheInfos.isEmpty()) listOf("default")
        else {
            cacheInfos.map { it -> it.cacheName }.toList()
        }
    }

    class CacheDetails {
        var cacheName: String = ""
        var ttl: Long = 0

        override fun toString(): String {
            return "${cacheName}, ttl: ${ttl} ms"
        }
    }
}