package id.ac.ukdw.final_71190443

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class AddActivity: AppCompatActivity() {
    var firestore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        firestore = FirebaseFirestore.getInstance()

        supportActionBar?.setTitle("E-Book Catalog")
        supportActionBar?.setSubtitle("by: Chandra W")


        val etISBN = findViewById<EditText>(R.id.etISBN)
        val etJudul = findViewById<EditText>(R.id.etJudul)
        val etPengarang = findViewById<EditText>(R.id.etPengarang)
        val etHarga = findViewById<EditText>(R.id.etHarga)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        btnSimpan.setOnClickListener {
            val buku = Buku(etISBN.text.toString(),etJudul.text.toString(),etPengarang.text.toString(),etHarga.text.toString())
            firestore?.collection("buku")?.document(buku.isbn)?.set(buku)?.addOnSuccessListener{ Log.d(TAG,"Penyimpanan Berhasil")}?.addOnFailureListener { Log.d(TAG,"Penyimpanan Gagal") }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_default, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_add -> {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
            true
        }
        R.id.menu_search -> {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}