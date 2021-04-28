package com.example.studyapp

import android.content.Intent
import android.os.Bundle
import com.example.studyapp.bmi.BmiActivity
import com.example.studyapp.databinding.ActivityMainBinding
import com.example.studyapp.lotto.LottoActivity

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initButton()
    }

    fun initButton() {
        binding.btnBmi.setOnClickListener {
            startActivity(Intent(this, BmiActivity::class.java))
        }
        binding.btnLotto.setOnClickListener {
             startActivity(Intent(this, LottoActivity::class.java))
        }
    }
}