package com.example.bookbudproto.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbudproto.R

// Dummy Book Model
data class Book(val title: String, val author: String, val imageResId: Int)

// Dummy Book List
val dummyBooks = listOf(
    Book("Kürk Mantolu Madonna", "Sabahattin Ali", R.drawable.book1),
    Book("İlk Aşk", "Ivan Turgenev", R.drawable.book2),
    Book("Son Ayı", "Hannah Gold", R.drawable.book3),
    Book("Yalnız Seni Sevdim", "Louisa May Alcott", R.drawable.book4)
)

@Composable
fun HomeScreen(onNavigateToProfile: () -> Unit, onNavigateToSearch: () -> Unit, onNavigateToReviews: () -> Unit) {
    var isMenuExpanded by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }

    val filteredBooks = if (searchQuery.isEmpty()) {
        dummyBooks
    } else {
        dummyBooks.filter { it.title.contains(searchQuery, ignoreCase = true) }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F291B))
    ) {
        Column {
            // Üst Menü ve Logo
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
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { isMenuExpanded = !isMenuExpanded }
                )

                Image(
                    painter = painterResource(id = R.drawable.homescreen_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .size(40.dp)
                        .clickable { onNavigateToProfile() } // Profile ekranına yönlendirme
                )
            }

            // Arama Çubuğu
            if (isSearchActive) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color(0xFF3B4C2A)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.search_logo),
                        contentDescription = "Search Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    BasicTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp),
                        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
                        decorationBox = { innerTextField ->
                            if (searchQuery.isEmpty()) {
                                Text("Arama yap", color = Color(0xFF8A8A8A), fontSize = 18.sp)
                            }
                            innerTextField()
                        }
                    )
                    IconButton(onClick = { isSearchActive = false; searchQuery = "" }) {
                        Icon(
                            painter = painterResource(id = R.drawable.homescreen_menubutton),
                            contentDescription = "Kapat",
                            tint = Color(0xFFA5FF00)
                        )
                    }
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { isSearchActive = true }) {
                        Icon(
                            painter = painterResource(id = R.drawable.search_logo),
                            contentDescription = "Arama",
                            tint = Color(0xFFA5FF00)
                        )
                    }
                }
            }

            // Popüler Kitaplar Bölümü
            Text(
                text = "popüler",
                color = Color(0xFFA5FF00),
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp)
            )
            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                items(filteredBooks) { book ->
                    BookCard(book = book)
                }
            }

            // Romanlar Bölümü
            Text(
                text = "romanlar",
                color = Color(0xFFA5FF00),
                fontSize = 20.sp,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )
            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                items(dummyBooks) { book ->
                    BookCard(book = book)
                }
            }
        }

        // Yan Menü
        if (isMenuExpanded) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(250.dp)
                    .background(Color(0xFF3B4C2A)),
                contentAlignment = Alignment.TopStart
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.homescreen_menubutton),
                                contentDescription = "Close Menu",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clickable { isMenuExpanded = false }
                            )
                        }

                        Text(
                            text = "search",
                            color = Color(0xFFA5FF00),
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable { onNavigateToSearch() }
                        )
                        Text(
                            text = "populer",
                            color = Color(0xFFA5FF00),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = "readlists",
                            color = Color(0xFFA5FF00),
                            fontSize = 20.sp,
                            modifier = Modifier.padding(8.dp)
                        )
                        Text(
                            text = "reviews",
                            color = Color(0xFFA5FF00),
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable { onNavigateToReviews() }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BookCard(book: Book) {
    Card(
        modifier = Modifier
            .width(180.dp)
            .height(250.dp)
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
                    .height(160.dp)
            )
            Text(
                text = book.title,
                color = Color(0xFFA5FF00),
                fontSize = 14.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
