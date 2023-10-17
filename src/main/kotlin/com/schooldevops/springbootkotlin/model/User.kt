package com.schooldevops.springbootkotlin.model

import com.schooldevops.springbootkotlin.validator.Password

data class User(var userName: String, @Password var password: String)