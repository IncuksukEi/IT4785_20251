package com.example.baitap18_11_25

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Bai2Activity : AppCompatActivity() {

    data class Email(val sender: String, val content: String)

    class EmailAdapter(
        private val context: Bai2Activity,
        private val list: ArrayList<Email>
    ) : BaseAdapter() {

        override fun getCount() = list.size
        override fun getItem(i: Int) = list[i]
        override fun getItemId(i: Int) = i.toLong()

        override fun getView(i: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.item_bai2, parent, false)

            val txtAvatar = view.findViewById<TextView>(R.id.txtAvatar)
            val txtSender = view.findViewById<TextView>(R.id.txtSender)
            val txtContent = view.findViewById<TextView>(R.id.txtContent)

            val email = list[i]
            txtAvatar.text = email.sender.first().uppercase()
            txtSender.text = email.sender
            txtContent.text = email.content

            return view
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai2)

        val listEmail = findViewById<ListView>(R.id.listEmail)

        val emails = arrayListOf(
            Email("Edurila", "Khóa học đang giảm giá 90%"),
            Email("Chris Abad", "Hãy đánh giá sản phẩm của chúng tôi"),
            Email("Tuto.com", "Bạn có bài giảng mới"),
            Email("Support", "Thông báo hệ thống")
        )

        listEmail.adapter = EmailAdapter(this, emails)
    }
}
