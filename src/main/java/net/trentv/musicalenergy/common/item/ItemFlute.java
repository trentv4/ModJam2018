package net.trentv.musicalenergy.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.trentv.musicalenergy.common.element.Element;

public class ItemFlute extends ItemInstrument
{
	public ItemFlute(SoundEvent soundEffect)
	{
		super(soundEffect);
	}

	@Override
	public void doot(Element[] elements, EntityLivingBase entity, World world, ItemStack stack)
	{
		for (Element e : elements)
		{
			e.onBeam(entity, world, stack);
		}
	}
}
