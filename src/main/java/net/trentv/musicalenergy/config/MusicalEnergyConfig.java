package net.trentv.musicalenergy.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.trentv.musicalenergy.MusicalEnergy;

@Config(modid = MusicalEnergy.MODID, category = "")
@EventBusSubscriber(modid = MusicalEnergy.MODID)
public class MusicalEnergyConfig
{
	public static final General GENERAL = new General();

	public static final class General
	{
		@Config.Name("max_elements")
		@Config.Comment("The maximum number of elements that are allowed in a spell.")
		public int maxElements = 5;
	}

	@SubscribeEvent
	public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.getConfigID() != null && event.getConfigID().equals(MusicalEnergy.MODID))
		{
			ConfigManager.sync(MusicalEnergy.MODID, Config.Type.INSTANCE);
		}
	}
}
