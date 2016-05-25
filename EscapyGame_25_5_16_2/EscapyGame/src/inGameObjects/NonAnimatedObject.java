package inGameObjects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import utilsAndConstants.GameConstants;

public class NonAnimatedObject extends InGameObject
{
	private Image staticObjectImage;

	public NonAnimatedObject(float x, float y, int id, String ImgUrl, double zoom, int typo) 
	{
		super(x,y,id,ImgUrl,zoom, typo);
	}

	@Override
	public void renderGraphic(Graphics g) 
	{
		g.drawImage(staticObjectImage, XPos()-GameConstants.translationX(), 
				YPos()-GameConstants.translationY());
	}

	@Override
	protected void initializeGraphic() 
	{
		try {
			staticObjectImage = (new Image(getImgUrl()[0], false, Image.FILTER_NEAREST)).
					getScaledCopy((float)zoom());
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
