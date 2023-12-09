package com.schooldevops.springbootkotlin.configs

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@EnableCaching
@Configuration
@EnableConfigurationProperties(CacheProperties::class)
class CacheConfig(var cacheProperties: CacheProperties) {

    @Bean
    @ConditionalOnExpression(value = "#{'\${cache.storage}' == 'memory'}")
    @Qualifier("inMemoryCache")
    fun inMemoryCache(): CacheManager {

        println("Storage: ${cacheProperties.storage}")
        println("cacheNames: ${cacheProperties.getCacheNames()}")

        val memoryCache = ConcurrentMapCacheManager()
        memoryCache.setCacheNames(cacheProperties.getCacheNames())
        return memoryCache
    }

    @Bean
    @ConditionalOnExpression(value = "#{'\${cache.storage}' == 'redis'}")
    fun lettuceConnectionFactory(): LettuceConnectionFactory {

        println("storage: ${cacheProperties.storage}")
        println("cacheNames: ${cacheProperties.getCacheNames()}")

        val config = LettuceClientConfiguration.builder()
            .commandTimeout(Duration.ofSeconds(3))
            .shutdownTimeout(Duration.ofSeconds(5))
            .build()

        return LettuceConnectionFactory(RedisStandaloneConfiguration("localhost", 6379), config)
    }

    @Bean
    @Qualifier("redis")
    @ConditionalOnExpression(value = "#{'\${cache.storage}' == 'redis'}")
    fun redisCacheManager(): RedisCacheManager {
        return RedisCacheManager.RedisCacheManagerBuilder
            .fromConnectionFactory(lettuceConnectionFactory())
            .cacheDefaults(defaultConfiguration())
            .withInitialCacheConfigurations(customConfigurationMap())
            .build()
    }

    private fun defaultConfiguration(): RedisCacheConfiguration {
        val cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(
                    GenericJackson2JsonRedisSerializer()
                ))
            .entryTtl(Duration.ofMinutes(1))
        return cacheConfig
    }

    private fun customConfigurationMap(): Map<String, RedisCacheConfiguration> {
        val customConfigurationMap: MutableMap<String, RedisCacheConfiguration> = HashMap()

        cacheProperties.cacheInfos.stream().forEach { it -> customConfigurationMap[it.cacheName] = defaultConfiguration().entryTtl(Duration.ofSeconds(it.ttl))}
        return customConfigurationMap
    }
}