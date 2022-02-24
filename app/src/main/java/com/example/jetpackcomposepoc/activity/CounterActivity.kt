package com.example.jetpackcomposepoc.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackcomposepoc.ui.theme.JetpackComposePOCTheme
import com.example.jetpackcomposepoc.viewmodel.CounterViewModel
import com.example.jetpackcomposepoc.viewmodel.CounterViewModel.NavState.GO_TO_CARDS_SCREEN

class CounterActivity : ComponentActivity() {

    private lateinit var viewModel: CounterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(CounterViewModel::class.java)
        viewModel.getDataValue().observe({ lifecycle }, ::updateUI)
        setContent {
            JetpackComposePOCTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    Counter()
                }
            }
        }
    }

    private fun updateUI(data: CounterViewModel.NavData) {
        when (data.state) {
            GO_TO_CARDS_SCREEN -> startActivity(CardsActivity.newIntent(this))
        }
    }

    private fun decreaseValue(number: Int): Int {
        if (number > 0) {
            return number - 1
        }
        return number
    }

    private fun increaseValue(number: Int): Int {
        return number + 1
    }

    private fun resetValue(): Int {
        return 0
    }

    @Composable
    fun Counter() {
        var number by remember { mutableStateOf(0) }
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (counter, recyclerButton, resetButton) = createRefs()
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.constrainAs(counter) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }) {
                Button(
                    onClick = { number = decreaseValue(number) },
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .padding(10.dp),
                    enabled = true,
                ) {
                    Text(text = "Dec")
                }
                Text(
                    text = "$number",
                    fontSize = 100.sp
                )
                Button(
                    onClick = { number = increaseValue(number) },
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight()
                        .padding(10.dp),
                    enabled = true
                ) {
                    Text(text = "Inc")
                }
            }
            Button(
                onClick = { number = resetValue() },
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight()
                    .padding(5.dp)
                    .constrainAs(resetButton) {
                        top.linkTo(counter.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
                enabled = true,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
            ) {
                Text(text = "Reset")
            }

            Column(
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .constrainAs(recyclerButton) {
                        bottom.linkTo(parent.bottom)
                        end.linkTo(parent.end)
                        start.linkTo(parent.start)
                    },
                verticalArrangement = Arrangement.Bottom
            ) {
                Button(
                    onClick = { viewModel.goToCardsScreen() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Recycler")
                }
            }
        }
    }

    @Preview
    @Composable
    fun PreviewCounter() {
        Counter()
    }
}
