package com.mygdx.game

import com.badlogic.gdx.Game



class GameIcicles : Game() {
    override fun create() {
        setScreen(ScreenDifficulity(this))
    }

}