package net.trentv.musicalenergy.common.item;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.trentv.musicalenergy.common.MusicalObjects;

public class ItemTrumpet extends Item
{
	public ItemTrumpet()
	{
		setUnlocalizedName("trumpet");
		this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter()
		{
			@Override
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
			{
				return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
			}
		});
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand)
	{
		// Clear spell and begin listening
		return EnumActionResult.PASS;
	}

	/**
	 * Called when the player stops using an Item (stops holding the right mouse button).
	 */
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
	{
		// Cast spell
		entityLiving.playSound(MusicalObjects.TRUMPET_SOUND, 1, 1f);
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.BLOCK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 72000;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		player.getHeldItem(hand).getTagCompound();
		player.setActiveHand(hand);
		return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}
}
