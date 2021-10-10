package id.ac.ukdw.pertemuan6_71190443

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class AFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragA = inflater.inflate(R.layout.fragment_a,container,false)
        val btnpage2 = fragA.findViewById<Button>(R.id.btnpage2)

        btnpage2.setOnClickListener {
            toast("Anda Beralih ke Page 2")
            val intent = Intent(context,DuaPage::class.java)
            context?.startActivity(intent)
        }
        return fragA
    }
    fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}