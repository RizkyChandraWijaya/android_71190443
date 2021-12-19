package id.ac.ukdw.final_71190443

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        supportActionBar?.setTitle("E-Book Catalog")
        supportActionBar?.setSubtitle("by: Chandra W")

        val isbn = intent.getStringExtra("isbn")
        val judul = intent.getStringExtra("judul")
        val pengarang = intent.getStringExtra("pengarang")
        val harga = intent.getStringExtra("harga")

        val tvIsbn = findViewById<TextView>(R.id.tvIsbnDetail)
        val tvJudul = findViewById<TextView>(R.id.tvJudulDetail)
        val tvPengarang = findViewById<TextView>(R.id.tvPengarangDetail)
        val tvHarga = findViewById<TextView>(R.id.tvHargaDetail)

        tvIsbn.text = isbn
        tvJudul.text = judul
        tvPengarang.text = pengarang
        tvHarga.text = harga

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