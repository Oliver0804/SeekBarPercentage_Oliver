package com.example.seekbarpercentage_oliver

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import android.view.inputmethod.EditorInfo
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    private var value = 0f


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
        //onProgressChanged()
    }

    private fun setupView() {
        seekBar1.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                value = editText.text.toString().removePrefix("$").toFloatOrNull() ?: 0f
                textView4.text = "打折（$p1%)"
                calculateResult()
                if(editText.text.toString()=="0") {
                    toast("請輸入數值")
                }else{
                    toast(editText.text.toString())
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        textView3.setOnEditorActionListener { textView, actionId, keyEvent ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                value = textView3.text.toString().removePrefix("$").toFloatOrNull() ?: 0f
                textView3.setText("$$value")
                calculateResult()
            }

            // false 表示收起鍵盤（不保留鍵盤）
            editText.clearFocus()
            false
        }
    }
    /*
override fun onProgressChanged(seekBar1: SeekBar, progress: Int, fromUser: Boolean){
        textView4.setText("${editText.text.toString().toFloat() * progress/100}")

    }
    */


    private fun calculateResult() {
        val discount = seekBar1.progress * value / 100
        val total = value - discount
        textView3.text = String.format("%.2f", discount)
        textView4.text = String.format("%.2f", total)
    }

}
