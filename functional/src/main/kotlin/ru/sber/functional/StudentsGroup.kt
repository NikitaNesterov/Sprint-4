package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun init(_firstName: List<String>, _lastNJame: List<String>, _averageRate: List<Double>, studentsQuantity: Int) {

        students = ArrayList<Student>()
        for(i in 0 until studentsQuantity) {
            (students as ArrayList<Student>).add(Student(_firstName[i], _lastNJame[i], averageRate = _averageRate[i]))
        }
    }

    fun filterByPredicate(predicate: (Student) -> Boolean) : ArrayList<Student> {
        return students.filterTo(ArrayList<Student>(), predicate)
    }

}



