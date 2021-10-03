package id.ac.ukdw.pertemuan5_71190443

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username = intent.getStringExtra("username")
        val tvGreetings = findViewById<TextView>(R.id.tvGreetings)
        tvGreetings.text = "Selamat Datang $username"

    }
}