package com.mygdx.game;
import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
/*
Classe onde acontece o nivel pricipal do jogo, são instanciados o personagem, 
os lixos, as latas de lixo, o tamanho do inventario, pontuação, o tempo
e as labels com informação sobre pontuação, inventario e tempo.
E tambem são aplicados os upgrades feitos na tela da loja. 
*/
public class LevelScreen extends BaseScreen{
    Hero hero;
    int levelBoots;
    int levelInventory;
    int levelTime;
    Label coinLabel;
    ArrayList<Paper> papers;
    ArrayList<Plastic> plastics;
    ArrayList<Can> cans;
    ArrayList<Glass> glasses;
    ArrayList<Paper> inventoryPapers;
    ArrayList<Plastic> inventoryPlastics;
    ArrayList<Can> inventoryCans;
    ArrayList<Glass> inventoryGlasses;
    private PaperGarbage paperGarbage;
    private GlassGarbage glassGarbage;
    private PlasticGarbage plasticGarbage;
    private CanGarbage canGarbage;
    private Random random;
    private Label inventoryLabel;
    private Label timeLabel;
    private Label lostLabel;
    int inventoryLeft;
    int inventorySize;
    int time;
    private int numeroLixo;
    int coins;
    private int score;
	private Label scoreLabel;
	int speed;
	int n;
	boolean a = false;
	boolean b = false;
	boolean c = false;
    public void initialize() {   
        time = 3000;
    	numeroLixo = 5;
    	TilemapActor tma;
        tma = new TilemapActor("map.tmx", mainStage);
        random = new Random();
        papers = new ArrayList<>();
        plastics = new ArrayList<>();
        cans = new ArrayList<>();
        glasses = new ArrayList<>();
        inventoryPapers = new ArrayList<>();
        inventoryPlastics = new ArrayList<>();
        inventoryCans = new ArrayList<>();
        inventoryGlasses = new ArrayList<>();
        paperGarbage = new PaperGarbage(159, 292, mainStage);
 	    plasticGarbage = new PlasticGarbage(1021,313,mainStage);
 	    canGarbage = new CanGarbage(100,1150,mainStage);
 	    glassGarbage = new GlassGarbage(1088,1150,mainStage);
        scoreLabel = new Label("Score: 0", BaseGame.labelStyle);
        scoreLabel.setX(0);
        scoreLabel.setY(580);
        coinLabel = new Label("Coins x" + coins ,BaseGame.labelStyle);
        coinLabel.setX(0);
        coinLabel.setY(560);
        inventoryLabel = new Label("Inventory Space: 10",BaseGame.labelStyle);
        inventoryLabel.setX(0);
        inventoryLabel.setY(540);
        timeLabel = new Label("Time: " + time,BaseGame.labelStyle);
        timeLabel.setX(400);
        timeLabel.setY(580);
        lostLabel = new Label("You Lose",BaseGame.labelStyle);
        lostLabel.setY(400);
        lostLabel.setX(300);
        lostLabel.setFontScale(2);
        lostLabel.setVisible(false);
        uiStage.addActor(lostLabel);
        uiStage.addActor(timeLabel);
        uiStage.addActor(scoreLabel);
        uiStage.addActor(coinLabel);
        uiStage.addActor(inventoryLabel);
        hero = new Hero(640, 640, mainStage);
        hero.setMaxSpeed(100 + speed);
        
        
    }
    public void update(float dt) {
    	
    	while(a == false) {
    		if (inventorySize == 0) {
    		inventorySize = 10;
    		inventoryLeft = inventorySize;
    		a = true;
    		}else if(inventorySize > 10) 
    		inventoryLeft = inventorySize;
    		a = true;
    	}
    	while (b == false) {
    		if (levelTime > 1) {
    			time += n;
    			b = true;
    		}else {
    			b = true;
    		}
    	}
    	while (c == false) {
    		if (levelBoots > 1) {
    			hero.setMaxSpeed(100 + speed);
    			hero.setAcceleration(400 + (speed * 4));
    			c = true;
    		} else {
    			c = true;
    		}
    	}
    	time --;
    	while (numeroLixo>0) {
        	Paper paper = new Paper(random.nextInt(1200),random.nextInt(1200),mainStage);
            papers.add(paper);
            Plastic plastic = new Plastic(random.nextInt(1200),random.nextInt(1200),mainStage);
        	plastics.add(plastic);
        	Can can = new Can(random.nextInt(1200),random.nextInt(1200),mainStage);
            cans.add(can);
            Glass glass = new Glass(random.nextInt(1200),random.nextInt(1200),mainStage);
        	glasses.add(glass);
        	numeroLixo --;
    	}
    	if (Gdx.input.isKeyPressed(Keys.LEFT)) 
        hero.accelerateAtAngle(180);
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) 
        hero.accelerateAtAngle(0);
        if (Gdx.input.isKeyPressed(Keys.UP)) 
        hero.accelerateAtAngle(90);
        if (Gdx.input.isKeyPressed(Keys.DOWN)) 
        hero.accelerateAtAngle(270);
        for (int i = 0; i < papers.size(); i++){
        	int a = 2;
        	if ( hero.overlaps(papers.get(i)) && !papers.get(i).collected && 
        		(inventoryPapers.size()+inventoryPlastics.size()+inventoryGlasses.size()+inventoryCans.size())<inventorySize){
        		papers.get(i).collected = true;
                papers.get(i).clearActions();
                inventoryPapers.add(papers.get(i));             
                papers.get(i).addAction( Actions.fadeOut(1) );
                papers.get(i).addAction( Actions.after( Actions.removeActor() ) );
                papers.get(i).remove();
                inventoryLeft--;
                while(a>0) {
                	Paper paper = new Paper(random.nextInt(1200),random.nextInt(1200),mainStage);
                	papers.add(paper);
                	a --;
                }
        	}
        }
        for (int i = 0; i < plastics.size(); i++){
        	int a = 1;
        	if ( hero.overlaps(plastics.get(i)) && !plastics.get(i).collected &&
        		(inventoryPapers.size()+inventoryPlastics.size()+inventoryGlasses.size()+inventoryCans.size())<inventorySize){
        		plastics.get(i).collected = true;
                plastics.get(i).clearActions();
                inventoryPlastics.add(plastics.get(i));
                plastics.get(i).addAction( Actions.fadeOut(1) );
                plastics.get(i).addAction( Actions.after( Actions.removeActor() ) );
                plastics.get(i).remove();
                inventoryLeft--;
                while(a>0) {
                	Plastic plastic = new Plastic(random.nextInt(1200),random.nextInt(1200),mainStage);
                	plastics.add(plastic);
                	a --;
                }
        	}
        }
        for (int i = 0; i < glasses.size(); i++){
        	int a = 1;
        	if ( hero.overlaps(glasses.get(i)) && !glasses.get(i).collected &&
        		(inventoryPapers.size()+inventoryPlastics.size()+inventoryGlasses.size()+inventoryCans.size())<inventorySize){
        		glasses.get(i).collected = true;
                glasses.get(i).clearActions();
                inventoryGlasses.add(glasses.get(i));              
                glasses.get(i).addAction( Actions.fadeOut(1) );
                glasses.get(i).addAction( Actions.after( Actions.removeActor() ) );
                glasses.get(i).remove();
                inventoryLeft--;
                while(a>0) {
                	Glass glass = new Glass(random.nextInt(1200),random.nextInt(1200),mainStage);
                	glasses.add(glass);
                	a --;
                }
        	}
        }
        for (int i = 0; i < cans.size(); i++){
        	int a = 1;
        	if ( hero.overlaps(cans.get(i)) && !cans.get(i).collected &&
        		(inventoryPapers.size()+inventoryPlastics.size()+inventoryGlasses.size()+inventoryCans.size())<inventorySize){
        		cans.get(i).collected = true;
                cans.get(i).clearActions();
                inventoryCans.add(cans.get(i));
                cans.get(i).addAction( Actions.fadeOut(1) );
                cans.get(i).addAction( Actions.after( Actions.removeActor() ) );
                cans.get(i).remove();
                inventoryLeft--;
                while(a>0) {
                	Can can = new Can(random.nextInt(1200),random.nextInt(1200),mainStage);
                	cans.add(can);
                	a--;
                }
        	}
        }
        
        if (hero.overlaps(canGarbage)) {
        	int c = 0;
        	while(inventoryCans.size()>c) {
        	inventoryCans.remove(inventoryCans.get(c));
        	c ++;
        	score += 50;
        	coins += 1;
        	inventoryLeft++;
        	time += 100;
        	}
        }
        if(hero.overlaps(glassGarbage)) {
        	int g = 0;
        	while(inventoryGlasses.size()>g) {
        	inventoryGlasses.remove(inventoryGlasses.get(g));
        	g ++;
        	score += 50;
        	coins += 1;
        	inventoryLeft++;
        	time += 100;
        	}
        }
        if (hero.overlaps(paperGarbage)) {
        	int pp = 0;
        	while(inventoryPapers.size()>pp) {
        	inventoryPapers.remove(inventoryPapers.get(pp));
        	pp ++;
        	score += 50;
        	coins += 1;
        	time += 100;
        	inventoryLeft++;
        	}
        }
        if(hero.overlaps(plasticGarbage)) {
        	int p = 0;
        	while(inventoryPlastics.size()>p) {
        	inventoryPlastics.remove(inventoryPlastics.get(p));
        	p++;
        	score += p * 50;
        	coins += 1;
        	inventoryLeft++;
        	time += 100;
        	}
        }
        scoreLabel.setText("Score: " + score);
        coinLabel.setText("Coins x " + coins);
        inventoryLabel.setText("Inventory Space: " + inventoryLeft);
        timeLabel.setText("Time: " + time/100);
        	if (time <= 0) {
        	timeLabel.setVisible(false);
        	coinLabel.setVisible(false);
        	inventoryLabel.setVisible(false);
        	lostLabel.setVisible(true);
        	scoreLabel.setX(200);
        	scoreLabel.setY(300);
        	scoreLabel.setFontScale(2);
        	scoreLabel.setText("              Score: " + score + "\n     Press Enter to shop\nPress Space to try again");
        	hero.setMaxSpeed(0);
        	if (Gdx.input.isKeyPressed(Keys.SPACE)) {
        		LevelScreen levelScreen = new LevelScreen();
        		ApsGame.setActiveScreen(levelScreen);
        		levelScreen.coins = coins;
        		levelScreen.levelBoots = levelBoots;
        		levelScreen.levelInventory = levelInventory;
        		levelScreen.levelTime = levelTime;
        		levelScreen.speed = speed;
        		levelScreen.n = n;
        		levelScreen.inventorySize = inventorySize;
        	}
        	if (Gdx.input.isKeyPressed(Keys.ENTER)) {
        		ShopScreen shopScreen = new ShopScreen(); 
        		ApsGame.setActiveScreen(shopScreen);
        		shopScreen.coins = coins;
        		shopScreen.levelBoots = levelBoots;
        		shopScreen.levelInventory = levelInventory;
        		shopScreen.levelTime = levelTime;
        		shopScreen.speed = speed;
        		shopScreen.n = n;
        		shopScreen.inventorySize = inventorySize;
        	}
        	}
        		
        }
      

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}

}