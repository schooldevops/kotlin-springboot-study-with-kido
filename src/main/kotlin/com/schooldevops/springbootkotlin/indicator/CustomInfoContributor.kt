package com.schooldevops.springbootkotlin.indicator

import org.springframework.boot.actuate.info.Info
import org.springframework.boot.actuate.info.InfoContributor
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
class CustomInfoContributor(private val env: Environment): InfoContributor {

    override fun contribute(builder: Info.Builder?) {
        builder?.withDetail("author", "UncleBae")
        builder?.withDetail("github", "https://github.com/schooldevops")
    }

    fun collectInfo(): Map<String, Any?> {
        val info = mutableMapOf<String, Any?>()

        val propertySources = (env as org.springframework.core.env.ConfigurableEnvironment).propertySources
        propertySources.forEach {
            propertySource ->
            if (propertySource.source != null) {
                info.put(propertySource.name, propertySource.source.toString())
            }
        }

        contribute(Info.Builder().withDetails(info))

        return info
    }
}