package net.trentv.musicalenergy.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.trentv.musicalenergy.MusicalEnergy;
import net.trentv.musicalenergy.client.GuiCasting;

public class GuiHandlerCasting implements IGuiHandler
{
	public static void openGui(EntityPlayer player, int id, BlockPos pos)
	{
		player.openGui(MusicalEnergy.instance, id, player.getEntityWorld(), pos.getX(), pos.getY(), pos.getZ());
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		if (ID == 0)
			return new GuiCasting(player);
		return null;
	}
}
