package com.schooldevops.springbootkotlin

import com.schooldevops.springbootkotlin.configs.logger
import com.schooldevops.springbootkotlin.model.Course
import com.schooldevops.springbootkotlin.repositories.CourseRepository
import com.schooldevops.springbootkotlin.repositories.CustomizedCourseRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.sql.SQLException
import javax.sql.DataSource

@SpringBootTest
class CourseTrackerSpringBootApplicationTests() {

	private lateinit var dataSource: DataSource
	val log = logger<CourseTrackerSpringBootApplicationTests>()

	@Autowired
	lateinit var courseRepository: CustomizedCourseRepository

	@Test
	fun givenCreateCourseWhenFindAllCoursesThenExpectOneCourse() {
		val course = Course(null, "Rapid Spring Boot Application Development", "Spring", 4, "'Spring Boot gives all the power of the Spring Framework without all of the complexities")
		val savedCourse = courseRepository.save(course)
		val findAll = courseRepository.findAll()
		assert(findAll.count() == 1)

	}


}
