package com.example.messenger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import com.example.messenger.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // пробуем связку viewBinding
    // mBinding общий член класса Binding
    // На котлине так никто не делает, но на джаве так
//    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}