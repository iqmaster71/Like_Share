package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val post = Post(
            id = 1,
            authorName = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            date = "21 мая в 18:36",
            likes = 1099,
            shared = 999,
            views = 8999,
            likedByMe = false
        )
        with(binding) {
            authorName.text = post.authorName
            content2.text = post.content
            date.text = post.date
            if (post.likedByMe) {
                favorite.setImageResource(R.drawable.ic_favorite_red_24)
            }

            countLike.text = formatCount(post.likes)
            countShare.text = formatCount(post.shared)
            countVisibility.text = formatCount(post.views)

            favorite.setOnClickListener {
                post.likedByMe = !post.likedByMe
                favorite.setImageResource(
                    if (post.likedByMe) R.drawable.ic_favorite_red_24 else R.drawable.ic_baseline_favorite_border_24
                )

                if (post.likedByMe) post.likes++ else post.likes--
                countLike.text = formatCount(post.likes)
            }

            share.setOnClickListener {
                countShare.text = formatCount(post.shared++)
            }

            visibility.setOnClickListener {
                countVisibility.text = formatCount(post.views++)
            }

            root.setOnClickListener {
                val toast =
                    Toast.makeText(applicationContext, "root element click", Toast.LENGTH_SHORT)
                toast.show()
            }

            avatar.setOnClickListener {
                val toast = Toast.makeText(
                    applicationContext,
                    "author image view click",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }

            foreground.setOnClickListener {
                val toast = Toast.makeText(
                    applicationContext,
                    "options image view click",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }

            content2.setOnClickListener {
                val toast =
                    Toast.makeText(applicationContext, "content view click", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
    }

    private fun formatCount(number: Int): String {
        return when (number) {
            in 0..999 -> "$number"
            in 1000..999_999 -> "${(kotlin.math.floor(number.toDouble() / 100) / 10)}K"
            in 1_000_000..999_999_999 -> "${(kotlin.math.floor(number.toDouble() / 100_000) / 10)}B"
            else -> "${(kotlin.math.floor(number.toDouble() / 100_000_000) / 10)}T"
        }
    }
}