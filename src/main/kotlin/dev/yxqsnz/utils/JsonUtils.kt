package dev.yxqsnz.utils

import com.beust.klaxon.Klaxon
import dev.yxqsnz.silyx.classes.Configuration
import java.io.File
import kotlin.system.exitProcess


fun readConfigFile(): Configuration? {
    val configFile = File("./config.json")
    if (!configFile.exists()) {
        println(" ❌ | Erro! o arquivo de configuração não existe. criando um...")
        configFile.writeText(
            """
            {
               "discordToken": "INSIRA_SEU_TOKEN_AQUI",
               "prefix": "?.",
               "devsID": ["INSIRA_SEU_ID_AQUI"]
            }
        """.trimIndent()
        )
        println(" ✅ | O arquivo de configurações foi criado com sucesso.")
        exitProcess(0)

    }
    return Klaxon().parse<Configuration>(configFile.readText())
}

inline fun <reified T> parseJson(rawJson: String): T? {
    return Klaxon().parse<T>(rawJson)
}