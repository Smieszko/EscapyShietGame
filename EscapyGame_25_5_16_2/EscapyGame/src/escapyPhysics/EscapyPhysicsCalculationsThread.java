package escapyPhysics;


public class EscapyPhysicsCalculationsThread extends EscapyPhysicsSuper
	implements EscapyPhysics, Runnable{

	
	public EscapyPhysicsCalculationsThread() {
		
	}

	@Override
	public void run() 
	{
		while (physicsOn)
		{
			for (int i = 0; i < eventObject.size(); i++)
			{
				if(eventObject.get(i) instanceof EscapyPhysicsEvent)
				{
					((EscapyPhysicsEvent) eventObject.get(i)).physicalCalculations((EscapyPhysicsObjectSuper) physObject.get(i));
					((EscapyPhysicsEvent) eventObject.get(i)).physicalEvent(((EscapyPhysicsObjectSuper) physObject.get(i)).xpos,
							((EscapyPhysicsObjectSuper) physObject.get(i)).ypos, ((EscapyPhysicsObjectSuper) physObject.get(i)).mass,
							((EscapyPhysicsObjectSuper) physObject.get(i)).tetha,(EscapyPhysicsObjectSuper) physObject.get(i));
				}
			}
			if(physicsOn)
			{
				try {
					Thread.sleep(0, 200000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		Thread.currentThread().interrupt();
	}

}
