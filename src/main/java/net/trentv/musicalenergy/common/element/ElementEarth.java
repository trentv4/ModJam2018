package net.trentv.musicalenergy.common.element;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ElementEarth extends Element
{
	public ElementEarth(String name)
	{
		super(name);
	}

	@Override
	public int onAOE(EntityLivingBase entity, World world, ItemStack stack)
	{
		List<Entity> a = getEntitiesNearby(5, entity, world);
		for (Entity target : a)
		{
			if (target instanceof EntityLivingBase)
			{
				target.attackEntityFrom(DamageSource.MAGIC, 2 * 2);
			}
		}
		return 0;
	}

	@Override
	public int onBeam(EntityLivingBase entity, World world, ItemStack stack)
	{
		EntityLivingBase target = raycastEntity(world, entity);
		if (target != null)
		{
			target.attackEntityFrom(DamageSource.MAGIC, 2 * 2);
			target.setEntityInvulnerable(false);
		}
		return 0;
	}

	@Override
	public void onProjectile(EntityLivingBase entity, World world, ItemStack stack)
	{
	}

	@Override
	public int onSelfCast(EntityLivingBase entity, World world, ItemStack stack)
	{
		return 4;
	}
}
