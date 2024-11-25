package com.asyfdzaky.pppb_crud

import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asyfdzaky.pppb_crud.Database.DataPemilih
import com.asyfdzaky.pppb_crud.databinding.ItemDisasterBinding

class DataPemilihAdapter(
    private val context: Context,
    private val deleteAction: (DataPemilih) -> Unit
) : ListAdapter<DataPemilih, DataPemilihAdapter.DataPemilihViewHolder>(DiffCallback) {

    // DiffUtil Callback untuk menghitung perubahan data
    object DiffCallback : DiffUtil.ItemCallback<DataPemilih>() {
        override fun areItemsTheSame(oldItem: DataPemilih, newItem: DataPemilih): Boolean {
            return oldItem.id == newItem.id // Periksa apakah ID item sama
        }

        override fun areContentsTheSame(oldItem: DataPemilih, newItem: DataPemilih): Boolean {
            return oldItem == newItem // Periksa apakah isi item sama
        }
    }


    // ViewHolder untuk mengikat data dengan layout item
    inner class DataPemilihViewHolder(private val binding: ItemDisasterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataPemilih, position: Int)  {
            with(binding) {
                nomer.text = "${position + 1}."
                nama.text = data.nama


                // Set click listener on the item view
                edit.setOnClickListener {
                    val intent = Intent(context, EditActivity::class.java)
                    intent.putExtra("id", data.id)
                    intent.putExtra("nama", data.nama)
                    intent.putExtra("nik", data.nik)
                    intent.putExtra("jenis_kelamin", data.jenisKelamin)
                    intent.putExtra("alamat", data.alamat)
                    context.startActivity(intent)
                }
                viewDetail.setOnClickListener {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra("nama", data.nama)
                    intent.putExtra("nik", data.nik)
                    intent.putExtra("jenis_kelamin", data.jenisKelamin)
                    intent.putExtra("alamat", data.alamat)
                    context.startActivity(intent)
                }
                delete.setOnClickListener {
                    // Menjalankan aksi delete melalui callback
                    deleteAction(data)

                }

            }
        }
    }

    // Inflate layout item untuk RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataPemilihViewHolder {
        val binding = ItemDisasterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DataPemilihViewHolder(binding)
    }

    // Mengikat data ke ViewHolder
    override fun onBindViewHolder(holder: DataPemilihViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

}
