package com.byethost33.wikimetns.item;

import com.byethost33.wikimetns.random_objects_main;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class transplanter extends Item {
	
	public static final PropertyDirection FACING = PropertyDirection.create("facing");
	
	public transplanter() {
		this.setRegistryName("transplanter");
		this.setUnlocalizedName("transplanter");
		this.setCreativeTab(random_objects_main.romodCreativeTabs);
		this.setMaxDamage(20);
	}
	
	
	/*@Override
	public ActionResult<ItemStack> onItemRightclick(World worldIn, EntityPlayer player,EnumHand handIn){
		
		
		ItemStack item = player.getHeldItem(handIn);
		
		item.damageItem(1, player);
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, item);
	}
	*/
	@Override
	public EnumActionResult onItemUse( EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		
		if(!worldIn.isRemote) {
			Block block = worldIn.getBlockState(pos).getBlock();
			
			if(block == random_objects_main.wheel_barrows_dirt) {
				worldIn.setBlockState(pos, random_objects_main.wheel_barrows.getDefaultState().withProperty(FACING, facing));
				
				worldIn.playSound(player, player.getPosition(),SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.f, 1.f);
				setDamage(player.getHeldItem(hand), player.getHeldItem(hand).getItemDamage() + 1);
				if (player.getHeldItem(hand).getItemDamage() >= player.getHeldItem(hand).getMaxDamage()) {
			    	player.getHeldItem(hand).setCount(0);
				}
				
				int posiX = (int)pos.getX();
				int posiY = (int)pos.getY();
				int posiZ = (int)pos.getZ();
				EntityItem loot =  new EntityItem(worldIn);
				loot.setPosition(posiX, posiY,posiZ);
				loot.setItem(new ItemStack(random_objects_main.dirt_bag, 1));
				
				
				worldIn.spawnEntity(loot);
				
			}
			else if (block == random_objects_main.wheel_barrows_tulip) {
				worldIn.setBlockState(pos, random_objects_main.wheel_barrows_dirt.getDefaultState().withProperty(FACING, facing));
				worldIn.playSound(player, player.getPosition(),SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.f, 1.f);
				setDamage(player.getHeldItem(hand), player.getHeldItem(hand).getItemDamage() + 1);
				if (player.getHeldItem(hand).getItemDamage() >= player.getHeldItem(hand).getMaxDamage()) {
			    	player.getHeldItem(hand).setCount(0);
				}
				
			}
			
			}
		
		//
		
		
		
	
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
		
	}
	
	

    }

