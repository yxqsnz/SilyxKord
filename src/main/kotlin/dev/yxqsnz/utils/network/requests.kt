package dev.yxqsnz.utils.network
import dev.yxqsnz.utils.parseJson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

object requests {
    private val client = OkHttpClient()
    fun get(url: String): Response {
        val request = Request.Builder()
            .url(url)
            .build()
        return client.newCall(request).execute()
    }
    inline fun <reified T> getAndParseJson(url: String): T? {
         val response = this.get(url)
         return parseJson<T>(response.body!!.string())
    }

}
