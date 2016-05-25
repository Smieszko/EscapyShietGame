package escapyExecutable;

public interface EscapyExecutable {

	public static final int JUST_OBJECT = 0;
	public static final int DOOR = 1;
	public static final int ELEVEATOR = 2;
	public static final int STATIC_NPC = 3;
	
	public static String nameByType(int type)
	{
		switch (type) 
		{
		case JUST_OBJECT:
			return "Just object";
			
		case DOOR:
			return "Door";
			
		case ELEVEATOR:
			return "Elevator";
			
		case  STATIC_NPC:
			return "Living being";
		}
		return " ";
	}
	
}
