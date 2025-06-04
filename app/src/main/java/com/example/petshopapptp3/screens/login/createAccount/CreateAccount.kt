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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petshopapptp3.components.shared.SubtitleSection
import com.example.petshopapptp3.components.shared.TitleSection
import com.example.petshopapptp3.R
import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.shared.InputField
import com.example.petshopapptp3.ui.theme.disableButton
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.petshopapptp3.components.loginComponents.HaveAccount
import com.example.petshopapptp3.components.shared.ClickeableText
import com.example.petshopapptp3.navigation.Screen
import com.example.petshopapptp3.ui.theme.purple

@Composable
fun CreateAccount(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onTermsClick: () -> Unit,
    onPrivacyClick: () -> Unit,
    onLoginClick: () -> Unit,
    navController: NavController
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var fullNameError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    val allFieldsFilled = fullName.isNotBlank() && email.isNotBlank() && password.isNotBlank()
    val buttonColor = if (allFieldsFilled) purple else disableButton

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        TitleSection(stringResource(R.string.createAccount_Title))
        Spacer(modifier = Modifier.height(24.dp))
        SubtitleSection(stringResource(R.string.login_SubTitle_General))
        Spacer(modifier = Modifier.height(24.dp))

        InputField("Full Name", value = fullName, onValueChange = {
            fullName = it
            if (it.isNotBlank()) fullNameError = false
        }, isError = fullNameError)

        Spacer(modifier = Modifier.height(24.dp))

        InputField("Email", value = email, onValueChange = {
            email = it
            if (it.isNotBlank()) emailError = false
        }, isError = emailError)

        Spacer(modifier = Modifier.height(24.dp))

        InputField("Password", value = password, onValueChange = {
            password = it
            if (it.isNotBlank()) passwordError = false
        }, isPassword = true, isError = passwordError)

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = checked, onCheckedChange = onCheckedChange)
            Text("I Agree to the ")
            ClickeableText("Terms of service ", onClick = onTermsClick)
            Text("And ")
            ClickeableText("Privacy Policy ", onClick = onPrivacyClick)
        }

        Spacer(modifier = Modifier.height(24.dp))
        HaveAccount(onLoginClick = onLoginClick)
        Spacer(modifier = Modifier.height(24.dp))

        StartButton(
            ButtonColor = buttonColor,
            onClick = {
                fullNameError = fullName.isBlank()
                emailError = email.isBlank()
                passwordError = password.isBlank()

                if (allFieldsFilled) {
                    navController.navigate(Screen.Login.route)
                }
            }
        )
    }
}
