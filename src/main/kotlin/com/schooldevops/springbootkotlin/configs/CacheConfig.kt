package com.schooldevops.springbootkotlin.configs

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@EnableCaching
@Configuration
@EnableConfigurationProperties(CacheProperties::class)
class CacheConfig(private var cacheProperties: CacheProperties) {
    @Bean
    @ConditionalOnExpression(value = "#{cacheProperties.storage.equals('memory')}")
    @Qualifier("inMemoryCache")
    fun inMemoryCache(): CacheManager {
        val memoryCache = ConcurrentMapCacheManager("default")
        return memoryCache
    }
}