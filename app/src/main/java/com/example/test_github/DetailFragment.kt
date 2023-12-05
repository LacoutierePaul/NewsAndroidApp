package com.example.test_github

import ArticleViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
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

        // Use the selected article in your UI
        titleTextView.text = articleViewModel.getSelectedArticle().value?.title
        Glide.with(view.context).load(articleViewModel.getSelectedArticle().value?.urlToImage).fitCenter().into(imageView)
        authorSourceTextView.text = articleViewModel.getSelectedArticle().value?.author
        dateTextView.text = articleViewModel.getSelectedArticle().value?.publishedAt
        descriptionTextView.text = articleViewModel.getSelectedArticle().value?.description
        urlTextView.text = articleViewModel.getSelectedArticle().value?.url



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
