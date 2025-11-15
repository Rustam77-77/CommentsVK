package ru.netology

object WallService {
    private var posts = emptyArray<Post>()
    private var lastId = 0

    fun add(post: Post): Post {
        val newPost = post.copy(id = ++lastId)
        posts += newPost
        return newPost
    }

    fun update(post: Post): Boolean {
        for ((index, existingPost) in posts.withIndex()) {
            if (existingPost.id == post.id) {
                posts[index] = post.copy(
                    ownerId = existingPost.ownerId,
                    date = existingPost.date
                )
                return true
            }
        }
        return false
    }

    fun clear() {
        posts = emptyArray()
        lastId = 0
    }

    fun printAll() {
        for (post in posts) {
            println(post)
        }
    }
}