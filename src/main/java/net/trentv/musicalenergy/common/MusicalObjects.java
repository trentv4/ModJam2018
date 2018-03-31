package net.trentv.musicalenergy.common;

import java.util.ArrayList;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.trentv.musicalenergy.MusicalEnergy;
import net.trentv.musicalenergy.common.element.ElementAir;
import net.trentv.musicalenergy.common.item.ItemTrumpet;

public class MusicalObjects
{
	public static ArrayList<Item> items = new ArrayList<Item>();
	public static ArrayList<SoundEvent> sounds = new ArrayList<SoundEvent>();
	/*
	 * Instruments:
	 * trumpet  : AoE effect
	 * flute    : beam effect
	 * vuvuzela : projectile
	 * harmonia : self-cast
	 */

	public static final Item TRUMPET = new ItemTrumpet().setRegistryName(MusicalEnergy.MODID, "trumpet").setCreativeTab(CreativeTabs.TOOLS);
	public static final SoundEvent TRUMPET_SOUND = new SoundEventMusical(new ResourceLocation(MusicalEnergy.MODID, "item.trumpet.use"));

	/*
	 * Elements:
	 * fire : sets target on fire, small damage
	 * water: slowness
	 * earth: medium damage
	 * air  : move backwards extreme
	 * death: large damage to target, small to caster
	 * life : healing
	 */
	public static final ElementAir FIRE = new ElementAir("fire");
	public static final ElementAir WATER = new ElementAir("water");
	public static final ElementAir EARTH = new ElementAir("earth");
	public static final ElementAir AIR = new ElementAir("air");
	public static final ElementAir DEATH = new ElementAir("death");
	public static final ElementAir LIFE = new ElementAir("life");

	public static final void init()
	{
		items.add(TRUMPET);
		sounds.add(TRUMPET_SOUND);
	}
}
