package com.example.graduation_project_kotlin

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class How : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_how)
        val getAruco = findViewById<Button>(R.id.getAruco)
        getAruco.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://tn1ck.github.io/aruco-print/"))
            startActivity(intent)
        }
    }
}