package com.schooldevops.springbootkotlin.model

import jakarta.persistence.*

@Entity(name = "AUTHOR")
@Table(name = "AUTHORS")
class Author(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var name: String,
    var bio: String
) {
    @ManyToMany
    @JoinTable(
        name = "author_course",
        joinColumns = [ JoinColumn(name="author_id", referencedColumnName = "id", nullable = false, updatable = false) ],
        inverseJoinColumns = [JoinColumn(name="course_id", referencedColumnName = "id", nullable = false, updatable = false)]
    )
    val course: Set<Course> = mutableSetOf()

    constructor() : this(null, "", "")

    override fun toString(): String {
        return "Author{id=$id, name='$name', bio='$bio'}";
    }
}