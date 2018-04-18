package net.trentv.musicalenergy.config;

import static net.trentv.musicalenergy.common.MusicalObjects.AIR;
import static net.trentv.musicalenergy.common.MusicalObjects.DEATH;
import static net.trentv.musicalenergy.common.MusicalObjects.EARTH;
import static net.trentv.musicalenergy.common.MusicalObjects.FIRE;
import static net.trentv.musicalenergy.common.MusicalObjects.LIFE;
import static net.trentv.musicalenergy.common.MusicalObjects.WATER;

import org.lwjgl.input.Keyboard;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.trentv.musicalenergy.client.KeyBindingElement;

public class MusicalEnergyKeybinds
{
	public static KeyBindingElement BIND_FIRE = new KeyBindingElement("Play fire tune", Keyboard.KEY_Q, FIRE);
	public static KeyBindingElement BIND_WATER = new KeyBindingElement("Play water tune", Keyboard.KEY_W, WATER);
	public static KeyBindingElement BIND_EARTH = new KeyBindingElement("Play earth tune", Keyboard.KEY_E, EARTH);
	public static KeyBindingElement BIND_AIR = new KeyBindingElement("Play air tune", Keyboard.KEY_A, AIR);
	public static KeyBindingElement BIND_LIFE = new KeyBindingElement("Play life tune", Keyboard.KEY_S, LIFE);
	public static KeyBindingElement BIND_DEATH = new KeyBindingElement("Play death tune", Keyboard.KEY_D, DEATH);
	public static final KeyBindingElement[] keybinds = { BIND_FIRE, BIND_WATER, BIND_EARTH, BIND_AIR, BIND_LIFE, BIND_DEATH };

	public static void init()
	{
		for (KeyBindingElement e : keybinds)
		{
			ClientRegistry.registerKeyBinding(e);
		}
	}
}
