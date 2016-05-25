package launcher;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import utilsAndConstants.GameConstants;
import utilsAndConstants.GameUtils;

public class LauncherClass extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6117644991389606127L;
	private int x,y;
	private JComboBox<String> mapBox, scrResBox;
	private String[] mapList;
	private JButton addbuttn, delbuttn;
	private boolean ucan = false;
	private Dimension screenSize;
	private JTextField texf;
	private JCheckBox area;
	
	public LauncherClass() 
	{
		super();
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){}
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(300,100,280,420);
		this.setResizable(false);
		setVisible(true);
		this.setTitle("Escapy temp launcher");
		
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//GameConstants.defineScreenRes((int)screenSize.getWidth(), (int)screenSize.getHeight());
		
		JPanel mainpan = new JPanel();
		mainpan.setLayout(new BorderLayout());
		
		this.getContentPane().add(mainpan);
		
		JLabel imglab = new JLabel();
		imglab.setIcon(new ImageIcon("data\\Launcher\\BgLogo.png")); //XXX DONT WORK
		imglab.setSize(new Dimension(280,300));
		imglab.setPreferredSize(new Dimension(280,300));
		imglab.setOpaque(false);
		imglab.setVisible(true);
		this.getRootPane().add(imglab);
		imglab.setVisible(true);
		
		JPanel podPan = new JPanel();
		podPan.setLayout(new GridLayout(9, 2));
		JPanel bordPan = new JPanel();
		
		mainpan.add(bordPan, BorderLayout.WEST);
		bordPan.add(podPan);
		
		JButton butn = new JButton("Start");
		
		x = 1000;
		y = 300;
		
		butn.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (ucan)
				{
					GameConstants.setLocation(GameConstants.UrlSourceList().get(GameConstants.getiDindexList().
							indexOf(GameConstants.getiDindexList().get(mapBox.getSelectedIndex())))+""+
							GameConstants.getMapList().get(GameConstants.getiDindexList().
							indexOf(GameConstants.getiDindexList().get(mapBox.getSelectedIndex()))));
					
					GameConstants.setActualiD(mapBox.getSelectedIndex());
				
					System.out.println(GameConstants.Location());
					System.out.println(GameConstants.getiDindexList().get(mapBox.getSelectedIndex()));
					System.out.println(GameConstants.ActualiD());
					
					
					
					try {
						double scale = Double.parseDouble(texf.getText());
						GameConstants.setDefaultScale(scale);
						
					} catch (NumberFormatException exx){
						GameConstants.setDefaultScale(1);
					}
					if (area.isSelected())
					{
						GameConstants.setMapVisible(true);
					}
					
					GameConstants.setGameSizeFF();
					GameConstants.defineScreenRes((int)screenSize.getWidth(), (int)screenSize.getHeight());
					System.out.println("screenSizeX::::: "+(int)screenSize.getWidth());
					System.out.println("frameSizeX::::: "+GameConstants.getFrameWIDHT());
					
					
					GameConstants.setStart(true);
				
						dispLauncher();
						//MainGameClass.main(new String[0]);
			
				}
			}
		});

		File dir = dirChekNGo("data\\maps\\");
		mapList = dir.list();	
		mapBox = new JComboBox<String>();
		mapBox.setPreferredSize(new Dimension(110,21));
		
		if (mapList != null && mapList.length != 0)
		{
			for (int i = 0; i < mapList.length; i++)
			{
				mapBox.addItem(mapList[i]);
				GameConstants.UrlSourceList().add("data\\maps\\"+mapList[i]+"\\source\\");
				GameConstants.getiDindexList().add(GameUtils.getIDFromName(mapList[i]));
				GameConstants.getMapList().add(mapList[i]);
			}//XXX
			mapBox.setSelectedIndex(0);
			butn.setEnabled(true);
			ucan = true;
		}	

		mapBox.setVisible(true);
		butn.setVisible(true);
			
		JPanel maplab = new JPanel();
		maplab.setLayout(new BorderLayout());
		maplab.add(new JLabel("Map:"), BorderLayout.WEST);
		
		podPan.add(maplab);
		podPan.add(new JLabel(" "));
		
		JPanel mapPanel = new JPanel();
		mapPanel.setLayout(new FlowLayout());
		mapPanel.add(mapBox);
		addbuttn = new JButton("	Add map");
		addbuttn.addActionListener(this);
		mapPanel.add(addbuttn);
		
		podPan.add(mapPanel);
		podPan.add(new JLabel(" "));
		JPanel delpanel = new JPanel();
		delpanel.setLayout(new BorderLayout());
		JPanel truedelpan = new JPanel();
		delbuttn = new JButton("Delete");
		delbuttn.addActionListener(this);
		delbuttn.setVisible(true);
		truedelpan.add(delbuttn);
		delpanel.add(truedelpan, BorderLayout.EAST);
		podPan.add(delpanel);
		podPan.add(new JLabel(" "));
		
		JPanel labresPan = new JPanel();
		labresPan.setLayout(new BorderLayout());
		labresPan.add(new JLabel("\n	Screen res:"), BorderLayout.WEST);
		
		podPan.add(labresPan);
		podPan.add(new JLabel(" "));
		
		scrResBox = new JComboBox<String>();
		scrResBox.setPreferredSize(new Dimension(80,21));
		//scrResBox.addActionListener(this);
		scrResBox.setEnabled(false);
		scrResBox.setVisible(true);
		JPanel scrRespan = new JPanel();
		scrRespan.setLayout(new BorderLayout());
		JPanel trueScrpan = new JPanel();
		trueScrpan.add(scrResBox);
		scrRespan.add(trueScrpan, BorderLayout.WEST);
		
		podPan.add(scrRespan);
		podPan.add(new JLabel(" "));
		
		JPanel sclrPan = new JPanel();
		sclrPan.setLayout(new BorderLayout());
		sclrPan.add(new JLabel("\n  Scale ratio ( 0.1 - 2 ): "), BorderLayout.WEST);
		podPan.add(sclrPan);
		podPan.add(new JLabel(" "));
		
		texf  = new JTextField("1");
		texf.setPreferredSize(new Dimension(80,21));
		texf.setEnabled(true);
		texf.setVisible(true);
		JPanel txtfPan = new JPanel();
		txtfPan.setLayout(new BorderLayout());
		JPanel trueTxtf = new JPanel();
		trueTxtf.add(texf);
		txtfPan.add(trueTxtf, BorderLayout.WEST);
		podPan.add(txtfPan);
		podPan.add(new JLabel(" "));
		
		JPanel checkb = new JPanel();
		checkb.setLayout(new BorderLayout());
		area = new JCheckBox("Map borders visible (- fps)");
		checkb.add(area, BorderLayout.WEST);
		podPan.add(checkb);
		podPan.add(new JLabel(" "));
		
		JPanel startPan = new JPanel();
		startPan.setLayout(new BorderLayout());
		JPanel trueStart = new JPanel();
		trueStart.add(butn);
		butn.setSize(new Dimension(30, 30));
		butn.setPreferredSize(new Dimension(60, 30));
		
		startPan.add(trueStart, BorderLayout.WEST);

		podPan.add(startPan);
		podPan.add(new JLabel(" "));
		
		this.revalidate();
		this.setVisible(true);
	}
	
	public void dispLauncher()
	{
		GameConstants.setStart(true);
		this.dispose();
	}
	public int[] getXY()
	{
		return (new int[]{x,y});
	}
	
	private File dirChekNGo(String directory)
	{
		File dir = new File(directory);
		if (!dir.exists())
		{
			dir.mkdirs();
		}
		return dir;
	}
	
	private void dirDeleteRecs(File dir)
	{
		if (dir.exists() == true)
		{
			for (File i : dir.listFiles())
			{
				if(i.isDirectory()) 
				{
					i.delete();
					dirDeleteRecs(i);
					i.delete();
				}
				else
				{
					i.delete();
				}
				if (!i.delete())
				{
					//System.out.println("cos cos sie popsulo");
				}
			}	
			dir.delete();
		}
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == addbuttn)
		{
			JFileChooser plik = new JFileChooser();
			plik.addChoosableFileFilter(new FileNameExtensionFilter("(Goft map data files) *.gmdf", "gmdf"));
			plik.setAcceptAllFileFilterUsed(true);
			int sure = plik.showOpenDialog(plik);
			if(sure == JFileChooser.APPROVE_OPTION)
			{
				File f = plik.getSelectedFile();
				String zpname = f.getAbsolutePath();
				String mapname = GameUtils.removeGMDF(f.getName());
				try {
					ZipFile zipf = new ZipFile(zpname);
					File dir = new File("data\\maps\\"+mapname);
					if (!dir.exists())
					{
						dir.mkdir();
					}
					@SuppressWarnings("unchecked")
					List<FileHeader> fileHeaders = zipf.getFileHeaders();
					for(FileHeader fileHeader : fileHeaders) 
					{
						if (!fileHeader.isDirectory() && 
								!fileHeader.getFileName().equals("tmp/")) 
						{
							zipf.extractFile(fileHeader, "data\\maps\\"+mapname+"\\");
						}
					}
					File old = new File("data\\maps\\"+mapname+"\\tmp");
					if (old.exists() && old.isDirectory())
					{
						old.renameTo(new File("data\\maps\\"+mapname+"\\source"));
						mapBox.addItem(mapname);
						GameConstants.UrlSourceList().add("data\\maps\\"+mapname+"\\source\\");
						GameConstants.getiDindexList().add(GameUtils.getIDFromName(mapname));
						GameConstants.getMapList().add(mapname);
						ucan = true;
					}
				} catch (ZipException e1) {
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource() == delbuttn)
		{
			int indx = mapBox.getSelectedIndex();
			if (mapBox.getSelectedItem() != null)
			{
				String dirname = (String) mapBox.getSelectedItem();
				File directory = new File("data\\maps\\"+dirname);
				mapBox.removeItemAt(indx);
				GameConstants.UrlSourceList().remove(indx);
				GameConstants.getMapList().remove(indx);
				GameConstants.getiDindexList().remove(indx);
				dirDeleteRecs(directory);
				revalidate();
			}
		}
	}

	
}
