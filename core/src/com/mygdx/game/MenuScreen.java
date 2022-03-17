package com.mygdx.game;
import com.badlogic.gdx.Gdx;
/*
Classe para a tela de menu para que o jogo não comece instantaneamente,
tem um BaseActor agindo como plano de fundo e outro com o a mensagem "Play",
quando a tela é clicada a tela do level pricipal é chamada.
*/


public class MenuScreen extends BaseScreen{
	public void initialize(){
		BaseActor menuBackground = new BaseActor(0,0, mainStage);
		BaseActor jogar = new BaseActor(0,0, mainStage);
		menuBackground.loadTexture( "earth.gif" );
		menuBackground.setSize(800,600);
		jogar.loadTexture( "Jogar.png" );
		jogar.centerAtPosition(400,300);
		jogar.moveBy(0,-100);
}
	public void update(float dt) {
		if (Gdx.input.isTouched())
		ApsGame.setActiveScreen(new LevelScreen());
	}
	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
}
