package com.marcelo.privalia.moviesapp.domain.model

import java.text.SimpleDateFormat
import java.util.*

class Movie(val title: String?, val overview: String?, val posterPath: String?, private val releaseDate: String?){

    private var simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    fun getReleaseYear(): Int{
        return if(releaseDate.equals("") || releaseDate.isNullOrBlank()){
            0
        }else{
            val date = simpleDateFormat.parse(releaseDate)
            val calendar = Calendar.getInstance()
            calendar.time = date
            calendar.get(Calendar.YEAR)
        }
    }
}