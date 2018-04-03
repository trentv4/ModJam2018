package net.trentv.musicalenergy.common.item;

import java.util.ArrayList;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.trentv.musicalenergy.MusicalEnergy;
import net.trentv.musicalenergy.common.MusicalObjects;
import net.trentv.musicalenergy.common.element.Element;

public abstract class ItemInstrument extends Item
{
	protected SoundEvent soundEffect;

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
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
	{
		if (!worldIn.isRemote)
		{
			Element[] elements = getCurrentElements(stack);
			String s = "Casting: ";
			for (Element e : elements)
			{
				s += e.serialize() + " ";
			}
			MusicalEnergy.logger.info(s);
			Class c = this.getClass();
			doot(elements, entityLiving, worldIn, stack);
		}
		entityLiving.playSound(soundEffect, 1, 1f);
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

	public abstract void doot(Element[] elements, EntityLivingBase entity, World world, ItemStack stack);

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		// TODO: implement control scheme
		player.setActiveHand(hand);

		Element[] elements = getCurrentElements(player.getHeldItem(hand));

		return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}

	public Element[] getCurrentElements(ItemStack heldItem)
	{
		Element[] e = new Element[] {};
		if (heldItem.hasTagCompound())
		{
			NBTTagList elementList = heldItem.getTagCompound().getTagList("elements", 8);
			ArrayList<Element> elements = new ArrayList<Element>();
			if (elementList.tagCount() > 0)
			{
				for (int i = 0; i < elementList.tagCount(); i++)
				{
					String tag = elementList.getStringTagAt(i);
					Element s = Element.deserialize(tag);
					elements.add(s);
				}
				e = elements.toArray(new Element[elements.size()]);
			}
		}
		e = new Element[] { MusicalObjects.AIR };
		return e;
	}

	public void setCurrentElements(ItemStack heldItem, Element[] elements)
	{
		if (!heldItem.hasTagCompound())
		{
			heldItem.setTagCompound(new NBTTagCompound());
		}

		NBTTagCompound baseTag = heldItem.getTagCompound();
		NBTTagList elementList = new NBTTagList();
		for (Element e : elements)
		{
			elementList.appendTag(new NBTTagString(e.serialize()));
		}
		baseTag.setTag("elements", elementList);
	}
}
