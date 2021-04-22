package dev.yxqsnz.database

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.*

class Client {
    lateinit var mongoClient: MongoClient
    fun connect(uri: String) {
        val settings = MongoClientSettings.builder()
            .applyConnectionString(ConnectionString(uri))
            .retryWrites(true)
            .retryReads(true)
            .build()
        mongoClient = MongoClients.create(settings)

    }
}