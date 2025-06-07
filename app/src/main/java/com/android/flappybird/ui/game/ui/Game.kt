package com.android.flappybird.ui.game.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.android.flappybird.ui.game.logic.BlockLogic
import com.android.flappybird.ui.game.logic.PlayerLogic
import com.android.flappybird.ui.game.model.Pipe
import com.android.flappybird.ui.game.ui.component.Player
import com.android.flappybird.ui.theme.FlappyBirdTheme

@Composable
fun Game(modifier: Modifier = Modifier) {
    val playerLogic = remember {
        PlayerLogic()
    }
    Box(
        modifier = modifier.clickable {
            playerLogic.jump()
        }
    ) {
        Player(playerLogic)
        val blockLogic = remember {
            BlockLogic()
        }
        Box {
            val blockPosition = blockLogic.blockPosition.collectAsState().value
            Pipe(blockPosition.topPipe)
            Pipe(blockPosition.bottomPipe)
        }
    }
}

@Composable
private fun Pipe(pipe: Pipe) {
    Box(
        Modifier
            .offset {
                IntOffset(pipe.x.dp.roundToPx(), pipe.topY.dp.roundToPx())
            }
            .size(
                width = 20.dp,
                height = (pipe.bottomY - pipe.topY).dp
            )
            .background(Color.Green)
    )
}


@Preview
@Composable
fun GamePreview() {
    FlappyBirdTheme {
        Game()
    }
}