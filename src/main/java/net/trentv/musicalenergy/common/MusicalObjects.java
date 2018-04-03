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
	 * trumpet   : AoE effect
	 * flute     : beam effect
	 * vuvuzela  : projectile
	 * harmonica : self-cast
	 */
	public static final SoundEvent TRUMPET_SOUND = new SoundEventMusical(new ResourceLocation(MusicalEnergy.MODID, "item.trumpet.use"));
	public static final SoundEvent FLUTE_SOUND = new SoundEventMusical(new ResourceLocation(MusicalEnergy.MODID, "item.flute.use"));
	public static final SoundEvent VUVUZELA_SOUND = new SoundEventMusical(new ResourceLocation(MusicalEnergy.MODID, "item.vuvuzela.use"));
	public static final SoundEvent HARMONICA_SOUND = new SoundEventMusical(new ResourceLocation(MusicalEnergy.MODID, "item.harmonica.use"));

	public static final Item TRUMPET = new ItemTrumpet(TRUMPET_SOUND).setRegistryName(MusicalEnergy.MODID, "trumpet").setUnlocalizedName("trumpet").setCreativeTab(CreativeTabs.TOOLS);
	public static final Item FLUTE = new ItemTrumpet(FLUTE_SOUND).setRegistryName(MusicalEnergy.MODID, "flute").setUnlocalizedName("flute").setCreativeTab(CreativeTabs.TOOLS);
	public static final Item VUVUZELA = new ItemTrumpet(VUVUZELA_SOUND).setRegistryName(MusicalEnergy.MODID, "vuvuzela").setUnlocalizedName("vuvuzela").setCreativeTab(CreativeTabs.TOOLS);
	public static final Item HARMONICA = new ItemTrumpet(HARMONICA_SOUND).setRegistryName(MusicalEnergy.MODID, "harmonica").setUnlocalizedName("harmonica").setCreativeTab(CreativeTabs.TOOLS);

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
		items.add(FLUTE);
		items.add(VUVUZELA);
		items.add(HARMONICA);
		sounds.add(TRUMPET_SOUND);
		sounds.add(FLUTE_SOUND);
		sounds.add(VUVUZELA_SOUND);
		sounds.add(HARMONICA_SOUND);
	}
}
