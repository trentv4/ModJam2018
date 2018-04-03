package net.trentv.musicalenergy.common.element;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ElementDeath extends Element
{
	public ElementDeath(String name)
	{
		super(name);
	}

	@Override
	public void onAOE(EntityLivingBase entity, World world, ItemStack stack)
	{
		int radius = 5;
		BlockPos pos1 = entity.getPosition().up(radius).north(radius).east(radius);
		BlockPos pos2 = entity.getPosition().down(radius).south(radius).west(radius);
		AxisAlignedBB boundingBox = new AxisAlignedBB(pos1, pos2);
		List<Entity> a = world.getEntitiesInAABBexcluding(entity, boundingBox, null);
		int entityCount = 0;
		for (Entity target : a)
		{
			if (target instanceof EntityLivingBase)
			{
				target.attackEntityFrom(DamageSource.MAGIC, 3 * 2);
				entityCount++;
			}
		}
		entity.attackEntityFrom(DamageSource.MAGIC, entityCount * 2);
	}

	@Override
	public void onBeam(EntityLivingBase entity, World world, ItemStack stack)
	{
		Entity target = raycastEntity(world, entity).entityHit;
		if (target != null)
		{
			target.attackEntityFrom(DamageSource.MAGIC, 3 * 2);
			entity.attackEntityFrom(DamageSource.MAGIC, 1 * 2);
		}
	}

	@Override
	public void onProjectile(EntityLivingBase entity, World world, ItemStack stack)
	{
	}

	@Override
	public void onSelfCast(EntityLivingBase entity, World world, ItemStack stack)
	{
		entity.attackEntityFrom(DamageSource.MAGIC, 4 * 2);
	}
}
