package com.byethost33.wikimetns;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import com.byethost33.wikimetns.block.basalt_block;
import com.byethost33.wikimetns.item.basalt_item;

@Mod(modid = random_objects_main.MODID, name = random_objects_main.NAME, version = random_objects_main.VERSION)
public class random_objects_main
{
    public static final String MODID = "romod";
    public static final String NAME = "random objects";
    public static final String VERSION = "1.0";

    private static Logger logger;
    
    //New variables
    public static ToolMaterial basalt;
    public static Item basalt_sword;
    public static Item basalt_i;
    public static Block basalt_block;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	
    	
        basalt = EnumHelper.addToolMaterial("basalt", 4, 6, 20.0F, 100.0F, 30);
        		
    	logger = event.getModLog();
        basalt_sword = new CustomSword();
        basalt_i = new basalt_item();
        basalt_block = new basalt_block("basalt_block", Material.ROCK);
        
        
        
        
        
        
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }
}

