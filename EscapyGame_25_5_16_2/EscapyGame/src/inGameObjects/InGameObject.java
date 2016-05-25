package inGameObjects;

import escapyAnimator.EscapyAnimatorObject;
import escapyAnimator.EscapyAnimatorSuperObject;
import escapyRenderable.EscapyRenderable;
import utilsAndConstants.GameUtils;

public abstract class InGameObject extends EscapyAnimatorSuperObject implements EscapyRenderable{

	private float xPos, yPos;
	private String[] imgUrl;
	private int ID;
	private double defZoom;
	private objectType type;
	
	public InGameObject(float x, float y, int iD, String imgurl, double defzoom, int typo) 
	{
		xPos = x;
		yPos = y;
		ID = iD;
		imgUrl = new String[]{imgurl};
		defZoom = defzoom;
		type = GameUtils.IntegerToObjectType(typo);
		initializeGraphic();
	}
	
	protected abstract void initializeGraphic();

	@Override   
	public void initObjectAnimator(EscapyAnimatorObject object)
	{
		if (this.getType() != objectType.Interactive)
			launchAnimated(object);
	}

	public enum objectType
	{
		Interactive(0), PassiveAnimated(1), PassiveStatic(2), FrontParallaxed(3),
		BackParallaxed(4), Background(5);
		
		private int type;
		
		private objectType(int typo)
		{
			type = typo;
		}
		public int getObjectType()
		{
			return type;
		}
		public String ObjectTypeName()
		{
			return toString();
		}
		public void setType(int typo)
		{
			type = typo;
		}
		public objectType getType()
		{
			return this;
		}
	}

	public float XPos()
	{
		return xPos;//*2;//*scaleRatio;
	}
	
	public float YPos()
	{
		return yPos;//*2;//*scaleRatio;
	}
	
	public float[] XYPos()
	{
		return new float[]{XPos(),YPos()};
	}
	
	public double getXpos() {
		return xPos;
	}

	public void setDefXpos(float xPos) {
		this.xPos = xPos;
	}

	public double getYpos() {
		return yPos;
	}

	public void setDefYpos(float yPos) {
		this.yPos = yPos;
	}

	public String[] getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String[] imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public double getDefZoom() {
		return defZoom;
	}
	
	public double zoom()
	{
		return defZoom;//*scaleRatio;
	}

	public void setDefZoom(double defZoom) {
		this.defZoom = defZoom;
	}

	public objectType getType() {
		return type;
	}

	public void setType(objectType type) {
		this.type = type;
	}

}
