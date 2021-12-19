package id.ac.ukdw.final_71190443

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.firestore.FirebaseFirestore
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firestore = FirebaseFirestore.getInstance()
        val rvBook = findViewById<RecyclerView>(R.id.rvBooks)
        var listBuku = ArrayList<Buku>()
        firestore!!.collection("buku")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("Success", "${document.id} => ${document.data}")
                    var data = JSONObject(document.data)

                    var resIsbn = data.getString("isbn")
                    var resJudul = data.getString("judul")
                    var resPengarang = data.getString("pengarang")
                    var resHarga = data.getString("harga")
                    Log.d("ID: ", resIsbn)
                    Log.d("JUDUL: ", resJudul)
//                    listBuku.add(Buku("12345","Judul nih","Rizky Chandra Wijaya","20000.00"))
                    listBuku.add(Buku("${resIsbn}","${resJudul}","${resPengarang}","${resHarga}"))

                    var bukuAdapter = BukuAdapter(listBuku)
                    rvBook.apply {
                        rvBook.layoutManager = LinearLayoutManager(context)
                        rvBook.adapter = bukuAdapter
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.d("ErrorDB", "Error getting documents: ", exception)
            }

        supportActionBar?.setTitle("E-Book Catalog")
        supportActionBar?.setSubtitle("by: Chandra W")

        //logout button

    }

    private fun checkUser() {
        TODO("Not yet implemented")
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
