package id.ac.ukdw.pertemuan9_sharedpreferences_71190443

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    var sp: SharedPreferences? = null
    var spEdit: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sp = getSharedPreferences("appsKu", MODE_PRIVATE)
        spEdit = sp?.edit()

        if(sp?.getBoolean("isLogin", false) == true){
            setContentView(R.layout.activity_home)

            val spBahasa = findViewById<Spinner>(R.id.spBahasa)
            val adapter = ArrayAdapter.createFromResource(this,R.array.list_bahasa, R.layout.support_simple_spinner_dropdown_item)

            Toast.makeText(this, "Halo "+sp!!.getString("user","admin"), Toast.LENGTH_SHORT).show()
            spBahasa.adapter = adapter
            spBahasa.setSelection(sp!!.getInt("bahasa",0))
            spBahasa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    spEdit?.putInt("bahasa", position)
                    spEdit?.commit()
                }
                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }

            val etUkuran = findViewById<EditText>(R.id.etUkuran)
            etUkuran.setText(sp!!.getString("ukuran","14"))
            etUkuran.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    spEdit?.putString("ukuran",s.toString())
                    spEdit?.commit()
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
            })
        }else{
            setContentView(R.layout.activity_main)
            val etUsername = findViewById<EditText>(R.id.etUsername)
            val etPassword = findViewById<EditText>(R.id.etPassword)
            val btnLogin = findViewById<Button>(R.id.btnLogin)
            btnLogin.setOnClickListener {
                if(etUsername.text.toString()=="admin" && etPassword.text.toString()=="1234"){
                    spEdit?.putBoolean("isLogin",true)
                    spEdit?.putString("user",etUsername.toString())
                    spEdit?.commit()


                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }




















    }
}