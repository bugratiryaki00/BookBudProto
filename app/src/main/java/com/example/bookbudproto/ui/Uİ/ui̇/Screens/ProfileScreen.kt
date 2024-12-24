package com.example.bookbudproto.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbudproto.models.Book
import com.example.bookbudproto.models.Review
import com.example.bookbudproto.R

@Composable
fun ProfileScreen(
    username: String,
    favoriteBooks: List<Book>,
    reviews: List<Review>,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F291B))
            .padding(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = username,
                color = Color(0xFFA5FF00),
                fontSize = 32.sp
            )
            Image(
                painter = painterResource(id = R.drawable.homescreen_logo),
                contentDescription = "Profile Logo",
                modifier = Modifier.size(40.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Favorite Books Section
        Text(
            text = "Favorite Books",
            color = Color(0xFFA5FF00),
            fontSize = 24.sp
        )
        LazyRow(
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            items(favoriteBooks) { book: Book ->
                BookCard(book = book)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Reviews Section
        Text(
            text = "Reviews",
            color = Color(0xFFA5FF00),
            fontSize = 24.sp
        )
        reviews.forEach { review: Review ->
            ReviewCard(review = review)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Back Button
        Button(
            onClick = onNavigateBack,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFA5FF00))
        ) {
            Text("Geri Dön", color = Color(0xFF1F291B))
        }
    }
}

@Composable
fun BookCard(book: Book) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(180.dp)
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF3B4C2A))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = book.imageResId),
                contentDescription = book.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Text(
                text = book.title,
                color = Color(0xFFA5FF00),
                fontSize = 14.sp,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Composable
fun ReviewCard(review: Review) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF3B4C2A))
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = review.bookImageResId),
                contentDescription = review.bookTitle,
                modifier = Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = review.bookTitle,
                    color = Color(0xFFA5FF00),
                    fontSize = 18.sp
                )
                Text(
                    text = review.content,
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}
