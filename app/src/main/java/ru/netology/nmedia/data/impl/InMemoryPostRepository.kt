package ru.netology.nmedia.data.impl

import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.dto.Post

class InMemoryPostRepository : PostRepository {

    private val posts
        get() = checkNotNull(data.value) {
        }

    override val data = MutableLiveData(
        List(10) { index ->
            Post(
                id = index + 1L,
                authorName = "Нетология. Университет интернет-профессий будущего",
                content = "Привет, это новая Нетология... пост № $index",
                date = "21 мая в 18:36",
                likes = 1099,
                shared = 999,
                views = 8999,
                likedByMe = false,
                sharedBySmb = false
            )
        }
    )

    override fun like(postId: Long) {
        data.value = posts.map {
            if (it.id != postId) it
            else it.copy(
                likedByMe = !it.likedByMe,
                likes = it.likes + if (!it.likedByMe) 1 else -1
            )
        }
    }

    override fun share(postId: Long) {
        data.value = posts.map {
            if (it.id != postId) it
            else it.copy(shared = it.shared + 1)
        }
        data.value = posts

    }
}