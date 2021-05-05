package com.byethost33.wikimetns.block;


import com.byethost33.wikimetns.random_objects_main;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class basalt_block extends Block{
	public basalt_block(String name, Material material) {
		super(material);
		setUnlocalizedName("basalt_block");
		setRegistryName("basalt_block");
		setCreativeTab(random_objects_main.romodCreativeTabs);
	}

}
