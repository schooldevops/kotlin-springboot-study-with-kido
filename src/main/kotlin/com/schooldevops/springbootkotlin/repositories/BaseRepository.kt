package com.schooldevops.springbootkotlin.repositories

import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.Repository

/**
 * @NoRepositoryBean 은 스프링 데이터가 감지해서 구현체가 자동으로 만들어 지지 않도록 해준다.
 * 즉, 프록시 객체가 생성되지 않음, 런타임에 실제 JPA 구현체의 메소드를 호출하게 된다.
 *
 * 이 BaseRepository는 save와 findAll 만 지원하기 위해서 정의하였다.
 */
@NoRepositoryBean
interface BaseRepository<T, ID> : Repository<T, ID> {

    fun save(entity: T): T
    fun findAll(): Iterable<T>

}