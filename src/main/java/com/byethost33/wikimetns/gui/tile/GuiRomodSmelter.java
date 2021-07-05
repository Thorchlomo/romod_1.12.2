package com.byethost33.wikimetns.gui.tile;

import com.byethost33.wikimetns.random_objects_main;
import com.byethost33.wikimetns.container.ContainerRomodSmelter;
import com.byethost33.wikimetns.tile.TileRomodSmelter;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiRomodSmelter extends GuiContainer{
	private static final ResourceLocation background = new ResourceLocation(random_objects_main.MODID + ":textures/gui/saturation.png");
	private TileRomodSmelter tile;
	
	public GuiRomodSmelter(TileRomodSmelter tile, InventoryPlayer playerInv) {
        super(new ContainerRomodSmelter(tile, playerInv));
        
        System.out.println("Did the machine is in debug ?");
        this.tile = tile;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float arg0, int arg1, int arg2) {
		
		/*
		 *TODO rewrtie the GUI
		 */
		int i = (this.width - this.xSize) / 2;
	    int j = (this.height - this.ySize) / 2;
	    this.drawDefaultBackground();
	    this.mc.getTextureManager().bindTexture(background);
	    this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
	 
	    int timePassed = this.tile.getField(1);
	    int textureWidth = (int) (23f / 200f * timePassed);
	    this.drawTexturedModalRect(i + 81, j + 24, 177, 18, textureWidth, 7);
	 
	    if (this.tile.isBurning()) {
	        int burningTime = this.tile.getField(0);
	        int textureHeight = (int) (12f / this.tile.getFullBurnTime() * burningTime);
	        this.drawTexturedModalRect(i + 37, j + 26 + 12 - textureHeight,
	                177, 12 - textureHeight, 27, textureHeight);
	    }
	 
	    this.fontRenderer.drawString(this.tile.getName(), i + 80, j + 45, 0xFFFFFF);
		
	}
}
