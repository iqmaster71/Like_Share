package ru.netology.nmedia.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.dto.Post

class InMemoryPostRepository : PostRepository {

    private var post = Post(
            id = 1,
            authorName = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            date = "21 мая в 18:36",
            likes = 1099,
            shared = 999,
            views = 8999,
            likedByMe = false,
            sharedBySmb = false
        )
    override val data = MutableLiveData(post)
    fun get(): LiveData<Post> = data

    override fun like() {
        val currentPost = checkNotNull(data.value) {
        }
        val likedPost = currentPost.copy(likedByMe = !currentPost.likedByMe)
        if (likedPost.likedByMe) likedPost.likes++ else likedPost.likes--
        data.value = likedPost
    }

    override fun share() {
        val currentPost = checkNotNull(data.value) {
        }
        val sharePost = currentPost.copy(shared = post.shared++)
        data.value = sharePost
    }
}