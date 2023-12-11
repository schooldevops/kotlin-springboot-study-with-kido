package com.schooldevops.springbootkotlin.controllers

import com.schooldevops.springbootkotlin.indicator.CustomInfoContributor
import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InfoController(private val customInfoContributor: CustomInfoContributor) {
    @GetMapping("/custom-info")
    fun customInfo(): Map<String, Any?> {
        return customInfoContributor.collectInfo()
    }
}