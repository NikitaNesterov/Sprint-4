package ru.sber.functional

import kotlin.math.pow

object PowFactory {
    /**
     * Возвращает функцию, которая всегда возводит аргумент в нужную степень, указанную при создании функции.
     */

    fun buildPowFunction (pow: Int) = {it:Int -> Math.pow(it.toDouble(), pow.toDouble()).toInt()}


}
