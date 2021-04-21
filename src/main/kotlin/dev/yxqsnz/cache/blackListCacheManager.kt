package dev.yxqsnz.cache

import dev.yxqsnz.entities.BlackListUser


class BlackListCache {
    private val blackListedUsersCache = mutableListOf<BlackListUser>()
    fun add(userId: String, reason: String) {
        println("an user has been blacklisted.")
        val user = BlackListUser(userId)
        user.reason = reason
        blackListedUsersCache.add(user)
    }
    fun remove(userId: String) {
        // verifica se o usuário está na blacklist
        val user = blackListedUsersCache.firstOrNull { it.userId == userId }
            ?: throw Exception("Esse usuário não está na blacklist.")
        blackListedUsersCache.remove(user)

    }
    fun isUserBlackListed(userId: String): Boolean {
        blackListedUsersCache.firstOrNull { it.userId == userId }
            ?: return false
        return true
    }
}