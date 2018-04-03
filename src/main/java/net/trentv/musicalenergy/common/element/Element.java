package net.trentv.musicalenergy.common.element;

import java.util.HashMap;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.trentv.musicalenergy.MusicalEnergy;

public class Element
{
	private static final HashMap<String, Element> elements = new HashMap<String, Element>();
	public final String NAME;

	static
	{
		new Element("null");
	}

	public Element(String name)
	{
		this.NAME = name;
		if (elements.containsKey(name))
		{
			MusicalEnergy.logger.warn("Element " + name + " already exists and must be unique.");
		}
		else
		{
			elements.put(name, this);
		}
	}

	public void onAOE(EntityLivingBase entity, World world, ItemStack stack)
	{
	}

	public void onBeam(EntityLivingBase entity, World world, ItemStack stack)
	{
	}

	public void onProjectile(EntityLivingBase entity, World world, ItemStack stack)
	{
	}

	public void onSelfCast(EntityLivingBase entity, World world, ItemStack stack)
	{
	}

	public String serialize()
	{
		return NAME;
	}

	public static Element deserialize(String str)
	{
		if (elements.containsKey(str))
			return elements.get(str);
		return elements.get("null");
	}
}
