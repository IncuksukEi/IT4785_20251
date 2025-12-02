package com.example.baitap18_11_25

import java.io.Serializable

data class SinhVien(
    var mssv: String,
    var name: String,
    var phone: String,
    var address: String
) : Serializable