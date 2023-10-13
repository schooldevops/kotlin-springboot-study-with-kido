package com.schooldevops.springbootkotlin.service

import com.schooldevops.springbootkotlin.SpringBootKotlinApplication
import com.schooldevops.springbootkotlin.configs.logger
import com.schooldevops.springbootkotlin.properties.AppProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AppService {
    val log = logger<AppService>()

    @Autowired
    private val appProperties: AppProperties? = null

    fun printAppProperties() {
        log.info("AppProperteis: {}", appProperties)
    }
}