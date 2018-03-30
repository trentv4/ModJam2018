package net.trentv.musicalenergy.common;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.trentv.musicalenergy.MusicalEnergy;
import net.trentv.musicalenergy.common.item.ItemTrumpet;

public class MusicalObjects
{
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public static ArrayList<Item> items = new ArrayList<Item>();
	public static ArrayList<ItemBlock> itemBlocks = new ArrayList<ItemBlock>();

	public static final Item TRUMPET = new ItemTrumpet().setRegistryName(MusicalEnergy.MODID, "trumpet").setCreativeTab(CreativeTabs.TOOLS);

	public static final void init()
	{
		items.add(TRUMPET);
	}
}
