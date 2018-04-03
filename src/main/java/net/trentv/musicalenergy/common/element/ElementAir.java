package net.trentv.musicalenergy.common.element;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ElementAir extends Element
{
	public ElementAir(String name)
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
				target.addVelocity(0, 0.5, 0);
				entity.velocityChanged = true;
			}
		}
	}

	@Override
	public void onBeam(EntityLivingBase entity, World world, ItemStack stack)
	{
		EntityLivingBase target = raycastEntity(world, entity);
		if (target != null)
		{
			entity.addVelocity(0, 0.5, 0);
			entity.velocityChanged = true;
		}
	}

	@Override
	public void onProjectile(EntityLivingBase entity, World world, ItemStack stack)
	{

	}

	@Override
	public void onSelfCast(EntityLivingBase entity, World world, ItemStack stack)
	{
		entity.addVelocity(0, 0.5, 0);
		entity.velocityChanged = true;
	}
}
