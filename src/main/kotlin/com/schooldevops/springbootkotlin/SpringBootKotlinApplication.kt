package com.schooldevops.springbootkotlin

import com.schooldevops.springbootkotlin.configs.DBConfiguration
import com.schooldevops.springbootkotlin.configs.logger
import com.schooldevops.springbootkotlin.model.Course
import com.schooldevops.springbootkotlin.model.User
import com.schooldevops.springbootkotlin.properties.AppProperties
import com.schooldevops.springbootkotlin.service.AppService
import com.schooldevops.springbootkotlin.service.DataSourceService
import jakarta.validation.Validation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import java.util.*

/**
 * application.properties 혹은 application.yaml 파일을 로드되는 시점
 * 1. 애플리케이션 Jar 파일 내부에 패키징 되는 application.yaml
 * 2. 애플리케이션 Jar 파일 내부에 패키징 되는 application-{profile}.yaml
 * 3. 애플리케이션 Jar 파일 밖에서 패키징 되는 application.yaml
 * 4. 애플리케이션 Jar 파일 밖에서 패키징 되는 application-{profile}.yaml
 */
@EnableConfigurationProperties(AppProperties::class)
@SpringBootApplication
class SpringBootKotlinApplication:CommandLineRunner {
	val log = logger<SpringBootKotlinApplication>()
	@Autowired
	private lateinit var dataSourceService: DataSourceService
	override fun run(vararg args: String?) {

		dataSourceService.loggingDataSource()

		log.info(">>>>>>>>>>>>>> command line runner")

		val course = Course(10, "kido", "class_a", 0, "Programmer")

		val validator = Validation.buildDefaultValidatorFactory().validator
		val validation = validator.validate(course)

		validation.forEach {
			log.error("A constraint validation has occured. Violation details: [{}]", it)
		}

		val user = User("schooldevops", "password")
		val validatedPassword = validator.validate(user)
		validatedPassword.forEach {
			log.error("Password Validation result: [{}]", it)
		}

		val user02 = User("schooldevops02", "Password!01")
		val validatePassword02 = validator.validate(user02)
		if (validatePassword02.isEmpty()) {
			log.info("Password 02 is valid")
		}

		val user03 = User("schooldevops02", "Password!00000")
		val validatePassword03 = validator.validate(user03)
		validatePassword03.forEach {
			log.error("Password Validation repeat result: [{}]", it)
		}

		val user04 = User("schooldevops02", "Password00000")
//		val validatePassword04 = validator.validate(user04)
		validatePassword03.forEach {
			log.error("Password Validation special result: [{}]", it)
		}


	}
}


fun main(args: Array<String>){

	val log = logger<SpringBootKotlinApplication>()

	/**
	 * 설정 파일이 클래스 패스 위치에 존재하지 않는경우 다음 오류가 나므로 설정 파일이 없다고 해도 서버가 동작하도록 설정한다.
	 * Config data resource 'class path resource [additional-application.properties]' via location 'classpath:additional-application.properties' does not exist
	 * 발생하므로 spring.config.on-not-found 을 ignore로 설정하면 서버가 수행된다.
	 */
	val properties = Properties()
	properties.setProperty("spring.config.on-not-found", "ignore")

	val springApplication = SpringApplication(SpringBootKotlinApplication::class.java)
	springApplication.setDefaultProperties(properties)

	/**
	 * dbProperty 를 조회하기 위해서 springApplication이 수행될때 컨텍스트를 획득한 후 빈을 조회한다.
	 */
	val applicationContext = springApplication.run(*args)
	val dbConfiguration = applicationContext.getBean(DBConfiguration::class.java)

	log.info("DB Config --------------- {}", dbConfiguration)

	val appService = applicationContext.getBean(AppService::class.java)
	appService.printAppProperties()

//	runApplication<SpringBootKotlinApplication>(*args)
}
