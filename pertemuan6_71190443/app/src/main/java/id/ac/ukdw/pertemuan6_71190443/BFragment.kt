package id.ac.ukdw.pertemuan6_71190443

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class BFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragB = inflater.inflate(R.layout.fragment_b,container,false)
        val btnpage3 = fragB.findViewById<Button>(R.id.btnpage3)

        btnpage3.setOnClickListener {
            toast("Anda Beralih ke Page 3")
            val intent = Intent(context,TigaPage::class.java)
            context?.startActivity(intent)
        }
        return fragB
    }
    fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}