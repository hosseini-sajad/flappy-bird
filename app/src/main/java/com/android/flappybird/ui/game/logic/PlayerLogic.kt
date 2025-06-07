package com.android.flappybird.ui.game.logic

import com.android.flappybird.ui.game.model.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PlayerLogic {
    private val _playerPosition = MutableStateFlow(Player(100f, 0f))
    val playerPosition: StateFlow<Player> = _playerPosition

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val deltaTime = flow {
        while (true) {
            val time = 20
            delay(time.toLong())
            emit(time.toFloat())
        }
    }

    init {
        coroutineScope.launch {
            deltaTime.collect { deltaTime ->
                _playerPosition.update { player ->
                    var newY =
                        player.y + player.speed * deltaTime + 0.5 * Player.acceleration * deltaTime * deltaTime
                    var newSpeed = player.speed + Player.acceleration * deltaTime

                    if (newY > 2000) {
                        newY = 0.0
                        newSpeed = 0f
                    }

                    player.copy(y = newY.toFloat(), speed = newSpeed)
                }
            }
        }
    }

    fun jump() {
        _playerPosition.update { player ->
            player.copy(
                speed = -1f
            )
        }
    }
}