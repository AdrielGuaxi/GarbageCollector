package com.mygdx.game;
/*
Classe que começa o jogo e chama a tela menu.
*/
public class ApsGame extends BaseGame{
    public void create() {        
        super.create();
        setActiveScreen( new MenuScreen() );
    }
}