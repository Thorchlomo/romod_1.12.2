package com.byethost33.wikimetns;

import net.minecraft.item.ItemSword;

public class CustomSword extends ItemSword {

	public CustomSword() {
		super(random_objects_main.basalt);
		// TODO Auto-generated constructor stub
		this.setRegistryName("basalt_sword");
		this.setUnlocalizedName("basalt_sword");
		this.setCreativeTab(random_objects_main.romodCreativeTabs);
	}

}
