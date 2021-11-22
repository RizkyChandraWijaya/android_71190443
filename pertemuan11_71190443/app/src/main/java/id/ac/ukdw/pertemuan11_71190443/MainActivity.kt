package id.ac.ukdw.pertemuan11_71190443

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MainActivity : AppCompatActivity() {
    var firestore: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firestore = FirebaseFirestore.getInstance()
        var valueBtn = ""

        val edtNama = findViewById<EditText>(R.id.edtNama)
        val edtNim = findViewById<EditText>(R.id.edtNim)
        val edtIpk = findViewById<EditText>(R.id.edtIpk)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnCari = findViewById<Button>(R.id.btnCari)
        val tvOutput = findViewById<TextView>(R.id.tvOutput)
        val btnAsc = findViewById<RadioButton>(R.id.radioAsc)
        val btnDesc = findViewById<RadioButton>(R.id.radioDesc)
        val btnMain = findViewById<RadioGroup>(R.id.radioMain)

        btnSimpan.setOnClickListener {
            val mahasiswa = Mahasiswa(
                edtNama.text.toString(),
                edtNim.text.toString(),
                edtIpk.text.toString().toDouble()
            )
            firestore?.collection("mahasiswa")?.document(mahasiswa.nim)?.set(mahasiswa)
        }

        btnMain.setOnCheckedChangeListener { group, checkedId ->
            valueBtn = when (checkedId) {
                R.id.radioAsc -> "Ascending"
                R.id.radioDesc -> "Descending"
                else -> ""
            }
        }

        btnCari.setOnClickListener {
            if (valueBtn.equals("Ascending")) {
                firestore?.collection("mahasiswa")?.whereEqualTo("nama", edtNama.text.toString())
                    ?.orderBy("ipk", Query.Direction.ASCENDING)?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for (dsc in docs) {
                            output += "\n Nama: ${dsc["nama"]}\n NIM: ${dsc["nim"]}\n IPK : ${dsc["ipk"]}\n"

                            tvOutput.setText(output)
                            btnAsc.isChecked = false
                        }
                    }
            } else if (valueBtn.equals("Descending")) {
                firestore?.collection("mahasiswa")?.whereEqualTo("nama", edtNama.text.toString())
                    ?.orderBy("ipk", Query.Direction.DESCENDING)?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for (dsc in docs) {
                            output += "\n Nama: ${dsc["nama"]}\n NIM: ${dsc["nim"]}\n IPK : ${dsc["ipk"]}\n"

                            tvOutput.setText(output)
                            btnDesc.isChecked = false
                        }
                    }
            } else {
                firestore?.collection("mahasiswa")?.whereEqualTo("nama", edtNama.text.toString())
                    ?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for (doc in docs) {
                            output += "\n Nama: ${doc["nama"]}\n NIM: ${doc["nim"]}\n IPK: ${doc["ipk"]}\n"
                        }
                        tvOutput.setText(output)
                    }
            }
        }
    }
}































































