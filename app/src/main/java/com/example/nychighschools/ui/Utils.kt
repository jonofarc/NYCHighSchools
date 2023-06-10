package com.example.nychighschools.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Utils {

    fun loadCSV() {
        CoroutineScope(Dispatchers.Main).launch {
            //schools.addAll(parseCSV())
           
            // Use the parsed schools list
        }
    }

}