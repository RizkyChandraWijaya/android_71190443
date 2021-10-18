package id.ac.ukdw.pertemuan7_71190443

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(val listContact : ArrayList<Contact>) : RecyclerView.Adapter<ContactAdapter.ContactHolder>() {

    class ContactHolder(val v:View): RecyclerView.ViewHolder(v){
        fun bindView(contact:Contact){
            v.findViewById<TextView>(R.id.tvName).text = contact.name
            v.findViewById<TextView>(R.id.tvPhoneNumber).text = contact.phoneNumber
            v.findViewById<ImageView>(R.id.ivContact).setImageResource(contact.photo)

            val test = v.findViewById<LinearLayout>(R.id.lyContact)

            test.setOnClickListener {
                val intent = Intent(v.context, DetailActivity::class.java)
                intent.putExtra("foto", contact.photo)
                intent.putExtra("nama", contact.name)
                intent.putExtra("nomor", contact.phoneNumber)
                v.context.startActivity(intent)
            }
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactAdapter.ContactHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
        return ContactHolder(v)

    }

    override fun onBindViewHolder(holder: ContactAdapter.ContactHolder, position: Int) {
        holder.bindView(listContact[position])
    }

    override fun getItemCount(): Int {
        return listContact.size
    }
}