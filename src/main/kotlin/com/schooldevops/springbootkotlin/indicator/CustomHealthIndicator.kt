package com.schooldevops.springbootkotlin.indicator

import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component

@Component
class CustomHealthIndicator: HealthIndicator {
    override fun health(): Health {
        val status = "healthy" // 특정 조건에 따라 healthy여부를 결정한다.
        return if (status == "healthy") {
            Health.up().withDetail("message", "Custom health check passed").build()
        } else {
            Health.down().withDetail("message", "Custom health check failed").build()
        }
    }
}