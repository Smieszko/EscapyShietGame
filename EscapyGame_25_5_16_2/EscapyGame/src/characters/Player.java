package characters;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import escapyAnimator.EscapyAnimatorCharacter;
import escapyPhysics.EscapyPhysics;
import escapyPhysics.EscapyPhysicsEvent;
import escapyPhysics.EscapyPhysicsObjectDefault;
import escapyPhysics.EscapyPhysicsObjectSuper;
import escapyPhysics.EscapyPhysicsPlayerControls;
import escapyRenderable.EscapyCenterOfTheWorld;
import staticControl.PlayerControl;
import utilsAndConstants.GameConstants;

public class Player extends AbstractCharacters implements EscapyAnimatorCharacter, 
	EscapyPhysicsEvent, EscapyCenterOfTheWorld {

	private Image playerImage;
	private Player player;
	private Image[] actualImage;
	
	private EscapyPhysicsObjectDefault physBody;
	
	public Player(ArrayList<String>[] urls, ArrayList<Integer>[] times, float zoom) 
	{
		super(urls, times, zoom);
		player = this;
		player.setXPos(300);
		setWidht(playerImage.getWidth());
		setHeight(playerImage.getHeight());
		physBody = new EscapyPhysicsObjectDefault(widht(), height(), mass(), XPos(), YPos(), this);
		physBody.setCalculation(true);
		addAnimatedCharacter(this);
		initCharacterAnimator(this);
	}

	@Override
	protected void initializeGraphic() 
	{
		playerImage = ((standImg[0]).getScaledCopy(zoom()));
	}
	
	@Override
	public void renderGraphic(Graphics g)
	{
		if(lastD())
			player.setFrame0(actualImage[actualFrame]);
		else if (lastA())
			player.setFrame180(actualImage[actualFrame]);
		
		g.drawImage(player.playerImage, player.XPos()-GameConstants.translationX(), 
				player.YPos()-GameConstants.translationY());
	}

	@Override
	public void defineStandAnimation() 
	{
		if (!PlayerControl.IS_MOVING())
		{
			if (!lastStand) {actualFrame = 0;}
			lastStand = true;
			actualImage = animation(standImg, stand);
		}else {lastStand = false;}
	}

	@Override
	public void defineMovAnimation() 
	{
		if(!lastJump && !lastFall && PlayerControl.down_D() & !PlayerControl.down_KEY_LSHIFT())
		{
			if (!EscapyPhysicsPlayerControls.isJumping() && !EscapyPhysicsPlayerControls.isFlyin()) 
			{
				if(!lastMov) {actualFrame = 0;}
				lastMov = true;
				actualImage = animation(walkImg, walk);
				setDlast();
			}
			
		}else if (!PlayerControl.down_D() && PlayerControl.down_KEY_LSHIFT()) {lastMov = false;}	
		
		if(!lastJump && !lastFall && PlayerControl.down_A() && !PlayerControl.down_KEY_LSHIFT())
		{
			if (!EscapyPhysicsPlayerControls.isJumping() && !EscapyPhysicsPlayerControls.isFlyin())
			{
				if(!lastMov) {actualFrame = 0;}
				lastMov = true;
				actualImage = animation(walkImg, walk);
				setAlast();
			}
			
		}else if (!PlayerControl.down_A() && PlayerControl.down_KEY_LSHIFT()) {lastMov = false;}	
	}

	@Override
	public void defineRunAnimation() 
	{
		if(!lastJump && !lastFall && PlayerControl.down_D() && PlayerControl.down_KEY_LSHIFT())
		{
			if (!EscapyPhysicsPlayerControls.isJumping() && !EscapyPhysicsPlayerControls.isFlyin())
			{
				if(!lastRun) {actualFrame = 0;}
				lastRun = true;
				actualImage = animation(runImg, run);
				setDlast();
			}
			
		}else if (!PlayerControl.down_D() && !PlayerControl.down_KEY_LSHIFT()) {lastRun = false;}	
		
		if(!lastJump && !lastFall && PlayerControl.down_A() && PlayerControl.down_KEY_LSHIFT())
		{
			if (!EscapyPhysicsPlayerControls.isJumping() && !EscapyPhysicsPlayerControls.isFlyin()) 
			{
				if(!lastRun) {actualFrame = 0;}
				lastRun = true;
				actualImage = animation(runImg, run);
				setAlast();
			}
			
		}else if (!PlayerControl.down_A() && !PlayerControl.down_KEY_LSHIFT()) {lastRun = false;}	

		
	}

	@Override
	public void defineJumpAnimation() 
	{
		if (EscapyPhysicsPlayerControls.isJumping() && !EscapyPhysicsPlayerControls.isFlyin())
		{
			lastFall = false;
			if (!lastJump) {actualFrame = 0;}
			lastJump = true;
			actualImage = flyAnimation(jumpImg, jump);
			if (PlayerControl.down_D())
				setDlast();
			else if (PlayerControl.down_A())
				setAlast();
		} else if (!EscapyPhysicsPlayerControls.isJumping() && !EscapyPhysicsPlayerControls.isFlyin()) 
			lastJump = false;
		
		if (!EscapyPhysicsPlayerControls.isJumping() && EscapyPhysicsPlayerControls.isFlyin())
		{
			lastJump = false;
			if (!lastFall) {actualFrame = 0;}
			lastFall = true;
			actualImage = flyAnimation(fallImg, fall);
			if (PlayerControl.down_D())
				setDlast();
			else if (PlayerControl.down_A())
				setAlast();
		} else if (!EscapyPhysicsPlayerControls.isFlyin() && !EscapyPhysicsPlayerControls.isFlyin()) 
			lastFall = false;
	}

	@Override
	public void defineInteractAnimation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defineOtherAnimation() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void InterruptAnimator(EscapyAnimatorCharacter character) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setFrame0(Image image)
	{
		player.playerImage = image.getScaledCopy(zoom());
	}
	@Override
	protected void setFrame180(Image image)
	{
		player.playerImage = (image.getScaledCopy(zoom())).getFlippedCopy(true, false);
	}

	
	
	@Override
	public void definePhysicalSystem(EscapyPhysicsObjectSuper physObject) 
	{
		physObject.xpos = +300;
		physObject = EscapyPhysics.initDefaultGravityAcceleration(physObject);
		physObject = EscapyPhysics.initDefaultMov(physObject, 1f, 11f, 120);
		physObject = EscapyPhysics.initDefaultPhysicalMap(physObject, 0.52f, 0.875f);
	}

	@Override
	public void physicalCalculations(EscapyPhysicsObjectSuper physObject) 
	{
		physObject = EscapyPhysics.defaultGravity(physObject);
		physObject = EscapyPhysics.defaultMovement(physObject, PlayerControl.down_A(),
				PlayerControl.down_D(), PlayerControl.down_KEY_LSHIFT(), 
				EscapyPhysicsPlayerControls.isSpace());
	}

	@Override
	public void physicalEvent(float xpos, float ypos, float mass, float tetha,
			EscapyPhysicsObjectSuper physObject) 
	{
		//System.out.println(xpos);
		player.setXPos(xpos);
		player.setYPos(ypos);
		GameConstants.setPlayer_pos(new int[]{(int)(xpos + physObject.width*0.52f),
				(int)(ypos + physObject.height*0.48f)});
	}

	@Override
	public void translateTheKraken()
	{
		if ((float)(player.XPos()) > (float)((float)GameConstants.getFrameWIDHT()/
				(2.75f*GameConstants.scaleRatio())))
		{
			GameConstants.setTranslationX((int)
					((int)player.XPos()-((float)GameConstants.getFrameWIDHT()/
							(2.75f*GameConstants.scaleRatio()))));
		}
	//	if ((float)(player.YPos()) < (float)((float)GameConstants.getFrameHEIGHT()/2.5f))
	//	{
			//GameConstants.setTranslationY((int)
			//		((int)player.YPos()-((float)GameConstants.getFrameHEIGHT()/2.5f)));
		//}
	
	}

	


	
	
	

}
