package com.example.test_github

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

/* la vidéo du goat pour retrofit :
https://www.youtube.com/watch?v=5gFrXGbQsc8
 */


//const val BASE_URL = "https://jsonplaceholder.typicode.com/"
//const val BASE_URL = "https://newsapi.org/v2/"

@AndroidEntryPoint
class MainActivity : AppCompatActivity()  {


    //Basse de donnée room, idéalement, à passer par injection de dépendance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)

        //Test de l'accès à la bdd via room
        //testRoom()

        //getMyDataTestPaul()
    }

    /*
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



    }*/

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

    /*
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
    */

    /*private fun getMyDataTestPaul() {
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
    }*/

}
