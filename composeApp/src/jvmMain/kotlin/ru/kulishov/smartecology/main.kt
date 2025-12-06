package ru.kulishov.smartecology

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.timeout
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.kulishov.smartecology.data.SystemPrompt

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "SmartEcology",
    ) {
        LaunchedEffect(1){
            val client = HttpClient(CIO) {
                install(ContentNegotiation) {
                    json(Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    })
                }

                // Настройка таймаутов
                install(HttpTimeout) {
                    requestTimeoutMillis = 300000L
                }
            }
            val jsonBody = """
        {
            "model": "ai/gemma3",
            "messages": [
                {
                    "role": "system",
                    "content": "$SystemPrompt"
                },
                {
                    "role": "user",
                    "content": "у меня после обеда осталось много одноразовой посуды, контейнеры, одноразовые вилки, салфетки. Что сними делать"
                }
            ]
        }
        """.trimIndent()

            val response: HttpResponse =
                client.post("http://localhost:12434/engines/llama.cpp/v1/chat/completions") {
                    contentType(ContentType.Application.Json)
                    setBody(jsonBody)
                    timeout {
                        requestTimeoutMillis = 120000L
                        connectTimeoutMillis = 30000L
                        socketTimeoutMillis = 240000L
                    }
                }

            println("Status: ${response.status}")
            println("Response: ${response.bodyAsText()}")
        }
        App()
    }
}