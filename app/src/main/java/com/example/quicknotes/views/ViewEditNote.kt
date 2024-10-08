package com.example.quicknotes.views


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.sharp.Done
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quicknotes.allNotes

//This is the ViewEditNoteScreen composable function
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewEditNoteScreen(
    onBackButtonClick: () -> Unit = {},
    onSaveButtonClick: () -> Unit = {},
    listIndex: Int,
){
    //These are the variables for the text fields to call the data from the allNotes list
    var textTitle by remember { mutableStateOf(allNotes[listIndex].title) }
    var textContent by remember { mutableStateOf(allNotes[listIndex].content) }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Row (
                        modifier = androidx.compose.ui.Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.primaryContainer)
                            .padding(4.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        //This is the back button to exit without saving the note
                        Button(
                            onClick = { onBackButtonClick() }
                        ) {
                            Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, "Return")
                        }
                        //This is the text field for the title of the note
                        TextField(
                            value = textTitle,
                            onValueChange = {textTitle = it},
                            modifier = androidx.compose.ui.Modifier
                                .fillMaxSize()
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                //This calls the SaveViewEdit button composable function
                SaveViewEditButton(
                    textTitle = textTitle,
                    textContent = textContent,
                    onClick = { onSaveButtonClick() },
                )
            }
        },
        ) { innerPadding ->
        Column(
            modifier = androidx.compose.ui.Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            //This is the text field for the context of the note
            TextField(
                value = textContent,
                onValueChange = {textContent = it},
                modifier = androidx.compose.ui.Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            )
        }
    }
}

//This is the SaveViewEditButton composable function
@Composable
fun SaveViewEditButton(
    textTitle: String,
    textContent: String,
    onClick: () -> Unit,
){
    Button(
        onClick = { onClick()
                   allNotes[globalIndex].title = textTitle
                   allNotes[globalIndex].content = textContent
                  },
    ) {
        Icon(Icons.Sharp.Done, "Save")
    }
}

//This is a preview of the ViewEditNoteScreen
@Preview
@Composable
fun ViewEditNoteScreenPreview(){
    ViewEditNoteScreen(
        onBackButtonClick = {},
        onSaveButtonClick = {},
        listIndex = globalIndex,
    )
}