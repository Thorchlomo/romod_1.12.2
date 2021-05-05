package com.byethost33.wikimetns.gui;

import com.byethost33.wikimetns.random_objects_main;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class RomodCreativeTabs extends CreativeTabs {

	public RomodCreativeTabs(String id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ItemStack getTabIconItem() {
		// TODO Auto-generated method stub
		return new ItemStack(random_objects_main.basalt_i);
	}

}
