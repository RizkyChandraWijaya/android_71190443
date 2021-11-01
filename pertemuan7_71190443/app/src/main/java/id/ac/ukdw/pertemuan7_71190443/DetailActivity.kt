package id.ac.ukdw.pertemuan7_71190443

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val foto = intent.getIntExtra("foto",0)
        val nama = intent.getStringExtra("nama")
        val nomer = intent.getStringExtra("nomor")


        val profilePic = findViewById<ImageView>(R.id.ivContact)
        val namaContact = findViewById<TextView>(R.id.tvName)
        val nomorHp = findViewById<TextView>(R.id.tvPhoneNumber)

        profilePic.setImageResource(foto)
        namaContact.text = nama
        nomorHp.text = nomer

    }
}