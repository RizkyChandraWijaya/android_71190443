package id.ac.ukdw.pertemuan10_71190443

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import id.ac.ukdw.pertemuan10_71190443.DatabaseContract.Penduduk.COLUMN_NAME_NAMA

class MainActivity : AppCompatActivity() {
    lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dbHelper = DatabaseHelper(this)
        db = dbHelper.writableDatabase
        val etNama = findViewById<EditText>(R.id.etNama)
        val etUsia = findViewById<EditText>(R.id.etUsia)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnHapus = findViewById<Button>(R.id.btnHapus)
        val btnCari = findViewById<Button>(R.id.btnCari)
        val btnLihatData = findViewById<Button>(R.id.btnLihatData)
        val tvHasil = findViewById<TextView>(R.id.tvHasil)

        btnSimpan.setOnClickListener {
            var nama = etNama.text.toString()
            var usia = etUsia.text.toString()
            if (nama.length < 1){
                nama = "unkown"
            }
            if(usia.length<1){
                usia = "0"
            }

            val insert = insertData(Penduduk(nama, usia.toInt()))
            etNama.setText("")
            etUsia.setText("")
            if(insert.hashCode()>0){
                Toast.makeText(this, "Data dengan nama ${nama} berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
            tvHasil.text = ""
        }

        btnCari.setOnClickListener {
            var nama = etNama.text.toString()
            var usia = etUsia.text.toString()
            if (nama.length<1 || usia.length<1){
                Toast.makeText(this, "Masukkan nama dan usia terlebih dahulu", Toast.LENGTH_SHORT).show()
            }else{
                val search = searchData(Penduduk(nama, usia.toInt()))
                if(search.hashCode()<1){
                    Toast.makeText(this, "Pencarian tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
                etNama.setText("")
                etUsia.setText("")
            }
        }

        btnLihatData.setOnClickListener {
            getData()
        }

        btnHapus.setOnClickListener {
            var nama = etNama.text.toString()
            var usia = etUsia.text.toString()
            if (nama.length < 1){
                nama = "unkown"
            }
            if(usia.length<1){
                usia = "0"
            }
            val delete = deleteData(Penduduk(nama, usia.toInt()))
            if(delete.hashCode()>0){
                Toast.makeText(this, "Data dengan nama ${nama} berhasil dihapus", Toast.LENGTH_SHORT).show()
            }
            etNama.setText("")
            etUsia.setText("")
            tvHasil.text = ""
        }
    }

    fun insertData(penduduk:Penduduk){
        val values = ContentValues().apply {
            put(DatabaseContract.Penduduk.COLUMN_NAME_NAMA,penduduk.nama)
            put(DatabaseContract.Penduduk.COLUMN_NAME_USIA,penduduk.usia)
            }
        db.insert(DatabaseContract.Penduduk.TABLE_NAME, null, values)

        }

    fun searchData(penduduk:Penduduk){
        val selection = "${DatabaseContract.Penduduk.COLUMN_NAME_NAMA} LIKE ? AND "+
                "${DatabaseContract.Penduduk.COLUMN_NAME_USIA} LIKE ?"
        val selectionArgs = arrayOf(penduduk.nama, penduduk.usia.toString())

        val columns = arrayOf(
            DatabaseContract.Penduduk.COLUMN_NAME_NAMA,
            DatabaseContract.Penduduk.COLUMN_NAME_USIA
        )

        val cursor = db.query(
            DatabaseContract.Penduduk.TABLE_NAME,
            columns,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var result = ""
        with(cursor) {
            while (moveToNext()) {
                val penduduk = Penduduk(getString(0),getInt(1))
                result += "${penduduk.nama} - ${penduduk.usia} \n"
            }
        }

        val tvHasil = findViewById<TextView>(R.id.tvHasil)
        tvHasil.text = result

    }

    fun deleteData(penduduk:Penduduk){
        val selection = "${DatabaseContract.Penduduk.COLUMN_NAME_NAMA} LIKE ? AND "+
                "${DatabaseContract.Penduduk.COLUMN_NAME_USIA} LIKE ?"
        val selectionArgs = arrayOf(penduduk.nama, penduduk.usia.toString())
        val deletedRows = db.delete(DatabaseContract.Penduduk.TABLE_NAME, selection, selectionArgs)

    }

    fun getData() {
        val columns = arrayOf(
            DatabaseContract.Penduduk.COLUMN_NAME_NAMA,
            DatabaseContract.Penduduk.COLUMN_NAME_USIA
        )

        val cursor = db.query(
            DatabaseContract.Penduduk.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )

        var result = ""
        with(cursor) {
            while (moveToNext()) {
                val penduduk = Penduduk(getString(0),getInt(1))
                result += "${penduduk.nama} - ${penduduk.usia} \n"
            }
        }

        val tvHasil = findViewById<TextView>(R.id.tvHasil)
        tvHasil.text = result
    }
}
