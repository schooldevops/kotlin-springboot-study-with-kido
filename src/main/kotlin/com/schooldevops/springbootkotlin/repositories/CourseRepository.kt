package com.schooldevops.springbootkotlin.repositories

import com.schooldevops.springbootkotlin.model.Course
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.stream.Stream

/**
 * SpringData query 생성 규칙은 다음 형식을 따른다.
 *
 * findDistinctCourseByCategoryOrderByName
 *
 * - find: 쿼리패턴
 * - findDistinctCourse: 주어부분 (즉, 구별되는 코스를 찾는다)
 * - By: 구분자 (어떤 조건에 의해서 해당하는 ... 등으로 해석)
 * - CategoryOrderByName: 서술어 부분 (이름에 따라 정렬된 카테고리를 말한다.)
 * - OrderBy: 조건식
 */
@Repository
interface CourseRepository : CrudRepository<Course, Long>{

    /**
     * 카테고리에 해당하는 모든 코스를 찾고 Iterable 으로 돌려줘라.
     */
    fun findAllByCategory(category: String) : Iterable<Course>

    /**
     * 카테고리에 해당하는 모든 코스를 찾고 이름에 의해서 정렬하여 반환해라.
     */
    fun findAllByCategoryOrderByName(category: String): Iterable<Course>

    /**
     * 이름에 해당하는 코스가 존재하는지 검사하라. 존재하면 true, 아니면 false반환
     */
    fun existsByName(name: String): Boolean

    /**
     * 카테고리에 해당하는 코스 개수를 반환하라.
     */
    fun countByCategory(category: String): Long

    /**
     * 이름이나 카테고리가 같은 코스를 찾아 Iterable로 반환하라.
     */
    fun findByNameOrCategory(name: String, category: String): Iterable<Course>

    /**
     * 이름을 특정 글자로 시작하는 코스를 찾아 Iterable로 반환하라.
     */
    fun findByNameStartsWith(name: String): Iterable<Course>

    /**
     * 카테고리에 해당하는 모든 코스를 찾아서 스트림으로 반환하라.
     */
    fun streamAllByCategory(category: String): Stream<Course>
}