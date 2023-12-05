package com.example.test_github

import ArticleViewModel
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
// RecyclerViewFragment.kt
class RecyclerViewFragment : Fragment() ,OnItemClickListener{

    private lateinit var recyclerView: RecyclerView
    private lateinit var txtView: TextView
    private val articleViewModel: ArticleViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recycler_view, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        txtView = view.findViewById(R.id.txtId)

        // Appeler la fonction de récupération de données ici
        // Observer pour les modifications des données dans le ViewModel
        articleViewModel.articleList.observe(viewLifecycleOwner, Observer { articles ->
            recyclerView.adapter = ArticleAdapter(articles,this)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        })

        // Appeler la fonction de récupération de données depuis le ViewModel
        articleViewModel.fetchData()

        return view
    }
    override fun onItemClick(article: Article) {
        // Faire quelque chose avec l'article cliqué, par exemple ouvrir le fragment de détail
        articleViewModel.setSelectedArticle(article)
        findNavController().navigate(R.id.action_recyclerViewFragment_to_detailFragment)
    }

}
