package com.example.bookbudproto.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbudproto.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(onAuthSuccess: () -> Unit) {
    var currentScreen by remember { mutableStateOf("main") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F291B))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (currentScreen) {
            "main" -> MainAuthScreen(
                onLoginSelected = { currentScreen = "login" },
                onSignupSelected = { currentScreen = "signup" }
            )
            "login" -> LoginScreen(
                onNavigateBack = { currentScreen = "main" },
                onAuthSuccess = onAuthSuccess
            )
            "signup" -> SignupScreen(
                onNavigateBack = { currentScreen = "main" },
                onAuthSuccess = onAuthSuccess
            )
        }
    }
}

@Composable
fun MainAuthScreen(onLoginSelected: () -> Unit, onSignupSelected: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F291B))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.ic_bookbud_logo),
            contentDescription = "BookBud Logo",
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Giriş Yap Butonu
        Button(
            onClick = onLoginSelected,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B4C2A)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("giriş yap", fontSize = 18.sp, color = Color(0xFFA5FF00))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Üye Ol Butonu
        Button(
            onClick = onSignupSelected,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3B4C2A)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("üye ol", fontSize = 18.sp, color = Color(0xFFA5FF00))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Google ile Devam Et
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google_logo),
                contentDescription = "Google Sign-In Button",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Continue with Google", fontSize = 16.sp, color = Color.Black)
        }
    }
}

@Composable
fun LoginScreen(onNavigateBack: () -> Unit, onAuthSuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F291B))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.ic_bookbud_logo),
            contentDescription = "BookBud Logo",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Başlık
        Text(
            text = "giriş yap",
            fontSize = 32.sp,
            color = Color(0xFFA5FF00)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Email Alanı
        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("e-mail", color = Color(0xFF8A8A8A)) },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Gray,
                focusedIndicatorColor = Color(0xFFA5FF00),
                containerColor = Color(0xFF3B4C2A)
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            textStyle = TextStyle(color = Color.White),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Şifre Alanı
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("şifre", color = Color(0xFF8A8A8A)) },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Gray,
                focusedIndicatorColor = Color(0xFFA5FF00),
                containerColor = Color(0xFF3B4C2A)
            ),
            textStyle = TextStyle(color = Color.White),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Hata Mesajı
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // Giriş Yap Butonu
        Button(
            onClick = {
                if (email == "test@example.com" && password == "123456") {
                    onAuthSuccess()
                } else {
                    errorMessage = "Geçersiz e-posta veya şifre"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B1FA2)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("giriş yap", color = Color(0xFF1F291B))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Geri Butonu
        TextButton(onClick = onNavigateBack) {
            Text("geri dön", color = Color(0xFFA5FF00))
        }
    }
}

@Composable
fun SignupScreen(onNavigateBack: () -> Unit, onAuthSuccess: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1F291B))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.ic_bookbud_logo),
            contentDescription = "BookBud Logo",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Başlık
        Text(
            text = "üye ol",
            fontSize = 32.sp,
            color = Color(0xFFA5FF00)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Kullanıcı Adı Alanı
        TextField(
            value = username,
            onValueChange = { username = it },
            placeholder = { Text("kullanıcı adı", color = Color(0xFF8A8A8A)) },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Gray,
                focusedIndicatorColor = Color(0xFFA5FF00),
                containerColor = Color(0xFF3B4C2A)
            ),
            textStyle = TextStyle(color = Color.White),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Email Alanı
        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("e-mail", color = Color(0xFF8A8A8A)) },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Gray,
                focusedIndicatorColor = Color(0xFFA5FF00),
                containerColor = Color(0xFF3B4C2A)
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            textStyle = TextStyle(color = Color.White),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Şifre Alanı
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("şifre", color = Color(0xFF8A8A8A)) },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Gray,
                focusedIndicatorColor = Color(0xFFA5FF00),
                containerColor = Color(0xFF3B4C2A)
            ),
            textStyle = TextStyle(color = Color.White),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Şifre Tekrar Alanı
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = { Text("şifre tekrar", color = Color(0xFF8A8A8A)) },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Gray,
                focusedIndicatorColor = Color(0xFFA5FF00),
                containerColor = Color(0xFF3B4C2A)
            ),
            textStyle = TextStyle(color = Color.White),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Kayıt Ol Butonu
        Button(
            onClick = {
                if (password == confirmPassword && username.isNotEmpty() && email.isNotEmpty()) {
                    onAuthSuccess()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B1FA2)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("üye ol", color = Color(0xFF1F291B))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Geri Butonu
        TextButton(onClick = onNavigateBack) {
            Text("geri dön", color = Color(0xFFA5FF00))
        }
    }
}
