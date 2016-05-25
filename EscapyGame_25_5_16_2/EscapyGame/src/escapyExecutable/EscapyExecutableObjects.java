package escapyExecutable;

import java.util.HashMap;

import org.newdawn.slick.Graphics;

public interface EscapyExecutableObjects extends EscapyExecutable{

	public static final HashMap<Short, EscapyExecutableObjects> EXE_OBJECT_MAP = new HashMap<>();
	
	
	public static void action(int type, String option, Graphics g, short ID)
	{
		switch(type)
		{
		case JUST_OBJECT:
			//TODO
			break;
		
		case DOOR:
			if (option.equalsIgnoreCase("enter"))
				EXE_OBJECT_MAP.get(ID).escapyAction();
			if (option.equalsIgnoreCase("inspect"))
				EscapyExecutableDialogs.setDialogToPrint("Hero: \"Old fullmetal door\"", 3.f);
			if (option.equalsIgnoreCase("knock"))
				EscapyExecutableDialogs.setDialogToPrint("Hero: *knocks on the door*", 3.f);
			break;
		
		case ELEVEATOR:
			//TODO
			break;
		
		case STATIC_NPC:
			//TODO
			break;
		}
	}
	
	public void escapyAction();
	
	public static void initExecutableObject(short id, EscapyExecutableObjects exeobject)
	{
		EXE_OBJECT_MAP.put(id, exeobject);
	}
}
