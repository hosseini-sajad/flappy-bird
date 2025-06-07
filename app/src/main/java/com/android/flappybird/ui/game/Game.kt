package com.android.flappybird.ui.game

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.android.flappybird.ui.game.logic.PlayerLogic
import com.android.flappybird.ui.theme.FlappyBirdTheme

@Composable
fun Game(modifier: Modifier = Modifier) {
    val playerLogic = remember {
        PlayerLogic()
    }
    Box(
        Modifier
            .clickable {
                playerLogic.jump()
                println("Testttttttttttttttt")
            }
    ) {
        val playerPosition = playerLogic.playerPosition.collectAsState()
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(x = 0, y = playerPosition.value.y.toInt())
                }
                .size(50.dp)
                .background(Color.Black)
        )
    }
}

@Preview
@Composable
fun GamePreview() {
    FlappyBirdTheme {
        Game()
    }
}