package escapyPhysics;

import org.newdawn.slick.GameContainer;

import cern.colt.matrix.ObjectMatrix3D;

public class EscapyPhysicsBase extends EscapyPhysicsSuper {

	private static Thread PHYS_THREAD;
	private static boolean ended = false;
	
	public static void initPhysic(ObjectMatrix3D areaMap, GameContainer cont)
	{
		addMap(areaMap);
		EscapyPhysicsPlayerControls.initInputs(cont);
		addInputs(EscapyPhysicsPlayerControls.getInp());
		PHYS_THREAD = new Thread(new EscapyPhysicsCalculationsThread());
		PHYS_THREAD.start();
		physicsOn = true;
		ended = false;
		System.gc();
	}
	
	public static void closePhysic()
	{
		physicsOn = false;
		while (!PHYS_THREAD.isInterrupted()) {}
		EscapyPhysicsPlayerControls.stopThread();
		ended = true;
	}
	
	public static void endPhysics()
	{
		closePhysic();
		eventObject.clear();
		physObject.clear();
		System.gc();
	}
	
	public static boolean isEnded(){
		return ended;
	}

}
