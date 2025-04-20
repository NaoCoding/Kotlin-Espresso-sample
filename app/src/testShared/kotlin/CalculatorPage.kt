package net.pot8os.kotlintestsample

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import java.text.DecimalFormat

class CalculatorPage {

    fun clickNumber(number: Int): CalculatorPage {
        number.toString().forEach {
            val buttonId = when (it) {
                '0' -> R.id.button_0
                '1' -> R.id.button_1
                '2' -> R.id.button_2
                '3' -> R.id.button_3
                '4' -> R.id.button_4
                '5' -> R.id.button_5
                '6' -> R.id.button_6
                '7' -> R.id.button_7
                '8' -> R.id.button_8
                '9' -> R.id.button_9
                else -> throw IllegalArgumentException("Invalid digit: $it")
            }
            Espresso.onView(ViewMatchers.withId(buttonId)).perform(ViewActions.click())
        }
        return this
    }

    fun clickOp(op: String): CalculatorPage {
        val opId = when (op) {
            "+" -> R.id.button_sum
            "-" -> R.id.button_sub
            "*" -> R.id.button_mul
            "/" -> R.id.button_div
            "=" -> R.id.button_calc
            "AC" -> R.id.button_all_clear
            else -> throw IllegalArgumentException("Unsupported op: $op")
        }
        Espresso.onView(ViewMatchers.withId(opId)).perform(ViewActions.click())
        return this
    }

    fun checkResultEquals(expected: String): CalculatorPage {
        Espresso.onView(ViewMatchers.withId(R.id.field))
            .check(ViewAssertions.matches(ViewMatchers.withText(expected)))
        return this
    }

    fun checkResultEquals(number: Int): CalculatorPage {
        return checkResultEquals(number.toString())
    }

    fun checkResultFormatted(number: Int): CalculatorPage {
        val formatter = DecimalFormat("#,###")
        return checkResultEquals(formatter.format(number))
    }

    fun checkResultDouble(number: Double): CalculatorPage {
        return checkResultEquals(number.toString())
    }
}