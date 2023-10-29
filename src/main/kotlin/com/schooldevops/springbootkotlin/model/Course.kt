package com.schooldevops.springbootkotlin.model

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

/**
 * Course
 * - validation 을 이용 (@field:NotBland 등으로 이용)
 * - hibernate validator
 *  - @NotBlank: CharSequence 타입 필드에 사용되어 문자열이 null이 아니고, 앞뒤 공백 문자를 제거한 후 문자열 길이가 0보다 크다는것을 검사
 *  - @NotEmpty: CharSequence, Collection, Map 타입과 배열에 사용되며 null이 아니고 비어 있는지 검사한다.
 *  - @NotNull: 모든 타입에 사용할 수 있으며 null이 아님을 검사
 *  - @Min(value): 최소값을 지정해서 이 값보다 크거나 같은지 검사한다.
 *  - @Max(value): 최대값을 지정해서 이 값보다 작거나 같은지 검사한다.
 *  - @Pattern(regex=, flags): regex로 지정한 정규 표현식을 준수하는지 검사, 정규 표현식의 플래그도 사용가능
 *  - @Size(min=, max=): 개수의 최소, 최대 값을 준수하는지 검사한다.
 *  - @Email: 문자열이 유효한 이메일 주소를 나타내는지 검사한다.
 *
 * - Controller에서는 컨트롤러 메소드에서 @Valid 를 이용한다.
 * - 일반적으로는 아래와 같이 Validator를 이용할 수 있다.
 *      val validator = Validation.buildDefaultValidatorFactory().validator
 * 		val validation = validator.validate(course)
 *
 * 		validation.forEach {
 * 			log.error("A constraint validation has occured. Violation details: [{}]", it)
 * 		}
 */

@Entity
@Table(name = "COURSE")
class Course(
    @Id @Column(name = "ID") @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long?,
    @Column(name = "NAME") var name: String,

    @field:NotBlank(message = "Name must not be null.")
    @Column(name = "CATEGORY") var category: String,

    @field:Min(value = 1, message = "A course should have a minimum of 1 rating")
    @field:Max(value = 5, message = "A course should have a maximum of 5 rating")
    @Column(name = "RATING") var rating: Int,
    @Column(name = "DESCRIPTION") var description: String
    ) {
    constructor() : this(0, "", "", 0, "") {

    }

    override fun equals(other: Any?): Boolean {
        val course = other as Course
        return this.name == course.name && this.category == course.category && this.description == course.description && this.rating == course.rating
    }
}