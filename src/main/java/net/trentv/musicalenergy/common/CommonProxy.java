package net.trentv.musicalenergy.common;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public abstract class CommonProxy
{
	public void registerEventHandlers()
	{
		MinecraftForge.EVENT_BUS.register(new CommonEvents());
	}

	public static class CommonEvents
	{
		@SubscribeEvent
		public void registerSoundEvents(RegistryEvent.Register<SoundEvent> event)
		{
			for (SoundEvent obj : MusicalObjects.sounds)
			{
				event.getRegistry().register(obj);
			}
		}

		@SubscribeEvent
		public void registerItems(RegistryEvent.Register<Item> event)
		{
			for (Item obj : MusicalObjects.items)
			{
				event.getRegistry().register(obj);
			}
		}

		@SubscribeEvent
		public void registerRenderers(ModelRegistryEvent event)
		{
			for (Item obj : MusicalObjects.items)
			{
				ModelLoader.setCustomModelResourceLocation(obj, 0, new ModelResourceLocation(obj.getRegistryName(), "inventory"));
			}
		}
	}
}
