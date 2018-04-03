package net.trentv.musicalenergy.common.element;

import java.util.List;

import net.minecraft.entity.Entity;
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
	public int onAOE(EntityLivingBase entity, World world, ItemStack stack)
	{
		List<Entity> a = getEntitiesNearby(5, entity, world);
		for (Entity target : a)
		{
			if (target instanceof EntityLivingBase)
			{
				((EntityLivingBase) target).heal(2);
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
			target.heal(5);
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
		entity.heal(3);
		return 0;
	}
}
