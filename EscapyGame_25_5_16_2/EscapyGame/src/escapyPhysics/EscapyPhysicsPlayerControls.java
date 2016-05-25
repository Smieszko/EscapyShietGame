package escapyPhysics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class EscapyPhysicsPlayerControls implements Runnable{

	private static Input phIn;
	private static boolean isEnd = false;
	private static boolean jumping = false;
	private static boolean flyin = false;
	private static boolean space = false;
	protected static Thread inpTr = new Thread(new EscapyPhysicsPlayerControls());
	
	
	public static void initInputs(GameContainer cont)
	{
		phIn = cont.getInput();
		
		inpTr.start();
		isEnd = false;
	}
	public static void stopThread()
	{
		isEnd = true;
		while (inpTr.isInterrupted()){};
	}
	public static Input getInp()
	{
		return phIn;
	}
	public static boolean checkSpace()
	{
		try {
			 if (phIn.isKeyDown(Input.KEY_SPACE))
				{
					 if(!flyin && !space)
					 {
						 setSpace(true);
					 }
					 else setSpace(false);
				}
				 else setSpace(false);
			
		} catch (IllegalStateException ilxcp) {}
		 return isSpace();
	}

	@Override
	public void run() 
	{
		while(!isEnd)
		{
			checkSpace();
			//System.out.println("work");
			try {
				Thread.sleep(0,10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Thread.currentThread().interrupt();	
	}
	public static boolean isSpace() {
		return space;
	}
	public static void setSpace(boolean space) {
		EscapyPhysicsPlayerControls.space = space;
	}
	public static boolean isJumping() {
		return jumping;
	}
	public static void setJumping(boolean jumping) {
		EscapyPhysicsPlayerControls.jumping = jumping;
	}
	public static boolean isFlyin() {
		return flyin;
	}
	public static void setFlyin(boolean flyin) {
		EscapyPhysicsPlayerControls.flyin = flyin;
	}

}
