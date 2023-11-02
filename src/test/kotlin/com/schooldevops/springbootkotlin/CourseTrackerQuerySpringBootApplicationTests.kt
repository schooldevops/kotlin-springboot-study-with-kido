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
class CourseTrackerQuerySpringBootApplicationTests() {

	private lateinit var dataSource: DataSource
	val log = logger<CourseTrackerQuerySpringBootApplicationTests>()

	@Autowired
	lateinit var courseRepository: CourseRepository

	@Test
	fun givenCreateCourseWhenLoadTheCourseThenExpectSameCourse() {
		courseRepository.saveAll(getCourseList());
		val findAllByCategory = courseRepository.findAllByCategory("Spring")
		println("findAllByCategory.count(): " + findAllByCategory.count())
//		assert(findAllByCategory.count() == 3)

		val existsByName = courseRepository.existsByName("JavaScript for All")
		assert(existsByName)

		val existsByName1 = courseRepository.existsByName("Mastering JavaScript")
		assert(existsByName1 == false)

		val countByCategory = courseRepository.countByCategory("Python")
		println("countByCategory : " + countByCategory)
//		assert(countByCategory == 2L)

		val findByNameStartsWith = courseRepository.findByNameStartsWith("Getting Started")
//		assert(findByNameStartsWith.count() == 3)
		println("findByNameStartsWith.count() : " + findByNameStartsWith.count())

	}

	@Test
	fun givenCoursesCreatedWhenLoadCoursesbySpringCategoryThenExpectThreeCourses() {
		courseRepository.saveAll(getCourseList());

		val findAllByCategoryAndRating = courseRepository.findAllByCategoryAndRating("Spring", 4)
		println("findAllByCategoryAndRating.count(): " + findAllByCategoryAndRating.count())
//		assert(findAllByCategoryAndRating.count() == 1)

		val findAllByRating = courseRepository.findAllByRating2(4)
		println("findAllByRating.count(): " + findAllByRating.count())
//		assert(findAllByRating.count() == 2)
	}

	private fun getCourseList(): List<Course> {
		val course1 = Course(
			null,
			"Rapid Spring Boot Application Development",
			"Spring",
			4,
			"Spring Boot gives all the power of the Spring Framework without all of the complexity"
		)
		val course2 = Course(
			null,
			"Getting Started with Spring Security",
			"Spring",
			5,
			"Learning Spring Security DSL in easy steps"
		)
		val course3 = Course(
			null,
			"Getting Started with Spring Cloud Kubernetes",
			"Spring",
			3,
			"Master Spring Boot application development with Kubrernetes"
		)
		val course4 = Course(
			null,
			"Getting Started with Python",
			"Python",
			5,
			"Learn Python concepts in easy steps"
		)
		val course5 = Course(
			null,
			"Game Development with Python",
			"Python",
			3,
			"Learn Python concepts in easy steps"
		)
		val course6 = Course(
			null,
			"JavaScript for All",
			"JavaScript",
			4,
			"Learn basic JavaScript syntax that can apply to anywhere"
		)
		val course7 = Course(
			null,
			"JavaScript Complete Guide",
			"JavaScript",
			5,
			"Master JavaScript with Core Concepts and Web Development"
		)
		return listOf(course1, course2, course3, course4, course5, course6, course7)
	}


}
