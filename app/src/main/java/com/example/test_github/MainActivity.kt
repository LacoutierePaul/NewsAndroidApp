package com.example.test_github

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/* la vidéo du goat pour retrofit :
https://www.youtube.com/watch?v=5gFrXGbQsc8
 */


//const val BASE_URL = "https://jsonplaceholder.typicode.com/"
const val BASE_URL = "https://newsapi.org/v2/"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getMyData()
    }

    /*
    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(Apiinterface::class.java)

        val retrofitData =retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>
            ) {
                val responseBody = response.body()!!

                val myStringBuilder = StringBuilder()
                for(myData in responseBody){ // C'est la qu'on met nos infos

                    myStringBuilder.append(myData.id)
                    myStringBuilder.append("\n")
                    myStringBuilder.append(myData.title)
                    myStringBuilder.append("\n")

                }
                // on change un peu de la vidéo ici
                val txtView: TextView = findViewById(R.id.txtId)
                txtView.text = myStringBuilder

            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: "+t.message)
            }
        })

    }

     */

    private fun getMyData() {
        // Initialisation de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiinterface::class.java)


        // Appel de la méthode de l'API pour obtenir les données
        val call = retrofit.getData()

        // Envoi de la requête de manière asynchrone
        call.enqueue(object : Callback<MyDataItem> {
            override fun onResponse(call: Call<MyDataItem>, response: Response<MyDataItem>) {
                if (response.isSuccessful) {
                    // Si la réponse est réussie, traiter les données ici
                    val myDataItem = response.body()
                    myDataItem?.let {
                        // Afficher le statut et le totalResults dans un TextView
                        val txtView: TextView = findViewById(R.id.txtId)
                        val stringBuilder = StringBuilder()
                        stringBuilder.append("Statut: ${it.status}\n")
                        stringBuilder.append("Total des résultats: ${it.totalResults}\n")

                        // Afficher les données de chaque article
                        stringBuilder.append("\nArticles:\n")
                        for (article in it.articles) {
                            stringBuilder.append("Source: ${article.source.name}\n")
                            stringBuilder.append("Auteur: ${article.author}\n")
                            stringBuilder.append("Titre: ${article.title}\n")
                            stringBuilder.append("Description: ${article.description}\n")
                            stringBuilder.append("URL: ${article.url}\n")
                            stringBuilder.append("Image URL: ${article.imageUrl}\n")
                            stringBuilder.append("Date de publication: ${article.publishDate}\n")
                            stringBuilder.append("Contenu: ${article.content}\n\n")
                        }

                        // Afficher le résultat dans le TextView
                        txtView.text = stringBuilder.toString()
                    }
                } else {
                    // Si la réponse n'est pas réussie, afficher un message d'erreur
                    val txtView: TextView = findViewById(R.id.txtId)
                    txtView.text = "Échec de la récupération des données"
                }
            }

            override fun onFailure(call: Call<MyDataItem>, t: Throwable) {
                // En cas d'échec de la requête, afficher un message d'erreur
                val txtView: TextView = findViewById(R.id.txtId)
                txtView.text = "Erreur : ${t.message}"
            }
        })
    }


    private fun getMyDataTestPaul() {
        // Initialisation de Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiinterface::class.java)

        // Appel de la méthode de l'API pour obtenir les données
        val call = retrofit.getData()

        // Envoi de la requête de manière asynchrone
        call.enqueue(object : Callback<MyDataItem> {
            override fun onResponse(call: Call<MyDataItem>, response: Response<MyDataItem>) {
                if (response.isSuccessful) {
                    // Si la réponse est réussie, traiter les données ici
                    val myDataItem = response.body()
                    myDataItem?.let {
                        // Afficher ou traiter les données comme nécessaire
                        // ...
                    }
                } else {
                    // Si la réponse n'est pas réussie, afficher un message d'erreur
                    val txtView: TextView = findViewById(R.id.txtId)
                    txtView.text = "Échec de la récupération des données"
                }
            }

            override fun onFailure(call: Call<MyDataItem>, t: Throwable) {
                // En cas d'échec de la requête, afficher un message d'erreur
                val txtView: TextView = findViewById(R.id.txtId)
                txtView.text = "Erreur : ${t.message}"
            }
        })
    }

}
