package com.example.baitap18_11_25

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class Bai1Activity : AppCompatActivity() {

    // MODEL
    data class SinhVien(var mssv: String, var name: String)

    // ADAPTER
    class SinhVienAdapter(
        private val context: Bai1Activity,
        private val list: ArrayList<SinhVien>
    ) : BaseAdapter() {

        override fun getCount() = list.size
        override fun getItem(position: Int) = list[position]
        override fun getItemId(position: Int) = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.item_bai1, parent, false)

            val txtName = view.findViewById<TextView>(R.id.txtName)
            val txtMssv = view.findViewById<TextView>(R.id.txtMSSV)
            val imgDelete = view.findViewById<ImageView>(R.id.imgDelete)

            val sv = list[position]
            txtName.text = sv.name
            txtMssv.text = sv.mssv

            imgDelete.setOnClickListener {
                list.removeAt(position)
                notifyDataSetChanged()
            }

            return view
        }
    }

    private val listSV = ArrayList<SinhVien>()
    private lateinit var adapter: SinhVienAdapter
    private var selectedIndex = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai1)

        val listView = findViewById<ListView>(R.id.listViewSV)
        val edtMSSV = findViewById<EditText>(R.id.edtMSSV)
        val edtName = findViewById<EditText>(R.id.edtName)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnUpdate = findViewById<Button>(R.id.btnUpdate)

        listSV.add(SinhVien("20200001", "Nguyễn Văn A"))
        listSV.add(SinhVien("20200002", "Trần Thị B"))
        listSV.add(SinhVien("20200003", "Lê Văn C"))
        listSV.add(SinhVien("20200004", "Phạm Thị D"))
        listSV.add(SinhVien("20200005", "Hoàng Văn E"))
        listSV.add(SinhVien("20200006", "Vũ Thị F"))
        listSV.add(SinhVien("20200007", "Đặng Văn G"))
        listSV.add(SinhVien("20200008", "Bùi Thị H"))
        listSV.add(SinhVien("20200009", "Hồ Văn I"))

        adapter = SinhVienAdapter(this, listSV)
        listView.adapter = adapter

        btnAdd.setOnClickListener {
            val mssv = edtMSSV.text.toString()
            val name = edtName.text.toString()

            if (mssv.isBlank() || name.isBlank()) {
                Toast.makeText(this, "Không được để trống!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            listSV.add(SinhVien(mssv, name))
            adapter.notifyDataSetChanged()
            edtMSSV.setText("")
            edtName.setText("")
        }

        listView.setOnItemClickListener { _, _, pos, _ ->
            selectedIndex = pos
            edtMSSV.setText(listSV[pos].mssv)
            edtName.setText(listSV[pos].name)
        }

        btnUpdate.setOnClickListener {
            if (selectedIndex == -1) {
                Toast.makeText(this, "Chọn item để cập nhật!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            listSV[selectedIndex].mssv = edtMSSV.text.toString()
            listSV[selectedIndex].name = edtName.text.toString()
            adapter.notifyDataSetChanged()

            selectedIndex = -1
            edtMSSV.setText("")
            edtName.setText("")
        }
    }
}
