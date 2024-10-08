package com.example.quicknotes.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quicknotes.allNotes
import com.example.quicknotes.test

//This sets the global index to 0
var globalIndex = 0

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onCreateButtonClick: () -> Unit,
    onViewEditButtonClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        modifier = modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Quick Notes"
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                FloatingActionButton(
                    onClick = { onCreateButtonClick() },
                    containerColor = Color.Magenta,
                    contentColor = Color.Black,
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add",
                    )
                }

            }
        },
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.LightGray),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            //This runs the lazy column composable functions
            NotesLazyCol(onClick = { onViewEditButtonClick() })
            //Uncomment below to test adding new note
            //test()
        }
    }
}

//This is the Lazy column composable function
@Composable
fun NotesLazyCol(
    onClick: () -> Unit,
){
    var selectedNote by remember { mutableStateOf("") }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
            .padding(8.dp)
    ) {
        //This runs a for loop for each note in the allNotes list
        items(allNotes){ note ->
            @Composable
            fun selectedNoteLine():String {
                return note.title+ "\n" + note.content
            }
            //I didn't used card, but I think that this functions just as well
            Text(
                //Here is an if statement that will display more details
                // about the note when it is selected
                text = if (selectedNote == note.title)
                {
                    ViewEditButton(onClick = { onClick()
                        //This will set the global index to the index of the note
                        // more about this in ViewEditNote.kt
                        globalIndex = allNotes.indexOf(note)})
                    selectedNoteLine()
                }
                else note.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, Color.Black)
                    .clickable { selectedNote = note.title }
                    .background(if (selectedNote == note.title) Color.Blue
                    else Color.Transparent)
                    .padding(16.dp),
                fontSize = 16.sp,
                maxLines = 2, //This will truncate the text if it is too long
                overflow = Ellipsis
            )
        }
    }
}

//This is the view edit button composable function
@Composable
fun ViewEditButton(
    onClick: () -> Unit
){
    Button(
        onClick = { onClick() }
    ) {
        Icon(
            Icons.Filled.Edit,
            contentDescription = "Edit Note Button")
    }
}

//This is a preview of the lazy Column
@Preview(showBackground = true)
@Composable
fun NotesLazyColPreview(){
    NotesLazyCol(onClick = {})
}

//This is a preview of the ViewEdit button
@Preview (showBackground = true)
@Composable
fun ViewEditButtonPreview(){
    ViewEditButton(onClick = {  })
}