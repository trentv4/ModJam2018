package net.trentv.musicalenergy.common;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class SoundEventMusical extends SoundEvent
{
	public SoundEventMusical(ResourceLocation soundNameIn)
	{
		super(soundNameIn);
		setRegistryName(soundNameIn);
	}
}
