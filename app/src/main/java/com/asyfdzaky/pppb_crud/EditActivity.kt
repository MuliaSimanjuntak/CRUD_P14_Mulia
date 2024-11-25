package com.asyfdzaky.pppb_crud

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.asyfdzaky.pppb_crud.Database.DataPemilih
import com.asyfdzaky.pppb_crud.Database.DataPemilihDao
import com.asyfdzaky.pppb_crud.Database.DataPemilihDatabase
import com.asyfdzaky.pppb_crud.databinding.ActivityEditBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    private lateinit var dataPemilihDao: DataPemilihDao
    private lateinit var executorService: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DataPemilihDatabase.getDatabase(this)
        dataPemilihDao = db!!.dataPemilihDao()
        executorService = Executors.newSingleThreadExecutor()

        val id = intent.getIntExtra("id", -1)
        val nama = intent.getStringExtra("nama")
        val nik = intent.getStringExtra("nik")
        val jenisKelamin = intent.getStringExtra("jenis_kelamin")
        val alamat = intent.getStringExtra("alamat")

        with(binding) {
            etNamaPemilih.setText(nama)
            etNik.setText(nik)
            if (jenisKelamin == "Laki-laki") rgGender.check(rbMale.id) else rgGender.check(rbFemale.id)
            etAlamat.setText(alamat)

            btnSimpan.setOnClickListener {
                val updatedGender = if (rgGender.checkedRadioButtonId == rbMale.id) "Laki-laki" else "Perempuan"
                val updatedData = DataPemilih(
                    id = id,
                    nama = etNamaPemilih.text.toString(),
                    nik = etNik.text.toString(),
                    jenisKelamin = updatedGender,
                    alamat = etAlamat.text.toString()
                )
                updateData(updatedData)
            }
        }
    }

    private fun updateData(dataPemilih: DataPemilih) {
        executorService.execute {
            dataPemilihDao.update(dataPemilih)
            runOnUiThread {
                Toast.makeText(this, "Data berhasil diperbarui", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
