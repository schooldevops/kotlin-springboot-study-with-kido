package com.schooldevops.springbootkotlin.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableMBeanExport
import org.springframework.jmx.export.MBeanExporter

@Configuration
@EnableMBeanExport
class JMXConfig {

//    @Bean
//    fun mbeanExporter(): MBeanExporter {
//        val exporter = MBeanExporter()
//        exporter.setAutodetect(true)
//        return exporter
//    }
}