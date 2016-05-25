package escapyGame;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;

import cern.colt.matrix.ObjectMatrix3D;
import cern.colt.matrix.impl.SparseObjectMatrix3D;
import inGameObjects.AnimatedObject;
import inGameObjects.BackGround;
import inGameObjects.InGameObject;
import inGameObjects.NonAnimatedObject;
import utilsAndConstants.GameConstants;
import utilsAndConstants.GameUtils;

public class InitMap implements DeferredResource{
	
	private InGameObject[][] inGameObject;
	private BackGround bckgr;
	private String locUrl;
	private ObjectMatrix3D areaMap;
	private String loadTextStat = "";
	private int loadIntstat = 0;
	private int[] adrtab, size;
	
	private long actualPointerPos;

	public InitMap(int id) 
	{
	
		GameConstants.setLocation(GameConstants.UrlSourceList().get(GameConstants.getiDindexList().
				indexOf(id))+""+GameConstants.getMapList().get(GameConstants.getiDindexList().indexOf(id)));
		
	//	GameConstants.setLocation(GameConstants.UrlSourceList().get((id))+""+
	//			GameConstants.getMapList().get((id)));
		
		locUrl = GameConstants.Location();
		System.out.println(locUrl);
		System.out.println(id);
		
		GameConstants.setGameSizeFF();
		double[] mapsize = GameConstants.getMapSize();

		areaMap = new SparseObjectMatrix3D((int)mapsize[0], (int)mapsize[1], 4);

		size  = getObjectsNumb();
		inGameObject = loadObjects(size);
		createObjectsFF(size);
		loadBackGround(locUrl);
		fillMapFF(new int[]{size[5],size[6]});
		
		GameUtils.showObsInfo(inGameObject, size, adrtab);
		System.gc();
	}
	public ObjectMatrix3D map()
	{
		return areaMap;
	}
	public InGameObject[][] gameObjects()
	{
		return inGameObject;
	}
	public BackGround backGround()
	{
		return bckgr;
	}
	public int[] indexTab()
	{
		return adrtab;
	}
	public int[] size()
	{
		return size;
	}
	
	private void createObjectsFF(int[] tabsize)
	{
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(locUrl+"_.gmd", "r");
			raf.seek(actualPointerPos);
			int[] adtab = new int[]{0,1,4,2,3};
			adrtab = new int[]{4,2,1,0,3};
			for (int i = 0; i < 5; i++)
			{
				for (int j = 0; j < tabsize[adtab[i]]; j++)
				{
					int x =  raf.readShort();
					int y =  raf.readShort();
					int id =  raf.readShort();
					int spt = raf.readShort(); 
					double zoom =  raf.readDouble();
					int[]animtime = new int[10];
					if (spt == 1)
					{
						for (int anm = 0; anm < 10; anm++)
						{
							animtime[anm] = raf.readShort();
						}
					}
					if (spt == 1)
					{
						inGameObject[adtab[i]][j] = new AnimatedObject(x, y, id, locUrl+""+id+".png", 
								animtime, zoom, adtab[i]);
					}else if (spt == 0)
					{
						inGameObject[adtab[i]][j] = new NonAnimatedObject(x, y, id, locUrl+""+id+".png", zoom, 
								adtab[i]);
					}actualPointerPos = raf.getFilePointer();
					LoadingList.get().add(this);
				}
			}
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int[] getObjectsNumb()
	{
		int[] numbtab = new int[7];
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(locUrl+"_.gmd", "r");
			raf.seek(4);
			for (int i = 0; i < 7; i++)
			{
				numbtab[i] = raf.readShort();
				LoadingList.get().add(this);
			}
			actualPointerPos = raf.getFilePointer();
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}return numbtab;
	}
	
	private InGameObject[][] loadObjects(int[] tab)
	{
		int[] adtab3 = new int[]{0,1,4,2,3};
		InGameObject[][] intab = new InGameObject[5][];
		for (int i = 0; i < 3; i++)
		{
			intab[adtab3[i]] = new AnimatedObject[tab[adtab3[i]]];
			LoadingList.get().add(this);
		}
		for (int i = 3; i < 5; i++)
		{
			intab[adtab3[i]] = new NonAnimatedObject[tab[adtab3[i]]];
			LoadingList.get().add(this);
		}return intab;
	}
	private void loadBackGround(String location)
	{
		bckgr = new BackGround(location+"bckgr.png");
	}

	private void fillMapFF(int[] tab)
	{
		RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(locUrl+"_.gmd", "r");
			raf.seek(actualPointerPos);
			
			for (int i = 0; i < tab[0]; i++)
			{
				int Figuretype = raf.readShort();
				int StX = raf.readShort();
				int StY = raf.readShort();
				int EdX = raf.readShort();
				int EdY = raf.readShort();
				int ID = raf.readShort();
				int ActionType = raf.readShort();
				crFigure(StX, StY, EdX, EdY, new Object[]{(byte)2, (short)ID, (byte)ActionType},
						Figuretype);
				LoadingList.get().add(this);
			}
			for (int i = 0; i < tab[1]; i++) // 1 == square  2 == triangle
			{
				int Figuretype = raf.readShort();
				int StX = raf.readShort();
				int StY = raf.readShort();
				int EdX = raf.readShort();
				int EdY = raf.readShort();
				crFigure(StX, StY, EdX, EdY, new Object[]{(byte)1, (short)Short.MAX_VALUE, (byte)0xFF}, 
						Figuretype);
				LoadingList.get().add(this);
			}
			raf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private void crFigure(int sx, int sy, int ex, int ey, Object[] vals, int type)
	{
		switch(type){
		case 1:
			figureSquare(sx, sy, ex, ey, vals);
			break;
		case 2:
			figureTriagnle(sx, sy, ex, ey, vals);
			break;
		}LoadingList.get().add(this);
	}
	
	private void figureSquare(int sx, int sy, int ex, int ey, Object[] vals)
	{
		if (vals == null) vals = new Object[]{(byte)0,(short)0,(byte)0};
		if (ex < 0) ex = 0;
		if (ey < 0) ey = 0;
		if (ex > GameConstants.getGameXsize()) ex = GameConstants.getGameXsize();
		if (ey > GameConstants.getGameYsize()) ey = GameConstants.getGameYsize();
		if (sx > ex)
		{int temp = sx;
			sx = ex;
			ex = temp;	
		}if (sy > ey)
		{int temp = sy;
			sy = ey;
			ey = temp;
		}for (int i = sx; i < ex; i++)
			{for (int j = sy; j < ey; j++)
			{
				try {
					areaMap.set(i, j, 0, (byte)vals[0]);
					areaMap.set(i, j, 1, (short)vals[1]);
					areaMap.set(i, j, 2, (byte)vals[2]);
				} catch (IndexOutOfBoundsException exdc) {}	
		}}
		LoadingList.get().add(this);
	}
	
	private void figureTriagnle(int stx, int sty, int edx, int edy, Object[] vals)
	{
		double sx = stx;
		double sy = sty;
		double ex = edx;
		double ey = edy;
		
		if(sx<ex)
		{for (double i = sx; i < ex; i+=1)
			{double tg =((ey-sy)/(ex-sx));
			double ki = ex-i;
			if (sy>ey)
			{for (double j = sy-ey; j >-ki*(tg); j-=1)
			{
				try {
					areaMap.set((int)i, (int)(j+ey), 0, (byte)vals[0]);
					areaMap.set((int)i, (int)(j+ey), 1, (short)vals[1]);
					areaMap.set((int)i, (int)(j+ey), 2, (byte)vals[2]);
				} catch (IndexOutOfBoundsException exdc) {}	
			}}else if (sy<ey)
			{for (double j = sy-ey; j <-ki*(tg); j+=1)
			{
				try {
					areaMap.set((int)i, (int)(j+ey), 0, (byte)vals[0]);
					areaMap.set((int)i, (int)(j+ey), 1, (short)vals[1]);
					areaMap.set((int)i, (int)(j+ey), 2, (byte)vals[2]);
				} catch (IndexOutOfBoundsException exdc) {}	
		}}}}else if (sx>ex)
		{for (double i = sx; i > ex; i-=1)
			{double tg =(((ey-sy)/(ex-sx)));
			double ki = sx-i;
			if (sy>ey)
			{for (double j = 0; j >-ki*(tg); j-=1)
			{
				try {
					areaMap.set((int)i, (int)(j+sy), 0, (byte)vals[0]);
					areaMap.set((int)i, (int)(j+sy), 1, (short)vals[1]);
					areaMap.set((int)i, (int)(j+sy), 2, (byte)vals[2]);
				} catch (IndexOutOfBoundsException exdc) {}
			
			}}else if (sy<ey)
			{for (double j = 0; j <-ki*(tg); j+=1)
			{
				try {
					areaMap.set((int)i, (int)(j+sy), 0, (byte)vals[0]);
					areaMap.set((int)i, (int)(j+sy), 1, (short)vals[1]);
					areaMap.set((int)i, (int)(j+sy), 2, (byte)vals[2]);
				} catch (IndexOutOfBoundsException exdc) {}
		}}}}
		LoadingList.get().add(this);
		
	}
	
	@Override
	public String getDescription() 
	{
		//Integer.toString(LoadingList.get().getRemainingResources());
		return loadTextStat;
	}
	
	@Override
	public void load() throws IOException 
	{
		loadIntstat+= 1;
		loadTextStat = "Loading map sources: "+Integer.toString(loadIntstat);
	}
	
	
	
}

	