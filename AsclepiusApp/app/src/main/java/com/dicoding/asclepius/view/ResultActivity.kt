package com.dicoding.asclepius.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.dicoding.asclepius.R
import com.dicoding.asclepius.data.Result
import com.dicoding.asclepius.databinding.ActivityResultBinding
import com.dicoding.asclepius.view.history.HistoryViewModel
import java.text.NumberFormat

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var imageUri: Uri
    private lateinit var resultLabel: String
    private var resultScore: Float = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        historyViewModel = ViewModelProvider(this).get(HistoryViewModel::class.java)

        imageUri = Uri.parse(intent.getStringExtra(EXTRA_IMAGE_URI))
        resultLabel = if (intent.getStringExtra(EXTRA_RESULT_LABEL) != null) intent.getStringExtra(
            EXTRA_RESULT_LABEL
        )!! else ""
        resultScore = intent.getFloatExtra(EXTRA_RESULT_SCORE, 0f)
        showResult(imageUri, resultLabel!!, resultScore)
    }

    private fun showResult(imageUri: Uri?, resultLabel: String, resultScore: Float) {
        binding.resultImage.setImageURI(imageUri)
        binding.resultText.text = "$resultLabel " + NumberFormat.getPercentInstance()
            .format(resultScore).trim()
    }


    override fun onPause() {
        super.onPause()
        historyViewModel.insert(
            Result(
                image = imageUri.toString(),
                label = resultLabel,
                score = resultScore
            )
        )
    }

    companion object {
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_RESULT_LABEL = "extra_result_label"
        const val EXTRA_RESULT_SCORE = "extra_result_score"
    }


}