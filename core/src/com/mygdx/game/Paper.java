package com.mygdx.game;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
/*
Uma das Classes para os lixos do jogo herdada da classe BaseActor, 
especificando a textura, os limites do objeto e adicionando uma propriedade 
para que o objeto possa ser coletado em jogo.
*/
public class Paper extends BaseActor{
	protected boolean collected;
	public Paper(float x, float y, Stage s) {
		super(x,y,s);
		loadTexture("Dirty_Paper.png");
		setBoundaryPolygon(8);
		collected = false;
	}
	public boolean isCollected() {
		return collected;
	}
	public void collect() {
	clearActions();
	addAction(Actions.fadeOut(1));
	addAction(Actions.after(Actions.removeActor()));
}

}
