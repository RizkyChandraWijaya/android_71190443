package id.ac.ukdw.pertemuan8_71190443

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //action bar
        setSupportActionBar(findViewById(R.id.tooblar_default))
//        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setTitle("Instagram KW")
        supportActionBar?.setSubtitle("by: Chandra W")

        //viewpager
        val viewPager = findViewById<ViewPager2>(R.id.pager)
        val listFragment: ArrayList<Fragment> = arrayListOf(FragmentSatu(), FragmentDua(),FragmentTiga())
        val pagerAdapter = PagerAdapter(this, listFragment)
        viewPager.adapter = pagerAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_default, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_message -> {
            val intent = Intent(this, MessageActivity::class.java)
            startActivity(intent)
            true
        }
        R.id.menu_profile -> {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            true
            }
        R.id.menu_settings -> {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
            true
            }
        else -> {
            super.onOptionsItemSelected(item)
            }
        }

    class PagerAdapter(val activity: AppCompatActivity, val listFragment: ArrayList<Fragment>) : FragmentStateAdapter(activity) {
        override fun getItemCount(): Int = listFragment.size
        override fun createFragment(position: Int): Fragment = listFragment.get(position)
        }
}