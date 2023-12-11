package com.example.test_github.tests


import android.net.ConnectivityManager
import android.net.Network
import junit.framework.Assert.assertEquals
import org.junit.Test
import android.util.Log

class MyClassTests {
    @Test
    fun testAddition() {
        val result = 2 + 2
        assertEquals(4, result)
    }

    /*
    @Test
    fun internet() {
        var etat =""
        etat ="available"
        val networkCallback = object : ConnectivityManager.NetworkCallback() {

            //Appellé qd réseau dispo
            //Faire l'appel API
            // network is available for use
            override fun onAvailable(network: Network) {
                Log.i("network","available")
                super.onAvailable(network)

                val etat = "available"
            }
            //Appellé qd réseau perdu
            //Faire la save
            // lost network connection
            override fun onLost(network: Network) {
                Log.i("main","network lost")
                super.onLost(network)

                val etat = "lost"
            }
        }

        assertEquals("available", etat)
    }
     */


}

