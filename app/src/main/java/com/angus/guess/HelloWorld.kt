package com.angus.guess

fun main() {

    val weight : Float = 65f
    val height = 1.7
    val bmi = weight/(weight*weight)
    print(bmi)
//    println("Hello World")
    //mutable var(variable) 可改變  val(value)immutable 以後不會變
    var person = Person()
    person.hello()
    val age = 35
}

class Person{
    fun hello(){
        print("Hello world! Kotlin!")
    }
}