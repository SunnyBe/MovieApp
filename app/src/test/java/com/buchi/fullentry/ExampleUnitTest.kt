package com.buchi.fullentry

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testSolution() {
        val numbers = listOf(6, 2, 8,1, 4,5)
//        val contains = pseudoBinaryContains(451, numbers)
//        val contains = linearContains(451, numbers)
        val contains = printSorted(numbers)
        println(contains)
    }

    private fun printSorted0(numbers: List<Int>) {
        val sorted = numbers.sorted()
        for (element in sorted) {
            println(element)
        }
    }

    fun printSorted(numbers: List<Int>) {
        // 1
        if (numbers.isEmpty()) return

        // 2
        var currentCount = 0
        var minValue = Int.MIN_VALUE

        // 3
        for (value in numbers) {
            if (value == minValue) {
                println(value)
                currentCount += 1
            }
        }

        while (currentCount < numbers.size) {
            // 4
            var currentValue = numbers.max()!!

            for (value in numbers) {
                if (value < currentValue && value > minValue) {
                    currentValue = value
                }
            }

            // 5
            for (value in numbers) {
                if (value == currentValue) {
                    println(value)
                    currentCount += 1
                }
            }

            // 6
            minValue = currentValue
        }
    }



}