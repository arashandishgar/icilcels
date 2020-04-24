package com.mygdx.game

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.Vector2

object Constants {

    val HUD_MARGIN=20
    val HUD_FONT_REFERENCE_SCREEN_SIZE=480
    const val PLAYER_HEAD_RAIDIUS = .5f
    const val PLAYER_HEIGH_HEAD = 4 * PLAYER_HEAD_RAIDIUS
    const val PLAYER_LIMB_WIDTH = .1f
    const val SEGMENT_PLAYER_HEAD = 20
    const val TRIANGLE_HEIGHT = 1f
    val PLAYER_COLOR = Color.BLACK

    const val WORLD_WIDTH = 10
    const val WORLD_HEIGHT = 10
    //world width in one secccond
    const val SPEED = WORLD_WIDTH
    const val TRIANGLE_WIDTH = .5f
    val BACKGROUND_COLOR = Color.BLUE
    val TRIANDGLE_COLOR = Color.WHITE
    // TODO: Add constants for the color of each difficulty select circle
    val EASY_COLOR = Color(0.2f, 0.2f, 1f, 1f)
    val MEDIUM_COLOR = Color(0.5f, 0.5f, 1f, 1f)
    val HARD_COLOR = Color(0.7f, 0.7f, 1f, 1f)
    // TODO: Add constant for the size of the difficulty world
    const val DIFFICULTY_WORLD_SIZE = 480.0f

    // TODO: Add constant for the radius of the difficulty select "buttons"
    const val DIFFICULTY_BUBBLE_RADIUS = DIFFICULTY_WORLD_SIZE / 9

    // TODO: Add constant for the scale of the difficulty labels (1.5 works well)
    const val DIFFICULTY_LABEL_SCALE = 1.5f

    // TODO: Add Vector2 constants for the centers of the difficulty select buttons
    val EASY_CENTER = Vector2(DIFFICULTY_WORLD_SIZE / 4, DIFFICULTY_WORLD_SIZE / 2)
    val MEDIUM_CENTER = Vector2(DIFFICULTY_WORLD_SIZE / 2, DIFFICULTY_WORLD_SIZE / 2)
    val HARD_CENTER = Vector2(DIFFICULTY_WORLD_SIZE * 3 / 4, DIFFICULTY_WORLD_SIZE / 2)

    val ICICLES_ACCELERATION = Vector2(0f, -5.0f)
    enum class Difficulity(val lable : String,val value: Int) {
        Cold("cold",5),Colder("colder", 15),Coldest("coldest",25)
    }

}

