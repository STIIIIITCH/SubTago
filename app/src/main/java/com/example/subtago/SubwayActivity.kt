package com.example.subtago

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.subtago.databinding.ActivityMainBinding
import com.example.subtago.databinding.Subway1Binding
import net.daum.mf.map.api.MapView


class SubwayActivity : AppCompatActivity() {
    private lateinit var binding : Subway1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.subway_1)

        // 위치 권한 요청 코드
        val permissionCheck =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)

        if (permissionCheck == PackageManager.PERMISSION_DENIED) { //포그라운드 위치 권한 확인
            //위치 권한 요청
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                0
            )
        }

        val permissionCheck2 =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)

        if (permissionCheck == PackageManager.PERMISSION_DENIED) { //백그라운드 위치 권한 확인
            //위치 권한 요청
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                0
            )
        }

//        val mapView = MapView(this)
//        val mapViewContainer = findViewById<View>(R.id.mapView) as ViewGroup
//        mapViewContainer.addView(mapView)

        binding = Subway1Binding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
        setContentView(binding.root)

        // 위치추적 버튼
        binding.btnStart.setOnClickListener {
            startTracking()
        }

        // 추적중지 버튼
        binding.btnStop.setOnClickListener {
            stopTracking()
        }

        btn_subway.setOnClickListener {
            finish() // 액티비티 종료
        }
    }
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    // 위치추적 시작
    private fun startTracking() {
        binding.mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading
    }

    // 위치추적 중지
    private fun stopTracking() {
        binding.mapView.currentLocationTrackingMode = MapView.CurrentLocationTrackingMode.TrackingModeOff
    }
}