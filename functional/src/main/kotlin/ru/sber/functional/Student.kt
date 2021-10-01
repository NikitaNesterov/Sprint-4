package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Отсутствует",
    val age: Int = 99,
    val averageRate: Double,
    val city: String = "Где-то в России",
    val specialization: String = "Пок аотсутствует",
    val prevEducation: String? = "Школа",
)
