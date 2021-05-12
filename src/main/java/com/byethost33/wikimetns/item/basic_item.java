package com.byethost33.wikimetns.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
/**
 * A class who is used to create generic-crafting item
 * 
 * 
 * @since 1.2.0
 * @version 1.0
 * @author Thorchlomo
 *
 */
public class basic_item extends Item{
	/**
	 * <h2>Used to create generic item</h2>
	 * Do not use for special items, it can only set name and creative tabs
	 * 
	 * 
	 * @param [String] registryName
	 * @param [String] unlocalizedName
	 * @param [CreativeTabs] creativetab
	 * 
	 * @since 1.2.0
	 * @author Thorchlomo
	 */
	public basic_item(String registryName, String unlocalizedName, CreativeTabs creativetab) {
		this.setRegistryName(registryName);
		this.setUnlocalizedName(unlocalizedName);
		this.setCreativeTab(creativetab);
	}
	
}
