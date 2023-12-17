package com.example.test_github

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.test_github.Utils.ArticleDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

class newPostFragment : Fragment() {
    private val articleViewModel: ArticleViewModel by activityViewModels()
    private lateinit var articleDAO: ArticleDAO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnPost: Button = view.findViewById(R.id.btnPost)
        val etTitle=view.findViewById<EditText>(R.id.etTitle)
        val etAuthor=view.findViewById<EditText>(R.id.etAuthor)
        val etSource=view.findViewById<EditText>(R.id.etSource)
        val etDescription=view.findViewById<EditText>(R.id.etDescription)
        val etContent=view.findViewById<EditText>(R.id.etContent)
        val categorySpinner = view.findViewById<Spinner>(R.id.categorySpinner)
        val languageSpinner = view.findViewById<Spinner>(R.id.languageSpinner)
        val etUrl=view.findViewById<EditText>(R.id.etUrl)

        btnPost.setOnClickListener {
            // Récupérer les informations saisies par l'utilisateur
            val title = etTitle.text.toString()
            val author=etAuthor.text.toString()
            val source=etSource.text.toString()
            val content = etContent.text.toString()
            val description=etDescription.text.toString()
            val currentDate = SimpleDateFormat("dd:MM:yyyy:HHmm").format(Date())
            val category =categorySpinner.selectedItem.toString().lowercase()
            val language=languageSpinner.selectedItem.toString().lowercase()
            val url=etUrl.text.toString()

            // Vérifier que toutes les informations nécessaires sont fournies
            if (title.isNotEmpty() && content.isNotEmpty() && description.isNotEmpty() && author.isNotEmpty() && source.isNotEmpty() && url.isNotEmpty()){
                articleDAO = articleViewModel.articlesDAO
                // Créer un nouvel article avec les informations saisies
                val article = ArticleDB(
                    // Les informations du constructeur peuvent être ajustées selon votre modèle
                    Source("",source),
                    author,
                    title,
                    description,
                    url,null,
                    currentDate,
                    content,
                    category,
                    language
                )
                Toast.makeText(requireContext(), "Article posté avec succès", Toast.LENGTH_SHORT).show()
                // Utiliser un CoroutineScope pour effectuer l'opération de publication de manière asynchrone
                lifecycleScope.launch(context = Dispatchers.IO) {
                    // Appeler la fonction d'insertion/upsert dans la base de données
                    articleDAO.upsertArticle(article)
                    // Afficher un message dans les logs une fois l'opération terminée
                    Log.i("PostFragment", "Article posté avec succès")

                }
                findNavController().navigate(R.id.action_newPostFragment_to_mainFragment)

            } else {
                // Afficher un message d'erreur si toutes les informations ne sont pas fournies
                Toast.makeText(requireContext(), "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
