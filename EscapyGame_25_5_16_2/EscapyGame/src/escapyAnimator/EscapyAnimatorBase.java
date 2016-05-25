package escapyAnimator;

public class EscapyAnimatorBase extends EscapyAnimatorSuper {

	private static Thread Objects_THREAD;
	private static Thread Characters_THREAD;
	private static boolean ended = false;
	
	public EscapyAnimatorBase() 
	{
		System.out.println(":::INCONSTRUCTOR:::");
	}
	
	public static void initAnimator()
	{
		Objects_THREAD = initAnimator(new EscapyAnimatorThreadObject(), Objects_THREAD);
		Characters_THREAD = initAnimator(new EscapyAnimatorThreadCharacter(), Characters_THREAD);
		finished[0] = false;
		finished[1] = false;
		stopItNow[0] = false;
		stopItNow[1] = false;
		ended = false;
		System.gc();
	}
	
	public static void closeAnimator()
	{
		stopItNow[0] = true;
		stopItNow[1] = true;
		finished[0] = true;
		finished[1] = true;
		while (!Objects_THREAD.isInterrupted() && !Characters_THREAD.isInterrupted()){}
		
		ended = true;
	}
	
	public static void endAnimator()
	{
		closeAnimator();
		animatedList.clear();
		indexOfList.clear();
		System.gc();
	}

	public static boolean isEnded() {
		return ended;
	}

}
