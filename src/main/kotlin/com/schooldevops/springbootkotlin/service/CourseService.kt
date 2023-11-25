package com.schooldevops.springbootkotlin.service

import com.schooldevops.springbootkotlin.model.Course
import com.schooldevops.springbootkotlin.model.project.DescriptionOnly
import com.schooldevops.springbootkotlin.repositories.QueryDSLProjectRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class CourseService {

    @Autowired
    lateinit var courseRepository: QueryDSLProjectRepository

    @Cacheable(value = ["default"], key = "#name")
    fun getCourceByName(name: String): Iterable<DescriptionOnly> {
        val result = courseRepository.getCourseByName("Rapid Spring Boot Application Development")
        result.forEach {println(it)}
        return result
    }

    @Cacheable(value = ["default"], key="#root.target + #root.methodName + #p0 + #p1")
    fun getCacheTest01(name: String, value: Int): String {
        val content = "getCacheTest01 : name: $name, value: $value"
        println(content)
        return content
    }

    @Cacheable(value = ["default"], condition = "#course.category == 'Spring'")
    fun getCacheTest02(course: Course): Course {
        println("getCacheTest02 : $course")
        return course
    }

    @Cacheable(value = ["default"], unless = "#course.category == 'Spring'")
    fun getCacheTest03(course: Course): Course {
        println("getCacheTest03 : $course")
        return course
    }

}