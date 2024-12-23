package com.example.bookbudproto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import com.example.bookbudproto.ui.HomeScreen
import com.example.bookbudproto.ui.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Kullanıcı geçiş durumunu kontrol eden state
            var currentScreen by remember { mutableStateOf("home") }

            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Color(0xFFA5FF00),
                    background = Color(0xFF1F291B)
                )
            ) {
                when (currentScreen) {
                    "home" -> HomeScreen(
                        onNavigateToProfile = { currentScreen = "profile" }
                    )
                    "profile" -> ProfileScreen(
                        username = "Kullanıcı Adı",
                        email = "kullanici@ornek.com",
                        favoriteBooks = emptyList(), // Favori kitaplar burada eklenebilir
                        onEditProfile = { /* Profil düzenleme işlemi */ }
                    )
                }
            }
        }
    }
}