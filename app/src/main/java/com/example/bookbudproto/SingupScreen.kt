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
fun SignupScreen(onNavigateToLogin: () -> Unit, onAuthSuccess: () -> Unit) {
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
            colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xFF3B4C2A)),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

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

        // Şifre Tekrar Alanı
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = { Text("şifre tekrar", color = Color(0xFF8A8A8A)) },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color(0xFF3B4C2A)),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Üye Ol Butonu
        Button(
            onClick = {
                if (password == confirmPassword) {
                    onAuthSuccess()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8256D0))
        ) {
            Text("üye ol", color = Color(0xFFA5FF00), fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Giriş Yap Yönlendirme
        TextButton(onClick = { onNavigateToLogin() }) {
            Text("Already have an account? Login", color = Color(0xFFA5FF00))
        }
    }
}
