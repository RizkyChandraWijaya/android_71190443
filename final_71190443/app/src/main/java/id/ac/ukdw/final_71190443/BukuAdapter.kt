package id.ac.ukdw.final_71190443

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class BukuAdapter(val listBuku : ArrayList<Buku>) : RecyclerView.Adapter<BukuAdapter.BukuHolder>() {
    var firestore: FirebaseFirestore? = null

    inner class BukuHolder(val v:View): RecyclerView.ViewHolder(v){
        fun bindView(buku:Buku){
            v.findViewById<TextView>(R.id.tvIsbn).text = buku.isbn
            v.findViewById<TextView>(R.id.tvJudul).text = buku.judul
            v.findViewById<TextView>(R.id.tvPengarang).text = buku.pengarang
            v.findViewById<TextView>(R.id.tvHarga).text = buku.harga

            val btnEdit = v.findViewById<ImageView>(R.id.ivEdit)
            val btnDelete = v.findViewById<ImageView>(R.id.ivHapus)

            val test = v.findViewById<LinearLayout>(R.id.lyBuku)

            test.setOnClickListener {
                val intent = Intent(v.context, DetailActivity::class.java)
                intent.putExtra("isbn", buku.isbn)
                intent.putExtra("judul", buku.judul)
                intent.putExtra("pengarang", buku.pengarang)
                intent.putExtra("harga", buku.harga)
                v.context.startActivity(intent)
            }

            btnEdit.setOnClickListener {
                val intent = Intent(v.context, EditActivity::class.java)
                intent.putExtra("isbn", buku.isbn)
                intent.putExtra("judul", buku.judul)
                intent.putExtra("pengarang", buku.pengarang)
                intent.putExtra("harga", buku.harga)
                v.context.startActivity(intent)
            }

            btnDelete.setOnClickListener {
                firestore = FirebaseFirestore.getInstance()
                firestore!!.collection("buku").document(buku.isbn)
                    .delete()
                    .addOnSuccessListener {
                        Log.d(TAG, "DocumentSnapshot successfully deleted!") }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error deleting document", e) }
            }
        }

    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BukuAdapter.BukuHolder {
        val v : View = LayoutInflater.from(parent.context).inflate(R.layout.item_buku,parent,false)
        return BukuHolder(v)

    }

    override fun onBindViewHolder(holder: BukuAdapter.BukuHolder, position: Int) {
        holder.bindView(listBuku[position])
    }

    override fun getItemCount(): Int {
        return listBuku.size
    }
}