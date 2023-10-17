package com.schooldevops.springbootkotlin

import com.schooldevops.springbootkotlin.configs.logger
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.sql.SQLException
import javax.sql.DataSource

@SpringBootTest
class SpringBootKotlinApplicationTests() {

	private lateinit var dataSource: DataSource
	val log = logger<SpringBootKotlinApplicationTests>()

	@Test
//	@Throws(SQLException::class)
	fun givenDatasourceAvailableWhenAccessDetailsThenExpectDetails() {
		log.info("------------->>> " + dataSource.javaClass.name)
		log.info("------------->>> " + dataSource.connection.metaData.databaseProductName)
//		assert(dataSource.javaClass.name.equals("com.zaxxer.hikari.HikariDataSource"))
//		assert(dataSource.connection.metaData.databaseProductName.equals("H2"))
	}

}
