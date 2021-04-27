package com.example.studyapp.bmi

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.studyapp.BR
import com.example.studyapp.BaseActivity
import com.example.studyapp.R
import com.example.studyapp.databinding.ActivityBmiBinding
import kotlin.math.pow


class BmiActivity : BaseActivity<ActivityBmiBinding>(R.layout.activity_bmi) {

    private lateinit var bmiViewModel: BmiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bmiViewModel = ViewModelProvider(this).get(BmiViewModel::class.java)

        binding.tvBmiTitle.isVisible = false
        binding.tvResultTitle.isVisible = false

        binding.btnResult.setOnClickListener {
            if (binding.edtHeight.text.isEmpty()|| binding.edtWeight.text.isEmpty()) {
                Toast.makeText(this, "빈 값이 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val input_height = binding.edtHeight.text.toString()
            val input_weight = binding.edtWeight.text.toString()

            val bmi1 = input_weight.toInt() / (input_height.toInt()/100.0).pow(2.0)

            val result1 = when {
                bmi1 >= 35.0 -> "고도 비만"
                bmi1 >= 30.0 -> "중정도 비만"
                bmi1 >= 25.0 -> "경도 비만"
                bmi1 >= 23.0 -> "과체중"
                bmi1 >= 18.5 -> "정상체중"
                else -> "저체중"
            }

            binding.tvBmiTitle.isVisible = true
            binding.tvResultTitle.isVisible = true

            bmiViewModel.bmi.value = String.format("%.1f",bmi1)
            bmiViewModel.result.value = result1

            binding.setVariable(BR.vm,bmiViewModel)
        }
    }
}

