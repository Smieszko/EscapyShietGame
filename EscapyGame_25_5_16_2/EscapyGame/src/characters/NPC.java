package characters;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import escapyAnimator.EscapyAnimatorCharacter;

public class NPC extends AbstractCharacters implements EscapyAnimatorCharacter{

	public NPC(ArrayList<String>[] urls, ArrayList<Integer>[] times, float zoom) 
	{
		super(urls, times, zoom);
		addAnimatedCharacter(this);
		initCharacterAnimator(this);
		
	}
	
	@Override
	protected void initializeGraphic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderGraphic(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defineStandAnimation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defineMovAnimation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defineRunAnimation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void defineJumpAnimation() {
		// TODO Auto-generated method stub
		
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
	protected void setFrame0(Image image) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setFrame180(Image image) {
		// TODO Auto-generated method stub
		
	}



}
