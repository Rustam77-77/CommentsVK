package ru.netology

data class Comments(
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = false,
    val canOpen: Boolean = false
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

data class Reposts(
    val count: Int = 0,
    val userReposted: Boolean = false
)

data class Views(
    val count: Int = 0
)

data class Post(
    val id: Int = 0,
    val ownerId: Int,
    val fromId: Int? = null,
    val createdBy: Int? = null,
    val date: Int = (System.currentTimeMillis() / 1000).toInt(),
    val text: String? = null,
    val replyOwnerId: Int? = null,
    val replyPostId: Int? = null,
    val friendsOnly: Boolean = false,
    val postType: String = "post",
    val signerId: Int? = null,
    val canPin: Boolean = false,
    val canDelete: Boolean = false,
    val canEdit: Boolean = false,
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false,
    val comments: Comments = Comments(),
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views? = null,
    val copyright: String? = null,
    val geo: String? = null
)