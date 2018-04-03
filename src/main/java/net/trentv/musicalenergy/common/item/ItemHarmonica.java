package net.trentv.musicalenergy.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.trentv.musicalenergy.common.element.Element;

public class ItemHarmonica extends ItemInstrument
{
	public ItemHarmonica(SoundEvent soundEffect)
	{
		super(soundEffect);
	}

	@Override
	public void doot(Element[] elements, EntityLivingBase entity, World world, ItemStack stack)
	{
		int selfDamage = 0;
		for (Element e : elements)
		{
			selfDamage += e.onSelfCast(entity, world, stack);
		}
		entity.attackEntityFrom(DamageSource.MAGIC, selfDamage);
	}
}
