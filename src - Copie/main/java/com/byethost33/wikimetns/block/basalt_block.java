package com.byethost33.wikimetns.block;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class basalt_block extends Block{
	public basalt_block(String name, Material material) {
		super(material);
		setUnlocalizedName("basalt_block");
		setRegistryName("basalt_block");
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	}

}
