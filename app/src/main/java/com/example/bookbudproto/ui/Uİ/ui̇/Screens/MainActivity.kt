package com.example.bookbudproto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.example.bookbudproto.models.Book
import com.example.bookbudproto.models.Review
import com.example.bookbudproto.ui.AuthScreen
import com.example.bookbudproto.ui.HomeScreen
import com.example.bookbudproto.ui.ProfileScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentScreen by remember { mutableStateOf("auth") }

            MaterialTheme(
                colorScheme = lightColorScheme(
                    primary = Color(0xFFA5FF00),
                    background = Color(0xFF1F291B)
                )
            ) {
                when (currentScreen) {
                    "auth" -> AuthScreen(
                        onAuthSuccess = { currentScreen = "home" }
                    )
                    "home" -> HomeScreen(
                        onNavigateToProfile = { currentScreen = "profile" },
                        onNavigateToSearch = { currentScreen = "search" },
                        onNavigateToReviews = { currentScreen = "reviews" }
                    )
                    "profile" -> ProfileScreen(
                        username = "John Doe",
                        favoriteBooks = listOf(
                            Book("Kürk Mantolu Madonna", "Sabahattin Ali", R.drawable.book1),
                            Book("İlk Aşk", "Ivan Turgenev", R.drawable.book2),
                            Book("Son Ayı", "Hannah Gold", R.drawable.book3)
                        ),
                        reviews = listOf(
                            Review(R.drawable.book1, "Kürk Mantolu Madonna", "Harika bir kitap!", 5),
                            Review(R.drawable.book2, "İlk Aşk", "Çok beğendim.", 4),
                            Review(R.drawable.book3, "Son Ayı", "Tavsiye ederim.", 5)
                        ),
                        onNavigateBack = { currentScreen = "home" }
                    )

                    "search" -> {
                        // SearchScreen composable çağırılabilir
                    }
                    "reviews" -> {
                        // ReviewsScreen composable çağırılabilir
                    }
                }
            }
        }
    }
}
