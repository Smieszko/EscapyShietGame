package escapyGame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import escapyAnimator.EscapyAnimatorBase;
import escapyPhysics.EscapyPhysicsBase;
import launcher.LauncherClass;
import statesBased.BaseMenuState;
import statesBased.BaseGameState;
import utilsAndConstants.GameConstants;

public class MainGameClass extends StateBasedGame {

	private static int x,y;
	
	public MainGameClass(String GameName) 
	{
		super(GameName);
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException 
	{
		addState(new BaseMenuState());
		addState(new BaseGameState(GameConstants.getiDindexList().get(GameConstants.ActualiD()), 
				GameConstants.ActualiD())); //XXX ok
		enterState(GameConstants.GAME);
		System.gc();
		//enterState(GameConstants.ActualiD());
	}
	
	public static void reinit(GameContainer container, StateBasedGame sBG)
	{
		GameConstants.setActualiD(2);
		try {
			if (!EscapyAnimatorBase.isEnded())
			{
				EscapyAnimatorBase.endAnimator();
				EscapyPhysicsBase.endPhysics();
				sBG.addState(new BaseGameState(GameConstants.getiDindexList().get(GameConstants.ActualiD()), 
						GameConstants.ActualiD()));
				sBG.init(container);
				sBG.enterState(GameConstants.GAME);
			}
		} catch (SlickException e) {
			e.printStackTrace();
		}
		System.gc();
	}
	
	public static void main(String[] args) throws SlickException 
	{
		Thread launthread = new Thread(new Runnable() {
			
			@Override
			public void run() 
			{
				LauncherClass launcher = new LauncherClass();
				launcher.setVisible(true);
				while (!GameConstants.Start())
				{
					if (!GameConstants.Start())
					{
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				Thread.currentThread().interrupt();
			}
		});
		launthread.setPriority(Thread.MIN_PRIORITY);
		launthread.start();
		while (launthread.isAlive()){}
		System.gc();
		AppGameContainer game = new AppGameContainer(
				new MainGameClass("EscapyTestEngine"));

		x = GameConstants.getFrameWIDHT();
		y = GameConstants.getFrameHEIGHT();
		
		if (GameConstants.Start())
		{
			game.setDisplayMode(x, y, false);
			game.start();
		}
		System.gc();
	}

	

}
