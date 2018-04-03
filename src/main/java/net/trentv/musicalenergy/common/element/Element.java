package net.trentv.musicalenergy.common.element;

import java.util.HashMap;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.trentv.musicalenergy.MusicalEnergy;

public class Element
{
	private static final HashMap<String, Element> elements = new HashMap<String, Element>();
	public final String NAME;

	static
	{
		new Element("null");
	}

	public Element(String name)
	{
		this.NAME = name;
		if (elements.containsKey(name))
		{
			MusicalEnergy.logger.warn("Element " + name + " already exists and must be unique.");
		}
		else
		{
			elements.put(name, this);
		}
	}

	public void onAOE(EntityLivingBase entity, World world, ItemStack stack)
	{
	}

	public void onBeam(EntityLivingBase entity, World world, ItemStack stack)
	{
	}

	public void onProjectile(EntityLivingBase entity, World world, ItemStack stack)
	{
	}

	public void onSelfCast(EntityLivingBase entity, World world, ItemStack stack)
	{
	}

	public String serialize()
	{
		return NAME;
	}

	public static Element deserialize(String str)
	{
		if (elements.containsKey(str))
			return elements.get(str);
		return elements.get("null");
	}

	// Credit to Leviathan#0044 in MMD for this method. Slightly modified for my use.
	public static final RayTraceResult raycastEntity(World world, Entity caster)
	{
		int rayDistance = 20;
		Vec3d startVec = caster.getPositionEyes(1);
		Vec3d lookVec = caster.getLook(1.0F);
		Vec3d endVec = startVec.addVector(lookVec.x * rayDistance, lookVec.y * rayDistance, lookVec.z * rayDistance);

		RayTraceResult result = world.rayTraceBlocks(startVec, endVec);
		double blockHitDistance = 0.0D; // The distance to the block that was
										// hit
		if (result != null)
			blockHitDistance = result.hitVec.distanceTo(startVec);

		// Encloses the entire area where entities that could collide with this
		// ray exist
		AxisAlignedBB entitySearchArea = new AxisAlignedBB(startVec.x, startVec.y, startVec.z, endVec.x, endVec.y, endVec.z);
		Entity hitEntity = null; // The closest entity that was hit
		double entityHitDistance = 0.0D; // The squared distance to the closest
											// entity that was hit
		for (Entity entity : world.getEntitiesInAABBexcluding(caster, entitySearchArea, EntitySelectors.NOT_SPECTATING))
		{
			// The collision AABB of the entity expanded by the collision border
			// size
			AxisAlignedBB collisionBB = entity.getEntityBoundingBox().grow(entity.getCollisionBorderSize());
			RayTraceResult intercept = collisionBB.calculateIntercept(startVec, endVec);
			if (intercept != null)
			{
				double distance = startVec.distanceTo(intercept.hitVec);

				if ((distance < blockHitDistance || blockHitDistance == 0) && (distance < entityHitDistance || entityHitDistance == 0.0D))
				{
					entityHitDistance = distance;
					hitEntity = entity;
				}
			}
		}

		if (hitEntity != null)
			result = new RayTraceResult(hitEntity, hitEntity.getPositionVector());
		if (result == null)
			result = new RayTraceResult(RayTraceResult.Type.MISS, endVec, null, new BlockPos(endVec));

		return result;
	}
}
