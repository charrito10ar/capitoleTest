package com.marcelo.privalia.moviesapp.presentation.ui.interfaces

import android.support.v4.app.Fragment

interface MainActivityView{
    fun initView()
    fun setSectionFragment(fragment: Fragment?, fragmentTag: String, title: String)
    fun showMessage(message: String)
}
