package com.example.quicknotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.composable
import com.example.quicknotes.ui.theme.QuickNotesTheme
import com.example.quicknotes.views.ViewEditNoteScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.quicknotes.data.ScreenRoutes
import com.example.quicknotes.views.HomeScreen
import com.example.quicknotes.views.NewNoteScreen
import com.example.quicknotes.views.globalIndex

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuickNotesTheme {
                MainScaffold()
            }
        }
    }
}

//This is where all the notes will be stored
data class Note(var title: String, var content: String)
var allNotes = mutableStateListOf(
    Note(title = "Note 1", content = "This is a note"),
    Note(title = "Note 2", content = "This is another note"),
    Note(title = "Note 3", content = "This is a third note"),
    Note(title = "Note 4", content = "This is a fourth note"),
    Note(title = "Note 5", content = "This is a fifth note that is " +
            "very very very very very long and should be truncated")
)

//This is a test for adding new notes
fun test(){allNotes.add(Note(title = "New Note", content = "This is a new note"))}

//This is the main scaffold for the app
//Although it functions more as the navigation to redirect the user to different screens
//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold() {
    Scaffold { innerPadding ->
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = ScreenRoutes.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            //This is where the routes are set
            //The routes will redirect the user to the selected screen
            composable(ScreenRoutes.Home.route){
                HomeScreen(
                    //This is where the routes are set
                    onCreateButtonClick = { navController.navigate(ScreenRoutes.CreateNew.route) },
                    onViewEditButtonClick = { navController.navigate(ScreenRoutes.ViewEdit.route) },
                )
            }
            composable(ScreenRoutes.ViewEdit.route){
                ViewEditNoteScreen(
                    onBackButtonClick = { navController.navigate(ScreenRoutes.Home.route) },
                    onSaveButtonClick = { navController.navigate(ScreenRoutes.Home.route) },
                    //This sets the listIndex to the global index
                    // more about this in ViewEditNote.kt
                    listIndex = globalIndex,
                )
            }
            composable(ScreenRoutes.CreateNew.route){
                NewNoteScreen(
                    onBackButtonClick = { navController.navigate(ScreenRoutes.Home.route) },
                    onSaveButtonClick = { navController.navigate(ScreenRoutes.Home.route) },
                )
            }
        }
    }
}

//This is a preview of the main scaffold
@Preview (showBackground = true)
@Composable
fun MainScaffoldPreview(){
    QuickNotesTheme{
        MainScaffold()
    }
}

