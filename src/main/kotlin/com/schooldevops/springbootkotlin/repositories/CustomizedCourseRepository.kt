package com.schooldevops.springbootkotlin.repositories

import com.schooldevops.springbootkotlin.model.Course
import org.springframework.stereotype.Repository

@Repository
interface CustomizedCourseRepository: BaseRepository<Course, Long> {
}