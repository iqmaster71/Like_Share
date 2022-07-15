package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.viewModel.PostViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<PostViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.data.observe(this) { post -> binding.render(post) }

        binding.favorite.setOnClickListener {
            viewModel.likeButtonClicked()
        }

        binding.share.setOnClickListener {
            viewModel.shareButtonClicked()
        }
    }

    private fun ActivityMainBinding.render(post: Post) {
        authorName.text = post.authorName
        content2.text = post.content
        date.text = post.date
        countLike.text = formatCount(post.likes)
        countShare.text = formatCount(post.shared)
        countVisibility.text = formatCount(post.views)
        favorite.setImageResource(getLikeIconResId(post.likedByMe))

    }

    @DrawableRes
    private fun getLikeIconResId(liked: Boolean) =
        if (liked) R.drawable.ic_favorite_red_24 else R.drawable.ic_baseline_favorite_border_24

    private fun formatCount(number: Int): String {
        return when (number) {
            in 0..999 -> "$number"
            in 1000..999_999 -> "${(kotlin.math.floor(number.toDouble() / 100) / 10)}K"
            in 1_000_000..999_999_999 -> "${(kotlin.math.floor(number.toDouble() / 100_000) / 10)}B"
            else -> "${(kotlin.math.floor(number.toDouble() / 100_000_000) / 10)}T"
        }
    }
}