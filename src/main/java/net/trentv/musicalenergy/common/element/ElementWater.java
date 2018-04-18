package net.trentv.musicalenergy.common.element;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.trentv.musicalenergy.common.MusicalObjects;

public class ElementWater extends Element
{
	public ElementWater(String name)
	{
		super(name);
	}

	@Override
	public void onAOE(EntityLivingBase entity, World world, ItemStack stack)
	{
		List<Entity> list = getEntitiesNearby(5, entity, world);
		for (Entity t : list)
		{
			if (t instanceof EntityLivingBase)
			{
				EntityLivingBase target = (EntityLivingBase) t;
				target.extinguish();
				PotionEffect a = target.getActivePotionEffect(MobEffects.SLOWNESS);
				int duration = 20 * 4;
				if (a != null)
				{
					duration += a.getDuration();
				}
				target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, duration, 1));
			}
		}
	}

	@Override
	public void onBeam(EntityLivingBase entity, World world, ItemStack stack)
	{
		EntityLivingBase target = raycastEntity(world, entity);
		if (target != null)
		{
			target.extinguish();
			PotionEffect a = target.getActivePotionEffect(MobEffects.SLOWNESS);
			int duration = 20 * 4;
			if (a != null)
			{
				duration += a.getDuration();
			}
			target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, duration, 1));
		}
	}

	@Override
	public void onProjectile(EntityLivingBase entity, World world, ItemStack stack)
	{
	}

	@Override
	public void onSelfCast(EntityLivingBase entity, World world, ItemStack stack)
	{
		entity.extinguish();
		PotionEffect a = entity.getActivePotionEffect(MobEffects.SLOWNESS);
		int duration = 20 * 4;
		if (a != null)
		{
			duration += a.getDuration();
		}
		entity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, duration, 1));
	}

	@Override
	public Element reactsWith(Element a)
	{
		if (a == MusicalObjects.FIRE)
			return MusicalObjects.STEAM;
		return this;
	}
}
