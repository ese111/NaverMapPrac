package com.example.navermap

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    lateinit var map: NaverMap

    private val mapFragment: MapFragment by lazy {
        val fm = supportFragmentManager
        fm.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map, it).commit()
            }
    }

    private lateinit var latLng: LatLng
    private lateinit var position: CameraUpdate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapFragment.getMapAsync(this)


        Log.d("TAG", "onCreate")
    }

    override fun onMapReady(naverMap: NaverMap) {
        map = naverMap
        map.uiSettings.apply {
            isCompassEnabled = true
            isScaleBarEnabled = false
        }
        val api = ApiClient.getApiClient()
        setPosition(api)
        Log.d("TAG", "mapReady")
    }

    private fun setPosition(api: AddressApi) {
//            withContext(Dispatchers.IO) {
//                val address = api.getAddress()
//                val lat = address.body()?.addresses?.get(0)?.x?.toDouble() ?: 0.0
//                val long = address.body()?.addresses?.get(0)?.y?.toDouble() ?: 0.0
//
//            }
            latLng = LatLng(127.1054065,37.3595669)
            val marker = Marker()
            marker.position = latLng
            marker.map = map
            position = CameraUpdate.scrollTo(latLng)
            map.moveCamera(position)
            Log.d("TAG", "${latLng.latitude} ${latLng.longitude}")
        }


}