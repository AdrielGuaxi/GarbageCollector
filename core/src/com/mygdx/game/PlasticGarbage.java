package com.mygdx.game;
import com.badlogic.gdx.scenes.scene2d.Stage;
/*
Uma das Classes para os objetos de latas de lixo, herdada da classe BaseActor
especificando a textura e os limites do objeto. 
*/
public class PlasticGarbage extends BaseActor{
	public PlasticGarbage(float x, float y, Stage s) {
		super(x,y,s);
		loadTexture("SPOILER_Lixo_Plastico.png");
		setBoundaryPolygon(8);
	}
}