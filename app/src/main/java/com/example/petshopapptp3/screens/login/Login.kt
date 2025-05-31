package com.example.petshopapptp3.screens.Login

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
import com.example.petshopapptp3.components.buttons.StartButton
import com.example.petshopapptp3.components.loginComponents.BottomText
import com.example.petshopapptp3.components.loginComponents.DividerWithOr
import com.example.petshopapptp3.components.loginComponents.SocialButtons
import com.example.petshopapptp3.components.shared.InputField
import com.example.petshopapptp3.components.shared.SubtitleSection
import com.example.petshopapptp3.components.shared.TitleSection
import com.example.petshopapptp3.R
import com.example.petshopapptp3.ui.theme.disableButton

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        TitleSection(stringResource(R.string.login_Title))
        Spacer(modifier = Modifier.height(16.dp))
        SubtitleSection(stringResource(R.string.login_SubTitle_General))
        Spacer(modifier = Modifier.height(30.dp))
        InputField()
        Spacer(modifier = Modifier.height(24.dp))
        InputField("Password",true)
        Spacer(modifier = Modifier.height(30.dp))
        DividerWithOr()
        Spacer(modifier = Modifier.height(30.dp))
        SocialButtons()
        Spacer(modifier = Modifier.height(30.dp))
        BottomText (onClick = {})
        Spacer(modifier = Modifier.height(48.dp))
        StartButton(ButtonColor = disableButton)
    }
}

@Preview(showBackground = true)
@Composable
fun ShowLoginScreen(){
    LoginScreen()
}