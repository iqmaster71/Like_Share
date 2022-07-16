package ru.netology.nmedia.data.impl

import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.dto.Post

class InMemoryPostRepository : PostRepository {

<<<<<<< HEAD
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
=======
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
>>>>>>> f3badb117893015883b127cf589bf3ae626453ce
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