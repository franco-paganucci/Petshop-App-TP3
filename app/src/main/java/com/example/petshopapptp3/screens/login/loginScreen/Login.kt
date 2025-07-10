package com.example.petshopapptp3.screens.login.loginScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.loginComponents.BottomText
import com.example.petshopapptp3.components.loginComponents.DividerWithOr
import com.example.petshopapptp3.components.loginComponents.SocialButtons
import com.example.petshopapptp3.components.shared.InputField
import com.example.petshopapptp3.components.shared.SubtitleSection
import com.example.petshopapptp3.components.shared.TitleSection
import com.example.petshopapptp3.R
import com.example.petshopapptp3.components.shared.ClickableText
import com.example.petshopapptp3.navigation.Screen
import com.example.petshopapptp3.ui.theme.disableButton
import com.example.petshopapptp3.ui.theme.purple
import com.example.petshopapptp3.util.ProvideWindowSize
import com.example.petshopapptp3.util.responsiveSizes

@Composable
fun LoginScreen(navController: NavController) {
    ProvideWindowSize {
        val sizes = responsiveSizes()

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        var emailError by remember { mutableStateOf(false) }
        var passwordError by remember { mutableStateOf(false) }

        val allFieldsFilled = email.isNotBlank() && password.isNotBlank()
        val buttonColor = if (allFieldsFilled) purple else disableButton

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(sizes.paddingTop))

            TitleSection(
                title = stringResource(R.string.login_Title),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start),
                fontSize = sizes.titleFontSize,
                lineHeight = sizes.titleLineHeight
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            SubtitleSection(
                subtitle = stringResource(R.string.login_SubTitle_General),
                fontSize = sizes.subtitleFontSize,
                lineHeight = sizes.subtitleLineHeight
            )

            Spacer(modifier = Modifier.height(15.dp))

            InputField(
                label = "Email",
                value = email,
                onValueChange = {
                    email = it
                    if (it.isNotBlank()) emailError = false
                },
                isError = emailError,
                showError = true,
                fontSize = sizes.inputFontSize,
                paddingVertical = sizes.inputPaddingVertical,
                height = sizes.inputHeight
            )

            Spacer(modifier = Modifier.height(24.dp))

            InputField(
                label = "Password",
                value = password,
                onValueChange = {
                    password = it
                    if (it.isNotBlank()) passwordError = false
                },
                isPassword = true,
                isError = passwordError,
                showError = true,
                fontSize = sizes.inputFontSize,
                paddingVertical = sizes.inputPaddingVertical,
                height = sizes.inputHeight
            )

            Spacer(modifier = Modifier.height(10.dp))
            DividerWithOr()
            Spacer(modifier = Modifier.height(5.dp))
            SocialButtons()
            Spacer(modifier = Modifier.height(15.dp))
            ClickableText("Forgot Your Password?", onClick = {
                navController.navigate(Screen.ForgotPasswordEmail.route)
            })

            Spacer(modifier = Modifier.height(10.dp))

            BottomText(onClick = {
                navController.navigate(Screen.CreateAccount.route)
            })

            Spacer(modifier = Modifier.height(sizes.loginBottomSpacing))

            StartButton(
                ButtonColor = buttonColor,
                text = "Login",
                onClick = {
                    emailError = email.isBlank()
                    passwordError = password.isBlank()

                    if (allFieldsFilled) {
                        navController.navigate(Screen.Home.route)
                    }
                }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ShowLoginScreen(){
    val navController = rememberNavController()
    LoginScreen(navController = navController)
}