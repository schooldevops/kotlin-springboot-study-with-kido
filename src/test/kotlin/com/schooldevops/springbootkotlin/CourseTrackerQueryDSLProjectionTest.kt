package com.schooldevops.springbootkotlin

import com.querydsl.jpa.impl.JPAQuery
import com.schooldevops.springbootkotlin.configs.logger
import com.schooldevops.springbootkotlin.model.Course
import com.schooldevops.springbootkotlin.model.QCourse
import com.schooldevops.springbootkotlin.repositories.CoursePagingRepository
import com.schooldevops.springbootkotlin.repositories.CourseRepository
import com.schooldevops.springbootkotlin.repositories.QueryDSLCourseRepository
import com.schooldevops.springbootkotlin.repositories.QueryDSLProjectRepository
import com.schooldevops.springbootkotlin.service.CourseService
import jakarta.persistence.EntityManager
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.sql.DataSource

@SpringBootTest
class CourseTrackerQueryDSLProjectionTest {

    private lateinit var dataSource: DataSource
    val log = logger<CourseTrackerPagingSpringBootApplicationTests>()

    @Autowired
    lateinit var courseRepository: QueryDSLProjectRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var courseService: CourseService

    @BeforeEach
    fun initialData() {
        courseRepository.saveAll(getCourseList());
    }

    @Test
    fun givenACourseAvailableWhenGetCourseByNameThenCourseDescription() {
        val result = courseRepository.getCourseByName("Rapid Spring Boot Application Development")
        result.forEach {println(it)}
    }

    @Test
    fun getCourseByName() {
        val result = courseService.getCourceByName("Rapid Spring Boot Application Development")
        result.forEach {println("------------------------------------------" + it)}
        val result2 = courseService.getCourceByName("Rapid Spring Boot Application Development")
        result2.forEach {println("2------------------------------------------" + it)}
    }

    @Test
    fun getCourseTest01() {
        val course: Course = Course(100, "Hello", "Spring", 10, "Hello Spring")
        val result = courseService.getCacheTest01(course.name, course.rating)
        println("getCourseTest01_01 ------------------------------------------" + result)
        val result2 = courseService.getCacheTest01(course.name, course.rating)
        println("getCourseTest01_02 ------------------------------------------" + result2)
    }

    @Test
    fun getCourseTest02() {
        val course: Course = Course(100, "Hello", "Spring", 10, "Hello Spring")
        val result = courseService.getCacheTest02(course)
        println("getCacheTest02_01 ------------------------------------------" + result)
        val result2 = courseService.getCacheTest02(course)
        println("getCacheTest02_02 ------------------------------------------" + result2)
    }

    @Test
    fun getCourseTest03() {
        val course: Course = Course(100, "Hello", "Python", 10, "Hello Spring")
        val result = courseService.getCacheTest03(course)
        println("getCacheTest03_01 ------------------------------------------" + result)
        val result2 = courseService.getCacheTest03(course)
        println("getCacheTest03_02 ------------------------------------------" + result2)
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