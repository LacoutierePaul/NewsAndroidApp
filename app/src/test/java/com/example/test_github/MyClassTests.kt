package com.example.test_github


import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test

class MyClassTests {
    @Test
    fun testAddition() {
        val result = 2 + 2
        assertEquals(4, result)
    }


/*
    @Test
    fun addDatabase(){
        val articlesDAO: ArticleDAOImpl = ArticleDAOImpl()

        // val articleViewModel : ArticleViewModel = ArticleViewModel(, articlesDAO)

    }

 */

    @Test
    fun CheckCredentials(){
        assertTrue(checkCredentials("admin", "admin"))
    }
    private fun checkCredentials(username: String, password: String): Boolean {
        val expectedUsername = "admin" // Le nom d'utilisateur attendu
        val expectedPassword = "admin" // Le mot de passe attendu

        return expectedUsername == username && expectedPassword == password
    }

}