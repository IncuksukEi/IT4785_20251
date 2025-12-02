package com.example.baitap18_11_25

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Bai1DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai1_detail)

        val edtMssv = findViewById<EditText>(R.id.edtDetMSSV)
        val edtName = findViewById<EditText>(R.id.edtDetName)
        val edtPhone = findViewById<EditText>(R.id.edtDetPhone)
        val edtAddr = findViewById<EditText>(R.id.edtDetAddress)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // 1. Nhận dữ liệu từ Bai1Activity gửi sang để hiển thị
        val oldSV = intent.getSerializableExtra("KEY_SV_EDIT") as? SinhVien
        val pos = intent.getIntExtra("KEY_POS", -1)

        if (oldSV != null) {
            edtMssv.setText(oldSV.mssv)
            edtName.setText(oldSV.name)
            edtPhone.setText(oldSV.phone)
            edtAddr.setText(oldSV.address)
        }

        // 2. Xử lý nút Cập nhật
        btnUpdate.setOnClickListener {
            val newSV = SinhVien(
                edtMssv.text.toString(),
                edtName.text.toString(),
                edtPhone.text.toString(),
                edtAddr.text.toString()
            )
            val intent = Intent()
            intent.putExtra("KEY_SV_UPDATED", newSV)
            intent.putExtra("KEY_POS_UPDATED", pos)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        btnBack.setOnClickListener { finish() }
    }
}