package com.example.test_github

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController


/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isUserLoggedIn()) {

            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }

    private fun isUserLoggedIn(): Boolean {
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("username", null)
        val savedPassword = sharedPreferences.getString("password", null)

        // Vérifiez si les informations d'identification sont présentes dans SharedPreferences
        return savedUsername != null && savedPassword != null
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val buttonLogin = view.findViewById<Button>(R.id.buttonLogin)
        val editTextUsername = view.findViewById<EditText>(R.id.editTextUsername)
        val editTextPassword = view.findViewById<EditText>(R.id.editTextPassword)

        buttonLogin.setOnClickListener {
            val enteredUsername = editTextUsername.text.toString()
            val enteredPassword = editTextPassword.text.toString()

            if (checkCredentials(enteredUsername, enteredPassword)) {
                saveCredentials()
                findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
            } else {
                // Afficher un message d'erreur ou prendre d'autres mesures en cas d'informations d'identification incorrectes
                Toast.makeText(requireContext(), "Invalid credentials", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun checkCredentials(username: String, password: String): Boolean {
        val expectedUsername = "admin" // Le nom d'utilisateur attendu
        val expectedPassword = "admin" // Le mot de passe attendu

        return expectedUsername == username && expectedPassword == password
    }

    private fun saveCredentials() {
        // Enregistrez les informations d'identification dans SharedPreferences ici
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", "admin")
        editor.putString("password", "admin")
        editor.apply()
    }
}