package net.trentv.musicalenergy.common.element;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.trentv.musicalenergy.common.MusicalObjects;

public class ElementDeath extends Element
{
	public ElementDeath(String name)
	{
		super(name);
	}

	@Override
	public void onAOE(EntityLivingBase entity, World world, ItemStack stack)
	{
		List<Entity> a = getEntitiesNearby(5, entity, world);
		int entityCount = 0;
		for (Entity target : a)
		{
			if (target instanceof EntityLivingBase)
			{
				attackEntity(target, DamageSource.MAGIC, 6);
				entityCount++;
			}
		}
		attackEntity(entity, DamageSource.MAGIC, entityCount * 2);
	}

	@Override
	public void onBeam(EntityLivingBase entity, World world, ItemStack stack)
	{
		EntityLivingBase target = raycastEntity(world, entity);
		if (target != null)
		{
			target.attackEntityFrom(DamageSource.MAGIC, 3 * 2);
		}
		attackEntity(entity, DamageSource.MAGIC, 2);
	}

	@Override
	public void onProjectile(EntityLivingBase entity, World world, ItemStack stack)
	{
	}

	@Override
	public void onSelfCast(EntityLivingBase entity, World world, ItemStack stack)
	{
		attackEntity(entity, DamageSource.MAGIC, 8);
	}

	@Override
	public Element reactsWith(Element a)
	{
		if (a == MusicalObjects.AIR)
			return MusicalObjects.LIGHTNING;
		return this;
	}
}
