package com.schooldevops.springbootkotlin.validator

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.passay.CharacterCharacteristicsRule
import org.passay.CharacterRule
import org.passay.EnglishCharacterData
import org.passay.LengthRule
import org.passay.PasswordData
import org.passay.PasswordValidator
import org.passay.RepeatCharacterRegexRule
import org.passay.Rule

class PasswordRuleValidator: ConstraintValidator<Password, String> {

    private final val MIN_COMPLEX_RULES: Int = 2
    private final val MAX_REPETITIVE_CHARS: Int = 3
    private final val MIN_SPECIAL_CASE_CHARS: Int = 1
    private final val MIN_UPPER_CASE_CHARS: Int = 1
    private final val MIN_LOWER_CASE_CHARS: Int = 1
    private final val MIN_DIGIT_CASE_CHARS: Int = 1

    override fun isValid(password: String?, context: ConstraintValidatorContext?): Boolean {
        var passwordRules = mutableListOf<Rule>()
        passwordRules.add(LengthRule(8, 30))
        val characterCharacteristicsRule = CharacterCharacteristicsRule(
            MIN_COMPLEX_RULES,
            CharacterRule(EnglishCharacterData.Special, MIN_SPECIAL_CASE_CHARS),
            CharacterRule(EnglishCharacterData.UpperCase, MIN_UPPER_CASE_CHARS),
            CharacterRule(EnglishCharacterData.LowerCase, MIN_LOWER_CASE_CHARS),
            CharacterRule(EnglishCharacterData.Digit, MIN_DIGIT_CASE_CHARS),
        )
        passwordRules.add(characterCharacteristicsRule)
        passwordRules.add(RepeatCharacterRegexRule(MAX_REPETITIVE_CHARS))
        val passwordValidator = PasswordValidator(passwordRules)
        val passwordData = PasswordData(password)
        val ruleResult = passwordValidator.validate(passwordData)
        return ruleResult.isValid

    }
}