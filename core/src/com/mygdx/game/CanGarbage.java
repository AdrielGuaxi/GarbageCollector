package com.mygdx.game;
import com.badlogic.gdx.scenes.scene2d.Stage;
/*
Uma das Classes para os objetos de latas de lixo, herdada da classe BaseActor
especificando a textura e os limites do objeto. 
*/
public class CanGarbage extends BaseActor {
	public CanGarbage (float x, float y, Stage s) {
		super(x,y,s);
		loadTexture("Lixo_Metal.png");
		setBoundaryPolygon(8);
	}
}
