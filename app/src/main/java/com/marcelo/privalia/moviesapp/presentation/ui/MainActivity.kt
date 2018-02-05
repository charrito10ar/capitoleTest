package com.marcelo.privalia.moviesapp.presentation.ui

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.marcelo.privalia.moviesapp.R
import com.marcelo.privalia.moviesapp.presentation.ui.fragments.MostPopularMoviesFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import android.support.v4.widget.DrawerLayout
import android.widget.Toast
import com.marcelo.privalia.moviesapp.presentation.ui.interfaces.MainActivityView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MostPopularMoviesFragment.OnMostPopularFragmentListener, MainActivityView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        initView()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        var title = getString(R.string.app_name)
        var fragmentTag = ""

        when (item.itemId) {
            R.id.nav_most_popular -> {
                fragment = MostPopularMoviesFragment.newInstance()
                title = getString(R.string.most_popular_txt)
                fragmentTag = "MostPopular"
            }
            R.id.nav_manage -> {showMessage("Settings")}
            R.id.nav_rate -> {showMessage("Rate")}
            R.id.nav_log_out -> {showMessage("LogOut")}
        }
        this.setSectionFragment(fragment, fragmentTag, title)

        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.START)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun setSectionFragment(fragment: Fragment?, fragmentTag: String, title: String) {
        if (fragment != null) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.content_frame, fragment, fragmentTag)
            fragmentTransaction.commit()
        }
        if (supportActionBar != null) {
            supportActionBar!!.title = title
        }
    }

    override fun initView() {
        this.setSectionFragment(MostPopularMoviesFragment.newInstance(), "MostPopular", getString(R.string.most_popular_txt))
    }

    override fun onFragmentInteraction() {    }

    override fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}