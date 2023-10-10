package com.schooldevops.springbootkotlin.configs

import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T> logger() : Logger = LoggerFactory.getLogger(T::class.java)
