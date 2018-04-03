package net.trentv.musicalenergy.common.element;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ElementLife extends Element
{
	public ElementLife(String name)
	{
		super(name);
	}

	@Override
	public void onAOE(EntityLivingBase entity, World world, ItemStack stack)
	{

	}

	@Override
	public void onBeam(EntityLivingBase entity, World world, ItemStack stack)
	{

	}

	@Override
	public void onProjectile(EntityLivingBase entity, World world, ItemStack stack)
	{

	}

	@Override
	public void onSelfCast(EntityLivingBase entity, World world, ItemStack stack)
	{

	}
}
