package com.norbi930523.trello.common.validation

data class ValidationError(val field: String, val errorCode: String) {
}

interface Validator<in T> {

    fun validate(fieldName: String, fieldValue: T?): ValidationError?

}

class NotBlankValidator {

    companion object: Validator<String> {

        override fun validate(fieldName: String, fieldValue: String?): ValidationError? {
            if (fieldValue.isNullOrBlank()) {
               return ValidationError(fieldName, "BLANK")
            }

            return null
        }
    }

}

class PasswordValidator {

    companion object: Validator<String> {

        private val PASSWORD_REGEX = """^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}""".toRegex()

        override fun validate(fieldName: String, fieldValue: String?): ValidationError? {
            NotBlankValidator.validate(fieldName, fieldValue)?.let { return it }

            if (!isPasswordStrong(fieldValue!!)) {
                return ValidationError(fieldName, "WEAK")
            }

            return null
        }

        private fun isPasswordStrong(password: String): Boolean = password.matches(PASSWORD_REGEX)

    }

}
