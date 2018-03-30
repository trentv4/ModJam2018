package net.trentv.musicalenergy.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class ItemTrumpet extends Item
{
	public ItemTrumpet()
	{
		setUnlocalizedName("trumpet");
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		float pitch = 0;
		if (!world.isRemote)
		{
		}
		player.playSound(SoundEvents.BLOCK_STONE_BREAK, 1, pitch);
		return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
	}
}
