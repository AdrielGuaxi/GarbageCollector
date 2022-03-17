package com.mygdx.game.desktop;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ApsGame;
/*
Classe que vai abrir o jogo nela é dito o nome da janela que vai abrir e o tamanho da mesma
*/
public class GameLauncher {
	public static void main (String[]args) {
			LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
			config.title = "Aps Game";
			config.width = 800;
			config.height = 600;
			new LwjglApplication(new ApsGame(), config);		
		}
    }

