package com.android.flappybird.ui.game.logic

import com.android.flappybird.ui.game.model.Block
import com.android.flappybird.ui.game.model.Pipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlockLogic {
    private val _blockPosition = MutableStateFlow(
        Block(
            Pipe(topY = 0f, bottomY = 200f, x = 0f),
            Pipe(topY = 300f, bottomY = 450f, x = 0f)
        )
    )
    val blockPosition: StateFlow<Block> = _blockPosition

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val deltaTime = flow {
        while (true) {
            val time = 20
            delay(time.toLong())
            emit(time.toFloat())
        }
    }

    init {
        updateBlockX({ _ -> 400f })
        coroutineScope.launch {
            deltaTime.collect {
                updateBlockX { x -> x - 1 }
            }
        }
    }

    private fun updateBlockX(update: (Float) -> Float) {
        _blockPosition.update { block ->
            block.copy(
                topPipe = block.topPipe.copy(
                    x = update(block.topPipe.x)
                ),
                bottomPipe = block.bottomPipe.copy(
                    x = update(block.bottomPipe.x)
                )
            )
        }
    }
}