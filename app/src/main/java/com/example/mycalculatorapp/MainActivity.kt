package com.example.mycalculatorapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var txtResult: TextView
    private var currentInput = ""
    private var memory: Double = 0.0
    private var currentNumber: Double? = null
    private var operator: String? = null
    private var firstOperand: Double? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vista_calculadora)

        txtResult = findViewById(R.id.txtResult)

        // Botones numéricos
        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )
        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener {
                appendNumber((it as Button).text.toString())
            }
        }

        // Operaciones básicas
        val operations = mapOf(
            R.id.btnAdd to "+",
            R.id.btnSubtract to "-",
            R.id.btnMultiply to "*",
            R.id.btnDivide to "/"
        )
        operations.forEach { (id, op) ->
            findViewById<Button>(id).setOnClickListener { setOperator(op) }
        }

        // Botón para potencia
        findViewById<Button>(R.id.btnpow).setOnClickListener { setOperator("^") }
        // Botón para porcentaje (operador %)
        findViewById<Button>(R.id.btnporcentaje).setOnClickListener { setOperator("%") }

        findViewById<Button>(R.id.btnEquals).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { clearInput() }

        // Botones de memoria
        findViewById<Button>(R.id.btnMc).setOnClickListener { memory = 0.0 }
        findViewById<Button>(R.id.btnMr).setOnClickListener {
            currentInput = memory.toString()
            txtResult.text = currentInput
        }
        findViewById<Button>(R.id.btnMPlus).setOnClickListener {
            memory += (currentInput.toDoubleOrNull() ?: 0.0)
        }
        findViewById<Button>(R.id.btnMMinus).setOnClickListener {
            memory -= (currentInput.toDoubleOrNull() ?: 0.0)
        }

        // Botón de raíz cuadrada
        findViewById<Button>(R.id.btnSqrt).setOnClickListener { calculateSqrt() }
    }

    private fun appendNumber(number: String) {
        currentInput += number
        txtResult.text = currentInput
    }

    private fun calculateSqrt() {
        currentNumber = currentInput.toDoubleOrNull()
        currentNumber?.let {
            val result = kotlin.math.sqrt(it)
            txtResult.text = result.toString()
            currentInput = result.toString()
        } ?: run {
            txtResult.text = "Error"
        }
    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            firstOperand = currentInput.toDoubleOrNull()
            if (firstOperand != null) {
                operator = op
                currentInput = ""  // Se reinicia la entrada para el segundo número
            } else {
                txtResult.text = "Error"
            }
        } else {
            // Permitir cambiar el operador si ya se ha establecido el primer operando
            if (firstOperand != null) {
                operator = op
            }
        }
    }

    private fun calculateResult() {
        val secondOperand = currentInput.toDoubleOrNull()
        if (firstOperand != null && secondOperand != null && operator != null) {
            val result = when (operator) {
                "+" -> firstOperand!! + secondOperand
                "-" -> firstOperand!! - secondOperand
                "*" -> firstOperand!! * secondOperand
                "/" -> if (secondOperand != 0.0) firstOperand!! / secondOperand else null
                "^" -> Math.pow(firstOperand!!, secondOperand)
                "%" -> firstOperand!! * (secondOperand / 100)
                else -> null
            }
            if (result != null) {
                txtResult.text = result.toString()
                currentInput = result.toString()
            } else {
                txtResult.text = "Error"
            }
            // Reiniciar estado
            firstOperand = null
            operator = null
        }
    }

    private fun clearInput() {
        currentInput = ""
        txtResult.text = "0"
        firstOperand = null
        operator = null
    }
}
