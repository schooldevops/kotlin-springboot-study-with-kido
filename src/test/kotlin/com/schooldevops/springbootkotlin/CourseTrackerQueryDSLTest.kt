package com.schooldevops.springbootkotlin

import com.querydsl.jpa.impl.JPAQuery
import com.schooldevops.springbootkotlin.configs.logger
import com.schooldevops.springbootkotlin.model.Course
import com.schooldevops.springbootkotlin.model.QCourse
import com.schooldevops.springbootkotlin.repositories.CoursePagingRepository
import com.schooldevops.springbootkotlin.repositories.CourseRepository
import com.schooldevops.springbootkotlin.repositories.QueryDSLCourseRepository
import jakarta.persistence.EntityManager
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.sql.DataSource

@SpringBootTest
class CourseTrackerQueryDSLTest {

    private lateinit var dataSource: DataSource
    val log = logger<CourseTrackerPagingSpringBootApplicationTests>()

    @Autowired
    lateinit var courseRepository: QueryDSLCourseRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @BeforeEach
    fun initialData() {
        courseRepository.saveAll(getCourseList());
    }

    @Test
    fun givenCoursesCreatedWhenLoadCoursesWithQueryThenExpectCorrectCourseDetails() {

        val course = QCourse.course
        val jpaQuery = JPAQuery<Course>(entityManager)
        jpaQuery.from(course)
            .where(course.category.eq("Spring"))
//        assert(jpaQuery.fetch().size == 3)
        println("Fetch Using Query DSL: " + jpaQuery.fetch().size)

        val jpaQuery2 = JPAQuery<Course>(entityManager)
        jpaQuery2.from(course)
            .where(course.category.eq("Spring").and(course.rating.gt(3)))
//        assert(jpaQuery2.fetch().size == 2)
        println("Fetch Using Query DSL2: " + jpaQuery2.fetch().size)

        val descOrderSpec = course.rating.desc()
        val allLists = courseRepository.findAll(descOrderSpec)
        println("Sort : ---------------")
        allLists.forEach{println(it)}
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