package com.byethost33.wikimetns;



import java.io.File;

import com.byethost33.wikimetns.init.biome_init;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class CommonProxy {
	
	
	
	//initialisation event
	public void preInit(File e) {
		System.out.println("romod: start preinitialization event of the common proxy");
    }

    public void init() {
    	System.out.println("romod: start initialization event of the common proxy");
    	
    }

    public void postInit() {
    	System.out.println("romod: common proxy correctly initializating");
    }
	
	
	@SubscribeEvent
	public static void registerRender(ModelRegistryEvent event) {
		registerRender(random_objects_main.basalt_sword);
		registerRender(random_objects_main.basalt_i);
		registerRender(random_objects_main.basalt_fragment);
		registerRender(random_objects_main.basalt_ingot);
		registerRender(random_objects_main.basalt_block_item);
		registerRender(random_objects_main.double_houe);
		registerRender(random_objects_main.redstone_stick);
		registerRender(random_objects_main.wheel_barrows_item);
		registerRender(random_objects_main.flower_kit);
		registerRender(random_objects_main.dirt_bag);
		registerRender(random_objects_main.transplanter);
		registerRender(random_objects_main.basalt_helmet);
		registerRender(random_objects_main.basalt_chestplate);
		registerRender(random_objects_main.basalt_leggings);
		registerRender(random_objects_main.basalt_boots);
		registerRender(random_objects_main.romod_smelter_item);
		//registerRender(random_objects_main.obsidian_helmet);
	}
	
	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation( item.getRegistryName(), "inventory"));
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(random_objects_main.basalt_sword);
		event.getRegistry().registerAll(random_objects_main.basalt_i);
		event.getRegistry().registerAll(random_objects_main.basalt_fragment);
		event.getRegistry().registerAll(random_objects_main.basalt_ingot);
		event.getRegistry().registerAll(random_objects_main.basalt_block_item);
		event.getRegistry().registerAll(random_objects_main.double_houe);
		event.getRegistry().registerAll(random_objects_main.redstone_stick);
		event.getRegistry().registerAll(random_objects_main.wheel_barrows_item);
		event.getRegistry().registerAll(random_objects_main.flower_kit);	
		event.getRegistry().registerAll(random_objects_main.dirt_bag);
		event.getRegistry().registerAll(random_objects_main.transplanter);
		event.getRegistry().registerAll(random_objects_main.basalt_helmet);
		event.getRegistry().registerAll(random_objects_main.basalt_chestplate);
		event.getRegistry().registerAll(random_objects_main.basalt_leggings);
		event.getRegistry().registerAll(random_objects_main.basalt_boots);
		event.getRegistry().registerAll(random_objects_main.romod_smelter_item);
		//event.getRegistry().registerAll(random_objects_main.obsidian_helmet);
		
	}
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(random_objects_main.basalt_block);
		event.getRegistry().registerAll(random_objects_main.wheel_barrows);
		event.getRegistry().registerAll(random_objects_main.wheel_barrows_dirt);
		event.getRegistry().registerAll(random_objects_main.wheel_barrows_tulip);
		event.getRegistry().registerAll(random_objects_main.romod_smelter);
	}
	
	public static void otherRegistries() {
		
		
		biome_init.regsiterBiomes();
	}
	
}
