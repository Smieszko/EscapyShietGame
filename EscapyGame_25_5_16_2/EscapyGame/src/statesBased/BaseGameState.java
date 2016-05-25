package statesBased;
import java.io.IOException;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.InputListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import characters.InitCharacters;
import escapyAnimator.EscapyAnimatorBase;
import escapyExecutable.EscapyExecutableBase;
import escapyExecutable.EscapyExecutableDialogs;
import escapyExecutable.EscapyExecutableMenu;
import escapyGame.InitMap;
import escapyPhysics.EscapyPhysicsBase;
import escapyRenderable.EscapyCenterOfTheWorld;
import escapyRenderable.EscapyRenderable;
import staticControl.PlayerControl;
import utilsAndConstants.GameConstants;
import utilsAndConstants.GameUtils;

public class BaseGameState extends BasicGameState implements InputListener{
	
	private boolean exitstat = false;
	private InitMap src;
	private InitCharacters character;
	private String stat = "";
	private int ID, trueID;
	
	public BaseGameState(int iD, int trueiD) 
	{
		ID = iD;
		trueID = trueiD;
		System.out.println("id: "+ID+"  actual:"+GameConstants.ActualiD()+"   trueID:  "+trueID);
	}

	private void exitIfneed(boolean need, GameContainer container)
	{
		if(need){
			container.exit();
			System.exit(0);
	}}
	
	private void renderGameObjects(Graphics g)
	{
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < src.size()[src.indexTab()[i]]; j++)
			{
				if (src.gameObjects()[src.indexTab()[i]][j] instanceof EscapyRenderable)
					src.gameObjects()[src.indexTab()[i]][j].renderGraphic(g);
			}
		EscapyExecutableMenu.showContextMenu(g);
		if (character.player() instanceof EscapyRenderable)
			character.player().renderGraphic(g);
		
		for (int i = 0; i < character.npc().length; i++)
		{
			if (character.npc()[i] instanceof EscapyRenderable)
				character.npc()[i].renderGraphic(g);
		}
		EscapyExecutableMenu.showPointerInfo(g);
	}

	@Override
	public void init(GameContainer container, StateBasedGame sBG) throws SlickException 
	{
		LoadingList.setDeferredLoading(true);
		GameConstants.defineScreenRes(container.getScreenWidth(), container.getScreenHeight());
		
		src = new InitMap(ID);
		character = new InitCharacters(container);
		
		EscapyPhysicsBase.initPhysic(src.map(), container);
		
		EscapyAnimatorBase.initAnimator();
		
		EscapyExecutableBase.initTriggers(src.map(), container, (container.getGraphics()).getFont());
		
		System.gc();
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame sBG, Graphics g) throws SlickException 
	{
		g.scale(GameConstants.scaleRatio(), GameConstants.scaleRatio());
		src.backGround().renderGraphic(g);
		//g.drawString(stat, 40, 40);
		renderGameObjects(g);
		GameUtils.drawAreas(src.map());
		EscapyExecutableDialogs.drawInLineMonolog(g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame sBG, int dlt) throws SlickException 
	{
		exitIfneed(exitstat,container);
		PlayerControl.keyboard_upd();
		
		if (character.player() instanceof EscapyCenterOfTheWorld)
			character.player().translateTheKraken();
		
		EscapyExecutableBase.updTriggers();
    	/*
		if (LoadingList.get().getRemainingResources() > 0) { 
	        DeferredResource nextResource = LoadingList.get().getNext(); 
	        try {
				nextResource.load();
				stat = nextResource.getDescription();
			} catch (IOException e) {e.printStackTrace();}  
	    } 
		else // XXX UPD VVV
	    {   
			stat = "";
	    }
	    */
	}
	
	@Override
	public void mousePressed(int arg0, int arg1, int arg2) 
	{
		GameConstants.setMousePressed(true);
	};
	
	@Override
	public void mouseReleased(int arg0, int arg1, int arg2) 
	{
		GameConstants.setMouseReleased(true);
	};

	@Override
	public int getID() 
	{
		return GameConstants.GAME;
	}

}
