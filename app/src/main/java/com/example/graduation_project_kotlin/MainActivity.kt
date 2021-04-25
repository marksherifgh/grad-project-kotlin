package com.example.graduation_project_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    val REQUEST_TAKE_GALLERY_VIDEO = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val how: Button = findViewById<Button>(R.id.howTo)
        val upload: Button = findViewById<Button>(R.id.upload)
        how.setOnClickListener {
            val intent = Intent(this@MainActivity, How::class.java)
            startActivity(intent)
        }
        upload.setOnClickListener {
            val videoIntent = Intent()
            videoIntent.type = "video/*"
            videoIntent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(videoIntent, "Select Video"), REQUEST_TAKE_GALLERY_VIDEO)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_GALLERY_VIDEO) {
                val videoPath = data!!.data!!.path
                val intent = Intent(this@MainActivity, Upload::class.java)
                //TODO: putExtra (Key, value)
                startActivity(intent)
            }
        }
    }
}