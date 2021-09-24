package ru.sber.generics

fun <T> compare(p1: T, p2: T): Boolean {
    if(p1!!.equals(p2!!)) {
        return true
    } else return false
}


fun <T> countGreaterThan(anArray: Array<T>, elem: T): Int {
    var count: Int = 0
    anArray.sort()
    count = (anArray.size - 1) - anArray.indexOf(elem)
    return count
}

//3.
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T> {

    val stack: MutableList<T> = mutableListOf()
    fun isEmpty() = stack.isEmpty()
    fun push(item: T) : Boolean = stack.add(item)
    fun <T> pop() : T {
        val item = stack.lastOrNull()
        if (!isEmpty()) {
            stack.removeAt(stack.size -1)
        }
        return item as T
    }
    fun <T> peek(): T = stack.lastOrNull() as T

}