import com.aallam.openai.api.completion.CompletionRequest
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import kotlinx.coroutines.*

val apiKey = "API_KEY_HERE"
val openAI = OpenAI(apiKey)
var story :String = ""

suspend fun generateResponse(user_prompt: String, category: String): String {
    val request = CompletionRequest(
        model = ModelId("davinci-instruct-beta-v3"),
        prompt = "Generate a $category story on the following prompt, $user_prompt",
        temperature = 0.5,
        topP = 1.0,
    )
    return openAI.completion(request).choices[0].text
}

fun getResponse(user_prompt: String, category: String){
    runBlocking{
        story = generateResponse(user_prompt, category)
    }
}