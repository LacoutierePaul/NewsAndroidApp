package com.example.test_github

import ArticleViewModel
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.test_github.Utils.ArticleDAO
import com.example.test_github.Utils.ArticleDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


/* la vidéo du goat pour retrofit :
https://www.youtube.com/watch?v=5gFrXGbQsc8
 */


//const val BASE_URL = "https://jsonplaceholder.typicode.com/"
const val BASE_URL = "https://newsapi.org/v2/"

class MainActivity : AppCompatActivity() {

    //Indique les besoins réseaux de l'app
    private val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        .build()

    //Définie les fonctions appellées automatiquement en fct d'évenements réseau
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {

        //Appellé qd réseau dispo
        //Faire l'appel API
        // network is available for use
        override fun onAvailable(network: Network) {
            Log.i("main","available")
            super.onAvailable(network)
        }

        //Appellé qd caractéristiques changent ex: de 4g vers wifi
        //Probablement pas nécessaire
        // Network capabilities have changed for the network
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            val unmetered =
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
        }

        //Appellé qd réseau perdu
        //Faire la save
        // lost network connection
        override fun onLost(network: Network) {
            Log.i("main","lost")
            super.onLost(network)
        }
    }


    //Basse de donnée room, idéalement, à passer par injection de dépendance
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            ArticleDatabase::class.java,
            "articles.db"
        ).allowMainThreadQueries().build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Démarre les notifs sur l'état du réseau
        val connectivityManager = getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

        //Test de l'accès à la bdd via room
        //testRoom()

        //getMyDataTestPaul()
    }

    private fun testRoom(){

        val articleDAO = db.dao;

        var articles = ArrayList<Article>();
        var articles2 = ArrayList<Article>();
        var articles1 = ArrayList<Article>();
        var article1 = Article(source=Source("a", "sourceA"), author = "M.A", content="test",
            title="TestTitle", description = "test", publishedAt = "2023-09-10", url="testUrl",
            urlToImage = "testUrl"  );
        var article2 = Article(source=Source("b", "sourceB"), author = "M.B", content="testb",
            title="TestTitle", description = "test", publishedAt = "2023-09-11", url="testUrl",
            urlToImage = "testUrl"  );
        var article3 = Article(source=Source("c", "sourceC"), author = "M.c", content="testc",
            title="TestTitle", description = "test", publishedAt = "2023-09-16", url="testUrl",
            urlToImage = "testUrl"  );
        var article4 = Article(source=Source("d", "sourced"), author = "M.d", content="testd",
            title="TestTitle", description = "test", publishedAt = "2023-09-14", url="testUrl",
            urlToImage = "testUrl"  );

        articles.add((article1));
        articles.add((article2));
        articles1.add((article3));
        articles1.add((article4));
        articles2.add(article1);
        articles2.add(article2);

        var articlesArray = articles.toTypedArray();
        var articles2Array = articles.toTypedArray();
        var articles1Array = articles.toTypedArray();


        var returnedArticles: StateFlow<List<Article>>? = null;



        lifecycleScope.launch {

            articleDAO.upsertAllArticle(*articles2Array);

        }
        lifecycleScope.launch {

            articleDAO.getArticles().collect {
                Log.i("main",it.toString())
            }

        }

        lifecycleScope.launch {
            articleDAO.upsertAllArticle(*articles1Array);
        }








        //.stateIn(lifecycleScope, SharingStarted.Eagerly, articles2) // Passer en sharing Subscribed si possible


        //Log.i("main",returnedArticles.toString())
        //Log.i("main",returnedArticles?.value.toString())



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

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
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
                            stringBuilder.append("Image URL: ${article.urlToImage}\n")
                            stringBuilder.append("Date de publication: ${article.publishedAt}\n")
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
                        // Configurer le RecyclerView avec l'adaptateur
                        Log.d("MainActivity", "Number of articles: ${it.articles.size}")

                        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                        recyclerView.adapter = ArticleAdapter(it.articles)
                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        val txtView: TextView = findViewById(R.id.txtId)
                        val stringBuilder = StringBuilder()
                        stringBuilder.append("Statut: ${it.status}\n")
                        stringBuilder.append("Total des résultats: ${it.totalResults}\n")
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

}
