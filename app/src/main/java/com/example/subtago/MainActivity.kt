package com.example.subtago

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

lateinit var btn_subway: Button
lateinit var btn_transfer: Button
lateinit var btn_lift: Button
lateinit var btn_convenience: Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_subway = findViewById(R.id.subway)
        btn_transfer = findViewById(R.id.transfer)
        btn_lift = findViewById(R.id.lift)
        btn_convenience = findViewById(R.id.convenience)

        val subwayIntent = Intent(this, SubwayActivity::class.java) // 인텐트를 생성
        btn_subway.setOnClickListener { // 버튼 클릭시 할 행동
            startActivity(subwayIntent)  // 화면 전환하기
        }

        val transferIntent = Intent(this, TransferActivity::class.java) // 인텐트를 생성
        btn_transfer.setOnClickListener { // 버튼 클릭시 할 행동
            startActivity(transferIntent)  // 화면 전환하기
        }

        val liftIntent = Intent(this, LiftActivity::class.java) // 인텐트를 생성
        btn_lift.setOnClickListener { // 버튼 클릭시 할 행동
            startActivity(liftIntent)  // 화면 전환하기
        }

        val convenienceIntent = Intent(this, ConvenienceActivity::class.java) // 인텐트를 생성
        btn_convenience.setOnClickListener { // 버튼 클릭시 할 행동
            startActivity(convenienceIntent)  // 화면 전환하기
        }
    }
    

}



