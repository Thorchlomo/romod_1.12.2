package com.byethost33.wikimetns.item.armor;

import com.byethost33.wikimetns.random_objects_main;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class basalt_armor extends ItemArmor{

	public basalt_armor(String name,ArmorMaterial materialIn,EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn,1, equipmentSlotIn);
		  setUnlocalizedName(name);
		  setRegistryName(name);
		  setCreativeTab(random_objects_main.romodCreativeTabs);
	}

	

	
}