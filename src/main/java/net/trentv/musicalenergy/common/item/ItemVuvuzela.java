package net.trentv.musicalenergy.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.trentv.musicalenergy.common.element.Element;

public class ItemVuvuzela extends ItemInstrument
{
	public ItemVuvuzela(SoundEvent soundEffect)
	{
		super(soundEffect);
	}

	@Override
	public void doot(Element[] elements, EntityLivingBase entity, World world, ItemStack stack)
	{
		for (Element e : elements)
		{
			e.onAOE(entity, world, stack);
			WorldServer w = (WorldServer) world;
			for (int i = 0; i < 360; i += 15)
			{
				Vec3d p = entity.getPositionVector();
				p = p.addVector(Math.cos(i) * 2, 1, Math.sin(i) * 2);
				w.spawnParticle(EnumParticleTypes.NOTE, p.x, p.y, p.z, 1, 0.0, 0.0, 0.0, 0);
				p = p.addVector(Math.cos(i) * 2, 0, Math.sin(i) * 2);
				w.spawnParticle(EnumParticleTypes.NOTE, p.x, p.y, p.z, 1, 0.0, 0.0, 0.0, 0);
			}
		}
	}
}
