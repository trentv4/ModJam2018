package net.trentv.musicalenergy.common.element;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ElementShield extends Element
{
	public ElementShield(String name)
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

				PotionEffect a = target.getActivePotionEffect(MobEffects.HEALTH_BOOST);
				int duration = 20 * 8;
				duration += (a != null ? a.getDuration() : 0);
				target.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, duration, 1));
			}
		}
	}

	@Override
	public void onBeam(EntityLivingBase entity, World world, ItemStack stack)
	{
		EntityLivingBase target = raycastEntity(world, entity);
		if (target == null)
			return;

		PotionEffect a = target.getActivePotionEffect(MobEffects.HEALTH_BOOST);
		int duration = 20 * 8;
		duration += (a != null ? a.getDuration() : 0);
		target.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, duration, 1));
	}

	@Override
	public void onProjectile(EntityLivingBase entity, World world, ItemStack stack)
	{
	}

	@Override
	public void onSelfCast(EntityLivingBase entity, World world, ItemStack stack)
	{
		PotionEffect a = entity.getActivePotionEffect(MobEffects.HEALTH_BOOST);
		int duration = 20 * 8;
		duration += (a != null ? a.getDuration() : 0);
		entity.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, duration, 1));
	}
}
