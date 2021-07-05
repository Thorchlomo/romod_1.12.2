package com.byethost33.wikimetns.handlers;

import com.byethost33.wikimetns.container.ContainerRomodSmelter;
import com.byethost33.wikimetns.gui.tile.GuiRomodSmelter;
import com.byethost33.wikimetns.tile.TileRomodSmelter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getClientGuiElement(int arg0, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        if(te instanceof TileRomodSmelter) {
            return new GuiRomodSmelter((TileRomodSmelter)te, player.inventory);
        }
        return null;
	}

	 @Override
	    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
	        if(te instanceof TileRomodSmelter) {
	        	return new ContainerRomodSmelter((TileRomodSmelter)te, player.inventory);
	        }
	        return null;
	    }
}
