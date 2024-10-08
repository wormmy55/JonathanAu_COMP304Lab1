package com.example.quicknotes.data

//These are the routes for the navigation
sealed class ScreenRoutes(val route: String) {
    data object Home : ScreenRoutes("home")
    data object ViewEdit : ScreenRoutes("viewEdit")
    data object CreateNew : ScreenRoutes("createNew")
}