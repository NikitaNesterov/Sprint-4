package ru.sber.datetime

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*
import kotlin.collections.ArrayList

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {

    val zoneList: Set<String>
    val result = mutableSetOf<String>()
    zoneList = ZoneId.getAvailableZoneIds()

    for(zone in zoneList) {
        val zd = ZoneId.of(zone)
        val zoneDiff = LocalDateTime.now().atZone(zd).offset
        val intFlag = zoneDiff.totalSeconds % 3600

        if(intFlag != 0) {
            result.add(zone)
        }
    }
    return result
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val lastDaysOfMonth = ArrayList<String>()
    val month = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    for (i in 0..month.size - 1) {
        val dayOfWeek = LocalDate.of(year, month[i], 1).with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek
        lastDaysOfMonth.add(dayOfWeek.toString())
    }
    return lastDaysOfMonth
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var counterOfFridayThirteen = 0
    val month = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    for (i in 0 until month.size) {
        val thirteensOfMonth = LocalDate.of(year, month[i], 13).dayOfWeek.toString()
        if (thirteensOfMonth.equals("FRIDAY")) {
            counterOfFridayThirteen++
        }
    }
    return counterOfFridayThirteen
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    val usFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US)
    val date = dateTime.format(usFormatter.withLocale(Locale.US))
    return date
}



