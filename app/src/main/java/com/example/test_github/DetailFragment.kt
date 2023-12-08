package com.example.test_github

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide

// DetailFragment.kt
class DetailFragment : Fragment() {

    private lateinit var titleTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var authorSourceTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var urlTextView: TextView
    private val articleViewModel: ArticleViewModel by activityViewModels()
    private lateinit var paragraphTextView:TextView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        titleTextView = view.findViewById(R.id.titleTextView)
        imageView = view.findViewById(R.id.imageView)
        authorSourceTextView = view.findViewById(R.id.authorSourceTextView)
        dateTextView = view.findViewById(R.id.dateTextView)
        descriptionTextView = view.findViewById(R.id.descriptionTextView)
        urlTextView = view.findViewById(R.id.urlTextView)
        paragraphTextView = view.findViewById(R.id.paragraphTextView)


        // Use the selected article in your UI
        titleTextView.text = articleViewModel.getSelectedArticle().value?.title
        if(articleViewModel.getSelectedArticle().value?.urlToImage==null)
        {
            Glide.with(view.context).load("https://cdn.vectorstock.com/i/preview-1x/82/99/no-image-available-like-missing-picture-vector-43938299.jpg").fitCenter().into(imageView)

        } else {
            Glide.with(view.context).load(articleViewModel.getSelectedArticle().value?.urlToImage)
                .fitCenter().into(imageView)
        }
        authorSourceTextView.text = "Author : "+articleViewModel.getSelectedArticle().value?.author+"\nSource : "+articleViewModel.getSelectedArticle().value?.source?.name
        dateTextView.text = "Published at " + (articleViewModel.getSelectedArticle().value?.publishedAt?.substring(0,10))
        descriptionTextView.text = "Description : " +articleViewModel.getSelectedArticle().value?.description
        paragraphTextView.text="Content : "+articleViewModel.getSelectedArticle().value?.content
        urlTextView.text = "To view more ... : "+articleViewModel.getSelectedArticle().value?.url




        // Autre manière de récupere les données avec des observes
       /*
        articleViewModel.selectedArticle.observe(this.viewLifecycleOwner) {
            authorSourceTextView.text = (it.author)
        }
        articleViewModel.getSelectedArticle().observe(this.viewLifecycleOwner) {
            descriptionTextView.text = (it.description)
        }*/

        return view
    }
}
