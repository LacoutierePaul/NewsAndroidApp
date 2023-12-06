import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_github.Apiinterface
import com.example.test_github.Article
import com.example.test_github.MyDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticleViewModel : ViewModel() {
     val BASE_URL = "https://newsapi.org/v2/"
    val selectedArticle = MutableLiveData<Article>()



    fun setSelectedArticle(article: Article) {
        selectedArticle.value = article
    }

    fun getSelectedArticle(): LiveData<Article> {
        return selectedArticle
    }


    private var _articleList: MutableLiveData<List<Article>> = MutableLiveData()
    val articleList: LiveData<List<Article>>
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
                if (response.isSuccessful) {
                    val myDataItem = response.body()
                    myDataItem?.let {
                        _articleList.value = it.articles
                    }
                }
            }

            override fun onFailure(call: Call<MyDataItem>, t: Throwable) {
                Log.e("API Call", "Error: ${t.message}", t)


            }
        })
    }
}
