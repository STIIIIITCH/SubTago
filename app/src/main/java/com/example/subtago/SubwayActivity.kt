package com.example.subtago

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SubwayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.subway_1)

        btn_subway.setOnClickListener {
            finish() // 액티비티 종료
        }
    }
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}