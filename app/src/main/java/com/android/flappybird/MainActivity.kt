package com.android.flappybird

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.flappybird.ui.game.ui.Game
import com.android.flappybird.ui.theme.FlappyBirdTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlappyBirdTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    Game(
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun GamePreview() {
    FlappyBirdTheme {
        Surface (
            modifier = Modifier
                .fillMaxSize()
        ) {
            Game(
                modifier = Modifier
            )
        }
    }
}

