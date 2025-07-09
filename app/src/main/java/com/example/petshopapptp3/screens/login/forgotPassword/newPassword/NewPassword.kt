package com.example.petshopapptp3.screens.login.forgotPassword.newPassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.petshopapptp3.components.shared.SubtitleSection
import com.example.petshopapptp3.components.shared.TitleSection
import com.example.petshopapptp3.R
import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.loginComponents.HaveAccount
import com.example.petshopapptp3.components.shared.InputField
import com.example.petshopapptp3.navigation.Screen
import com.example.petshopapptp3.ui.theme.disableButton
import com.example.petshopapptp3.ui.theme.purple

@Composable
fun NewPassword(onLoginClick: () -> Unit = { }, navController: NavController) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }
    var mismatchError by remember { mutableStateOf(false) }

    val isValid = password.isNotBlank() && confirmPassword.isNotBlank() && password == confirmPassword
    val buttonColor = if (isValid) purple else disableButton

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        TitleSection("Forgot Password")
        Spacer(modifier = Modifier.height(30.dp))
        SubtitleSection(stringResource(R.string.login_SubTitle_General))
        Spacer(modifier = Modifier.height(30.dp))

        InputField(
            label = "New Password",
            value = password,
            onValueChange = {
                password = it
                if (it.isNotBlank()) passwordError = false
                mismatchError = false
            },
            isPassword = true,
            isError = passwordError,
            showError = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        InputField(
            label = "Confirm Password",
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
                if (it.isNotBlank()) confirmPasswordError = false
                mismatchError = false
            },
            isPassword = true,
            isError = confirmPasswordError || mismatchError,
            showError = true,
        )

        Spacer(modifier = Modifier.height(300.dp))
        HaveAccount(onLoginClick = {navController.navigate(Screen.Login.route)})
        Spacer(modifier = Modifier.height(16.dp))

        StartButton(
            text = "Reset Password",
            ButtonColor = buttonColor,
            onClick = {
                passwordError = password.isBlank()
                confirmPasswordError = confirmPassword.isBlank()
                mismatchError = password != confirmPassword

                if (isValid) {
                    navController.navigate(Screen.Login.route)
                }
            }
        )
    }
}

