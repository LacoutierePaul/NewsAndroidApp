package com.example.test_github

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.*


/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

// RecyclerViewFragment.kt
class RecyclerViewFragment: Fragment(), OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var txtView: TextView
    private lateinit var connectionTxt: TextView
    private lateinit var categorySpinner: Spinner
    private lateinit var languageSpinner: Spinner
    private val articleViewModel: ArticleViewModel by activityViewModels()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_recycler_view, container, false)

        //articleViewModel = ViewModelProvider(this).get(ArticleViewModel::class.java)

        recyclerView = view.findViewById(R.id.recyclerView)
        txtView = view.findViewById(R.id.txtId)
        connectionTxt = view.findViewById(R.id.connectionTxt)

        connectionTxt.text = if(articleViewModel.connectionUp) "online" else "offline"

        categorySpinner = view.findViewById(R.id.categorySpinner)
        languageSpinner = view.findViewById(R.id.languageSpinner)


        //set up Category spinner
        val categories = resources.getStringArray(R.array.categories)
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = spinnerAdapter

        //set up languages spinner
        val languages = resources.getStringArray(R.array.language)
        val languageAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        languageSpinner.adapter = languageAdapter

        // Appeler la fonction de récupération de données ici
        // Observer pour les modifications des données dans le ViewModel
        articleViewModel.articleList.observe(viewLifecycleOwner, Observer { articles ->
            Log.i("RecyclerViewFragment", "data changed")
            recyclerView.adapter = ArticleAdapter(articles,this)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        })

        articleViewModel.connectionState.observe(viewLifecycleOwner, Observer {
            connectionTxt.text = it
        })

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {

                Log.i("main","category selected")

                articleViewModel.selectedCategory = categories[position]
                articleViewModel.selectedLanguage = languageSpinner.selectedItem.toString().lowercase()

                if(articleViewModel.connectionUp){
                    Log.i("category","connection up fetching data")
                    articleViewModel.fetchData(articleViewModel.selectedCategory,articleViewModel.selectedLanguage)

                }
                else{
                    //accès bdd

                    Log.i("category","connection down fetching data from bdd")

                    articleViewModel.fetchFromDB(articleViewModel.selectedCategory,articleViewModel.selectedLanguage)

                }


            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                Log.i("main","language selected")


                articleViewModel.selectedLanguage = languages[position].lowercase()
                articleViewModel.selectedCategory = categorySpinner.selectedItem.toString()


                if(articleViewModel.connectionUp){
                    Log.i("language","connection up fetching data")
                    articleViewModel.fetchData(articleViewModel.selectedCategory, articleViewModel.selectedLanguage)

                    //if(articleViewModel.apiCallSuccess){
                    //    previousCategory = selectedCategory
                    //}
                    //else{
                    //    selectedCategory = previousCategory
                    //}

                }
                else{
                    Log.i("language","connection down fetching data from bdd")

                    articleViewModel.fetchFromDB(articleViewModel.selectedCategory, articleViewModel.selectedLanguage)

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Peut être laissé vide si nécessaire
            }
        }

        // Appeler la fonction de récupération de données depuis le ViewModel
        articleViewModel.fetchData("business","us")

        return view
    }


    override fun onItemClick(article: Article) {
        // Faire quelque chose avec l'article cliqué, par exemple ouvrir le fragment de détail
        articleViewModel.setSelectedArticle(article)
        findNavController().navigate(R.id.action_recyclerViewFragment_to_detailFragment)
    }

}
