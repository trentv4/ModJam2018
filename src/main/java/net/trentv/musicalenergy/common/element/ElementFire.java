package net.trentv.musicalenergy.common.element;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.trentv.musicalenergy.common.MusicalObjects;

public class ElementFire extends Element
{
	public ElementFire(String name)
	{
		super(name);
	}

	@Override
	public void onAOE(EntityLivingBase entity, World world, ItemStack stack)
	{
		List<Entity> a = getEntitiesNearby(5, entity, world);
		for (Entity target : a)
		{
			if (target instanceof EntityLivingBase)
			{
				target.setFire(4);
				attackEntity(target, DamageSource.ON_FIRE, 2);
			}
		}
	}

	@Override
	public void onBeam(EntityLivingBase entity, World world, ItemStack stack)
	{
		EntityLivingBase target = raycastEntity(world, entity);
		if (target != null)
		{
			target.setFire(4);
			target.attackEntityFrom(DamageSource.ON_FIRE, 2 * 2);
		}
	}

	@Override
	public void onProjectile(EntityLivingBase entity, World world, ItemStack stack)
	{
	}

	@Override
	public void onSelfCast(EntityLivingBase entity, World world, ItemStack stack)
	{
		entity.setFire(4);
		attackEntity(entity, DamageSource.ON_FIRE, 2);
	}

	@Override
	public Element reactsWith(Element a)
	{
		if (a == MusicalObjects.WATER)
			return MusicalObjects.STEAM;
		return this;
	}
}
