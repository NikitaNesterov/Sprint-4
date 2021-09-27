package ru.sber.generics

fun <T> compare(p1: Pair<T, T>, p2: Pair<T, T>): Boolean {
    if(p1.first!!.equals(p2.first) && p1.second!!.equals(p2.second)) {
        return true
    } else return false
}


fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
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