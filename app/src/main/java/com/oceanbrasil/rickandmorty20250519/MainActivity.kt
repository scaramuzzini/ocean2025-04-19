package com.oceanbrasil.rickandmorty20250519

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.oceanbrasil.rickandmorty20250519.ui.theme.RickAndMorty20250519Theme
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    suspend fun getCharacters(): CharacterResponse
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMorty20250519Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListaDePersonagens(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ListaDePersonagens( modifier: Modifier = Modifier
                        , viewModel: RickAndMortyViewModel = viewModel()) {

    val personagens by viewModel.characters.collectAsState()
    LazyColumn {
        items(personagens.size) { i ->
            Card(modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()) {
                Row {
                    Text(text = personagens[i].id.toString())
                    Text(text = personagens[i].name)
                }
            }
        }
    }
    Column {
        Box() {
            Text("personagem1")
        }
        Box() {
            Text("personagem2")
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