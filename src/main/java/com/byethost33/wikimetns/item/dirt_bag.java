package com.byethost33.wikimetns.item;

import com.byethost33.wikimetns.random_objects_main;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class dirt_bag extends Item {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	public dirt_bag()
	{
		this.setRegistryName("dirt_bag");
		this.setUnlocalizedName("dirt_bag");
		this.setCreativeTab(random_objects_main.romodCreativeTabs);
		this.setMaxDamage(1);
		
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		
		if(!worldIn.isRemote) {
			Block block = worldIn.getBlockState(pos).getBlock();
			
			if(block == random_objects_main.wheel_barrows) {
				worldIn.setBlockState(pos, random_objects_main.wheel_barrows_dirt.getDefaultState().withProperty(FACING, facing));
				setDamage(player.getHeldItem(hand), player.getHeldItem(hand).getItemDamage() + 1);
				if (player.getHeldItem(hand).getItemDamage() >= player.getHeldItem(hand).getMaxDamage()) {
			    	player.getHeldItem(hand).setCount(0);
				}
			}
		}
		
		
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}
