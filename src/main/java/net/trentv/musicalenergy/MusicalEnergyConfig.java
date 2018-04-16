package net.trentv.musicalenergy;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class MusicalEnergyConfig
{
	public static KeyBinding BIND_FIRE, BIND_WATER, BIND_EARTH, BIND_AIR, BIND_LIFE, BIND_DEATH;

	public static void init()
	{
		BIND_FIRE = new KeyBinding("Play fire tune", Keyboard.KEY_Q, MusicalEnergy.MODID);
		BIND_WATER = new KeyBinding("Play water tune", Keyboard.KEY_W, MusicalEnergy.MODID);
		BIND_EARTH = new KeyBinding("Play earth tune", Keyboard.KEY_E, MusicalEnergy.MODID);
		BIND_AIR = new KeyBinding("Play air tune", Keyboard.KEY_A, MusicalEnergy.MODID);
		BIND_LIFE = new KeyBinding("Play life tune", Keyboard.KEY_S, MusicalEnergy.MODID);
		BIND_DEATH = new KeyBinding("Play death tune", Keyboard.KEY_D, MusicalEnergy.MODID);

		ClientRegistry.registerKeyBinding(BIND_FIRE);
		ClientRegistry.registerKeyBinding(BIND_WATER);
		ClientRegistry.registerKeyBinding(BIND_EARTH);
		ClientRegistry.registerKeyBinding(BIND_AIR);
		ClientRegistry.registerKeyBinding(BIND_LIFE);
		ClientRegistry.registerKeyBinding(BIND_DEATH);
	}
}
