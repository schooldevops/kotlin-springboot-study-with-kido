package com.schooldevops.springbootkotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity(name = "AUTHOR_COURSE")
@Table(name = "AUTHOR_COURSE")
class AuthorCourse(
    @Id
    @Column(name = "author_id")
    var authorId: Long?,
    @Column(name = "course_id")
    var courseId: Long?
) {
    constructor() : this(null, null) {

    }

}