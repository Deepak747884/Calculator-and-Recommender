package com.example.midterm

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val number1EditText = findViewById<EditText>(R.id.number1)
        val number2EditText = findViewById<EditText>(R.id.number2)
        val resultEditText = findViewById<EditText>(R.id.result)
        val calculateButton = findViewById<Button>(R.id.calculate)
        val operationSpinner = findViewById<Spinner>(R.id.operation)

        // Setup the spinner with operation items
        ArrayAdapter.createFromResource(
            this,
            R.array.operations_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            operationSpinner.adapter = adapter
        }

        calculateButton.setOnClickListener {
            val number1 = number1EditText.text.toString().toDoubleOrNull()
            val number2 = number2EditText.text.toString().toDoubleOrNull()
            val operation = operationSpinner.selectedItem.toString()

            if (number1 == null || number2 == null) {
                resultEditText.setText("Invalid Input")
            } else {
                val result = when (operation) {
                    "Add" -> number1 + number2
                    "Subtract" -> number1 - number2
                    "Multiply" -> number1 * number2
                    "Divide" -> {
                        if (number2 != 0.0) {
                            number1 / number2
                        } else {
                            "Cannot divide by zero"
                        }
                    }
                    else -> "Unknown Operation"
                }
                resultEditText.setText(result.toString())
            }
        }
    }
}
