package com.example.recyclerviewpractice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import android.content.Context


class MahasiswaAdapter(
    private val dataList: ArrayList<Mahasiswa>,
    private val context: Context
) :
    RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.row_mahasiswa, parent, false)
        return MahasiswaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        holder.txtNama.text = dataList[position].nama
        holder.txtNpm.text = dataList[position].nim
        holder.txtNoHp.text = dataList[position].nohp
        val mahasiswa = dataList[position]

        // Tambahkan klik listener untuk item
        holder.itemView.setOnClickListener {
            Toast.makeText(
                context,
                "Nama: ${mahasiswa.nama}, NIM: ${mahasiswa.nim}, No HP: ${mahasiswa.nohp}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class MahasiswaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtNama: TextView = itemView.findViewById(R.id.txt_nama_mahasiswa)
        val txtNpm: TextView = itemView.findViewById(R.id.txt_nim_mahasiswa)
        val txtNoHp: TextView = itemView.findViewById(R.id.txt_nohp_mahasiswa)
    }
}