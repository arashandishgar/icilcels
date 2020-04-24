package com.mygdx.game

import com.badlogic.gdx.*
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.ScreenViewport

class ScreenIcicles(val difficulity: Constants.Difficulity,val gameIcicles: Game) : ScreenAdapter() {


    companion object {
        val TAG = Icicle::class.java.name
    }
    var topScore=0
    val bitMapFont:BitmapFont by lazy {
        BitmapFont().apply {
            data.setScale(1f)
            getRegion().texture.setFilter(Texture.TextureFilter.Linear,Texture.TextureFilter.Linear)
        }
    }
    val spriteBatch by lazy {
        SpriteBatch()
    }
    val hudViewPort :ScreenViewport by lazy {
        ScreenViewport()
    }
    val player:Player by lazy {
        Player(extendViewport)
    }
    val shapeRenderer: ShapeRenderer by lazy {
        ShapeRenderer().apply {
            setAutoShapeType(true)
        }
    }
    val icicles :Icicles by lazy {
        Icicles(extendViewport,difficulity)
    }
    val extendViewport: ExtendViewport by lazy {
        ExtendViewport(Constants.WORLD_WIDTH.toFloat(), Constants.WORLD_HEIGHT.toFloat())
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

    override fun show() {
        super.show()
        Gdx.input.inputProcessor=B(gameIcicles)
    }
    override fun render(delta: Float) {
        player.update(delta)
        icicles.upadate(delta)
        if(player.hitByIcicles(icicles)){
            resetGame()
        }
        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        extendViewport.apply()
        shapeRenderer.setProjectionMatrix(extendViewport.camera.combined)
        shapeRenderer.begin()
        icicles.render(shapeRenderer)
        player.render(shapeRenderer)
        shapeRenderer.end()
        hudViewPort.apply()
        spriteBatch.projectionMatrix=hudViewPort.camera.combined
        topScore=Math.max(topScore,icicles.dodged)
        spriteBatch.begin()
        bitMapFont.draw(spriteBatch,"number of death: ${player.death}",0f,hudViewPort.worldHeight)
        bitMapFont.draw(spriteBatch,"difficulity : "+ difficulity.lable,0f,hudViewPort.worldHeight-40)
        bitMapFont.draw(spriteBatch, "Score: " + icicles.dodged + "\nTop Score: " + topScore,
                hudViewPort.getWorldWidth() - Constants.HUD_MARGIN, hudViewPort.getWorldHeight() - Constants.HUD_MARGIN, 0f, Align.right, false)
        spriteBatch.end()

    }

    private fun resetGame() {
        icicles.reset(extendViewport)
    }


    override fun resize(width: Int, height: Int) {
        extendViewport.update(width, height, true)
        player.reset(extendViewport)
        icicles.reset(extendViewport)
        hudViewPort.update(width,height,true)
        // TODO: Set font scale to min(width, height) / reference screen size
        bitMapFont.getData().setScale((Math.min(width, height) / Constants.HUD_FONT_REFERENCE_SCREEN_SIZE).toFloat())
    }


}
