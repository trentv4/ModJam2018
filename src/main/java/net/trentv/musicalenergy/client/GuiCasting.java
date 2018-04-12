package net.trentv.musicalenergy.client;

import java.io.IOException;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.trentv.musicalenergy.MusicalEnergy;
import net.trentv.musicalenergy.common.MusicalObjects;
import net.trentv.musicalenergy.common.element.Element;
import net.trentv.musicalenergy.common.item.ItemInstrument;

public class GuiCasting extends GuiScreen
{
	private static final ResourceLocation BACKGROUND = new ResourceLocation(MusicalEnergy.MODID, "textures/gui/casting.png");
	private Element[] elements = new Element[5];
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
		for (int i = 0; i < elements.length; i++)
		{
			if (elements[i] != null)
			{
				int pos = elements[i].ID;
				drawTexturedModalRect(startWidth + (15 * i), height - 60, 10 * pos, 0, 10, 15);
			}
		}
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
				newElement = MusicalObjects.DEATH;
				break;
			case 'd':
				newElement = MusicalObjects.LIFE;
				break;
		}
		if (newElement != null)
		{
			for (int i = 0; i < elements.length; i++)
			{
				if (elements[i] == null)
				{
					elements[i] = newElement;
					float pitch = (newElement.ID / (float) Element.MAX_ID) * 2;
					ItemInstrument instrument = (ItemInstrument) player.getHeldItem(player.getActiveHand()).getItem();
					player.playSound(instrument.soundEffect, 1, pitch);
					break;
				}
			}
		}
		super.keyTyped(typedChar, keyCode);
	}
}
