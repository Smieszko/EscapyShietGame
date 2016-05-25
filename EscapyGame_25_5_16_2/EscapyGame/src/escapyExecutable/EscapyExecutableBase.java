package escapyExecutable;

import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import cern.colt.matrix.ObjectMatrix3D;
import utilsAndConstants.GameConstants;

public class EscapyExecutableBase {

	private static ObjectMatrix3D map;
	protected static Input mouseInpt;
	private static boolean pressedInArea = false;
	private static int[] mXY = new int[]{0,0};
	protected static boolean uCanShow = false;
	private static Font imgFont;
	private static HashMap<Integer, String[]> optionsMap;
	private static HashMap<Integer, Short> typeMap;
	protected static int actualOptionType = 0;
	private static short actualID = 0;
	private static Graphics graphics;
	
	public static void initTriggers(ObjectMatrix3D m, GameContainer container, Font font)
	{
		map = m;
		mouseInpt = container.getInput();
		imgFont = font;
		EscapyExecutableMenu.active.setFont(imgFont);
		EscapyExecutableMenu.active.setColor(Color.red);
		EscapyExecutableMenu.inactive.setFont(imgFont);
		EscapyExecutableMenu.inactive.setColor(new Color(133, 133, 133, 255));
		optionsMap = EscapyExecutableUtils.createHashMapOfOptionArray();
		typeMap = EscapyExecutableUtils.createHashMapOfOptionTypes();
		graphics = container.getGraphics();
	}
	
	public static void updTriggers()
	{
		try 
		{
			if ((map.get((int)(mouseInpt.getMouseX()/GameConstants.scaleRatio()+GameConstants.translationX()), 
					(int) (mouseInpt.getMouseY()/GameConstants.scaleRatio()+GameConstants.translationY()), 0)) != null
					&& Byte.toUnsignedInt((byte)map.get((int) (mouseInpt.getMouseX()/GameConstants.scaleRatio()
							+GameConstants.translationX()), (int) (mouseInpt.getMouseY()/GameConstants.scaleRatio()
							+GameConstants.translationY()), 0)) == 2  && !pressedInArea)
			{
				EscapyExecutableMenu.overTheArea = true;
				if (mouseInpt.isMousePressed(Input.MOUSE_LEFT_BUTTON))
				{
					pressedInArea = true;
					mXY[0] = (int) (mouseInpt.getMouseX()/GameConstants.scaleRatio()
							+GameConstants.translationX());
					mXY[1] = (int) (mouseInpt.getMouseY()/GameConstants.scaleRatio()
							+GameConstants.translationY());
					actualID = (short)map.get((int) (mouseInpt.getMouseX()/GameConstants.scaleRatio()
							+GameConstants.translationX()), (int) (mouseInpt.getMouseY()/GameConstants.scaleRatio()
							+GameConstants.translationY()), 1);
					EscapyExecutableMenu.overTheArea = false;
				}
				actualOptionType = Byte.toUnsignedInt((byte)map.get((int) (mouseInpt.getMouseX()/GameConstants.scaleRatio()
						+GameConstants.translationX()), (int) (mouseInpt.getMouseY()/GameConstants.scaleRatio()
						+GameConstants.translationY()), 2));
			}
			else EscapyExecutableMenu.overTheArea = false;
			
		} catch (IndexOutOfBoundsException exxs) {}
		
		if (GameConstants.isMouseReleased())
		{
			//cont.setMouseGrabbed(false);
			pressedInArea = false;
			mXY[0] = 0;
			mXY[1] = 0;
			if (uCanShow && EscapyExecutableMenu.selectedOption != Integer.MAX_VALUE)
				EscapyExecutableObjects.action(typeMap.get(actualOptionType),
						optionsMap.get(actualOptionType)[EscapyExecutableMenu.selectedOption], graphics, actualID);
			uCanShow = false;
		}
		if (pressedInArea)
		{
			//cont.setMouseGrabbed(true);
			EscapyExecutableMenu.contextMenu(optionsMap.get(actualOptionType), mXY, 
					(int) (mouseInpt.getMouseY()/GameConstants.scaleRatio()), 
					(int) (mouseInpt.getMouseX()/GameConstants.scaleRatio()), imgFont);
			uCanShow = true;
		}
	}

}
