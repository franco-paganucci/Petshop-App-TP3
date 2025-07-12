package com.example.petshopapptp3.screens.profilePage.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.example.petshopapptp3.R
import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.shared.ArrowTitle
import com.example.petshopapptp3.components.shared.InputField
import com.example.petshopapptp3.navigation.Screen
import com.example.petshopapptp3.ui.theme.purple
import com.example.petshopapptp3.viewModel.AuthViewModel

@Composable
fun AccountScreen(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val profileState by viewModel.userProfile.collectAsState()
    val updateState by viewModel.updateState.collectAsState()

    var showMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) } // Initialize to true for initial load

    // Cargar perfil al entrar a la vista
    LaunchedEffect(Unit) {
        viewModel.loadUserProfile()
    }

    // Cuando se carga perfil con éxito, setear valores
    LaunchedEffect(profileState) {
        profileState?.let { result ->
            if (result.isSuccess) {
                val profile = result.getOrNull()
                if (profile != null) {
                    name = profile.fullName
                    email = profile.email
                    showMessage = null
                }
                isLoading = false
            } else if (result.isFailure) {
                showMessage = "Error al cargar perfil: ${result.exceptionOrNull()?.localizedMessage ?: "desconocido"}"
                isLoading = false
            }
        }
    }

    // Cuando se actualiza perfil, mostrar mensaje y manejar loading
    LaunchedEffect(updateState) {
        updateState?.let { result ->
            isLoading = false
            if (result.isSuccess) {
                showMessage = "Perfil actualizado correctamente"
            } else {
                showMessage = "Error al actualizar perfil: ${result.exceptionOrNull()?.localizedMessage ?: "desconocido"}"
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ArrowTitle(Text = "Account") {
                navController.navigate(Screen.Settings.route)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.LightGray)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fondo_avatar),
                    contentDescription = "Fondo decorativo",
                    modifier = Modifier
                        .matchParentSize(),
                    contentScale = ContentScale.Crop,
                )

                // --- Background Image Edit Icon (floating) ---
                IconButton(
                    onClick = { /* TODO: Edit background */ },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit background image",
                        tint = Color.White
                    )
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // --- Avatar Image and Edit Icon (floating) ---
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.avatar4),
                            contentDescription = "Avatar",
                            modifier = Modifier
                                .matchParentSize()
                                .clip(RoundedCornerShape(100))
                                .background(Color.Gray),
                            contentScale = ContentScale.Crop
                        )

                        // Edit Icon for Avatar
                        IconButton(
                            onClick = { /* TODO: Edit avatar */ },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .offset(x = 12.dp, y = 12.dp)
                                .size(32.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.edit),
                                contentDescription = "Edit avatar",
                                tint = purple
                            )
                        }
                    }

                    Text(
                        text = name.ifBlank { "Abduldul" },
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(60.dp))

            InputField(label = "Name", value = name, onValueChange = { name = it })
            Spacer(modifier = Modifier.height(12.dp))
            InputField(label = "Email", value = email, onValueChange = { email = it })

            showMessage?.let { message ->
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = message,
                    color = if (updateState?.isSuccess == true) Color.Green else Color.Red,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(bottom = 16.dp))
        } else {
            StartButton(
                text = "Save Changes",
                ButtonColor = purple,
                onClick = {
                    showMessage = null
                    isLoading = true
                    viewModel.updateUserProfile(name, email)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }
    }
}