package com.example.assignment0

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment0.ui.theme.Assignment0Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val choiceRepository = ChoiceRepository()
        setContent {
            Assignment0Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DisplayScreen(
                        onOkay = { choiceRepository.okayChoice()},
                        onMaybe = {choiceRepository.maybeChoice()},
                        onNoWay = {choiceRepository.nowayChoice()},
                        clickCount = choiceRepository.clickCount,
                        modifier = Modifier.padding(innerPadding)

                    )
                }
            }
        }
    }
}

@Composable
fun DisplayScreen(
    onOkay: () -> String,
    onMaybe: () -> String,
    onNoWay: () -> String,
    clickCount: Int,
    modifier: Modifier = Modifier
){
    //remember - maintains value even after recomposing
    var result by remember {mutableStateOf("")}

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = result,
            fontSize = 48.sp
        )

        Row(
            modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
            Arrangement.SpaceEvenly

        ) {
            Button(onClick = {result = onOkay()}) {
                Text("Okay")
            }
            Button(onClick = {result = onMaybe()}) {
                Text("Maybe?")
            }
            Button(onClick = {result = onNoWay()}) {
                Text("No Way!")
            }

        }
        Text(text = "Clicks: $clickCount")
        Text("1789024")
        Text("dcreyes1")

    }
}

class ChoiceRepository{
    var clickCount by mutableIntStateOf(0)
    fun roll(chanceOfYes: Double): String{
        clickCount += 1
        return if (Math.random() < chanceOfYes) "Yes" else "No"
    }
    fun okayChoice(): String = roll(0.75)
    fun maybeChoice(): String = roll(0.40)
    fun nowayChoice(): String = roll(0.20)
}