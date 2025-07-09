package com.example.petshopapptp3.screens.login.createAccount

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.runtime.*
import androidx.navigation.NavController

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


@Composable
fun CreateAccount(
    navController: NavController
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
                paddingVertical = sizes.inputPaddingVertical
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
                paddingVertical = sizes.inputPaddingVertical
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
                paddingVertical = sizes.inputPaddingVertical
            )

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = checked, onCheckedChange = { checked = it })
                Text(
                    "I Agree to the ",
                    fontSize = sizes.subtitleFontSize
                )
                ClickableText(
                    "Terms of service ",
                    onClick = { /* TODO: handle terms click */ },
                )
                Text(
                    "and ",
                    fontSize = sizes.subtitleFontSize
                )
                ClickableText(
                    "Privacy Policy",
                    onClick = { /* TODO: handle privacy click */ },
                )
            }

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            HaveAccount(onLoginClick = {
                navController.navigate(Screen.Login.route)
            })

            Spacer(modifier = Modifier.height(sizes.spacerHeightLarge))

            StartButton(
                text = "Create Account",
                ButtonColor = buttonColor,
                onClick = {
                    fullNameError = fullName.isBlank()
                    emailError = email.isBlank()
                    passwordError = password.isBlank()

                    if (allFieldsValid) {
                        navController.navigate(Screen.Login.route)
                    }
                }
            )
        }
    }
}



