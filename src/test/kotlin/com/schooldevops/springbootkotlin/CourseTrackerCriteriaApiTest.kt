package com.schooldevops.springbootkotlin

import com.schooldevops.springbootkotlin.configs.logger
import com.schooldevops.springbootkotlin.model.Course
import com.schooldevops.springbootkotlin.repositories.CoursePagingRepository
import com.schooldevops.springbootkotlin.repositories.CourseRepository
import jakarta.persistence.EntityManager
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.sql.DataSource

@SpringBootTest
class CourseTrackerCriteriaApiTest {

    private lateinit var dataSource: DataSource
    val log = logger<CourseTrackerPagingSpringBootApplicationTests>()

    @Autowired
    lateinit var courseRepository: CoursePagingRepository

    @Autowired
    lateinit var crudRepository: CourseRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @BeforeEach
    fun initialData() {
        crudRepository.saveAll(getCourseList());
    }

    @Test
    fun givenCourseCreatedWhenLoadCoursesWithQueryThenExpectCorrectCourseDetails() {

        // 관련 조건:
        //  - https://www.baeldung.com/hibernate-criteria-queries
        //  - https://docs.oracle.com/javaee/7/tutorial/persistence-criteria001.htm#GJRIJ
        //  - https://docs.oracle.com/javaee/7/tutorial/persistence-criteria003.htm#GJIVM

        // CriteriaBuilder 를 생성하여 쿼리 빌딩을 준비한다.
        val criteriaBuilder = entityManager.criteriaBuilder

        // Entity Course 에 대해서 쿼리를 하겠다고 빌더에 선언하고 쿼리구문을 생성해 낸다.
        val courseCriteria = criteriaBuilder.createQuery(Course::class.java)
        //  어떤 테이블에서 조회할지 엔터티를 지정한다.
        val courseRoot = courseCriteria.from(Course::class.java)

        //  빌더에 where 조건식을 생성해 낸다. category가 Spring인지 검사하게 된다.
        val predicate = criteriaBuilder.equal(courseRoot.get<String>("category"), "Spring")
        // 조회조건식을 생성한 쿼리 구문에 요청한다.
        courseCriteria.where(predicate)

        // entityManager 를 이용하여 쿼리를 실행한다.
        val query = entityManager.createQuery(courseCriteria)
        assert(query.resultList.size == 3)
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