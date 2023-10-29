package com.schooldevops.springbootkotlin.repositories

import com.schooldevops.springbootkotlin.model.Course
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : CrudRepository<Course, Long>{
}