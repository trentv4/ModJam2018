package net.trentv.musicalenergy;


import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MusicalEnergy.MODID, version = MusicalEnergy.VERSION)
public class MusicalEnergy
{
	public static final String MODID = "musicalenergy";
    public static final String VERSION = "1.0";
	
    public static Logger logger;

	
    /*
     * Pitch: instruments which give you access to 'notes' (elements) which can
     * combine together into spells for offense, building, and mining. Think
     * Magicka spells but with 
     */
    
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		logger = event.getModLog();
	}
	
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
    
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		logger.info(MODID + " initialized");
	}
}
