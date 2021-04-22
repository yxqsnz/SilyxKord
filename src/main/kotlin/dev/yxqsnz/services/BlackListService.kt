package dev.yxqsnz.services

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import dev.yxqsnz.cache.blackListCache
import dev.yxqsnz.classes.user.BlackListUser
import org.bson.BsonDocument
import org.bson.BsonString
import org.bson.Document
import org.bson.conversions.Bson
import org.bson.types.ObjectId
import java.time.Instant

// Blacklist = Xban.
object BlackListService {
    private var bannedUsersCount = 0
    private lateinit var blackListCollection: MongoCollection<Document>

    fun loadBlackList(mongodb: MongoClient): Int {

        this.blackListCollection = mongodb.getDatabase("Silyx").getCollection("blacklist")
        val bannedUsers = this.blackListCollection.find()
        for (bannedUser in bannedUsers) {
            this.add(
                bannedUser["userId"] as String,
                bannedUser["reason"] as String,
                bannedUser["bannedIn"] as String
            )
            bannedUsersCount++
        }
        return bannedUsersCount
    }
    private fun add(userId: String,reason: String, bannedIn: String = Instant.now().toEpochMilli().toString()) {
        val user = BlackListUser(userId)
        user.reason = reason
        user.bannedIn = bannedIn
        blackListCache.add(user)
    }
    fun addUserToBlackList(userId: String,reason: String, bannedIn: String = Instant.now().toEpochMilli().toString()) {
        val user = BlackListUser(userId)
        user.reason = reason
        user.bannedIn = bannedIn
        // add to cache
        blackListCache.add(user)
        // add to database
        val blacklistUserDocument = Document("_id",ObjectId())
        blacklistUserDocument.append("userId",userId)
            .append("reason",reason)
            .append("bannedIn",bannedIn)

        this.blackListCollection.insertOne(blacklistUserDocument)


    }
    fun isUserBlackListed(userId: String): Boolean {
        blackListCache.firstOrNull { it.userId == userId }
            ?: return false
        return true
    }
    fun removeUser(userId: String) {
        // verifica se o usuário está na blacklist
        val user  = blackListCache.firstOrNull { it.userId == userId }
            ?: throw Exception("Esse usuário não está na blacklist.")

        blackListCache.remove(user)
        val query = BsonDocument()
        query.append("userId",BsonString(userId))
        this.blackListCollection.findOneAndDelete(query)
    }
    fun getAllUsers(): MutableList<BlackListUser> = blackListCache

    fun getUser(userId: String) = blackListCache.firstOrNull { it.userId == userId }
        ?: throw Exception("Esse usuário não está na blacklist.")
}