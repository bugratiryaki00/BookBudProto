package com.example.bookbudproto.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookbudproto.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onNavigateToSignup: () -> Unit, onAuthSuccess: () -> Unit) {
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
            colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xFF3B4C2A)),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Şifre Alanı
        TextField(
            value = password,
            onValueChange = { password = it },
            placeholder = { Text("şifre", color = Color(0xFF8A8A8A)) },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xFF3B4C2A)),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Hata Mesajı
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                modifier = Modifier.padding(8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Giriş Yap Butonu
        Button(
            onClick = {
                if (email == "test@example.com" && password == "password123") {
                    onAuthSuccess() // Başarılı giriş
                } else {
                    errorMessage = "Geçersiz e-mail veya şifre!"
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8256D0))
        ) {
            Text("giriş yap", color = Color(0xFFA5FF00), fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Üye Ol Yönlendirme
        TextButton(onClick = { onNavigateToSignup() }) {
            Text("Don't have an account? Sign Up", color = Color(0xFFA5FF00))
        }
    }
}
