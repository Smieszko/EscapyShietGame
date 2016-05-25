package utilsAndConstants;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import cern.colt.matrix.ObjectMatrix3D;
import inGameObjects.InGameObject;
import inGameObjects.InGameObject.objectType;

public final class GameUtils {
	
	public static String removeGMDF(String url)
	{//.gmdf
		StringBuffer strb = new StringBuffer(url);
		if (strb.charAt(strb.length()-5) == '.')
			strb.delete(strb.length()-5, strb.length());
		return strb.toString();
	}
	
	public static int getIDFromName(String name)
	{
		StringBuffer strb = new StringBuffer(name);
		int index = strb.indexOf("_");
		String val = strb.substring(index+1);
		int value = Integer.parseInt(val);
		return value;
	}

	public static objectType IntegerToObjectType(int typo)
	{
		switch(typo)
		{
		case 0:
			return objectType.Interactive;
		case 1:
			return objectType.PassiveAnimated;
		case 2:
			return objectType.PassiveStatic;
		case 3:
			return objectType.FrontParallaxed;
		case 4:
			return objectType.BackParallaxed;
		case 5: 
			return objectType.Background;
		}return null;
	}
	
	public static void showObsInfo(InGameObject[][] gameObject, int[] size, int[] indexTab)
	{
		System.out.println("\n size.length::: "+size.length+"\n");
		for (int i : size)
			System.out.print(" "+i);
		
		System.out.println("\n "+gameObject.length+"\n");
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < size[indexTab[i]]; j++)
			{
				System.out.println("\n type:	"+gameObject[indexTab[i]][j].getType().toString());
				System.out.println(" ID:	"+gameObject[indexTab[i]][j].getID());
				System.out.println(" x:	"+gameObject[indexTab[i]][j].getXpos());
				System.out.println(" y:	"+gameObject[indexTab[i]][j].getYpos());
				System.out.println(" scale:	"+gameObject[indexTab[i]][j].getDefZoom());
				System.out.println(" url:	"+gameObject[indexTab[i]][j].getImgUrl()[0]);
			}
	}}

	public static void drawAreas(ObjectMatrix3D areaMap)
	{
		if (GameConstants.isMapVisible())
		{
			Graphics green = new Graphics();
			Graphics red = new Graphics();
			red.setColor(Color.red);
			green.setColor(Color.green);
			for (int i = 0; i <areaMap.slices(); i+=4)
			{
				for (int j = 0; j < areaMap.rows(); j+=4)
				{	
					if (areaMap.get(i, j, 0) != null)
					{
						if (Byte.toUnsignedInt((byte)areaMap.get(i, j, 0) ) == 1)
						{
							red.fillRect(i-GameConstants.translationX(), j, 1, 1);
						}
						if (Byte.toUnsignedInt((byte)areaMap.get(i, j, 0) ) == 2)
						{
							green.fillRect(i-GameConstants.translationX(), j, 1, 1);
						}
			}}}
		}
		
	}
	
}
