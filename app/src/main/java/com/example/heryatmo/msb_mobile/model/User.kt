package com.example.heryatmo.msb_mobile.model

import java.util.*

data class User(
         val id_user: Int,
         val id_role: Int,
         val id_daftar: Int,
         val nama: String,
         val tempat_lahir: String,
         val tanggal_lahir: Date,
         val jenis_kelamin: String,
         val golongan_darah: String,
         val alamat: String,
         val no_hp: String,
         val status_user: String,
         val email: String,
         val password: String) {
}