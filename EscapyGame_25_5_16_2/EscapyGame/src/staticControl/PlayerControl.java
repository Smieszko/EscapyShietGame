package staticControl;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class PlayerControl {

	private static boolean KEY_D_Down = false;
	private static boolean KEY_A_Down = false;
	private static boolean KEY_SPACE_Pressed = false;
	private static boolean IS_MOVING = false;
	private static boolean KEY_SHIFT_Down = false;
	private static boolean KEY_F_Pressed = false;
	private static boolean KEY_ESC_Pressed = false;
	private static boolean keyboardStop = true;
	
	static Input input;
	
	public static void initKeyBoard(GameContainer container) throws SlickException 
	{
		input = container.getInput();
		keyboardStop = false;
	}
	
	public static void keyboard_upd()
	{
		try {
			if (input.isKeyDown(Input.KEY_D))
				KEY_D_Down = true;
			else
				KEY_D_Down = false;
			
			
			if( input.isKeyDown(Input.KEY_A))
				KEY_A_Down = true;
			else 
				KEY_A_Down = false;
			
			
			if(input.isKeyPressed(Input.KEY_SPACE))
				KEY_SPACE_Pressed = true;
			else 
				KEY_SPACE_Pressed = false;
			
			
			if(input.isKeyDown(Input.KEY_LSHIFT))
				KEY_SHIFT_Down = true;
			else
				KEY_SHIFT_Down = false;
			
			
			if (KEY_D_Down == true || KEY_A_Down == true || KEY_SPACE_Pressed == true)
				IS_MOVING = true;
			else
				IS_MOVING = false;
			
			
			if (input.isKeyPressed(Input.KEY_F))
				KEY_F_Pressed = true;
			else 
				KEY_F_Pressed = false;
			
			
			if(input.isKeyPressed(Input.KEY_ESCAPE))
				KEY_ESC_Pressed = true;
			else
				KEY_ESC_Pressed = false;
			
		} catch (IllegalStateException keyscep){}
		
	}
	
	public static boolean down_A()
	{
		return KEY_A_Down;
	}
	public static boolean down_D()
	{
		return KEY_D_Down;
	}
	public static boolean down_SPACE()
	{
		return KEY_SPACE_Pressed;
	}
	public static boolean down_KEY_LSHIFT()
	{
		return KEY_SHIFT_Down;
	}
	public static boolean down_KEY_F()
	{
		return KEY_F_Pressed;
	}
	public static boolean IS_MOVING()
	{
		return IS_MOVING;
	}
	public static boolean pressed_ESC()
	{
		return KEY_ESC_Pressed;
	}

	public static boolean isKeyboardStop() {
		return keyboardStop;
	}
	public static void setKeyboardStop(boolean keyboardStop) {
		PlayerControl.keyboardStop = keyboardStop;
	}
	
	
}
