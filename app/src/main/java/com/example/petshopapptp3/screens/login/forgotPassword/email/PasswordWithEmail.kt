package com.example.petshopapptp3.screens.login.forgotPassword.email


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.petshopapptp3.components.shared.SubtitleSection
import com.example.petshopapptp3.components.shared.TitleSection
import com.example.petshopapptp3.R
import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.loginComponents.HaveAccount
import com.example.petshopapptp3.components.shared.InputField

@Composable
fun PasswordWithEmail (){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Spacer(modifier = Modifier.height(50.dp))
        TitleSection("Forgot Password")
        Spacer(modifier = Modifier.height(30.dp))
        SubtitleSection(stringResource(R.string.login_SubTitle_General))
        Spacer(modifier = Modifier.height(30.dp))
        InputField()
        Spacer(modifier = Modifier.height(400.dp))
        HaveAccount()
        Spacer(modifier = Modifier.height(16.dp))
        StartButton("Next")

    }
}

@Preview (showBackground = true)
@Composable
fun showPasswordWithEmail (){
    PasswordWithEmail()
}