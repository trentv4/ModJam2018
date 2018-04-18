package net.trentv.musicalenergy.common.element;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
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
			Random random = new Random();
			Vec3d p = target.getPositionVector();
			attackEntity(target, DamageSource.LIGHTNING_BOLT, 4);
			target.setLocationAndAngles(p.x, p.y, p.z, random.nextInt(360), random.nextInt(180) - 90);
			// to set Entity#isPositionDirty because lmao private
			target.setPositionAndUpdate(p.x, p.y, p.z);
		}
	}

	@Override
	public void onBeam(EntityLivingBase entity, World world, ItemStack stack)
	{
		EntityLivingBase target = raycastEntity(world, entity);
		if (target != null)
		{
			Random random = new Random();
			Vec3d p = target.getPositionVector();
			attackEntity(target, DamageSource.LIGHTNING_BOLT, 4);
			target.setLocationAndAngles(p.x, p.y, p.z, random.nextInt(360), random.nextInt(180) - 90);
			// to set Entity#isPositionDirty because lmao private
			target.setPositionAndUpdate(p.x, p.y, p.z);
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
		entity.setLocationAndAngles(p.x, p.y, p.z, entity.rotationYaw + random.nextInt(180) - 90, entity.rotationPitch);
		// to set Entity#isPositionDirty because lmao private
		entity.setPositionAndUpdate(p.x, p.y, p.z);
		attackEntity(entity, DamageSource.LIGHTNING_BOLT, 4);
	}
}
