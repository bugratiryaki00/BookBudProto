package com.example.bookbudproto.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbudproto.R

// Dummy Book Model
data class Book(val title: String, val author: String)

// Dummy Book List
val dummyBooks = listOf(
    Book("Madonna in a Fur Coat", "Sabahattin Ali"),
    Book("First Love", "Ivan Turgenev"),
    Book("The Last Bear", "Hannah Gold")
)

@Composable
fun HomeScreen() {
    val selectedBook = remember { mutableStateOf<Book?>(null) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredBooks = if (searchQuery.isEmpty()) {
        dummyBooks
    } else {
        dummyBooks.filter { it.title.contains(searchQuery, ignoreCase = true) }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F291B)) // Arka plan rengi
    ) {
        Column {
            // Üst Menü ve Logo
            TopBar()

            // Arama Çubuğu
            SearchBar(searchQuery, onQueryChange = { searchQuery = it })

            // Popüler Sekmesi
            SectionHeader(title = "popüler")
            if (filteredBooks.isEmpty()) {
                Text(
                    text = "No books found",
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(filteredBooks) { book ->
                        BookCard(
                            book = book,
                            onClick = { selectedBook.value = book }
                        )
                    }
                }
            }

            // Romanlar Sekmesi
            SectionHeader(title = "romanlar")
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(filteredBooks) { book ->
                    BookCard(
                        book = book,
                        onClick = { selectedBook.value = book }
                    )
                }
            }
        }

        selectedBook.value?.let { book ->
            BookDetailOverlay(
                book = book,
                onDismiss = { selectedBook.value = null }
            )
        }
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.homescreen_menubutton),
            contentDescription = "Menu Button",
            modifier = Modifier.size(40.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.homescreen_logo),
            contentDescription = "App Logo",
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun SearchBar(query: String, onQueryChange: (String) -> Unit) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        placeholder = { Text("Search books...") },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFF3B4C2A),
            focusedIndicatorColor = Color(0xFFA5FF00),
            unfocusedIndicatorColor = Color.Gray,
            cursorColor = Color(0xFFA5FF00)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
fun SectionHeader(title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color(0xFF3B4C2A), shape = RoundedCornerShape(12.dp))
    ) {
        Text(
            text = title,
            color = Color(0xFFA5FF00),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}

@Composable
fun BookCard(book: Book, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(120.dp)
            .height(180.dp)
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF3B4C2A))
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.Gray) // Placeholder for image
            )
            Text(
                text = book.title,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFFA5FF00),
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun BookDetailOverlay(book: Book, onDismiss: () -> Unit) {
    val isExpanded = remember { mutableStateOf(true) }

    // Animations for dimensions
    val width by animateDpAsState(targetValue = if (isExpanded.value) 300.dp else 120.dp)
    val height by animateDpAsState(targetValue = if (isExpanded.value) 400.dp else 180.dp)
    val alpha by animateFloatAsState(targetValue = if (isExpanded.value) 1f else 0f)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x80000000)) // Semi-transparent background
            .clickable { onDismiss() }, // Close when clicking on the background
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .width(width)
                .height(height)
                .clickable { isExpanded.value = !isExpanded.value }, // Toggle expansion
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF3B4C2A))
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(Color.Gray) // Placeholder for image
                )

                if (isExpanded.value) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                            .alpha(alpha)
                    ) {
                        Text(
                            text = book.title,
                            style = MaterialTheme.typography.titleLarge,
                            color = Color(0xFFA5FF00)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Author: ${book.author}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFFCCCCCC)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Book details will be displayed here.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFFAAAAAA)
                        )
                    }
                }
            }
        }
    }
}
