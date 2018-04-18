package net.trentv.musicalenergy.client;

import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.trentv.musicalenergy.MusicalEnergy;
import net.trentv.musicalenergy.MusicalEnergyPacketHandler;
import net.trentv.musicalenergy.MusicalEnergyPacketHandler.SpellMessage;
import net.trentv.musicalenergy.common.element.Element;
import net.trentv.musicalenergy.common.item.ItemInstrument;
import net.trentv.musicalenergy.config.MusicalEnergyConfig;
import net.trentv.musicalenergy.config.MusicalEnergyKeybinds;

public class GuiCasting extends GuiScreen
{
	private static final ResourceLocation TEXTURE_ELEMENTS = new ResourceLocation(MusicalEnergy.MODID, "textures/gui/casting.png");
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
		mc.getTextureManager().bindTexture(TEXTURE_ELEMENTS);

		int startWidth = (width / 2) - 34;
		for (int i = 0; i < elements.size(); i++)
		{
			drawTexturedModalRect(startWidth + (15 * i), height - 60, 10 * (elements.get(i).ID - 1), 0, 10, 15);
		}
	}

	@Override
	protected void mouseReleased(int mouseX, int mouseY, int state)
	{
		int[] ids = new int[elements.size()];
		for (int i = 0; i < ids.length; i++)
		{
			ids[i] = elements.get(i).ID;
		}
		MusicalEnergyPacketHandler.INSTANCE.sendToServer(new SpellMessage(ids));

		mc.displayGuiScreen(null);
		super.mouseReleased(mouseX, mouseY, state);
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		Element newElement = null;
		for (KeyBindingElement e : MusicalEnergyKeybinds.keybinds)
		{
			if (e.getKeyCode() == keyCode)
				newElement = e.element;
		}

		if (newElement == null)
			return;

		boolean hasReacted = false;

		for (int i = 0; i < elements.size(); i++)
		{
			Element startingElement = elements.get(i);
			Element reactedElement = startingElement.reactsWith(newElement);
			if (startingElement != reactedElement)
			{
				hasReacted = true;
				elements.set(i, reactedElement);
				break;
			}
		}

		if (elements.size() < MusicalEnergyConfig.GENERAL.maxElements)
		{
			ItemStack heldItem = player.getActiveItemStack();
			if (heldItem.getItem() instanceof ItemInstrument)
			{
				ItemInstrument instrument = (ItemInstrument) heldItem.getItem();
				player.playSound(instrument.soundEffect, 1, (newElement.ID / (float) Element.MAX_ID) * 2);
				if (!hasReacted)
					elements.add(newElement);
			}
		}
		super.keyTyped(typedChar, keyCode);
	}
}
