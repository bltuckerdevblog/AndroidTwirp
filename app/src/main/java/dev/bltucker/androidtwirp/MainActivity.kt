package dev.bltucker.androidtwirp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.gson.GsonBuilder
import com.squareup.wire.Instant
import dev.bltucker.androidtwirp.ui.theme.AndroidTwirpTheme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import twitch.twirp.example.Haberdasher

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTwirpTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    private fun getTwirpService(): Haberdasher{
        val gson = GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Instant::class.java, InstantTypeConverter())
            .setFieldNamingStrategy(WireAnnotatedFieldNamingStrategy())
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://twirp.bltucker.com/not-real-just-an-example")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(Haberdasher::class.java)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AndroidTwirpTheme {
        Greeting("Android")
    }
}