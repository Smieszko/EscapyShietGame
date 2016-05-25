package inGameObjects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import utilsAndConstants.GameConstants;

public class BackGround extends InGameObject{

	private static final int DefinitelyNotID = Integer.MAX_VALUE;
	private static final int BACKGROUND = 6;
	private static final float X = 0, Y = 0;
	private Image background;
	
	public BackGround(String ImgUrl) 
	{
		super (X, Y, DefinitelyNotID, ImgUrl, calcBgrZoom(), BACKGROUND);
	}

	@Override
	public void renderGraphic(Graphics g) 
	{
		//TODO OPTIONAL MASKING ETC
		g.drawImage(background, X, Y);
	}

	@Override
	protected void initializeGraphic()
	{
		try {
			background = (new Image(getImgUrl()[0], GameConstants.isFlipped(),
					Image.FILTER_NEAREST)).getScaledCopy((float)zoom());
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void reInitializeGraphic()
	{
		setDefZoom(calcBgrZoom());
		initializeGraphic();
	}
	
	private static double calcBgrZoom()
	{
		double xScale = GameConstants.getFrameWIDHT()/(640.*GameConstants.scaleRatio());
		double yScale = GameConstants.getFrameHEIGHT()/(510.*GameConstants.scaleRatio());	
		return Math.max(xScale, yScale);		
	}

}
