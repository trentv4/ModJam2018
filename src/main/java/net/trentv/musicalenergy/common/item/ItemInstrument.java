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
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.trentv.musicalenergy.MusicalEnergy;
import net.trentv.musicalenergy.common.element.Element;

public abstract class ItemInstrument extends Item
{
	public SoundEvent soundEffect;

	public ItemInstrument(SoundEvent soundEffect)
	{
		this.soundEffect = soundEffect;
		this.addPropertyOverride(new ResourceLocation("dooting"), new IItemPropertyGetter()
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
		BlockPos pos = player.getPosition();
		player.openGui(MusicalEnergy.instance, 0, player.getEntityWorld(), pos.getX(), pos.getY(), pos.getZ());
		player.setActiveHand(hand);

		return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}

	public abstract void doot(Element[] elements, EntityLivingBase entity, World world, ItemStack stack);
}
