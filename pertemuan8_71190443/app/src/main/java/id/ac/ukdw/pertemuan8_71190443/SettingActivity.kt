package id.ac.ukdw.pertemuan8_71190443

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.tooblar_default))
        supportActionBar?.setTitle("Setting Menu")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tvGreetings = findViewById<TextView>(R.id.tvHome)
        tvGreetings.text = "Page Setting"
    }
}