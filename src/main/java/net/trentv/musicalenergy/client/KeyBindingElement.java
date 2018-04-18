package net.trentv.musicalenergy.client;

import net.minecraft.client.settings.KeyBinding;
import net.trentv.musicalenergy.MusicalEnergy;
import net.trentv.musicalenergy.common.element.Element;

public class KeyBindingElement extends KeyBinding
{
	public final Element element;

	public KeyBindingElement(String description, int keyCode, Element element)
	{
		super(description, keyCode, MusicalEnergy.MODID);
		this.element = element;
	}
}
