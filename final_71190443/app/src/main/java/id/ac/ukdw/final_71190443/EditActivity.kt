package id.ac.ukdw.final_71190443
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class EditActivity : AppCompatActivity(){
    var firestore: FirebaseFirestore? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        firestore = FirebaseFirestore.getInstance()

        supportActionBar?.setTitle("E-Book Catalog")
        supportActionBar?.setSubtitle("by: Chandra W")

        val isbn = intent.getStringExtra("isbn")
        val judul = intent.getStringExtra("judul")
        val pengarang = intent.getStringExtra("pengarang")
        val harga = intent.getStringExtra("harga")


        val etISBN = findViewById<EditText>(R.id.etEditISBN)
        val etJudul = findViewById<EditText>(R.id.etEditJudul)
        val etPengarang = findViewById<EditText>(R.id.etEditPengarang)
        val etHarga = findViewById<EditText>(R.id.etEditHarga)
        val btnSimpan = findViewById<Button>(R.id.btnEdit)

        etISBN.setText(isbn)
        etJudul.setText(judul)
        etPengarang.setText(pengarang)
        etHarga.setText(harga)

        btnSimpan.setOnClickListener {
            val buku = Buku(etISBN.text.toString(),etJudul.text.toString(),etPengarang.text.toString(),etHarga.text.toString())
            firestore?.collection("buku")?.document(buku.isbn)?.set(buku)?.addOnSuccessListener{ Log.d(
                ContentValues.TAG,"Data Berhasil Diubah")}?.addOnFailureListener { Log.d(
                ContentValues.TAG,"Pengubahan Gagal") }
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