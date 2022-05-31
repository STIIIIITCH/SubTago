package com.example.subtago

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import net.daum.mf.map.api.MapPOIItem
import net.daum.mf.map.api.MapPoint
import net.daum.mf.map.api.MapView


class SubwayActivity : AppCompatActivity() {
    val mapView: MapView? = null
    var provider: String? = null
    var longitude = 0.0
    var latitude = 0.0
    var altitude = 0.0

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

        val  permissionCheck2 =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION)

        if (permissionCheck2 == PackageManager.PERMISSION_DENIED) { //백그라운드 위치 권한 확인
            //위치 권한 요청
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                0
            )
        }

        val mapView = MapView(this)
        val mapViewContainer = findViewById<View>(R.id.mapView) as ViewGroup
        mapViewContainer.addView(mapView)

        val lm = getSystemService(LOCATION_SERVICE) as LocationManager
        val location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER)

        // 위치정보를 원하는 시간, 거리마다 갱신

        // 위치정보를 원하는 시간, 거리마다 갱신
        lm.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000, 1f,
            gpsLocationListener
        )
        lm.requestLocationUpdates(
            LocationManager.NETWORK_PROVIDER,
            1000, 1f,
            gpsLocationListener
        )

        val marker = MapPOIItem()
        val nowPosition = MapPoint.mapPointWithGeoCoord(latitude, longitude)
        marker.customImageResourceId = R.drawable.pin
        marker.itemName = "현위치"
        marker.mapPoint = nowPosition
        marker.setCustomImageAnchor(0.5f, 1.0f)


//        mapView.setMapViewEventListener(this);
////        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
        val btn = findViewById<Button>(R.id.btn_start)
        btn.setOnClickListener(myLocation)

        btn_subway.setOnClickListener {
            finish() // 액티비티 종료
        }
    }

    var myLocation = View.OnClickListener {
        Toast.makeText(this, "불러옵니다.", Toast.LENGTH_SHORT).show()
        mapView?.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(latitude, longitude), true)
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    val gpsLocationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            provider = location.provider
            longitude = location.longitude
            latitude = location.latitude
            altitude = location.altitude
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }
}