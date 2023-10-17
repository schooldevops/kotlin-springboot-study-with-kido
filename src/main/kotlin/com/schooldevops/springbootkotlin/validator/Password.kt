package com.schooldevops.springbootkotlin.validator

import jakarta.validation.Constraint
import jakarta.validation.Payload
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [PasswordRuleValidator::class])
annotation class Password (
    val message: String = "Password do not adhere to the specified rule",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)