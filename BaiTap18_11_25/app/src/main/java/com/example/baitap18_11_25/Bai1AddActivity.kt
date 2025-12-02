package com.example.baitap18_11_25

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Bai1AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai1_add)

        val edtMssv = findViewById<EditText>(R.id.edtAddMSSV)
        val edtName = findViewById<EditText>(R.id.edtAddName)
        val edtPhone = findViewById<EditText>(R.id.edtAddPhone)
        val edtAddr = findViewById<EditText>(R.id.edtAddAddress)
        val btnSave = findViewById<Button>(R.id.btnSaveAdd)

        btnSave.setOnClickListener {
            // Lấy dữ liệu và đóng gói vào Intent
            val sv = SinhVien(
                edtMssv.text.toString(),
                edtName.text.toString(),
                edtPhone.text.toString(),
                edtAddr.text.toString()
            )
            val intent = Intent()
            intent.putExtra("KEY_SV_NEW", sv)
            setResult(Activity.RESULT_OK, intent)
            finish() // Đóng màn hình này
        }
    }
}