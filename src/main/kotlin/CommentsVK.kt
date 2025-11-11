package ru.netology

// Класс для комментариев
data class Comments(
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = false,
    val canOpen: Boolean = false
)

// Класс для лайков
data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

// Класс для репостов
data class Reposts(
    val count: Int = 0,
    val userReposted: Boolean = false
)

// Класс для просмотров
data class Views(
    val count: Int = 0
)

// Основной класс поста
data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val createdBy: Int = 0,
    val date: Int = 0,
    val text: String = "",
    val replyOwnerId: Int = 0,
    val replyPostId: Int = 0,
    val friendsOnly: Boolean = false,
    val postType: String = "post",
    val signerId: Int = 0,
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val comments: Comments = Comments(),
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views = Views()
)

// Сервис для работы с постами
object WallService {
    private var posts = emptyArray<Post>()
    private var lastId = 0

    fun add(post: Post): Post {
        val newPost = post.copy(id = ++lastId)
        posts += newPost
        return newPost
    }

    fun printAll() {
        for (post in posts) {
            println(post)
        }
    }

    fun clear() {
        posts = emptyArray()
        lastId = 0
    }
}

// Функция для запуска программы
fun main() {
    val post1 = Post(
        ownerId = 12345,
        fromId = 12345,
        text = "Первый пост в системе!",
        friendsOnly = false,
        comments = Comments(count = 5, canPost = true),
        likes = Likes(count = 42, userLikes = true)
    )

    val post2 = Post(
        ownerId = 67890,
        text = "Второй пост с минимальными параметрами"
    )

    val post3 = Post(
        ownerId = 11111,
        fromId = 11111,
        text = "Третий пост с репостами и просмотрами",
        reposts = Reposts(count = 10, userReposted = false),
        views = Views(count = 150)
    )

    println("=== Добавление постов ===")
    val addedPost1 = WallService.add(post1)
    println("Добавлен пост с ID: ${addedPost1.id}")

    val addedPost2 = WallService.add(post2)
    println("Добавлен пост с ID: ${addedPost2.id}")

    val addedPost3 = WallService.add(post3)
    println("Добавлен пост с ID: ${addedPost3.id}")

    println("\n=== Все посты ===")
    WallService.printAll()
}