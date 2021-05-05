package com.byethost33.wikimetns.item;

import com.byethost33.wikimetns.random_objects_main;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class double_houe extends ItemSword {

	public double_houe() {
		super(random_objects_main.super_diamond);
		// TODO Auto-generated constructor stub
		this.setRegistryName("double_houe");
		this.setUnlocalizedName("double_houe");
		this.setCreativeTab(random_objects_main.romodCreativeTabs);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		
		if(!worldIn.isRemote) {
			Block block = worldIn.getBlockState(pos).getBlock();
			
			if(block == Blocks.DIRT) {
				worldIn.setBlockState(pos, Blocks.FARMLAND.getDefaultState());
				setDamage(player.getHeldItem(hand), player.getHeldItem(hand).getItemDamage() + 1);
				if (player.getHeldItem(hand).getItemDamage() >= player.getHeldItem(hand).getMaxDamage()) {
			    	player.getHeldItem(hand).setCount(0);
				}
			}
			else if(block == Blocks.GRASS) {
				worldIn.setBlockState(pos, Blocks.FARMLAND.getDefaultState());
				setDamage(player.getHeldItem(hand), player.getHeldItem(hand).getItemDamage() + 2);
				if (player.getHeldItem(hand).getItemDamage() >= player.getHeldItem(hand).getMaxDamage()) {
			    	player.getHeldItem(hand).setCount(0);
				}
			}
		}
		
		
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
}
