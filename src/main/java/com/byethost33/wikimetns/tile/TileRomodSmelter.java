package com.byethost33.wikimetns.tile;


import com.byethost33.wikimetns.block.RomodSmelter;
import com.byethost33.wikimetns.util.RecipesRomodSMelter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;

public class TileRomodSmelter extends TileEntityLockable implements ITickable {

	
	private NonNullList<ItemStack> stacks = NonNullList.withSize(5, ItemStack.EMPTY);
	private String customName;
	private int	timePassed = 0;
	private int	burningTimeLeft	= 0;
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
	    super.readFromNBT(compound);
	    this.stacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
	    ItemStackHelper.loadAllItems(compound, this.stacks);
	 
	    if (compound.hasKey("CustomName", 8)) {
	        this.customName = compound.getString("CustomName");
	    }
	    this.burningTimeLeft = compound.getInteger("burningTimeLeft");
	    this.timePassed = compound.getInteger("timePassed");
	}
	 
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
	    super.writeToNBT(compound);
	    ItemStackHelper.saveAllItems(compound, this.stacks);
	 
	    if (this.hasCustomName()) {
	        compound.setString("CustomName", this.customName);
	    }
	 
	    compound.setInteger("burningTimeLeft", this.burningTimeLeft);
	    compound.setInteger("timePassed", this.timePassed);
	 
	    return compound;
	}
	
	@Override
	public boolean hasCustomName() {
	    return this.customName != null && !this.customName.isEmpty();
	}
	 
	@Override
	public String getName() {
	    return hasCustomName() ? this.customName : "tile.romod_smelter";
	}
	 
	public void setCustomName(String name) {
	    this.customName = name;
	}
	
	@Override
	public int getField(int id) {
	    switch (id) {
	        case 0:
	            return this.burningTimeLeft;
	        case 1:
	            return this.timePassed;
	    }
	    return 0;
	}
	 
	@Override
	public void setField(int id, int value) {
	    switch (id) {
	        case 0:
	            this.burningTimeLeft = value;
	            break;
	        case 1:
	            this.timePassed = value;
	    }
	}
	 
	@Override
	public int getFieldCount() {
	    return 2;
	}
	
	@Override
	public int getSizeInventory() {
	    return this.stacks.size();
	}
	 
	@Override
	public ItemStack getStackInSlot(int index) {
	    return this.stacks.get(index);
	}
	 
	@Override
	public ItemStack decrStackSize(int index, int count) {
	    return ItemStackHelper.getAndSplit(this.stacks, index, count);
	}
	 
	@Override
	public ItemStack removeStackFromSlot(int index) {
	    return ItemStackHelper.getAndRemove(stacks, index);
	}
	 
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
	    this.stacks.set(index, stack);
	 
	    if (stack.getCount() > this.getInventoryStackLimit()) {
	        stack.setCount(this.getInventoryStackLimit());
	    }
	}
	 
	@Override
	public int getInventoryStackLimit() {
	    return 64;
	}
	 
	@Override
	public boolean isEmpty() {
	    for(ItemStack stack : this.stacks) {
	        if (!stack.isEmpty()) {
	            return false;
	        }
	    }
	    return true;
	}
	 
	@Override
	public void clear() {
	    for(int i = 0; i < this.stacks.size(); i++) {
	        this.stacks.set(i, ItemStack.EMPTY);
	    }
	}
	
	@Override
	public void openInventory(EntityPlayer player) {
		System.out.println("opened Romod Smelter !");
	}
	 
	@Override
	public void closeInventory(EntityPlayer player) {}
	
	
	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
	    return null;
	}
	 
	@Override
	public String getGuiID() {
	    return null;
	}
	
	
	/*
	 * Fonction used for what can contains each slot
	 */
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
	    // Le slot 2 je n'autorise que les planches de bois
	    if (index == 2)
	        return OreDictionary.getOres("plankWood").contains(
	                new ItemStack(stack.getItem(), 1,
	                        OreDictionary.WILDCARD_VALUE));
	    // Le slot 3 (celui du résultat) je n'autorise rien
	    if (index == 3)
	        return false;
	    // Sinon pour les slots 1 et 2 on met ce qu'on veut
	    return true;
	}
	
	/* Check the position of the player by the block */
	public boolean isUsableByPlayer(EntityPlayer player) {
	    return this.world.getTileEntity(this.pos) != this ? false : player
	            .getDistanceSq((double) this.pos.getX() + 0.5D,
	                    (double) this.pos.getY() + 0.5D,
	                    (double) this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	public boolean hasFuelEmpty() {
		return this.getStackInSlot(2).isEmpty(); 
	}
	
	public ItemStack getRecipeResult() {
	    return RecipesRomodSMelter.getRecipeResult(new ItemStack[] {
	            this.getStackInSlot(0), this.getStackInSlot(1) });
	}
	
	public boolean canSmelt() {
	    // We recover the result of the recipe
	    ItemStack result = this.getRecipeResult();
	 
	    // While it's null, there's no recipe patern correct
	    if (result != null) {
	 
	        // We check if we can inject items into slot 2
	        ItemStack slot3 = this.getStackInSlot(3);
	 
	       
	        if (slot3.isEmpty())
	            return true;
	 
	        // Else if it's not free, wee check is the same item that the one we will create
	        if (slot3.getItem() == result.getItem() && slot3.getItemDamage() == result.getItemDamage()) {
	            int newStackSize = slot3.getCount() + result.getCount();
	            if (newStackSize <= this.getInventoryStackLimit() && newStackSize <= slot3.getMaxStackSize()) {
	                return true;
	            }
	        }
	        
	    }
	    return false;
	}
	public void smelt() {
	    // Cette fonction n'est appelée que si result != null, c'est pourquoi on ne fait pas de null check
	    ItemStack result = this.getRecipeResult();
	    // On enlève un item de chaque ingrédient
	    this.decrStackSize(0, 1);
	    this.decrStackSize(1, 1);
	    // On récupère le slot de résultat
	    ItemStack stack3 = this.getStackInSlot(3);
	    // Si il est vide
	    if (stack3.isEmpty()) {
	        // On y insère une copie du résultat
	        this.setInventorySlotContents(3, result.copy());
	    } else {
	        // Sinon on augmente le nombre d'objets de l'ItemStack
	        stack3.setCount(stack3.getCount() + result.getCount());
	    }
	}
	
	/** Temps de cuisson de la recette */
	public int getFullRecipeTime() {
	    return 200;
	}
	 
	/** Temps que dure 1 unité de carburant (ici : 1 planche) */
	public int getFullBurnTime() {
	    return 300;
	}
	 
	/** Renvoie vrai si le feu est allumé */
	public boolean isBurning() {
	    return burningTimeLeft > 0;
	}
	
	@Override
	public void update() {
	    if (!this.world.isRemote) {
	 
	        /* Si le carburant brûle, on réduit réduit le temps restant */
	        if (burningTimeLeft > 0) {
	            this.burningTimeLeft--;
	            RomodSmelter.setState(true, world, pos);
	        }
	 
	        /*
	            * Si la on peut faire cuire la recette et que le four ne cuit pas
	            * alors qu'il peut, alors on le met en route
	            */
	        if (!this.isBurning() && this.canSmelt() && !this.hasFuelEmpty()) {
	            this.burningTimeLeft = this.getFullBurnTime();
	            this.decrStackSize(2, 1);
	            //this.decrStackSize(0, 1);
	        }
	 
	        /* Si on peut faire cuire la recette et que le feu cuit */
	        if (this.isBurning() && this.canSmelt()) {
	            this.timePassed++;
	            if (timePassed >= this.getFullRecipeTime()) {
	                timePassed = 0;
	                this.smelt();
	            }
	        } else {
	            timePassed = 0;
	        }
	        
	        if (this.isBurning() == false) {
	        	RomodSmelter.setState(false, world, pos);
	        }
	        //System.out.println(this.canSmelt());
	        this.markDirty();
	        
	    }
	}
}
