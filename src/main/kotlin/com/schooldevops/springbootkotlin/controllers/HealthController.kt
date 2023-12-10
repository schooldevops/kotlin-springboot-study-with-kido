package com.schooldevops.springbootkotlin.controllers

import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController(private val customHealthIndicator: HealthIndicator) {
    @GetMapping("/custom-health")
    fun customHealth(): Health {
        return customHealthIndicator.health()
    }
}