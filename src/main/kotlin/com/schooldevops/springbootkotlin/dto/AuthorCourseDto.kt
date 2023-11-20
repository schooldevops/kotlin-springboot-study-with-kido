package com.schooldevops.springbootkotlin.dto

data class AuthorCourseDto(
    var id: Long,
    var authorName: String,
    var courseName: String,
    var description: String
    )
{
}