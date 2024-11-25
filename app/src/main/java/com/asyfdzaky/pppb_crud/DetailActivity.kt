package com.asyfdzaky.pppb_crud

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.asyfdzaky.pppb_crud.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data yang dikirim melalui Intent
        val nama = intent.getStringExtra("nama") ?: "Tidak tersedia"
        val nik = intent.getStringExtra("nik") ?: "Tidak tersedia"
        val jenisKelamin = intent.getStringExtra("jenis_kelamin") ?: "Tidak tersedia"
        val alamat = intent.getStringExtra("alamat") ?: "Tidak tersedia"

        // Tampilkan data pada TextView
        with(binding) {
            tvNamaPemilih.text = "Nama: $nama"
            tvNik.text = "NIK: $nik"
            tvGender.text = "Gender: $jenisKelamin"
            tvAlamat.text = "Alamat: $alamat"

            // Fungsi tombol kembali
            btnBack.setOnClickListener { finish() }
        }
    }
}
