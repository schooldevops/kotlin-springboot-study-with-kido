package com.schooldevops.springbootkotlin.configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment


/**
 * application.properties 혹은 application.yaml 파일 찾는 위치는 다음과 같다.
 * A. 기본 조회 위치
 *  1. 클래스 패스 루트
 *  2. 클래스패스 /config 패키지
 *  3. 현재 디렉토리
 *  4. 현재 디렉토리 /config 디렉토리
 *  5. /config 디렉토리의 바로 하위 디렉토리
 *
 * B. spring.config.location 프로퍼티를 이용하면 사용자 지정 위치의 속성을 가져올 수 있다.
 * C. java jar 실행시 다음과 같이 설정
 *  - 일반적인 방법:
 *      - java -jar target/test-spring.jar --spring.config.location=data/sbip.yaml
 *  - 옵션(없어도 오류없게):
 *      - java -jar target/test-spring.jar --spring.config.location:optional:data/sbip1.yaml
 *
 */
@Configuration
@PropertySource("classpath:dbConfig.properties")
class DBConfiguration {

    /**
     * 프로퍼티에 설정된 값을 획득하기 위해서 Environment를 autowired 하여 조회할 수 있다.
     */
    @Autowired
    private var env: Environment? = null

    @Override
    override fun toString(): String = "Username: ${this.env?.getProperty("username")}, Password: ${this.env?.getProperty("password")} "
}