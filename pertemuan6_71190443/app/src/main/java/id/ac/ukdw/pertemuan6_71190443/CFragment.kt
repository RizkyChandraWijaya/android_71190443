package id.ac.ukdw.pertemuan6_71190443

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class CFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragC = inflater.inflate(R.layout.fragment_c,container,false)
        val btnpage1 = fragC.findViewById<Button>(R.id.btnpage1)

        btnpage1.setOnClickListener {
            toast("Anda Beralih ke Page 1")
            val intent = Intent(context,SatuPage::class.java)
            context?.startActivity(intent)
        }
        return fragC
    }
    fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}