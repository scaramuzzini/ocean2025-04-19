package com.oceanbrasil.rickandmorty20250519

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.oceanbrasil.rickandmorty20250519.ui.theme.RickAndMorty20250519Theme
import retrofit2.Retrofit
import retrofit2.http.GET

data class CharacterResponse(
    val results: List<Character>
)

data class Character(
    val id: Int,
    val name: String
)

interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacters(): List<CharacterResponse>
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .build()

        val service = retrofit.create(RickAndMortyApi::class.java)
        //service.getCharacters()


        enableEdgeToEdge()
        setContent {
            RickAndMorty20250519Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RickAndMorty20250519Theme {
        Greeting("Android")
    }
}