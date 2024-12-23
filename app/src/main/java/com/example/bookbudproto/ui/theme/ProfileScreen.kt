package com.example.bookbudproto.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbudproto.models.Book

@Composable
fun ProfileScreen(username: String, email: String, favoriteBooks: List<Book>, onEditProfile: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F291B))
            .padding(16.dp)
    ) {
        // User Information
        Text(
            text = "User Profile",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFA5FF00)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Name: $username",
            fontSize = 16.sp,
            color = Color.Gray
        )
        Text(
            text = "Email: $email",
            fontSize = 16.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onEditProfile,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B4C2A)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Edit Profile", color = Color(0xFFA5FF00))
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Bookmarked Books
        Text(
            text = "Bookmarked Books:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFA5FF00)
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn {
            items(favoriteBooks) { book ->
                Text(
                    text = book.title,
                    fontSize = 16.sp,
                    color = Color(0xFFA5FF00),
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}
