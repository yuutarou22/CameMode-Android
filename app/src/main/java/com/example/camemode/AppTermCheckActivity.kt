package com.example.camemode

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.AppLaunchChecker

import kotlinx.android.synthetic.main.activity_app_term_check.*

class AppTermCheckActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_term_check)

        AppLaunchChecker.onActivityCreate(this)
        initView()
    }

    private fun initView() {
        app_term_checked_button.setOnClickListener {

            if (app_term_check.isChecked) {
                // 初回起動済みを設定する
                val data = getSharedPreferences("status", Context.MODE_PRIVATE)
                val editor = data.edit()
                editor.putBoolean("isTutorialAndAppTermFinished", true)
                editor.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "利用規約に同意してください！", Toast.LENGTH_SHORT).show()
            }
        }
        
        app_term_check.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                app_term_checked_button.setBackgroundColor(getColor(R.color.colorThema))
            } else {
                app_term_checked_button.setBackgroundColor(getColor(R.color.colorNegative))
            }
        }
    }
}