package net.pot8os.kotlintestsample

import androidx.fragment.app.testing.launchFragmentInContainer
import net.pot8os.kotlintestsample.CalculatorPage
import org.junit.Before
import org.junit.Test

abstract class CalculatorPageSpec {

    private lateinit var calculator: CalculatorPage

    @Before
    fun setup() {
        launchFragmentInContainer<CalculatorFragment>(themeResId = R.style.Theme_MyApp)
        calculator = CalculatorPage()
    }

    @Test
    fun testSum() {
        calculator
            .clickNumber(123)
            .clickOp("+")
            .clickNumber(321)
            .clickOp("=")
            .checkResultEquals(123 + 321)
    }

    @Test
    fun testSub() {
        calculator
            .clickNumber(999)
            .clickOp("-")
            .clickNumber(333)
            .clickOp("=")
            .checkResultEquals(999 - 333)
    }

    @Test
    fun testMul() {
        calculator
            .clickNumber(100)
            .clickOp("*")
            .clickNumber(200)
            .clickOp("=")
            .checkResultFormatted(100 * 200)
    }

    @Test
    fun testDiv() {
        calculator
            .clickNumber(333)
            .clickOp("/")
            .clickNumber(100)
            .clickOp("=")
            .checkResultDouble(333 / 100.0)
    }

    @Test
    fun testAC() {
        calculator
            .clickNumber(2)
            .clickOp("AC")
            .checkResultEquals(0)
            .clickNumber(333)
            .checkResultEquals(333)
            .clickOp("/")
            .clickNumber(100)
            .clickOp("AC")
            .checkResultEquals(0)
            .clickNumber(123)
            .clickOp("*")
            .clickNumber(700)
            .clickOp("=")
            .clickOp("AC")
            .checkResultEquals(0)
    }
}