package com.byethost33.wikimetns.block;

import com.byethost33.wikimetns.random_objects_main;
import com.byethost33.wikimetns.tile.TileRomodSmelter;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class RomodSmelter extends BlockContainer {

	public RomodSmelter(Material p_i45386_1_) {
		super(Material.ROCK);
		this.setRegistryName("romod_smelter");
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean hasTileEntity() {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		// TODO Auto-generated method stub
		return new TileRomodSmelter();
	}
	
	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
	    TileEntity tileentity = worldIn.getTileEntity(pos);
	 
	    if (tileentity instanceof TileRomodSmelter) {
	        InventoryHelper.dropInventoryItems(worldIn, pos,(TileRomodSmelter) tileentity);
	    }
	 
	    super.breakBlock(worldIn, pos, state);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
	    if (world.isRemote) {
	        return true;
	    } else {
	        TileEntity tileentity = world.getTileEntity(pos);
	 
	        if (tileentity instanceof TileRomodSmelter) {
	            player.openGui(random_objects_main.instance, 0, world, pos.getX(),
	                    pos.getY(), pos.getZ());
	        }
	 
	        return true;
	    }
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
	    return EnumBlockRenderType.MODEL;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
	    if (stack.hasDisplayName()) {
	        TileEntity tileentity = worldIn.getTileEntity(pos);
	 
	        if (tileentity instanceof TileRomodSmelter) {
	            ((TileRomodSmelter) tileentity).setCustomName(stack.getDisplayName());
	        }
	    }
	}
}
