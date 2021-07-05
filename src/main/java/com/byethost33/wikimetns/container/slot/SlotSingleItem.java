package com.byethost33.wikimetns.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotSingleItem extends Slot {
	 
    private Item item;
 
    public SlotSingleItem(IInventory inventoryIn, int index, int xPosition, int yPosition, Item item) {
        super(inventoryIn, index, xPosition, yPosition);
        this.item = item;
    }
 
    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.isEmpty() || stack.getItem() == item;
    }
}