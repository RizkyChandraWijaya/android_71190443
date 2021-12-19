package id.ac.ukdw.final_71190443

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

class LoginActivity: AppCompatActivity() {

    private val file = "mydata"
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.setTitle("E-Book Catalog")
        supportActionBar?.setSubtitle("by: Chandra W")

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val etUsername = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)

        var username : String

        auth = Firebase.auth

        btnRegister.setOnClickListener {

            // Add to local storage
            try{
                // MODE private = last entry only
                // APPEND = add data
                val fOut = this.openFileOutput(file, Context.MODE_PRIVATE)
                // data = username, password

                var data = etUsername.text.toString() + "," + etPassword.text.toString() + "\n"

                fOut.write(data!!.toByteArray())
                fOut.close()
                Toast.makeText(this, "Data has been saved", Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
                e.printStackTrace()
            }

            // Add to firebase auth

            auth.createUserWithEmailAndPassword(etUsername.text.toString(), etPassword.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("register", "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("register", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
                .addOnFailureListener(this){
                    // Ganti toast
                    Log.d("regsiter failed", it.toString())
                }

        }

        btnLogin.setOnClickListener {
            Log.d("login", "pencet")
            try{
                val fIn = this.openFileInput(file)
                val mfile = InputStreamReader(fIn)
                val br = BufferedReader(mfile)
                var line = br.readLine()

                while (line != null){
                    var savedData = line.split(",")

                    // Go through each line and add to the array
                    username = savedData[0]

                    // check username and password

                    // Kurang check password
                    if(savedData[0] == etUsername.text.toString()){
                        Log.d("login", username)
                        Toast.makeText(this, "Logged In", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                        break

                    }

                    line = br.readLine()
                }

                br.close()
                mfile.close()
                fIn.close()

            }catch (e: java.lang.Exception){
                e.printStackTrace()
            }

        }

    }

    private fun updateUI(username: FirebaseUser?) {
        // store current user to global var
    }
}