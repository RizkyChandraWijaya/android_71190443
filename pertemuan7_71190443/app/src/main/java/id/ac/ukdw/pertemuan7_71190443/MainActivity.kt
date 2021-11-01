package id.ac.ukdw.pertemuan7_71190443

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listContact = ArrayList<Contact>()
        listContact.add(Contact(R.mipmap.me,"Rizky Chandra Wijaya","083893887191"))
        listContact.add(Contact(R.mipmap.koko,"Kakak","083597882911"))
        listContact.add(Contact(R.mipmap.yoko,"Yeriko Evandi","085219012351"))
        listContact.add(Contact(R.mipmap.aldo,"Devaldo Filbert","08377866819"))

        val rvContact = findViewById<RecyclerView>(R.id.rvContacts)
        rvContact.layoutManager = LinearLayoutManager(this)

        val adapter = ContactAdapter(listContact)
        rvContact.adapter = adapter

    }
}