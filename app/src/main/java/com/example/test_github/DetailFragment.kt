package com.example.test_github

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

// DetailFragment.kt
class DetailFragment : Fragment() {

    private lateinit var titleTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var authorSourceTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var urlTextView: TextView

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

        descriptionTextView.text = "coucou"

        return view
    }
}
