package com.mygdx.game;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
/*
Clase onde alguns elementos do jogos são estabelecidos,
como a fonte para as labels com informções e também é settado a tela que vai aparecer.
*/
public abstract class BaseGame extends Game{
    private static BaseGame game;

    public static LabelStyle labelStyle; 
    public BaseGame() {        
        game = this;
    }
    public void create() {        
        InputMultiplexer im = new InputMultiplexer();
        Gdx.input.setInputProcessor( im );
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("OpenSans.ttf"));
        FreeTypeFontParameter fontParameters = new FreeTypeFontParameter();
        fontParameters.size = 15;
        fontParameters.color = Color.WHITE;
        fontParameters.borderWidth = 2;
        fontParameters.borderColor = Color.BLACK;
        fontParameters.borderStraight = true;
        fontParameters.minFilter = TextureFilter.Linear;
        fontParameters.magFilter = TextureFilter.Linear;
        BitmapFont customFont = fontGenerator.generateFont(fontParameters);
        labelStyle = new LabelStyle();
        labelStyle.font = customFont;
    }
    public static void setActiveScreen(BaseScreen s){
        game.setScreen(s);
    }
}