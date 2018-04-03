package net.trentv.musicalenergy.common.element;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ElementDeath extends Element
{
	public ElementDeath(String name)
	{
		super(name);
	}

	@Override
	public int onAOE(EntityLivingBase entity, World world, ItemStack stack)
	{
		List<Entity> a = getEntitiesNearby(5, entity, world);
		int entityCount = 0;
		for (Entity target : a)
		{
			if (target instanceof EntityLivingBase)
			{
				target.attackEntityFrom(DamageSource.MAGIC, 3 * 2);
				entityCount++;
			}
		}
		return entityCount * 2;
	}

	@Override
	public int onBeam(EntityLivingBase entity, World world, ItemStack stack)
	{
		EntityLivingBase target = raycastEntity(world, entity);
		if (target != null)
		{
			target.attackEntityFrom(DamageSource.MAGIC, 3 * 2);
		}
		return 2;
	}

	@Override
	public void onProjectile(EntityLivingBase entity, World world, ItemStack stack)
	{
	}

	@Override
	public int onSelfCast(EntityLivingBase entity, World world, ItemStack stack)
	{
		return 4 * 2;
	}
}
