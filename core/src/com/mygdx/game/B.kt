package com.mygdx.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter

class B(val gameIcicles: Game): InputAdapter(){
    override fun keyDown(keycode: Int): Boolean {
        if(keycode== Input.Keys.R){
            gameIcicles.screen=ScreenDifficulity(gameIcicles)
        }
        return true
    }
}