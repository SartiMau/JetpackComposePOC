package com.example.jetpackcomposepoc.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposepoc.R
import com.example.jetpackcomposepoc.data.CardInfo
import com.example.jetpackcomposepoc.ui.theme.GlobantDark
import com.example.jetpackcomposepoc.ui.theme.GlobantGreen
import com.example.jetpackcomposepoc.viewmodel.CardsViewModel

class CardsActivity : ComponentActivity() {

    private lateinit var viewModel: CardsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(CardsViewModel::class.java)
        val cardInfo = CardInfo(
            cardNames = listOf("Mariano", "Juan", "Julio", "Mauro", "Ivan", "Carlos", "Lucas", "Sebastian"),
            cardBody = resources.getString(R.string.card_body)
        )

        setContent {
            // A surface container using the 'background' color from the theme
            Surface(color = MaterialTheme.colors.background) {
                CardHolder(cardInfo.cardNames, cardInfo.cardBody)
            }
        }
    }

    @Composable
    fun CardHolder(names: List<String>, body: String) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF89898c))
        ) {
            LazyColumn(modifier = Modifier
                .fillMaxSize()) {
                items(items = names) { name ->
                    Card(name = name, body = body)
                }
            }
        }
    }

    @Composable
    fun Card(name: String, body: String) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .shadow(26.dp, RoundedCornerShape(5))
                .background(color = GlobantDark)
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(R.drawable.profile_picture),
                contentDescription = "Contact profile picture",
                modifier = Modifier
                    // Set image size to 40 dp
                    .size(40.dp)
                    // Clip image to be shaped as a circle
                    .clip(CircleShape)
                    .border(1.dp, GlobantGreen, CircleShape)
            )
            Column(content = {
                Text(
                    text = name,
                    color = GlobantGreen,
                    modifier = Modifier
                        .width(200.dp)
                        .padding(end = 5.dp, start = 5.dp),
                    style = MaterialTheme.typography.subtitle2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = body,
                    modifier = Modifier.padding(end = 10.dp, start = 10.dp),
                    color = Color.White
                )
            })
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        val names = listOf("Mariano", "Juan", "Julio", "Mauro", "Ivan", "Carlos", "Lucas", "Sebastian")
        val body = resources.getString(R.string.card_body)

        CardHolder(body = body, names = names)
    }

    companion object {
        fun newIntent(context: Context): Intent = Intent(context, CardsActivity::class.java)
    }
}
