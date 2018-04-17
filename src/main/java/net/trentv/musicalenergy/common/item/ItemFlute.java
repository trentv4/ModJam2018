package net.trentv.musicalenergy.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.trentv.musicalenergy.common.element.Element;

public class ItemFlute extends ItemInstrument
{
	public ItemFlute(SoundEvent soundEffect)
	{
		super(soundEffect);
	}

	@Override
	public void doot(Element[] elements, EntityLivingBase entity, World world, ItemStack stack)
	{
		for (Element e : elements)
		{
			e.onBeam(entity, world, stack);
			WorldServer w = (WorldServer) world;
			for (int i = 1; i < 20; i++)
			{
				Vec3d p = entity.getPositionVector();
				Vec3d lookVector = entity.getLookVec();
				p = p.add(lookVector.scale(i));
				p = p.addVector(0, entity.getEyeHeight(), 0);
				w.spawnParticle(EnumParticleTypes.NOTE, p.x, p.y, p.z, 1, 0.0, 0.0, 0.0, 0);
			}
		}
	}
}
