package com.example.baitap18_11_25

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class Bai1Activity : AppCompatActivity() {

    // --- Adapter Nội Bộ ---
    class SinhVienAdapter(val ctx: Bai1Activity, val data: ArrayList<SinhVien>) : BaseAdapter() {
        override fun getCount() = data.size
        override fun getItem(i: Int) = data[i]
        override fun getItemId(i: Int) = i.toLong()
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: LayoutInflater.from(ctx).inflate(R.layout.item_bai1, parent, false)

            // Theo đề bài: List chỉ hiện MSSV và Tên
            val txtName = view.findViewById<TextView>(R.id.txtName)
            val txtMssv = view.findViewById<TextView>(R.id.txtMSSV)
            val imgDelete = view.findViewById<ImageView>(R.id.imgDelete)

            val sv = data[position]
            txtName.text = sv.name
            txtMssv.text = sv.mssv

            imgDelete.setOnClickListener {
                data.removeAt(position)
                notifyDataSetChanged()
            }
            return view
        }
    }

    private val listSV = ArrayList<SinhVien>()
    private lateinit var adapter: SinhVienAdapter

    // 1. Launcher để nhận kết quả khi Thêm mới
    private val launcherAdd = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val newSV = result.data?.getSerializableExtra("KEY_SV_NEW") as? SinhVien
            if (newSV != null) {
                listSV.add(newSV)
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Đã thêm mới!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 2. Launcher để nhận kết quả khi Sửa/Chi tiết
    private val launcherEdit = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val updatedSV = result.data?.getSerializableExtra("KEY_SV_UPDATED") as? SinhVien
            val pos = result.data?.getIntExtra("KEY_POS_UPDATED", -1) ?: -1

            if (updatedSV != null && pos != -1) {
                listSV[pos] = updatedSV
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Đã cập nhật!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai1)

        // Dữ liệu mẫu
        listSV.add(SinhVien("20201234", "Nguyễn Văn A", "0909123456", "Hà Nội"))
        listSV.add(SinhVien("20205678", "Trần Thị B", "0912345678", "Đà Nẵng"))

        val lv = findViewById<ListView>(R.id.listViewBai1)
        adapter = SinhVienAdapter(this, listSV)
        lv.adapter = adapter

        // Bắt sự kiện click vào item -> Mở màn hình Detail
        lv.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, Bai1DetailActivity::class.java)
            intent.putExtra("KEY_SV_EDIT", listSV[position])
            intent.putExtra("KEY_POS", position)
            launcherEdit.launch(intent)
        }
    }

    // Tạo Option Menu (Nút thêm ở góc phải)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bai1, menu)
        return true
    }

    // Xử lý khi bấm nút thêm
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_add_bai1) {
            val intent = Intent(this, Bai1AddActivity::class.java)
            launcherAdd.launch(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}