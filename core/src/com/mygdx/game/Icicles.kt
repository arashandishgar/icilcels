package com.mygdx.game

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.DelayedRemovalArray
import com.badlogic.gdx.utils.viewport.ExtendViewport

class Icicles(val extendViewport: ExtendViewport,val tempreture: Constants.Difficulity) {
    lateinit var icicleList: DelayedRemovalArray<Icicle>
     var dodged:Int=0

    fun render(shapeRenderer: ShapeRenderer) {
        shapeRenderer.set(ShapeRenderer.ShapeType.Filled)
        for (i in icicleList) {
            i.render(shapeRenderer)
        }
    }

    init {
        reset(extendViewport)
    }

    fun reset(extendViewport: ExtendViewport) {
        icicleList = DelayedRemovalArray()
        dodged=0
    }

    fun upadate(delta: Float) {
        if (MathUtils.random() < delta *tempreture.value) {
            val x = MathUtils.random(0f, extendViewport.worldWidth - Constants.TRIANGLE_WIDTH)
            icicleList.add(Icicle(Vector2(x, extendViewport.worldHeight + Constants.TRIANGLE_HEIGHT)))
        }
        for (i in icicleList) {
            i.update(delta)
        }
        icicleList.begin()
        for(i in 0 until icicleList.size){
            if(icicleList.get(i).position.y<0){
                dodged++;
                icicleList.removeIndex(i)
            }
        }
        icicleList.end()
        println(icicleList.size)
    }

    companion object {
        val TAG = Icicle::class.java.name
    }
}
