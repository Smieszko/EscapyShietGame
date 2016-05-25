package utilsAndConstants;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

public final class GameConstants 
{
	private static int gameXsize, gameYsize;
	private static boolean start = false;
	private static LinkedList<String> UrlSourceList = new LinkedList<>();
	private static LinkedList<Integer> iDindexList = new LinkedList<>();
	private static LinkedList<String> mapList = new LinkedList<>();
	private static String location ="";
	private static int SCREEN_WIDTH, SCREEN_HEIGHT;
	private static boolean flipped = false;
	private static double scaleRatio = 1;
	private static double defaultScale = 1;//1;
	private static int actualiD = 0;
	public static final int MENU = (int)Short.MAX_VALUE;
	public static final int GAME = (int)(Short.MAX_VALUE-1);
	private static boolean mapVisible = false;
	private static int[] player_pos = new int[2];
	
	private static int translationX  = 0;
	private static int translationY = 0;
	
	private static boolean mousePressed = false, mouseReleased = false;
	
	public GameConstants() 
	{
		
	}
	
	public static void setLocationNumb(int numb)
	{
		//locationNumb = numb;
	}
	public static int locationNumb()
	{
		return UrlSourceList.size();
	}
	
	public static void setDefaultScale(double scalez)
	{
		defaultScale = scalez;
	}

	public static double zoomScaleRatio()
	{
		return scaleRatio;
	}
	public static void setZoomScaleRatio(double scl)
	{
		scaleRatio = scl;
	}
	public static float scaleRatio()
	{
		return (float)(scaleRatio*defaultScale);
	}
	
	public static int getFrameWIDHT()
	{
		if ((gameXsize*scaleRatio()) < SCREEN_WIDTH)
			return (int) (gameXsize*scaleRatio());
		else return SCREEN_WIDTH;
	}
	public static int getFrameHEIGHT()
	{
		if ((gameYsize*scaleRatio()) < SCREEN_HEIGHT)
			return (int) (gameYsize*scaleRatio());
		else return SCREEN_HEIGHT;
	}
	
	public static void defineScreenRes(int x, int y)
	{
		SCREEN_WIDTH = x;
		SCREEN_HEIGHT = y;
	}
	public static int getScreenWIDTH()
	{
		return SCREEN_WIDTH;
	}
	public static int getScreenHEIGHT()
	{
		return SCREEN_HEIGHT;
	}
	
	public static double[] getMapSize()
	{
		return new double[]{getGameXsize(), getGameYsize()};
	}
	
	public static int getGameXsize() {
		return gameXsize;
	}

	public static void setGameXsize(int gameXsize) {
		GameConstants.gameXsize = gameXsize;
	}

	public static int getGameYsize() {
		return gameYsize;
	}

	public static void setGameYsize(int gameYsize) {
		GameConstants.gameYsize = gameYsize;
	}

	public static boolean Start() {
		return start;
	}

	public static void setStart(boolean starts) {
		start = starts;
	}

	public static LinkedList<String> UrlSourceList() {
		return UrlSourceList;
	}

	public static void setUrlSourceList(LinkedList<String> urlSourceList) {
		UrlSourceList = urlSourceList;
	}

	public static String Location() {
		return location;
	}

	public static void setLocation(String location) {
		GameConstants.location = location;
	}

	public static void setGameSizeFF() 
	{
		try {
			RandomAccessFile raf = new RandomAccessFile(new File(Location()+"_.gmd"), "r");
			raf.seek(0);
			setGameXsize(raf.readShort());
			setGameYsize(raf.readShort());
			raf.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static boolean isFlipped() {
		return flipped;
	}

	public static void setFlipp(boolean flipped) {
		GameConstants.flipped = flipped;
	}

	public static LinkedList<Integer> getiDindexList() {
		return iDindexList;
	}

	public static void setiDindexList(LinkedList<Integer> iDindexList) {
		GameConstants.iDindexList = iDindexList;
	}

	public static LinkedList<String> getMapList() {
		return mapList;
	}

	public static void setMapList(LinkedList<String> mapList) {
		GameConstants.mapList = mapList;
	}

	public static int ActualiD() {
		return actualiD;
	}

	public static void setActualiD(int actualiD) {
		GameConstants.actualiD = actualiD;
	}

	public static int translationX() {
		return translationX;
	}

	public static void setTranslationX(int translationX) {
		GameConstants.translationX = translationX;
	}

	public static int translationY() {
		return translationY;
	}

	public static void setTranslationY(int trasnlationY) {
		GameConstants.translationY = trasnlationY;
	}

	public static boolean isMapVisible() {
		return mapVisible;
	}

	public static void setMapVisible(boolean mapVisible) {
		GameConstants.mapVisible = mapVisible;
	}

	public static boolean isMousePressed()
	{
		return mousePressed;
	}

	public static void setMousePressed(boolean mousePressed) 
	{
		GameConstants.mousePressed = mousePressed;
		GameConstants.mouseReleased = false;
	}

	public static boolean isMouseReleased() 
	{
		return mouseReleased;
	}

	public static void setMouseReleased(boolean mouseReleased) 
	{
		GameConstants.mouseReleased = mouseReleased;
		GameConstants.mousePressed = false;
	}

	public static int[] getPlayer_pos() {
		return player_pos;
	}

	public static void setPlayer_pos(int[] player_pos) {
		GameConstants.player_pos = player_pos;
	}
	

}
