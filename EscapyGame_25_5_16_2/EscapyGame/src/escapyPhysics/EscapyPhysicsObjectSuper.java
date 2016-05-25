package escapyPhysics;

public abstract class EscapyPhysicsObjectSuper extends EscapyPhysicsSuper
	implements EscapyPhysics {

	public float width, height, mass, xpos, ypos, Vx, Vy, tetha, ax, ay, Fx, Fy, px, py;
	private EscapyPhysicsEvent obj;
	public EscapyPhysicsObjectSuper thisob;
	protected EscapyPhysicsConstants variables;
	
	public EscapyPhysicsObjectSuper(float w, float h, float m, float xp, float yp) 
	{
		width = w;
		height = h;
		mass = m;
		xpos = xp;
		ypos = yp;
		variables = new EscapyPhysicsConstants();
	}
	public EscapyPhysicsObjectSuper()
	{
		
	}

	public void setCalculation(boolean calculation)
	{
		if (calculation)
		{
			obj.definePhysicalSystem((EscapyPhysicsObjectSuper) thisob);
			addToCalcList(obj, thisob);
		}
			
		else if (!calculation)
			removeFromCalcList(obj, thisob);
	}
	protected void setObject(EscapyPhysicsEvent obj, EscapyPhysicsObjectSuper thisob)
	{
		this.obj = obj;
		this.thisob = thisob;
	}

	public EscapyPhysicsConstants var()
	{
		return variables;
	}
	
	public float width() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float height() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float mass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public float xpos() {
		return xpos;
	}

	public void setXpos(float xpos) {
		this.xpos = xpos;
	}

	public float ypos() {
		return ypos;
	}

	public void setYpos(float ypos) {
		this.ypos = ypos;
	}

	public float getVx() {
		return Vx;
	}

	public void setVx(float vx) {
		Vx = vx;
	}

	public float getVy() {
		return Vy;
	}

	public void setVy(float vy) {
		Vy = vy;
	}
}

