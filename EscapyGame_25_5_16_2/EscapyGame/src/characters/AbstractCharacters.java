package characters;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import escapyAnimator.EscapyAnimatorSuperCharacter;
import escapyRenderable.EscapyRenderable;

public abstract class AbstractCharacters extends EscapyAnimatorSuperCharacter implements EscapyRenderable {

	private float XPos, YPos;
	private float height, widht;
	private float mass;
	private float Vx, Vy;
	private float zoom;
	
	private int HP = 100;
	private int MP = 100;
	private int SP = 100;
	
	protected Image[] standImg;
	protected Image[] walkImg;
	protected Image[] runImg;
	protected Image[] jumpImg;
	protected Image[] fallImg;
	protected Image[] landImg;
	
	protected int[] stand, walk,
		run, jump, fall, land;
	
	protected boolean lastWasA = false, lastWasD = true, 
		lastMov = false, lastRun = false, lastStand = false,
		lastJump = false, lastFall = false, lastLand = false, jumped = false;
	
	protected int actualFrame;
	protected long time0 = 0;
	
	public AbstractCharacters(ArrayList<String>[] urls, ArrayList<Integer>[] times, float zoom) 
	{
		super();
		fillDataTabs(urls, times, zoom);
		initializeGraphic();
	}
	
	protected abstract void initializeGraphic();
	
	protected abstract void setFrame0(Image image);

	protected abstract void setFrame180(Image image);
	
	private void fillDataTabs(ArrayList<String>[] urls, ArrayList<Integer>[] times, float zoom)
	{
		standImg = listToImg(urls[0]);
		walkImg = listToImg(urls[1]);
		runImg = listToImg(urls[2]);
		jumpImg = listToImg(urls[3]);
		fallImg = listToImg(urls[4]);
		landImg = listToImg(urls[5]);
		stand = listToArray(times[0]);
		walk = listToArray(times[1]);
		run = listToArray(times[2]);
		jump = listToArray(times[3]);
		fall = listToArray(times[4]);
		land = listToArray(times[5]);
		this.zoom = zoom;
		this.actualFrame = 0;
	}
	
	private Image[] listToImg(ArrayList<String> urls)
	{
		Image[] img = new Image[urls.size()];
		Iterator<String> iterator = urls.iterator();
		for (int i = 0; i < img.length; i++)
		{
			try {
				img[i] = new Image(iterator.next(), false, Image.FILTER_NEAREST);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		return img;
	}
	
	private int[] listToArray(ArrayList<Integer> times)
	{
		int[] timeTab = new int[times.size()];
		Iterator<Integer> iterator = times.iterator();
		for (int i = 0; i < timeTab.length; i++)
		{
			timeTab[i] = iterator.next().intValue();
		}
		return timeTab;
	}
	
	protected Image[] animation(Image[] imgg, int[] times)
	{
		long time1 = System.nanoTime() - time0;
		if ((float)(time1/1000000.f) >= times[actualFrame])
		{
			time0 = System.nanoTime();
			actualFrame++;
			if (actualFrame > imgg.length-1)
				actualFrame = 0;
		}
		return imgg;
	}
	protected Image[] flyAnimation(Image[] imgg, int[] times)
	{
		long time1 = System.nanoTime() - time0;
		if ((float)(time1/1000000.f) >= times[actualFrame])
		{
			time0 = System.nanoTime();
			actualFrame++;
			if (actualFrame > imgg.length-1)
				actualFrame -= 1;
		}
		return imgg;
	}
	
	protected void setJumped(boolean jumped)
	{
		this.jumped = jumped;
	}
	protected boolean areJumped()
	{
		if (jumped)
		{
			jumped = false;
			return true;
		}
		else 
			return false;
	}

	public float XPos() {
		return XPos;
	}

	public void setXPos(float xPos) {
		XPos = xPos;
	}

	public float YPos() {
		return YPos;
	}

	public void setYPos(float yPos) {
		YPos = yPos;
	}

	public float height() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float widht() {
		return widht;
	}

	public void setWidht(float widht) {
		this.widht = widht;
	}

	public float mass() {
		return mass;
	}

	public void setMass(float mass) {
		this.mass = mass;
	}

	public float Vx() {
		return Vx;
	}

	public void setVx(float vx) {
		Vx = vx;
	}

	public float Vy() {
		return Vy;
	}

	public void setVy(float vy) {
		Vy = vy;
	}

	public float zoom() {
		return zoom;
	}

	public void setZoom(float zoom) {
		this.zoom = zoom;
	}

	public int HP() {
		return HP;
	}

	public void setHP(int hP) {
		HP = hP;
	}

	public int MP() {
		return MP;
	}

	public void setMP(int mP) {
		MP = mP;
	}

	public int SP() {
		return SP;
	}

	public void setSP(int sP) {
		SP = sP;
	}

	public boolean lastA()
	{
		return lastWasA;
	}
	public boolean lastD()
	{
		return lastWasD;
	}
	protected void setAlast()
	{
		lastWasA = true;
		lastWasD = false;
	}
	protected void setDlast()
	{
		lastWasD = true;
		lastWasA = false;
	}
	
}
