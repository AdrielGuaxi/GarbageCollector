package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
/*
Classe onde serão comprados os upgrades, aqui são instanciados 4 BaseActors, 
o plano de fundo, a ilustração da bota, a ilustração do inventario, e a ilustração do tempo,
e 3 labels com o preço dos upgrades, que são atualizadas conforme o jogador os compra.
*/
public class ShopScreen extends BaseScreen{
	int coins;
	Label coinLabel;
	Label upgradeBoots;
	Label upgradeInventory;
	Label upgradeTime;
	int inventorySize;
    int levelBoots;
    int bootsPrice;
    int levelInventory;
    int inventoryPrice;
    int levelTime;
    int timePrice;
    int speed;
    int n;
	public void initialize() {
		BaseActor shopBackground = new BaseActor(0, 0, mainStage);
		shopBackground.loadTexture("black.png");
		shopBackground.setSize(800, 600);
		BaseActor boots = new BaseActor(350, 450, mainStage);
		boots.loadTexture("boots.png");
		boots.setSize(100, 150);
		BaseActor inventory = new BaseActor(350, 300, mainStage);
		inventory.loadTexture("garbagebag.png");
		inventory.setSize(100, 100);
		BaseActor time = new BaseActor(350, 150, mainStage);
		time.loadTexture("time.png");
		time.setSize(100, 100);
		coinLabel = new Label("Coins x" + coins ,BaseGame.labelStyle);
        coinLabel.setX(350);
        coinLabel.setY(580);
        uiStage.addActor(coinLabel);
        upgradeBoots = new Label("Boots lv " + levelBoots + "\nPress Z to upgrade boots \nCosts " + bootsPrice + " coins",BaseGame.labelStyle);
        upgradeBoots.setX(300);
        upgradeBoots.setY(400);
        uiStage.addActor(upgradeBoots);
        upgradeInventory = new Label("Inventory lv " + levelInventory + "\nPress X to upgrade Inventory \n Costs " + inventoryPrice + " coins",BaseGame.labelStyle);
        upgradeInventory.setX(300);
        upgradeInventory.setY(250);
        uiStage.addActor(upgradeInventory);
        upgradeTime = new Label("Time lv " + levelTime + "\nPress C to upgrade Time \n Costs " + timePrice + " coins",BaseGame.labelStyle);
        upgradeTime.setX(300);
        upgradeTime.setY(80);
        uiStage.addActor(upgradeTime);
	}
	@Override
	public void update(float dt) {
		
		bootsPrice = 5 + (levelBoots * 5);
		inventoryPrice = 5 + (levelInventory * 5);
		timePrice = 5 + (levelTime * 5);
		if (Gdx.input.isKeyJustPressed(Keys.Z)&& coins > (bootsPrice - 1)){
			speed += 100;
			coins -= bootsPrice;
			levelBoots ++;
		}
		if(Gdx.input.isKeyJustPressed(Keys.X)&& coins > (inventoryPrice - 1)) {
			coins -= inventoryPrice;
			levelInventory ++;
			inventorySize += 2;
			
		}
		if(Gdx.input.isKeyJustPressed(Keys.C)&& coins > (timePrice - 1)) {
			coins -= timePrice;
			levelTime ++;
			n += 200;
			
		}
		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
			LevelScreen levelScreen = new LevelScreen();
    		ApsGame.setActiveScreen(levelScreen);
    		levelScreen.coins = coins;
    		levelScreen.levelBoots = levelBoots;
    		levelScreen.levelInventory = levelInventory;
    		levelScreen.levelTime = levelTime;
    		levelScreen.inventorySize = inventorySize;
    		levelScreen.n = n;
    		levelScreen.speed = speed;
		}
        coinLabel.setText("Coins x " + coins);
        upgradeBoots.setText("              Boots lv " + levelBoots + "\nPress Z to upgrade boots \n             Costs " + bootsPrice + " coins");
		upgradeInventory.setText("              Inventory lv " + levelInventory + "\nPress X to upgrade Inventory \n             Costs " + inventoryPrice + " coins");
		upgradeTime.setText("              Time lv " + levelTime + "\nPress C to upgrade Time \n             Costs " + timePrice + " coins");
	}
	
	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
}
