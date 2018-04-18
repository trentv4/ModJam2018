package net.trentv.musicalenergy.common.element;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class ElementSteam extends Element
{
	public ElementSteam(String name)
	{
		super(name);
	}

	@Override
	public void onAOE(EntityLivingBase entity, World world, ItemStack stack)
	{
		List<Entity> a = getEntitiesNearby(5, entity, world);
		for (Entity target : a)
		{
			target.setVelocity(entity.motionX, 0.25, entity.motionZ);
			target.velocityChanged = true;
			attackEntity(target, DamageSource.ON_FIRE, 4);
			if (target instanceof EntityPlayer)
			{
				EntityPlayer p = (EntityPlayer) target;
				p.dropItem(true);
			}
		}
	}

	@Override
	public void onBeam(EntityLivingBase entity, World world, ItemStack stack)
	{
		EntityLivingBase target = raycastEntity(world, entity);
		if (target != null)
		{
			target.setVelocity(entity.motionX, 0.25, entity.motionZ);
			target.velocityChanged = true;
			attackEntity(target, DamageSource.ON_FIRE, 4);
			if (target instanceof EntityPlayer)
			{
				EntityPlayer p = (EntityPlayer) entity;
				p.dropItem(true);
			}
		}
	}

	@Override
	public void onProjectile(EntityLivingBase entity, World world, ItemStack stack)
	{

	}

	@Override
	public void onSelfCast(EntityLivingBase entity, World world, ItemStack stack)
	{
		entity.setVelocity(entity.motionX, 0.25, entity.motionZ);
		entity.velocityChanged = true;
		attackEntity(entity, DamageSource.ON_FIRE, 4);
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) entity;
			p.dropItem(true);
		}
	}
}
