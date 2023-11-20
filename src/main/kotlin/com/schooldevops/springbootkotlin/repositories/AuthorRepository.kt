package com.schooldevops.springbootkotlin.repositories

import com.schooldevops.springbootkotlin.model.Author
import com.schooldevops.springbootkotlin.model.AuthorCourse
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository: CrudRepository<Author, Long> {
    @Query("SELECT new com.schooldevops.springbootkotlin.dto.AuthorCourseDto(c.id, a.name, c.name, c.description) from AUTHOR a, Course c, AUTHOR_COURSE ac where a.id = ac.authorId and c.id=ac.courseId and ac.authorId=?1")
    fun getAuthorCourseInfo(authorId: Long): Iterable<AuthorCourse>
}