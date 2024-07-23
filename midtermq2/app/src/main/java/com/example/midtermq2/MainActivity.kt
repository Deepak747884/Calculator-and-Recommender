package com.example.midtermq2

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerGenres: Spinner
    private lateinit var buttonRecommend: Button
    private lateinit var textTitle: TextView
    private lateinit var imageIcon: ImageView
    private lateinit var textDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerGenres = findViewById(R.id.spinner_genres)
        buttonRecommend = findViewById(R.id.button_recommend)
        textTitle = findViewById(R.id.text_title)
        imageIcon = findViewById(R.id.image_icon)
        textDescription = findViewById(R.id.text_description)

        // Set up the Spinner
        ArrayAdapter.createFromResource(
            this,
            R.array.genres_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerGenres.adapter = adapter
        }

        // Set up Button listener
        buttonRecommend.setOnClickListener {
            val selectedGenre = spinnerGenres.selectedItem.toString()
            if (selectedGenre == "Choose") {
                textTitle.text = ""
                textDescription.text = "Please select a genre to get a recommendation."
                imageIcon.setImageDrawable(null)
            } else {
                val recommendation = getRecommendation(selectedGenre)
                textTitle.text = recommendation.first
                textDescription.text = recommendation.second
                imageIcon.setImageResource(recommendation.third)
            }
        }

        // Set up Spinner listener
        spinnerGenres.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: android.view.View?, position: Int, id: Long) {
                // Optional: Do something when an item is selected
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Optional: Do something when nothing is selected
            }
        }
    }

    private fun getRecommendation(genre: String): Triple<String, String, Int> {
        return when (genre) {
            "Horror" -> Triple(
                "Dr. Strange in the Multiverse of Madness",
                "Doctor Strange teams up with a mysterious teenage girl from his dreams who can travel across multiverses, to battle multiple threats, including other-universe versions of himself, which threaten to wipe out millions across the multiverse.",
                R.drawable.drs
            )
            "Adventure" -> Triple(
                "Pirates of the Caribbean",
                "Pirates of the Caribbean is a sweeping action-adventure story set in an era when villainous pirates scavenged the Caribbean seas. This roller coaster tale teams a young man, Will Turner, with an unlikely ally in rogue and unusually heroic pirate Jack Sparrow.",
                R.drawable.poc
            )
            "Romance" -> Triple(
                "Titanic",
                "Incorporating both historical and fictionalized aspects, it is based on accounts of the sinking of RMS Titanic in 1912. Leonardo DiCaprio and Kate Winslet star as members of different social classes who fall in love during the ship's maiden voyage.",
                R.drawable.titanic
            )
            "SciFi" -> Triple(
                "Inception",
                "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.",
                R.drawable.inception
            )

            else -> Triple(
                "Select a genre",
                "Select a genre to get a recommendation.",
                0 // No image
            )
        }
    }
}
