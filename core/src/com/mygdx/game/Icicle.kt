package com.mygdx.game

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.mygdx.game.Constants.TRIANDGLE_COLOR
import com.mygdx.game.Constants.TRIANGLE_HEIGHT
import com.mygdx.game.Constants.TRIANGLE_WIDTH


data class Icicle(val position: Vector2) {
    val velocity=Vector2(0f,0f)
    companion object {
        val TAG = Icicle::class.java.name
    }

    fun render(shapeRenderer: ShapeRenderer) {
        shapeRenderer.setColor(TRIANDGLE_COLOR)
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.triangle(position.x, position.y, position.x + TRIANGLE_WIDTH, position.y, position.x + TRIANGLE_WIDTH/2, position.y - TRIANGLE_HEIGHT)
    }

    fun update(delta: Float) {
        velocity.mulAdd(Constants.ICICLES_ACCELERATION,delta)
        position.mulAdd(velocity,delta)
    }
}