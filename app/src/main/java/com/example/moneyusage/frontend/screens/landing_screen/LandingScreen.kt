package com.example.moneyusage.frontend.screens.landing_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyusage.R
import com.example.moneyusage.backend.models.services.impl.AccountServiceImpl
import com.example.moneyusage.frontend.components.AppTitle
import com.example.moneyusage.frontend.components.GoogleText


/**
 * This represents a first start view of app
 * when the user has not yet been signed in
 */
@Composable
fun LandingScreen(
    viewModel: LandingScreenViewModel = LandingScreenViewModel(AccountServiceImpl()),
    openAndPopUp: (String, String) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.3f))

        Column {
            AppTitle()

            Spacer(modifier = Modifier.height(10.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(end = 10.dp)
            ){
                Text(text = stringResource(R.string.intro_description))
            }
        }
        Spacer(modifier = Modifier.height(30.dp))

        Column {
            Row {
                Text(
                    text = stringResource(R.string.login_register_subtitle),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.fillMaxWidth(0.8f),
            ) {
                // Google button
                OutlinedButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(2.dp),
                    border = BorderStroke(
                        1.dp,
                        brush = Brush.linearGradient(
                            0.0f to Color(214, 43, 8, 255),
                            0.3f to Color(238, 164, 86, 255),
                            0.6f to Color(1, 142, 255, 255),
                            1.0f to Color(22, 133, 33, 255)
                        )
                    )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.g_icon),
                        contentDescription = stringResource(R.string.google_icon),
                        modifier = Modifier.size(40.dp)
                    )
                    GoogleText()
                }

                Spacer(modifier = Modifier.height(10.dp))

                /**
                 * Register and login Row
                 */

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    val registerAndLoginColor = colorResource(R.color.authorization_color)

                    // Register button
                    Button(
                        onClick = {
                            viewModel.onRegisterClick(openAndPopUp)
                        },
                        modifier = Modifier.width(150.dp),
                        shape = RoundedCornerShape(2.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = registerAndLoginColor,
                        )
                    ) {
                        Text(text = "Register")
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    // Login button
                    OutlinedButton(
                        onClick = {
                            viewModel.onLoginClick(openAndPopUp)
                        },
                        modifier = Modifier.width(200.dp),
                        shape = RoundedCornerShape(2.dp),
                        border = BorderStroke(1.dp, registerAndLoginColor)
                    ) {
                        Text(
                            text = "Login",
                            color = registerAndLoginColor
                        )
                    }
                }
            }
        }

    }
}

@Preview(
    showBackground = true,
    device = Devices.PIXEL_2_XL,
    showSystemUi = true
)
@Composable
fun StartUpPagePreview() {
    LandingScreen(){
        _, _ ->
    }
}