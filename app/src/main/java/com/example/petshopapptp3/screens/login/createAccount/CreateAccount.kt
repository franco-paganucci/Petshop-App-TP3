package com.example.petshopapptp3.screens.login.createAccount

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

import com.google.firebase.auth.FirebaseUser

import com.example.petshopapptp3.components.shared.SubtitleSection
import com.example.petshopapptp3.components.shared.TitleSection
import com.example.petshopapptp3.R
import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.shared.InputField
import com.example.petshopapptp3.ui.theme.disableButton
import com.example.petshopapptp3.components.loginComponents.HaveAccount
import com.example.petshopapptp3.components.shared.ClickableText
import com.example.petshopapptp3.navigation.Screen
import com.example.petshopapptp3.ui.theme.purple
import com.example.petshopapptp3.util.ProvideWindowSize
import com.example.petshopapptp3.util.responsiveSizes
import com.example.petshopapptp3.viewModel.AuthViewModel

@Composable
fun CreateAccount(
    navController: NavController,
    viewModel: AuthViewModel = hiltViewModel()
) {
    ProvideWindowSize {
        val sizes = responsiveSizes()

        var fullName by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var checked by remember { mutableStateOf(false) }

        var fullNameError by remember { mutableStateOf(false) }
        var emailError by remember { mutableStateOf(false) }
        var passwordError by remember { mutableStateOf(false) }

        val allFieldsValid =
            fullName.isNotBlank() && email.isNotBlank() && password.isNotBlank() && checked
        val buttonColor = if (allFieldsValid) purple else disableButton

        var isLoading by remember { mutableStateOf(false) }
        val context = LocalContext.current

        val authState by viewModel.authState.collectAsState()
        var registrationError by remember { mutableStateOf<String?>(null) }

        // Reacción al resultado del registro
        LaunchedEffect(authState) {
            authState?.let { result ->
                isLoading = false
                if (result.isSuccess) {
                    Toast.makeText(context, "Cuenta creada con éxito", Toast.LENGTH_SHORT).show()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.CreateAccount.route) { inclusive = true }
                    }
                    viewModel.clearState()
                } else {
                    registrationError = result.exceptionOrNull()?.localizedMessage ?: "Error desconocido"
                    viewModel.clearState()
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = sizes.paddingHorizontal)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(sizes.paddingTop))

            TitleSection(
                title = stringResource(R.string.createAccount_Title),
                fontSize = sizes.titleFontSize,
                lineHeight = sizes.titleLineHeight
            )
            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            SubtitleSection(
                subtitle = stringResource(R.string.login_SubTitle_General),
                fontSize = sizes.subtitleFontSize,
                lineHeight = sizes.subtitleLineHeight
            )
            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            InputField(
                label = "Full Name",
                value = fullName,
                onValueChange = {
                    fullName = it
                    if (it.isNotBlank()) fullNameError = false
                },
                isError = fullNameError,
                fontSize = sizes.inputFontSize,
                paddingVertical = sizes.inputPaddingVertical,
                height = sizes.inputHeight
            )
            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            InputField(
                label = "Email",
                value = email,
                onValueChange = {
                    email = it
                    if (it.isNotBlank()) emailError = false
                },
                isError = emailError,
                fontSize = sizes.inputFontSize,
                paddingVertical = sizes.inputPaddingVertical,
                height = sizes.inputHeight
            )
            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            InputField(
                label = "Password",
                value = password,
                onValueChange = {
                    password = it
                    if (it.isNotBlank()) passwordError = false
                },
                isPassword = true,
                isError = passwordError,
                fontSize = sizes.inputFontSize,
                paddingVertical = sizes.inputPaddingVertical,
                height = sizes.inputHeight
            )

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = checked, onCheckedChange = { checked = it })
                Text("I Agree to the ", fontSize = sizes.subtitleFontSize)
                ClickableText("Terms of service ", onClick = { /* TODO */ })
                Text("and ", fontSize = sizes.subtitleFontSize)
                ClickableText("Privacy Policy", onClick = { /* TODO */ })
            }

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            HaveAccount {
                navController.navigate(Screen.Login.route)
            }

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            if (isLoading) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(16.dp))
            } else {
                StartButton(
                    text = "Create Account",
                    ButtonColor = buttonColor,
                    onClick = {
                        fullNameError = fullName.isBlank()
                        emailError = email.isBlank()
                        passwordError = password.isBlank()

                        val valid = !fullNameError && !emailError && !passwordError && checked
                        if (valid) {
                            isLoading = true
                            registrationError = null
                            viewModel.register(email, password, fullName)
                        }
                    }
                )
            }

            registrationError?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text("❌ $it", color = Color.Red)
            }
        }
    }
}




