package com.byethost33.wikimetns.container;

import com.byethost33.wikimetns.container.slot.SlotOutput;
import com.byethost33.wikimetns.container.slot.SlotSingleItem;
import com.byethost33.wikimetns.tile.TileRomodSmelter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerRomodSmelter extends Container{
	private TileRomodSmelter tile;
	private int	timePassed = 0;
	private int	burnTimeLeft = 0;
	
	public ContainerRomodSmelter(TileRomodSmelter tile, InventoryPlayer playerInventory) {
	    this.tile = tile;
	 
	    int i;
	    
	    this.addSlotToContainer(new Slot(tile, 0, 48, 17));
	    this.addSlotToContainer(new Slot(tile, 1, 117, 8));
	    
	    this.addSlotToContainer(new SlotSingleItem(tile, 4, 48, 53,Item.getItemFromBlock(Blocks.PLANKS)));
	    
	    this.addSlotToContainer(new SlotOutput(tile, 3, 117, 35));
	 
	    for(i = 0; i < 3; ++i) {
	        for(int j = 0; j < 9; ++j) {
	            this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
	        }
	    }
	 
	    for(i = 0; i < 9; ++i) {
	        this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
	    }
	}
	
	
	@Override
	public boolean canInteractWith(EntityPlayer player) {
	    return tile.isUsableByPlayer(player);
	}
	
	@Override
	public void addListener(IContainerListener listener) {
	    super.addListener(listener);
	    listener.sendAllWindowProperties(this, this.tile);
	}
	 
	@Override
	public void detectAndSendChanges() {
	    super.detectAndSendChanges();
	 
	    for(int i = 0; i < this.listeners.size(); ++i) {
	        IContainerListener icontainerlistener = (IContainerListener) this.listeners
	                .get(i);
	 
	        if (this.burnTimeLeft != this.tile.getField(0)) {
	            icontainerlistener.sendWindowProperty(this, 0,
	                    this.tile.getField(0));
	        }
	 
	        if (this.timePassed != this.tile.getField(1)) {
	            icontainerlistener.sendWindowProperty(this, 1,this.tile.getField(1));
	        }
	    }
	 
	    this.burnTimeLeft = this.tile.getField(0);
	    this.timePassed = this.tile.getField(1);
	}
	 
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int data) {
	    this.tile.setField(id, data);
	}
	
	
	//TODO set on the shift click !
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
	    return ItemStack.EMPTY;
	}
}
