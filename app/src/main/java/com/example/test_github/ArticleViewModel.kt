package com.example.test_github

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.test_github.Fragments.Apiinterface
import com.example.test_github.Utils.ArticleDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val database: ArticleDatabase,
    private val connectivityManager: ConnectivityManager
): ViewModel() {
    val BASE_URL = "https://newsapi.org/v2/"
    val selectedArticle = MutableLiveData<Article>()
    val connectionState = MutableLiveData<String>()
    var apiCallSuccess = false
    var connectionUp = false
    var skippedStartUpNetworkState = false
    var selectedCategory = "business"
    var selectedLanguage = "us"
    val articlesDAO = database.dao


    //Indique les besoins réseaux de l'app
    val networkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        .build()

    //Définie les fonctions appellées automatiquement en fct d'évenements réseau
    val networkCallback = object : ConnectivityManager.NetworkCallback() {

        //Appellé qd réseau dispo
        //Faire l'appel API
        // network is available for use
        override fun onAvailable(network: Network) {
            Log.i("network","available")
            super.onAvailable(network)

            OnNetworkAvailable()

        }

        //Appellé qd réseau perdu
        //Faire la save
        // lost network connection
        override fun onLost(network: Network) {
            Log.i("main","network lost")
            super.onLost(network)


            OnNetworkLost()

        }
    }


    init {

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)

    }

    override fun onCleared() {
        super.onCleared()

        connectivityManager.unregisterNetworkCallback(networkCallback)

    }

    fun OnNetworkAvailable(){


        connectionUp = true

        connectionState.postValue("online")

        Log.i("networkAvailable","fetching data")

        fetchData(selectedCategory, selectedLanguage);
        Log.i("networkAvailable","api call success: " + apiCallSuccess)

        //if(articleViewModel.apiCallSuccess){
        //    previousCategory = selectedCategory
        //}
        //else{
        //    selectedCategory = previousCategory
        //}



        Log.i("networkAvailable","api call success: " + apiCallSuccess)
    }

    fun OnNetworkLost(){

        connectionUp = false

        connectionState.postValue("offline")


        if(!apiCallSuccess){

            Log.i("main","get from bdd : "+apiCallSuccess)

            fetchData(selectedCategory, selectedLanguage)

        }
        if(apiCallSuccess){

            Log.i("main","save articles : "+apiCallSuccess)

            saveToDB(selectedCategory, selectedLanguage)

        }
    }

    fun fetchFromDB(category: String, language: String){

        lateinit var listArticleDatabase: MutableList<Article>

        viewModelScope.launch(context = Dispatchers.IO) {

            val listArticleDB=  articlesDAO.getArticlesCategoryLanguage(category, language)


            listArticleDatabase = ArticleDB.toArticleList(listArticleDB)

            Log.i("RecView", listArticleDB.toString())

            _articleList.postValue(listArticleDatabase)

        }

    }

    fun saveToDB(category: String, language: String){

        val articlesList = articleList.value

        //Log.i("save", articlesList.toString())

        viewModelScope.launch(context = Dispatchers.IO) {

            if(articlesList != null){
                articlesDAO.upsertAllArticle(*Article.toArticleDBList(
                    articlesList,
                    category,
                    language
                ).toTypedArray());
            }

        }

    }


    fun setSelectedArticle(article: Article) {
        selectedArticle.value = article
    }

    fun getSelectedArticle(): LiveData<Article> {
        return selectedArticle
    }


    var _articleList: MutableLiveData<MutableList<Article>> = MutableLiveData()

    val articleList: LiveData<MutableList<Article>>
        get() = _articleList


    fun fetchData(category:String,country:String) {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiinterface::class.java)


        val call = retrofit.getData(country = country, category = category, apiKey = "150c1cb0b4e644e98f794f4d1a14be2e")

        call.enqueue(object : Callback<MyDataItem> {
            override fun onResponse(call: Call<MyDataItem>, response: Response<MyDataItem>) {
                Log.i("API Call", "On response")
                if (response.isSuccessful) {
                    Log.i("API Call", "Success")

                    val myDataItem = response.body()
                    myDataItem?.let {
                        _articleList.value = it.articles
                    }
                    apiCallSuccess = true
                    Log.i("API Call", apiCallSuccess.toString())

                }
            }

            override fun onFailure(call: Call<MyDataItem>, t: Throwable) {
                Log.e("API Call", "Error: ${t.message}", t)

            }
        })

    }

    fun post() {
        val newArticle = ArticleDB(
            source = Source(sourceId = "sourceId", name = "SourceName"),
            author = "Auteur",
            title = "Nouvel Article",
            description = "Description de l'article",
            url = "urltestbalalal",
            urlToImage = "Url de l'image",
            publishedAt = "12:12:2023:11321",
            content = "Contenu de l'article",
            category = "general",
            language = "us"
        )
        viewModelScope.launch(context=Dispatchers.IO){
            articlesDAO.upsertArticle(newArticle)
        }
        Log.i("ADD" ,"article Added")
    }
}
