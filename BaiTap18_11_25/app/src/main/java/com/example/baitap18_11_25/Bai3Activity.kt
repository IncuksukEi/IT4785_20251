package com.example.baitap18_11_25

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Bai3Activity : AppCompatActivity() {

    data class AppItem(
        val name: String,
        val rating: Double,
        val size: String,
        val icon: Int
    )

    class AppAdapter(
        private val context: Bai3Activity,
        private val list: ArrayList<AppItem>
    ) : BaseAdapter() {

        override fun getCount() = list.size
        override fun getItem(i: Int) = list[i]
        override fun getItemId(i: Int) = i.toLong()

        override fun getView(i: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.item_bai3, parent, false)

            val img = view.findViewById<ImageView>(R.id.imgIcon)
            val txtName = view.findViewById<TextView>(R.id.txtAppName)
            val txtRating = view.findViewById<TextView>(R.id.txtRating)
            val txtSize = view.findViewById<TextView>(R.id.txtSize)

            val app = list[i]

            img.setImageResource(app.icon)
            txtName.text = app.name
            txtRating.text = "Rating: ${app.rating}"
            txtSize.text = "Size: ${app.size}"

            return view
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai3)

        val listApp: ListView = findViewById(R.id.listApp)

        val apps = arrayListOf(
            AppItem("Zombie Swarm", 4.8, "624 MB", android.R.drawable.ic_media_play),
            AppItem("MU Hỏa Đao", 4.8, "339 MB", android.R.drawable.ic_menu_gallery),
            AppItem("War Inc Rising", 4.9, "231 MB", android.R.drawable.ic_menu_compass)
        )

        listApp.adapter = AppAdapter(this, apps)
    }
}
