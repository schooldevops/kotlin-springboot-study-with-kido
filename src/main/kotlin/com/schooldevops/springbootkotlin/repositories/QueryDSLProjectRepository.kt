package com.schooldevops.springbootkotlin.repositories

import com.schooldevops.springbootkotlin.model.Course
import com.schooldevops.springbootkotlin.model.project.DescriptionOnly
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface QueryDSLProjectRepository : CrudRepository<Course, Long>{
    fun getCourseByName(name: String): Iterable<DescriptionOnly>
}