package com.tasty.recipesapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.tasty.recipesapp.R
import com.tasty.recipesapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    companion object {
        const val TAG = "SplashActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val logo = binding.logo
        Handler().postDelayed({
            val animation = AnimationUtils.loadAnimation(this, R.anim.float_up_animation)
            logo.startAnimation(animation)
            logo.visibility = ImageView.VISIBLE
        }, 2000)
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("message", "Hello from Splash!")
            startActivity(intent)
        },5000)
        Log.d(TAG,"SplashActivity onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"SplashActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"SplashActivity onResume")
    }
}