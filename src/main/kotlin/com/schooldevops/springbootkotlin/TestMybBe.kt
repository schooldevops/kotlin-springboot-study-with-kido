package com.schooldevops.springbootkotlin


fun maybeTwice2(b: Boolean, i: () -> Int) {
    val j: Int by lazy(i)
    println("-------------- Hi222")
    if(b) j + j else 0

    println("-----------------------------")

    val k: Lazy<Int> = lazy(i)
    if(b) k.value + k.value else 0

}

fun main() {
    maybeTwice2(true, {println("----------------------------------- Hi"); 1 + 41})
}