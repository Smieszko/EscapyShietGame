package escapyPhysics;

import java.util.ArrayList;

import org.newdawn.slick.Input;

import cern.colt.matrix.ObjectMatrix3D;

public abstract class EscapyPhysicsSuper implements EscapyPhysics {

	protected static ArrayList<EscapyPhysicsEvent> eventObject = new ArrayList<>();
	protected static ArrayList<EscapyPhysics> physObject = new ArrayList<>();
	protected static ObjectMatrix3D areaMap;
	protected static Input phsInp;
	
	protected static boolean physicsOn = false;
	
	protected static void addToCalcList(EscapyPhysicsEvent object, EscapyPhysics physOb)
	{
		physObject.add(physOb);
		eventObject.add(object);
	}
	protected static void removeFromCalcList(EscapyPhysicsEvent object, EscapyPhysics physOb)
	{
		physObject.remove(physOb);
		eventObject.remove(object);
	}
	protected static void addMap(ObjectMatrix3D map)
	{
		areaMap = map;
	}
	protected static void addInputs(Input inp)
	{
		phsInp = inp;
	}
	


}
