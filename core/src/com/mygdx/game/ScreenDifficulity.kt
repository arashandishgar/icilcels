package com.mygdx.game

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport

class ScreenDifficulity(val gameIcicles: Game) : ScreenAdapter() {
    val spriteBatch by lazy {
        SpriteBatch()
    }
    val shapeRenderer by lazy {
        ShapeRenderer().apply {

        }
    }
    val bitMapFont by lazy {
        BitmapFont().apply {
            data.setScale(Constants.DIFFICULTY_LABEL_SCALE)
            getRegion().texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        }
    }
    val fitViewPort by lazy {
        FitViewport(Constants.DIFFICULTY_WORLD_SIZE, Constants.DIFFICULTY_WORLD_SIZE)
    }

    override fun show() {
        Gdx.input.inputProcessor = A(gameIcicles, fitViewPort)

    }

    companion object {
        val TAG = Icicle::class.java.name
    }

    override fun render(delta: Float) {
        super.render(delta)
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        fitViewPort.apply()
        shapeRenderer.projectionMatrix = fitViewPort.camera.combined
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled)
        shapeRenderer.setColor(Constants.EASY_COLOR)
        shapeRenderer.circle(Constants.EASY_CENTER.x, Constants.EASY_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS)
        shapeRenderer.setColor(Constants.MEDIUM_COLOR)
        shapeRenderer.circle(Constants.MEDIUM_CENTER.x, Constants.MEDIUM_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS)
        shapeRenderer.setColor(Constants.HARD_COLOR)
        shapeRenderer.circle(Constants.HARD_CENTER.x, Constants.HARD_CENTER.y, Constants.DIFFICULTY_BUBBLE_RADIUS)
        shapeRenderer.end()
        spriteBatch.projectionMatrix = fitViewPort.camera.combined
        spriteBatch.begin()
        val glyphLayoutEasy = GlyphLayout(bitMapFont, Constants.Difficulity.Cold.lable)
        bitMapFont.draw(spriteBatch, Constants.Difficulity.Cold.lable, Constants.EASY_CENTER.x, Constants.EASY_CENTER.y + glyphLayoutEasy.height / 2, 0f, Align.center, false)
        val glyphLayoutMeduim = GlyphLayout(bitMapFont, Constants.Difficulity.Colder.lable)
        bitMapFont.draw(spriteBatch, Constants.Difficulity.Colder.lable, Constants.MEDIUM_CENTER.x, Constants.MEDIUM_CENTER.y + glyphLayoutMeduim.height / 2, 0f, Align.center, false)
        val glyphLayoutHard = GlyphLayout(bitMapFont, Constants.Difficulity.Coldest.lable)
        bitMapFont.draw(spriteBatch, Constants.Difficulity.Coldest.lable, Constants.HARD_CENTER.x, Constants.HARD_CENTER.y + glyphLayoutHard.height / 2, 0f, Align.center, false)
        spriteBatch.end()
    }


    override fun resize(width: Int, height: Int) {
        super.resize(width, height)
        fitViewPort.update(width, height, true)
    }
    override fun hide() {
        shapeRenderer.dispose()
        spriteBatch.dispose()
    }

    override fun dispose() {
        super.dispose()
        shapeRenderer.dispose()
        spriteBatch.dispose()
    }


}
