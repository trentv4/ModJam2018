package net.trentv.musicalenergy.client;

import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.trentv.musicalenergy.MusicalEnergy;
import net.trentv.musicalenergy.MusicalEnergyPacketHandler;
import net.trentv.musicalenergy.MusicalEnergyPacketHandler.SpellMessage;
import net.trentv.musicalenergy.common.MusicalObjects;
import net.trentv.musicalenergy.common.element.Element;
import net.trentv.musicalenergy.common.item.ItemInstrument;

public class GuiCasting extends GuiScreen
{
	private static final ResourceLocation BACKGROUND = new ResourceLocation(MusicalEnergy.MODID, "textures/gui/casting.png");
	private ArrayList<Element> elements = new ArrayList<Element>();
	private EntityPlayer player;

	public GuiCasting(EntityPlayer player)
	{
		this.player = player;
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		super.drawScreen(mouseX, mouseY, partialTicks);
		mc.getTextureManager().bindTexture(BACKGROUND);

		int startWidth = (width / 2) - 34;
		for (int i = 0; i < elements.size(); i++)
		{
			drawTexturedModalRect(startWidth + (15 * i), height - 60, 10 * (elements.get(i).ID - 1), 0, 10, 15);
		}
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state)
	{
		this.mc.displayGuiScreen((GuiScreen) null);

		int[] ids = new int[elements.size()];
		for (int i = 0; i < ids.length; i++)
		{
			ids[i] = elements.get(i).ID;
		}
		MusicalEnergyPacketHandler.INSTANCE.sendToServer(new SpellMessage(ids));

		super.mouseReleased(mouseX, mouseY, state);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		Element newElement = null;
		switch (Character.toLowerCase(typedChar))
		{
			case 'q':
				newElement = MusicalObjects.FIRE;
				break;
			case 'w':
				newElement = MusicalObjects.WATER;
				break;
			case 'e':
				newElement = MusicalObjects.EARTH;
				break;
			case 'a':
				newElement = MusicalObjects.AIR;
				break;
			case 's':
				newElement = MusicalObjects.LIFE;
				break;
			case 'd':
				newElement = MusicalObjects.DEATH;
				break;
		}
		if (newElement != null)
		{
			if (elements.size() < 5)
			{
				elements.add(newElement);
				float pitch = (newElement.ID / (float) Element.MAX_ID) * 2;
				ItemInstrument instrument = (ItemInstrument) player.getHeldItem(player.getActiveHand()).getItem();
				player.playSound(instrument.soundEffect, 1, pitch);
			}
		}
		super.keyTyped(typedChar, keyCode);
	}
}
