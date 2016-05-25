package inGameObjects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import escapyAnimator.EscapyAnimatorObject;
import escapyExecutable.EscapyExecutableObjects;
import utilsAndConstants.GameConstants;

public class AnimatedObject extends InGameObject implements EscapyExecutableObjects {

	private int[] animPeriod;
	private Image objectImage, frame;
	private AnimatedObject animob;
	private EscapyAnimatorObject animator;
	private int actualFrame;
	
	public AnimatedObject(float x, float y, int id, String ImgUrl, int[] AnimPeriod, double zoom,
			int typo)
	{
		super(x,y,id,ImgUrl,zoom, typo); 
		animPeriod = AnimPeriod; 
		animob = this;   
		actualFrame = 0;
		
		animator = new EscapyAnimatorObject()          
		{     
			private long time0 = System.nanoTime();         
			private int currentFrame = 0;          
			
			@Override        
			public void defineAnimation()              
			{       
				long time1 = System.nanoTime() - time0;         
				if (currentFrame > 9)         
					currentFrame = 0;        
				if ((time1/1000000) >= getAnimPeriod()[currentFrame])         
				{    
					time0 = System.nanoTime();          
					currentFrame++;         
				}   
				animob.setActualFrame(currentFrame); //FIXME
			}

			@Override     
			public void InterruptAnimator(EscapyAnimatorObject object)     
			{      
				if (animob.getType() == objectType.Interactive && currentFrame == 9)    
				{	    
					interruptObjectAnimation(object);      
					currentFrame = 0;
					System.gc();
				}   
			}   
		};    
		
		addEscapyObjectAnimator(animator);  
		initObjectAnimator(animator);
		EscapyExecutableObjects.initExecutableObject((short)getID(), this);
		
	} 

	@Override
	public void renderGraphic(Graphics g) 
	{
		animob.setFrame(animob.getObImage().getSubImage((int)      
				((double)animob.getObImage().getWidth()/10.)       
				*(animob.getActualFrame()), 0, (int)((double)animob.getObImage().getWidth()/10.),      
				animob.getObImage().getHeight()));      
		
		g.drawImage(animob.frame, (float)animob.XPos()-GameConstants.translationX(), 
				(float)animob.YPos()-GameConstants.translationY());
	}

	@Override
	protected void initializeGraphic() 
	{
		try {
			objectImage = new Image(getImgUrl()[0], false, Image.FILTER_NEAREST);
			frame = objectImage.getSubImage(0, 0, (int)((double)objectImage.getWidth()/10.), 
					objectImage.getHeight());
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void escapyAction() 
	{
		startObjectAnimator(animator);
		
	}

	
	private int[] getAnimPeriod() {
		return animPeriod;
	}
	private Image getObImage() {
		return objectImage;
	}
	private void setFrame(Image fram) {
		frame = fram.getScaledCopy((float)zoom());
	}

	private int getActualFrame() {
		return actualFrame;
	}

	private void setActualFrame(int actualFrame) {
		this.actualFrame = actualFrame;
	}
	
}
