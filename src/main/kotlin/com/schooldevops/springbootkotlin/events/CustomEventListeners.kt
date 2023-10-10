package com.schooldevops.springbootkotlin.events

import com.schooldevops.springbootkotlin.configs.logger
import org.springframework.boot.context.event.ApplicationContextInitializedEvent
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent
import org.springframework.boot.context.event.ApplicationFailedEvent
import org.springframework.boot.context.event.ApplicationPreparedEvent
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.event.ApplicationStartedEvent
import org.springframework.boot.context.event.ApplicationStartingEvent
import org.springframework.boot.web.context.WebServerInitializedEvent
import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent
import org.springframework.context.event.ContextClosedEvent
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class CustomEventListeners {
    val log = logger<CustomEventListeners>()

    /**
     * 웹서버가 준비되면 발행된다.
     * 이 이벤트는 두 가지 하위 이벤트를 갖고 있는데,
     * 서블릿 기반 웹 애플리케이션에서는 ServletWebServerInitializedEvent 를 사용할 수 있다.
     * 리액티브 기반 웹 애플리케이션에서는 ReactiveWebServerInitializedEvent 를 사용할 수 있다.
     * 이 이벤트도 SpringApplicationEvent를 상속하지 않는다.
     */
    @EventListener
    fun onApplicationEvent(event: WebServerInitializedEvent) {
        log.info("---------- 01. WebServerInitializedEvent !!")
    }

    /**
     * ApplicationContext가 리프레시된 후에 발행된다.
     * 이 이벤트는 스프링부트가 아니라 스프링이 발행하는 이벤트라서 SpringApplicationEvent를 상속하지 않는다.
     * 스프링 부트의 ConditionEvaluationReportLoggingListener는 이 이벤트가 발행되면 자동 구성 보고서를 출력한다.
     */
    @EventListener
    fun onApplicationEvent(event: ContextRefreshedEvent) {
        log.info("---------- 02. ContextRefreshedEvent !!")
    }

    @EventListener
    fun onApplicationEvent(event: ServletWebServerInitializedEvent) {
        log.info("---------- 03. ServletWebServerInitializedEvent !!")
    }

    /**
     * ApplicationContext가 리프레시되고 나서 ApplicationRunner와 CommandLineRunner가 호출되기 전에 발행된다.
     */
    @EventListener
    fun onApplicationEvent(event: ApplicationStartedEvent) {
        log.info("---------- 04. ApplicationStartedEvent !!")
    }

    /**
     * 애플리케이션이 요청을 처리할 준비가 되면 발행된다.
     * 이 이벤트가 발행되면 모든 애플리케이션 초기화가 완료된 것이므로 이 시점 이후로 애플리케이션 내부 상태를 변경하는 것은 권장하지 않는다.
     */
    @EventListener
    fun onApplicationEvent(event: ApplicationReadyEvent) {
        log.info("---------- 05. ApplicationReadyEvent !!")
    }

    /**
     * 애플리케이션이 시작되고 리스너가 등록되면 발행된다.
     * 스프링부트의 LoggingSystem은 이 이벤트를 사용해서 애플리케이션 초기화 단계에 들어가기 전에 필요한 작업을 수행한다.
     */
    @EventListener
    fun onApplicationEvent(event: ApplicationStartingEvent) {
        log.info("---------- ApplicationStartingEvent !!")
    }

    /**
     * 애플리케이션이 시작되고 Environment가 준비되어 검사하고 수정할 수 있게 되면 발행된다.
     * 스프링 부트는 내부적으로 이 이벤트를 사용해서 MessageConverter, ConversionService 등을 초기화 할때 사용
     */
    @EventListener
    fun onApplicationEvent(event: ApplicationEnvironmentPreparedEvent) {
        log.info("---------- ApplicationEnvironmentPreparedEvent !!")
    }

    /**
     * ApplicationContext가 준비되고 ApplicationContextInitializers가 실행되면 발행된다.
     * 하지만 아직 아무런 빈도 로딩되지 않는다.
     * 빈이 스프링 컨테이너에 리딩되어 초기화되기 전에 어떤 작업을 수행해야 할 때 이 이벤트를 사용하면 된다.
     */
    @EventListener
    fun onApplicationEvent(event: ApplicationContextInitializedEvent) {
        log.info("---------- ApplicationContextInitializedEvent !!")
    }

    /**
     * ApplicationContext가 준비도고 빈이 로딩은 되었지만 아직 ApplicationContext가 리프레시 되지 않은 시점에 발행된다.
     * 이 이벤트가 발행된 후에 Environment를 사용할 수 있다.
     */
    @EventListener
    fun onApplicationEvent(event: ApplicationPreparedEvent) {
        log.info("---------- ApplicationPreparedEvent !!")
    }

    /**
     * 애플리케이션 시작 과정에서 예외가 발생하면 발행된다.
     * 예외 발생사 스크립트를 실행하거나 스타트업 실패를 알린다.
     */
    @EventListener
    fun onApplicationEvent(event: ApplicationFailedEvent) {
        log.info("---------- ApplicationFailedEvent !!")
    }

    /**
     * 컨텍스트가 종료된경우 발행된다.
     */
    @EventListener
    fun onApplicationEvent(event: ContextClosedEvent) {
        log.info("---------- ContextClosedEvent !!")
    }
}