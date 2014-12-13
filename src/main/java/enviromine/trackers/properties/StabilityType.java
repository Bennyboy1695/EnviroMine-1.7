package enviromine.trackers.properties;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import enviromine.core.EM_Settings;
import enviromine.core.EnviroMine;

public class StabilityType
{
	public String name;
	public boolean enablePhysics;
	public int supportDist;
	public int minFall;
	public int maxFall;
	public boolean canHang;
	public boolean holdOther;
	
	/** Stability properties:<br>0 ({@link Boolean}) Enable Physics <br>1 ({@link Int}) Max Support Distance <br>2 ({@link Int}) Min Missing Blocks To Fall <br>3 ({@link Int}) Max Missing Blocks To Fall <br>4 ({@link Boolean}) Can Hang <br>5 ({@link Boolean}) Holds Others Up */
	static String[] SPName;
	
	public StabilityType(String name, boolean enablePhysics, int supportDist, int minFall, int maxFall, boolean canHang, boolean holdOther)
	{
		this.name = name;
		this.enablePhysics = enablePhysics;
		this.supportDist = supportDist;
		this.minFall = minFall;
		this.maxFall = maxFall;
		this.canHang = canHang;
		this.holdOther = holdOther;
	}
	
	/**Set up Config Names for Stability properties:<br>0 ({@link Boolean}) Enable Physics <br>1 ({@link Int}) Max Support Distance <br>2 ({@link Int}) Min Missing Blocks To Fall <br>3 ({@link Int}) Max Missing Blocks To Fall <br>4 ({@link Boolean}) Can Hang <br>5 ({@link Boolean}) Holds Others Up */
	public static void setConfigNames()
	{
		SPName = new String[6];
		SPName[0] = "01.Enable Physics";
		SPName[1] = "02.Max Support Distance";
		SPName[2] = "03.Min Missing Blocks To Fall";
		SPName[3] = "04.Max Missing Blocks To Fall";
		SPName[4] = "05.Can Hang";
		SPName[5] = "06.Holds Others Up";
	}
	
	public static void loadStabilityTypes(File file)
	{
		Configuration config;
		try
		{
			config = new Configuration(file, true);
		} catch(NullPointerException e)
		{
			e.printStackTrace();
			EnviroMine.logger.log(Level.WARN, "FAILED TO LOAD MAIN CONFIG!\nBACKUP SETTINGS ARE NOW IN EFFECT!");
			return;
		} catch(StringIndexOutOfBoundsException e)
		{
			e.printStackTrace();
			EnviroMine.logger.log(Level.WARN, "FAILED TO LOAD MAIN CONFIG!\nBACKUP SETTINGS ARE NOW IN EFFECT!");
			return;
		}
		
		config.load();
		
		loadDefaultStabilityTypes(config);
		
		// 	Grab all Categories in File
		List<String> catagory = new ArrayList<String>();
		Set<String> nameList = config.getCategoryNames();
		Iterator<String> nameListData = nameList.iterator();
		
		// add Categories to a List 
		while(nameListData.hasNext())
		{
			catagory.add(nameListData.next());
		}
		
		// Now Read/Save Each Category And Add into Proper Hash Maps
		
		for(int x = 0; x <= (catagory.size() - 1); x++)
		{
			String currentCat = catagory.get(x);
			
			boolean physEnable = config.get(currentCat, SPName[0], true).getBoolean(true);
			int supportDist = config.get(currentCat, SPName[1], 0).getInt(0);
			int minFall = config.get(currentCat, SPName[2], -1).getInt(-1);
			int maxFall = config.get(currentCat, SPName[3], -1).getInt(-1);
			boolean canHang = config.get(currentCat, SPName[4], false).getBoolean(false);
			boolean holdOther = config.get(currentCat, SPName[5], false).getBoolean(false);
			
			EM_Settings.stabilityTypes.put(currentCat, new StabilityType(currentCat.split("\\.")[0], physEnable, supportDist, minFall, maxFall, canHang, holdOther));
		}
		
		config.save();
	}
	
	public static void loadDefaultStabilityTypes(Configuration config)
	{
		boolean physEnable = config.get("sand-like", SPName[0], true).getBoolean(true);
		int supportDist = config.get("sand-like", SPName[1], 0).getInt(0);
		int minFall = config.get("sand-like", SPName[2], -1).getInt(-1);
		int maxFall = config.get("sand-like", SPName[3], -1).getInt(-1);
		boolean canHang = config.get("sand-like", SPName[4], false).getBoolean(false);
		boolean holdOther = config.get("sand-like", SPName[5], false).getBoolean(false);
		
		EM_Settings.stabilityTypes.put("sand-like", new StabilityType("sand-like", physEnable, supportDist, minFall, maxFall, canHang, holdOther));
		
		physEnable = config.get("loose", SPName[0], true).getBoolean(true);
		supportDist = config.get("loose", SPName[1], 1).getInt(1);
		minFall = config.get("loose", SPName[2], 10).getInt(10);
		maxFall = config.get("loose", SPName[3], 15).getInt(15);
		canHang = config.get("loose", SPName[4], false).getBoolean(false);
		holdOther = config.get("loose", SPName[5], false).getBoolean(false);
		
		EM_Settings.stabilityTypes.put("loose", new StabilityType("loose", physEnable, supportDist, minFall, maxFall, canHang, holdOther));
		
		physEnable = config.get("average", SPName[0], true).getBoolean(true);
		supportDist = config.get("average", SPName[1], 2).getInt(2);
		minFall = config.get("average", SPName[2], 15).getInt(15);
		maxFall = config.get("average", SPName[3], 22).getInt(22);
		canHang = config.get("average", SPName[4], false).getBoolean(false);
		holdOther = config.get("average", SPName[5], false).getBoolean(false);
		
		EM_Settings.stabilityTypes.put("average", new StabilityType("average", physEnable, supportDist, minFall, maxFall, canHang, holdOther));
		
		physEnable = config.get("strong", SPName[0], true).getBoolean(true);
		supportDist = config.get("strong", SPName[1], 3).getInt(3);
		minFall = config.get("strong", SPName[2], 22).getInt(22);
		maxFall = config.get("strong", SPName[3], 25).getInt(25);
		canHang = config.get("strong", SPName[4], true).getBoolean(true);
		holdOther = config.get("strong", SPName[5], false).getBoolean(false);
		
		EM_Settings.stabilityTypes.put("strong", new StabilityType("strong", physEnable, supportDist, minFall, maxFall, canHang, holdOther));
		
		physEnable = config.get("none", SPName[0], false).getBoolean(false);
		supportDist = config.get("none", SPName[1], 3).getInt(3);
		minFall = config.get("none", SPName[2], 0).getInt(0);
		maxFall = config.get("none", SPName[3], 0).getInt(0);
		canHang = config.get("none", SPName[4], true).getBoolean(true);
		holdOther = config.get("none", SPName[5], false).getBoolean(false);
		
		EM_Settings.stabilityTypes.put("none", new StabilityType("none", physEnable, supportDist, minFall, maxFall, canHang, holdOther));
		
		physEnable = config.get("glowstone", SPName[0], false).getBoolean(false);
		supportDist = config.get("glowstone", SPName[1], 3).getInt(3);
		minFall = config.get("glowstone", SPName[2], 0).getInt(0);
		maxFall = config.get("glowstone", SPName[3], 0).getInt(0);
		canHang = config.get("glowstone", SPName[4], true).getBoolean(true);
		holdOther = config.get("glowstone", SPName[5], true).getBoolean(true);
		
		EM_Settings.stabilityTypes.put("glowstone", new StabilityType("glowstone", physEnable, supportDist, minFall, maxFall, canHang, holdOther));
	}
}
