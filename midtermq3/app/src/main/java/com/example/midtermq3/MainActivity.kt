package com.example.midtermq3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalculator = findViewById<Button>(R.id.btnCalculator)
        val btnRecommender = findViewById<Button>(R.id.btnRecommender)

        btnCalculator.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            intent.putExtra("EXTRA_MESSAGE", "Hello Calculator!")
            startActivity(intent)
        }

        btnRecommender.setOnClickListener {
            val intent = Intent(this, RecommenderActivity::class.java)
            intent.putExtra("EXTRA_MESSAGE", "Hello Movie Recommender!")
            startActivity(intent)
        }
    }
}
