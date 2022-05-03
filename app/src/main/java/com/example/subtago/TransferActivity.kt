package com.example.subtago

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TransferActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.transfer_1)

        btn_transfer.setOnClickListener {
            finish() // 액티비티 종료
        }
    }
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}