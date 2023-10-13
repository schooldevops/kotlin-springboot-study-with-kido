package com.schooldevops.springbootkotlin.properties

import org.springframework.boot.context.properties.ConfigurationProperties


@ConfigurationProperties("app.sbip.ct")
data class AppProperties(
        val name: String? = null,
        val ip: String? = null,
        val port: Int? = 8080,
        val security: Security? = null


    )

data class Security(val enabled: Boolean = false, val token: String? = null, val roles: List<String>?)

