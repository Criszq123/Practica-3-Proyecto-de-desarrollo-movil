package com.example.mycalculatorapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNumberButtons() {
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.txtResult)).check(matches(withText("1")))
    }

    @Test
    fun testAddition() {
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.btnAdd)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())
        onView(withId(R.id.txtResult)).check(matches((withText("5.0"))))
    }

    @Test
    fun testSubtraction() {
        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.btnSubtract)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())
        onView(withId(R.id.txtResult)).check(matches(withText("3.0")))
    }

    @Test
    fun testMultiplication() {
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.btnMultiply)).perform(click())
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())
        onView(withId(R.id.txtResult)).check(matches(withText("12.0")))
    }

    @Test
    fun testDivision() {
        onView(withId(R.id.btn8)).perform(click())
        onView(withId(R.id.btnDivide)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())
        onView(withId(R.id.txtResult)).check(matches(withText("4.0")))
    }
    @Test
    fun testMCButton() {
        onView(withId(R.id.btnMc)).perform(click()) // Limpia la memoria antes de iniciar

        onView(withId(R.id.btn7)).perform(click())
        onView(withId(R.id.btnMPlus)).perform(click())
        onView(withId(R.id.btnMr)).perform(click()) // Verifica que MR recupera correctamente
        onView(withId(R.id.txtResult)).check(matches(withText("7.0")))

        onView(withId(R.id.btnMc)).perform(click()) // Borra la memoria
        onView(withId(R.id.btnMr)).perform(click()) // Intenta recuperar memoria
        onView(withId(R.id.txtResult)).check(matches(withText("0.0"))) // Confirma que está vacía
    }


    @Test
    fun testMRButton() {
        onView(withId(R.id.btnMc)).perform(click()) // Limpia la memoria antes de empezar

        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.btnMPlus)).perform(click())
        onView(withId(R.id.btnMr)).perform(click()) // Recuperar memoria
        onView(withId(R.id.txtResult)).check(matches(withText("5.0")))

        // Verifica nuevamente para asegurarse de que MR no alteró el valor en pantalla
        onView(withId(R.id.btnMr)).perform(click())
        onView(withId(R.id.txtResult)).check(matches(withText("5.0")))
    }


    @Test
    fun testMPlusButton() {
        // 1. Limpiar memoria antes de empezar
        onView(withId(R.id.btnMc)).perform(click())

        // 2. Ingresar "3" y presionar M+
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.btnMPlus)).perform(click())

        // 3. Verificar que la memoria tiene "3.0"
        onView(withId(R.id.btnMr)).perform(click())
        onView(withId(R.id.txtResult)).check(matches(withText("3.0")))

        // 4. Ingresar "4" y presionar M+
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.btnMPlus)).perform(click())

        // 5. Verificar que la memoria tiene "7.0" (3.0 + 4.0)
        onView(withId(R.id.btnMr)).perform(click())
        onView(withId(R.id.txtResult)).check(matches(withText("7.0")))
    }



    @Test
    fun testMSubstractButton() {
        // 1. Limpiar memoria antes de empezar
        onView(withId(R.id.btnMc)).perform(click())

        // 2. Ingresar "10" y presionar M+
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.btn0)).perform(click())
        onView(withId(R.id.btnMPlus)).perform(click())

        // 3. Verificar que la memoria tiene "10.0"
        onView(withId(R.id.btnMr)).perform(click())
        onView(withId(R.id.txtResult)).check(matches(withText("10.0")))

        // 4. Ingresar "4" y presionar M-
        onView(withId(R.id.btn4)).perform(click())
        onView(withId(R.id.btnMMinus)).perform(click())

        // 5. Verificar que la memoria ahora tiene "6.0" (10.0 - 4.0)
        onView(withId(R.id.btnMr)).perform(click())
        onView(withId(R.id.txtResult)).check(matches(withText("6.0")))
    }

    @Test
    fun testPercentButton() {
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.btn0)).perform(click())
        onView(withId(R.id.btn0)).perform(click())
        onView(withId(R.id.btnporcentaje)).perform(click())
        onView(withId(R.id.btn5)).perform(click())
        onView(withId(R.id.btn0)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())

        onView(withId(R.id.txtResult)).check(matches(withText("50.0")))

    }
    @Test
    fun testSquareRootButton() {
        onView(withId(R.id.btn1)).perform(click())
        onView(withId(R.id.btn0)).perform(click())
        onView(withId(R.id.btn0)).perform(click())
        onView(withId(R.id.btnSqrt)).perform(click())
        onView(withId(R.id.txtResult)).check(matches(withText("10.0")))
        onView(withId(R.id.btnClear)).perform(click())
        onView(withId(R.id.btn9)).perform(click())
        onView(withId(R.id.btnSqrt)).perform(click())
        onView(withId(R.id.txtResult)).check(matches(withText("3.0")))

    }

    @Test
    fun testPowerButton() {
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.btnpow)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())
        onView(withId(R.id.txtResult)).check(matches(withText("9.0")))
        onView(withId(R.id.btnClear)).perform(click())
        onView(withId(R.id.btn2)).perform(click())
        onView(withId(R.id.btnpow)).perform(click())
        onView(withId(R.id.btn3)).perform(click())
        onView(withId(R.id.btnEquals)).perform(click())
        onView(withId(R.id.txtResult)).check(matches(withText("8.0")))
    }


}