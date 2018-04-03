package net.trentv.musicalenergy.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.trentv.musicalenergy.common.element.Element;
import net.trentv.musicalenergy.common.item.ItemInstrument;

public class CommandTune implements ICommand
{
	@Override
	public int compareTo(ICommand arg0)
	{
		return 0;
	}

	@Override
	public String getName()
	{
		return "tune";
	}

	@Override
	public String getUsage(ICommandSender sender)
	{
		Collection<Element> values = Element.elements.values();
		String s = "tune <";
		for (Element element : values)
		{
			if (element.ID == -1)
				continue;
			s += element.NAME;
			if (element.ID < Element.MAX_ID - 1)
			{
				s += "|";
			}
		}
		s += "> Maximum elements: 5.";
		return s;
	}

	@Override
	public List<String> getAliases()
	{
		return new ArrayList<String>();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		if (args.length == 0)
			sender.sendMessage(new TextComponentString("Usage: " + getUsage(sender)));
		if (args.length > 5)
			sender.sendMessage(new TextComponentString("You must enter only up to 5 elements to tune your instrument."));
		ArrayList<Element> elements = new ArrayList<Element>();
		String output = "";
		for (String s : args)
		{
			Element a = Element.deserialize(s);
			if (a.ID != -1)
			{
				elements.add(a);
				output += s + " ";
			}
			else
			{
				sender.sendMessage(new TextComponentString("Usage: " + getUsage(sender)));
				break;
			}
		}

		if (sender.getCommandSenderEntity() instanceof EntityPlayer)
		{
			EntityPlayer a = (EntityPlayer) sender.getCommandSenderEntity();
			ItemStack stack = a.getHeldItem(a.getActiveHand());
			if (stack != null && stack.getItem() instanceof ItemInstrument)
			{
				ItemInstrument.setCurrentElements(stack, elements.toArray(new Element[elements.size()]));
				sender.sendMessage(new TextComponentString("Instrument tuned with: " + output));
			}
			else
			{
				sender.sendMessage(new TextComponentString("You must be holding an instrument to tune it."));
			}
		}
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender)
	{
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos)
	{
		return new ArrayList<String>();
	}

	@Override
	public boolean isUsernameIndex(String[] args, int index)
	{
		return false;
	}

}
