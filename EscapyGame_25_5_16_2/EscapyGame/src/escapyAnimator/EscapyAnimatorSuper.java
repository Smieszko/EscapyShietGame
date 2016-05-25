package escapyAnimator;

import java.util.ArrayList;

public abstract class EscapyAnimatorSuper implements EscapyAnimator {

	protected volatile static boolean[] finished = new boolean[]{false, false};
	protected volatile static boolean[] stopItNow = new boolean[]{false, false};
	protected static ArrayList<EscapyAnimator> animatedList = new ArrayList<>();
	protected static ArrayList<Integer> indexOfList = new ArrayList<>();
	
	public EscapyAnimatorSuper() {
		
	}

	public void addAnimated(EscapyAnimator object)
	{
		if (object instanceof EscapyAnimatorCharacter)
			System.out.println(":::one:::");
		if (object instanceof EscapyAnimatorObject)
			System.out.println(":::two:::");
		animatedList.add(object);
		indexOfList.add(new Integer(EscapyAnimator.INTERRUPTED));
		System.gc();
	}
	
	public void removeAnimated(EscapyAnimator object)
	{
		animatedList.remove(object);
		indexOfList.remove(animatedList.indexOf(object));
		System.gc();
	}
	
	public void launchAnimated(EscapyAnimator object)
	{
		indexOfList.set(animatedList.indexOf(object), EscapyAnimator.ACTIVE);
		System.gc();
	}
	
	public void interruptAnimated(EscapyAnimator object)
	{
		indexOfList.set(animatedList.indexOf(object), EscapyAnimator.INTERRUPTED);
		System.gc();
	}
	
	public static Thread initAnimator(Runnable object, Thread obThread)
	{
		obThread = new Thread(object);
		obThread.start();
		return obThread;
	}
	
	
}
