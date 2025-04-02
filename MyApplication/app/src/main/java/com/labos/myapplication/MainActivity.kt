package com.labos.myapplication

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.labos.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(51, 91, 255)
                ) {
                    MainComponent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainComponent() {
    data class Note(val title: String, val content: String)

    val notes = remember { mutableStateListOf<Note>() }

    var title = remember { mutableStateOf("") }
    var content = remember { mutableStateOf("") }

    Scaffold (
        topBar = {
            TopAppBar(title = { Text("To do app") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { notes.add(Note(title.value, content.value)) }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        Column (modifier = Modifier.padding((paddingValues))) {
            Column (
                modifier = Modifier.padding(10.dp)
                /*alinearlo*/
            ) {
                TextField(value = title.value, onValueChange = {
                    title.value = it
                })
                Spacer(modifier = Modifier.padding(9.dp))
                TextField(value = content.value, onValueChange = {
                    content.value = it
                })
                notes.forEach { note ->
                    Card(
                        modifier = Modifier.padding(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        )

                    ) {
                        Row {
                            Column {
                                Text(text = note.title)
                                Spacer(modifier = Modifier.padding(10.dp))
                                Text(text = note.content)
                            }
                            Spacer(modifier = Modifier.padding(8.dp))
                            Button(onClick = { notes.remove(note) }) {
                                Text(text = "Delete")
                            }
                        }
                    }
                }

            }

        }
    }
}
