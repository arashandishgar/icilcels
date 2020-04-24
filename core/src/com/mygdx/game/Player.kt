package com.mygdx.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.ExtendViewport

class Player(val extendViewport: ExtendViewport) {
    lateinit var possitionFromHead: Vector2
    var death=0

    companion object {
        val TAG = javaClass.toString()
    }

    init {
        reset(extendViewport)
    }

    fun reset(extendViewport: ExtendViewport) {
        possitionFromHead = Vector2(extendViewport.worldWidth / 2, Constants.PLAYER_HEIGH_HEAD.toFloat())
    }

    fun update(delta: Float) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            possitionFromHead.x -= delta * Constants.SPEED
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            possitionFromHead.x += delta * Constants.SPEED
        }
        ensureBound()
    }

    private fun ensureBound() {
        if (possitionFromHead.x + Constants.PLAYER_HEAD_RAIDIUS > extendViewport.worldWidth) {
            possitionFromHead.x = extendViewport.worldWidth - Constants.PLAYER_HEAD_RAIDIUS
        }
        if (possitionFromHead.x - Constants.PLAYER_HEAD_RAIDIUS < 0) {
            possitionFromHead.x = Constants.PLAYER_HEAD_RAIDIUS
        }

    }

    fun render(shapeRenderer: ShapeRenderer) {
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.setColor(Constants.PLAYER_COLOR);
        val startPossionOfLimb = Vector2(possitionFromHead.x, possitionFromHead.y - Constants.PLAYER_HEAD_RAIDIUS)
        val endPossionOfLimb = Vector2(startPossionOfLimb.x, startPossionOfLimb.y - 2 * Constants.PLAYER_HEAD_RAIDIUS)
        //draw head
        shapeRenderer.circle(startPossionOfLimb.x, startPossionOfLimb.y + Constants.PLAYER_HEAD_RAIDIUS, Constants.PLAYER_HEAD_RAIDIUS, Constants.SEGMENT_PLAYER_HEAD)
        //drawVertical limb
        shapeRenderer.rectLine(startPossionOfLimb.x, startPossionOfLimb.y, endPossionOfLimb.x, endPossionOfLimb.y, Constants.PLAYER_LIMB_WIDTH)
        val baseForHorrizentalLimb = startPossionOfLimb
        repeat(2) {
            shapeRenderer.rectLine(baseForHorrizentalLimb.x, baseForHorrizentalLimb.y, baseForHorrizentalLimb.x + Constants.PLAYER_HEAD_RAIDIUS, baseForHorrizentalLimb.y - Constants.PLAYER_HEAD_RAIDIUS, Constants.PLAYER_LIMB_WIDTH)
            shapeRenderer.rectLine(baseForHorrizentalLimb.x, baseForHorrizentalLimb.y, baseForHorrizentalLimb.x - Constants.PLAYER_HEAD_RAIDIUS, baseForHorrizentalLimb.y - Constants.PLAYER_HEAD_RAIDIUS, Constants.PLAYER_LIMB_WIDTH)
            baseForHorrizentalLimb.y -= 2 * Constants.PLAYER_HEAD_RAIDIUS
        }

    }

    fun hitByIcicles(icicles: Icicles): Boolean {
        for (i in icicles.icicleList) {
            i.position.x+=Constants.TRIANGLE_WIDTH/2
            i.position.y-=Constants.TRIANGLE_HEIGHT
            if (possitionFromHead.dst(i.position) <= Constants.PLAYER_HEAD_RAIDIUS) {
                death++;
                return true
            }
            i.position.x-=Constants.TRIANGLE_WIDTH/2
            i.position.y+=Constants.TRIANGLE_HEIGHT
        }
        return false
    }
}
