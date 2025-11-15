package ru.netology

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class WallServiceTest {

    @BeforeEach
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun addPostShouldAssignId() {
        val post = Post(
            ownerId = 12345,
            text = "Тестовый пост"
        )

        val addedPost = WallService.add(post)

        assertNotEquals(0, addedPost.id)
    }

    @Test
    fun addPostWithNullableFields() {
        val post = Post(
            ownerId = 100,
            text = null,
            fromId = null,
            views = null,
            copyright = null
        )

        val addedPost = WallService.add(post)

        assertNotEquals(0, addedPost.id)
        assertNull(addedPost.text)
        assertNull(addedPost.fromId)
        assertNull(addedPost.views)
    }

    @Test
    fun updateExisting() {
        WallService.add(Post(ownerId = 1, text = "Первый пост"))
        WallService.add(Post(ownerId = 2, text = "Второй пост"))
        val addedPost = WallService.add(Post(ownerId = 3, text = "Третий пост"))

        val update = addedPost.copy(text = "Обновленный третий пост")
        val result = WallService.update(update)

        assertTrue(result)
    }

    @Test
    fun updateWithNullText() {
        val addedPost = WallService.add(Post(ownerId = 1, text = "Оригинал"))

        val update = addedPost.copy(text = null)
        val result = WallService.update(update)

        assertTrue(result)
    }

    @Test
    fun updateNonExisting() {
        WallService.add(Post(ownerId = 1, text = "Первый пост"))
        WallService.add(Post(ownerId = 2, text = "Второй пост"))

        val update = Post(id = 999, ownerId = 999, text = "Несуществующий пост")
        val result = WallService.update(update)

        assertFalse(result)
    }

    @Test
    fun updatePreservesOwnerIdAndDate() {
        val original = WallService.add(Post(ownerId = 123, text = "Оригинал"))
        val originalDate = original.date

        val update = original.copy(ownerId = 999, text = "Попытка изменить владельца")
        WallService.update(update)

        assertEquals(123, original.ownerId)
        assertEquals(originalDate, original.date)
    }

    @Test
    fun addMultiplePostsWithMixedNullableFields() {
        val post1 = WallService.add(Post(ownerId = 1, text = "Пост с текстом"))
        val post2 = WallService.add(Post(ownerId = 2, text = null, copyright = "© 2025"))
        val post3 = WallService.add(Post(ownerId = 3, fromId = 100, geo = "Москва"))

        assertNotNull(post1.text)
        assertNull(post2.text)
        assertNotNull(post2.copyright)
        assertNotNull(post3.geo)
        assertEquals(100, post3.fromId)
    }
}