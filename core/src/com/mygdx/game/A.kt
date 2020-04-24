package com.mygdx.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.FitViewport

class A(val gameIcicles: Game, val fitViewPort: FitViewport) : InputAdapter() {

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        val v = fitViewPort.unproject(Vector2(screenX.toFloat(), screenY.toFloat()))
        if (v.dst(Constants.EASY_CENTER) <= Constants.DIFFICULTY_BUBBLE_RADIUS) {
            gameIcicles.setScreen(ScreenIcicles(Constants.Difficulity.Cold,gameIcicles))
            return true
        }
        if (v.dst(Constants.MEDIUM_CENTER) <= Constants.DIFFICULTY_BUBBLE_RADIUS) {
            gameIcicles.setScreen(ScreenIcicles(Constants.Difficulity.Colder,gameIcicles))
            return true
        }
        if (v.dst(Constants.HARD_CENTER) <= Constants.DIFFICULTY_BUBBLE_RADIUS) {
            gameIcicles.setScreen(ScreenIcicles(Constants.Difficulity.Coldest,gameIcicles))
            return true
        }
        return false
    }
}