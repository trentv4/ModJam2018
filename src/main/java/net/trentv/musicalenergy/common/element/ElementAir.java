package net.trentv.musicalenergy.common.element;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class ElementAir extends Element
{
	public ElementAir(String name)
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
				Vec3d vector = new Vec3d(target.posX - entity.posX, target.posY - entity.posY, target.posZ - entity.posZ);

				target.addVelocity(vector.x, vector.y, vector.z);
				target.velocityChanged = true;
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
			Vec3d lookVec = entity.getLookVec();
			target.addVelocity(lookVec.x, lookVec.y, lookVec.z);
			target.velocityChanged = true;
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
		entity.addVelocity(0, 0.5, 0);
		entity.velocityChanged = true;
		return 0;
	}
}
