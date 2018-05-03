package net.trentv.musicalenergy.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.trentv.musicalenergy.MusicalEnergy;
import net.trentv.musicalenergy.common.element.Element;

public class KeyBindingElement extends KeyBinding
{
	private static final KeyConflictContextNone CONFLICT_NONE = new KeyConflictContextNone();
	public final Element element;

	public KeyBindingElement(String description, int keyCode, Element element)
	{
		super(description, keyCode, MusicalEnergy.MODID);
		this.setKeyConflictContext(CONFLICT_NONE);
		this.element = element;
	}

	private static class KeyConflictContextNone implements IKeyConflictContext
	{
		@Override
		public boolean isActive()
		{
			return Minecraft.getMinecraft().currentScreen != null;
		}

		@Override
		public boolean conflicts(IKeyConflictContext other)
		{
			return false;
		}
	}
}
