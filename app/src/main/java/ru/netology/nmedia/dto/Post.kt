package ru.netology.nmedia.dto

data class Post(
    val id: Long,
    val authorName: String,
    val content: String,
    val date: String,
    var likes: Int = 1099,
    var shared: Int = 999,
    var views: Int = 8999,
    val likedByMe: Boolean = false,
    val sharedBySmb: Boolean = false
)