package com.schooldevops.springbootkotlin.service

import com.schooldevops.springbootkotlin.SpringBootKotlinApplication
import com.schooldevops.springbootkotlin.configs.logger
import org.springframework.stereotype.Service
import javax.sql.DataSource

@Service
class DataSourceService(val dataSource: DataSource) {
    val log = logger<SpringBootKotlinApplication>()

    fun loggingDataSource() {
        log.info("DataSource --------- [{}]", dataSource)
    }
}