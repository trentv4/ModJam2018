package net.trentv.musicalenergy.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.trentv.musicalenergy.common.element.Element;

public class ItemVuvuzela extends ItemInstrument
{
	public ItemVuvuzela(SoundEvent soundEffect)
	{
		super(soundEffect);
	}

	@Override
	public void doot(Element[] elements, EntityLivingBase entity, World world, ItemStack stack)
	{
		for (Element e : elements)
		{
			e.onProjectile(entity, world, stack);
		}
	}
}