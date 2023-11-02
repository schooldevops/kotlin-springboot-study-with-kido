package com.schooldevops.springbootkotlin

import com.schooldevops.springbootkotlin.configs.logger
import com.schooldevops.springbootkotlin.model.Course
import com.schooldevops.springbootkotlin.repositories.CourseRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.sql.SQLException
import javax.sql.DataSource

@SpringBootTest
class SpringBootKotlinApplicationTests() {

	private lateinit var dataSource: DataSource
	val log = logger<SpringBootKotlinApplicationTests>()

	@Autowired
	lateinit var courseRepository: CourseRepository

	@Test
	fun givenCreateCourseWhenLoadTheCourseThenExpectSameCourse() {
		val course = Course(null, "Rapid Spring Boot Application Development", "Spring", 4, "'Spring Boot gives all the power of the Spring Framework without all of the complexities")
		val savedCourse = courseRepository.save(course)
		val selectedCourse = savedCourse.id?.let { courseRepository.findById(it).get() }
		println("selectedCourse : " +  selectedCourse + " course : " + course)
//		assert(selectedCourse == course)

	}

	@Test
	fun givneUpdateCourseWhenLoadTheCourseThenExpectUpdatedCourse() {
		var course = Course(null, "Rapid Spring Boot Application Development", "Spring", 4, "'Spring Boot gives all the power of the Spring Framework without all of the complexities")
		courseRepository.save(course)
		course.rating = 5
		val savedCourse = courseRepository.save(course)

		println("savedCourse.rating : " +  savedCourse.rating)

//		assert(savedCourse.rating == 5)
	}

	@Test
	fun givenDeleteCourseWhenLoadTheCourseThenExpectNoCourse() {
		var course = Course(null, "Rapid Spring Boot Application Development", "Spring", 4, "'Spring Boot gives all the power of the Spring Framework without all of the complexities")
		val savedCourse = courseRepository.save(course)
//		assert(savedCourse == course)
		println("savedCourse : " +  savedCourse + " : course ; " + course)


		courseRepository.delete(course)
//		assert(savedCourse.id?.let { courseRepository.findById(it) }?.isPresent == false)
	}

//	@Test
//	@Throws(SQLException::class)
	fun givenDatasourceAvailableWhenAccessDetailsThenExpectDetails() {
		log.info("------------->>> " + dataSource.javaClass.name)
		log.info("------------->>> " + dataSource.connection.metaData.databaseProductName)
//		assert(dataSource.javaClass.name.equals("com.zaxxer.hikari.HikariDataSource"))
//		assert(dataSource.connection.metaData.databaseProductName.equals("H2"))
	}

}
