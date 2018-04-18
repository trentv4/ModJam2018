package net.trentv.musicalenergy.common.element;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ElementLightning extends Element
{
	public ElementLightning(String name)
	{
		super(name);
	}

	@Override
	public void onAOE(EntityLivingBase entity, World world, ItemStack stack)
	{
		List<Entity> a = getEntitiesNearby(5, entity, world);
		for (Entity target : a)
		{

		}
	}

	@Override
	public void onBeam(EntityLivingBase entity, World world, ItemStack stack)
	{
		EntityLivingBase target = raycastEntity(world, entity);
		if (target != null)
		{

		}
	}

	@Override
	public void onProjectile(EntityLivingBase entity, World world, ItemStack stack)
	{

	}

	@Override
	public void onSelfCast(EntityLivingBase entity, World world, ItemStack stack)
	{
		Random random = new Random();
		Vec3d p = entity.getPositionVector();
		entity.setPositionAndRotation(p.x, p.y, p.z, random.nextInt(360), random.nextInt(180) - 90);
	}
}
