package com.schooldevops.springbootkotlin

import com.schooldevops.springbootkotlin.configs.logger
import com.schooldevops.springbootkotlin.model.Course
import com.schooldevops.springbootkotlin.repositories.CoursePagingRepository
import com.schooldevops.springbootkotlin.repositories.CourseRepository
import com.schooldevops.springbootkotlin.repositories.CustomizedCourseRepository
import org.hamcrest.Condition
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.repository.CrudRepository
import java.sql.SQLException
import javax.sql.DataSource

@SpringBootTest
class CourseTrackerPagingSpringBootApplicationTests() {

	private lateinit var dataSource: DataSource
	val log = logger<CourseTrackerPagingSpringBootApplicationTests>()

	@Autowired
	lateinit var courseRepository: CoursePagingRepository

	@Autowired
	lateinit var crudRepository: CourseRepository

	@BeforeEach
	fun initialData() {
		crudRepository.saveAll(getCourseList());
	}

	@Test
	fun givenDataAvilableWhenLoadFirstPageThenGetFiveRecords() {
//		crudRepository.saveAll(getCourseList());

		// PageRequest.of는 페이지번호, 페이지당 개수를 지정한다. 다음은 0번 페이지, 5개 row를 가져오라는 의미이다.
		val pageable = PageRequest.of(0, 5)
		val findAll = courseRepository.findAll(pageable)

		println("findAll.count(): " + findAll.count())
		println("pageable.pageNumber: " + pageable.pageNumber)
		println("pageable.pageSize: " + pageable.pageSize)
//		assert(findAll.count() == 5)
//		assert(pageable.pageNumber == 0)
//		assert(pageable.pageSize == 5)

		val next = pageable.next()
		val findAll01 = courseRepository.findAll(next)
		println("findAll01: " + findAll01.count())

	}

	@Test
	fun givenDataAvailableWhenSortsFirstPageThenGetSortedData() {
//		crudRepository.saveAll(getCourseList());
		val pageable = PageRequest.of(0, 5, Sort.by(Sort.Order.asc("Name")))

		val findAll = courseRepository.findAll(pageable)
		findAll.content.forEach {
			println(it.toString())
		}
	}

	@Test
	fun givenDataAvailableWhenApplyCustomSortThenGetSortedResult() {
		val pageable = PageRequest.of(0, 5, Sort.by("Rating").descending().and(Sort.by("Name")))
		val findAll = courseRepository.findAll(pageable)
		findAll.content.forEach {
			println(it.toString())
		}
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
