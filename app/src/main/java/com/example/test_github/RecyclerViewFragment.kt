package com.example.test_github

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
// RecyclerViewFragment.kt
class RecyclerViewFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var txtView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recycler_view, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        txtView = view.findViewById(R.id.txtId)

        // Appeler la fonction de récupération de données ici
        getMyDataTestPaul()

        return view
    }

    private fun getMyDataTestPaul() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiinterface::class.java)

        val call = retrofit.getData()

        call.enqueue(object : Callback<MyDataItem> {
            override fun onResponse(call: Call<MyDataItem>, response: Response<MyDataItem>) {
                if (response.isSuccessful) {
                    val myDataItem = response.body()
                    myDataItem?.let {
                        Log.d("RecyclerViewFragment", "Number of articles: ${it.articles.size}")

                        recyclerView.adapter = ArticleAdapter(it.articles)
                        recyclerView.layoutManager = LinearLayoutManager(requireContext())

                        val stringBuilder = StringBuilder()
                        stringBuilder.append("Statut: ${it.status}\n")
                        stringBuilder.append("Total des résultats: ${it.totalResults}\n")
                        txtView.text = stringBuilder.toString()
                    }
                } else {
                    txtView.text = "Échec de la récupération des données"
                }
            }

            override fun onFailure(call: Call<MyDataItem>, t: Throwable) {
                txtView.text = "Erreur : ${t.message}"
            }
        })
    }
}
