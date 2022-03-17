package com.mygdx.game;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
/*
Classe usada para pegar as caracteristicas de um tiledmap, como tamanho, texturas e settar a camera, 
e usá-lo no jogo.   
*/
public class TilemapActor extends Actor {
    public static int windowWidth  = 800;
    public static int windowHeight = 600;
    private TiledMap tiledMap;
    private OrthographicCamera tiledCamera;
    private OrthoCachedTiledMapRenderer tiledMapRenderer;
    public TilemapActor(String filename, Stage theStage){
        tiledMap = new TmxMapLoader().load(filename);
        int tileWidth = (int)tiledMap.getProperties().get("tilewidth");
        int tileHeight = (int)tiledMap.getProperties().get("tileheight");
        int numTilesHorizontal = (int)tiledMap.getProperties().get("width");
        int numTilesVertical = (int)tiledMap.getProperties().get("height");
        int mapWidth  = tileWidth * numTilesHorizontal;
        int mapHeight = tileHeight * numTilesVertical;
        tiledMapRenderer = new OrthoCachedTiledMapRenderer(tiledMap);
        tiledMapRenderer.setBlending(true);
        tiledCamera = new OrthographicCamera();
        tiledCamera.setToOrtho(false, windowWidth, windowHeight);
        tiledCamera.update();
        theStage.addActor(this);
        BaseActor.setWorldBounds(mapWidth, mapHeight);
    }
    public void draw(Batch batch, float parentAlpha){
        Camera mainCamera = getStage().getCamera();
        tiledCamera.position.x = mainCamera.position.x;
        tiledCamera.position.y = mainCamera.position.y;
        tiledCamera.update();
        tiledMapRenderer.setView(tiledCamera);
        batch.end();
        tiledMapRenderer.render();        
        batch.begin();
    }
}