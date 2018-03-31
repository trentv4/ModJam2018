package net.trentv.musicalenergy.common.element;

import java.util.HashMap;

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

	public void onAOE()
	{
	}

	public void onBeam()
	{
	}

	public void onProjectile()
	{
	}

	public void onSelfCast()
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
