package id.ac.ukdw.final_71190443

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class SearchActivity: AppCompatActivity() {
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val rvBook = findViewById<RecyclerView>(R.id.rvBooks)
        val search = findViewById<SearchView>(R.id.searchView)

        firestore = FirebaseFirestore.getInstance()

        supportActionBar?.setTitle("E-Book Catalog")
        supportActionBar?.setSubtitle("by: Chandra W")

        var filterBuku = ArrayList<Buku>()
        filterBuku.clear()


        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String): Boolean {
                search.clearFocus()
                filterBuku.clear()
                firestore?.collection("buku")?.whereEqualTo("judul", p0)
                    ?.get()!!
                    .addOnSuccessListener { docs ->
                        for (dsc in docs) {
                            filterBuku.add(
                                Buku(
                                    "${dsc["isbn"]}",
                                    "${dsc["judul"]}",
                                    "${dsc["pengarang"]}",
                                    "${dsc["harga"]}"
                                )
                            )
                        }
                    }
                return false
            }

            override fun onQueryTextChange(p0: String): Boolean {
                filterBuku.clear()
                firestore?.collection("buku")?.whereEqualTo("judul", p0)
                    ?.get()!!
                    .addOnSuccessListener { docs ->
                        for (dsc in docs) {
                            filterBuku.add(
                                Buku(
                                    "${dsc["isbn"]}",
                                    "${dsc["judul"]}",
                                    "${dsc["pengarang"]}",
                                    "${dsc["harga"]}"
                                    )
                                )
                            }
                        }
                return false
            }

        })
        var bukuAdapter = BukuAdapter(filterBuku)
        rvBook.apply {
            rvBook.layoutManager = LinearLayoutManager(context)
            rvBook.adapter = bukuAdapter
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