package com.example.studyapp.lotto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.studyapp.BaseActivity
import com.example.studyapp.R
import com.example.studyapp.bmi.BmiViewModel
import com.example.studyapp.databinding.ActivityLottoBinding

class LottoActivity : BaseActivity<ActivityLottoBinding>(R.layout.activity_lotto) {

    private val numberTvList : List<TextView> by lazy {
        listOf<TextView>(
            binding.tv1,
            binding.tv2,
            binding.tv3,
            binding.tv4,
            binding.tv5,
            binding.tv6
        )
    }
    private var didRun = false
    private val pickNumberSet = hashSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.numberPicker.minValue = 1
        binding.numberPicker.maxValue = 45

        initRunButton()
        initAddButton()
        initClearButton()
    }
    private fun initRunButton(){
        binding.runButton.setOnClickListener {
            val list = getRandomNumber()
            didRun = true
            list.forEachIndexed{
                    index, number -> val textView = numberTvList[index]
                textView.text = number.toString()
                textView.isVisible = true

                setNumberBackground(number,textView)
            }
        }
    }
    private fun initAddButton(){
        binding.addButton.setOnClickListener {
            if(didRun){
                Toast.makeText(this,"초기화 후 시도해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(pickNumberSet.size >= 5){
                Toast.makeText(this,"번호는 5개까지만 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(pickNumberSet.contains(binding.numberPicker.value)){
                Toast.makeText(this,"이미 선택한 번호입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tv = numberTvList[pickNumberSet.size]
            tv.isVisible = true
            tv.text = binding.numberPicker.value.toString()

            setNumberBackground(binding.numberPicker.value,tv)

            pickNumberSet.add(binding.numberPicker.value)
        }
    }
    private fun initClearButton(){
        binding.clearButton.setOnClickListener {
            pickNumberSet.clear()
            numberTvList.forEach{
                it.isVisible = false
            }
            didRun = false
        }
    }
    private fun setNumberBackground(number:Int, textView: TextView){
        when(number){
            in 1..10 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_yello)
            in 11..20 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_blue)
            in 21..30 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_red)
            in 31..40 -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_green)
            else -> textView.background = ContextCompat.getDrawable(this,R.drawable.circle_gray)
        }
    }
    private fun getRandomNumber() : List<Int> {
        val numberList = mutableListOf<Int>()
            .apply {
                for(i in 1..45 ){
                    if(pickNumberSet.contains(i)){
                        continue
                    }
                    this.add(i)
                }
            }
        numberList.shuffle()
        val newList = pickNumberSet.toList() + numberList.subList(0,6 - pickNumberSet.size)
        return newList.sorted()
    }
}