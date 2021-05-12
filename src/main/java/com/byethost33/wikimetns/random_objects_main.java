package com.byethost33.wikimetns;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.byethost33.wikimetns.block.*;
import com.byethost33.wikimetns.block.wheel.barrows.wheel_barrows_dirt;
import com.byethost33.wikimetns.gen.worldGen;
import com.byethost33.wikimetns.gui.RomodCreativeTabs;
import com.byethost33.wikimetns.init.biome_init;
import com.byethost33.wikimetns.item.*;
import com.byethost33.wikimetns.item.armor.basalt_armor;
import com.byethost33.wikimetns.util.DataFileReader;

@Mod(modid = random_objects_main.MODID, name = random_objects_main.NAME, version = random_objects_main.VERSION)
public class random_objects_main
{
    public static final String MODID = "romod";
    public static final String NAME = "random objects";
    public static final String VERSION = "1.2.0";
    
    
    
    @Mod.Instance(random_objects_main.MODID)
    public static random_objects_main instance;
    
    
    //declaration des proxy
    @SidedProxy(clientSide = "com.byethost33.wikimetns.ClientProxy", serverSide = "com.byethost33.wikimetns.ServerProxy")
    public static CommonProxy proxy;


    private static Logger logger;
    
    //New variables
    public static ToolMaterial basalt;
    public static Item basalt_sword;
    public static Item basalt_i;
    public static Item basalt_block_item;
    public static Item basalt_ingot;
    public static Item basalt_fragment;
    public static Item wheel_barrows_item;
    public static Item redstone_stick;
    //public static Block basalt_block;
    public static Block wheel_barrows;
    public static Item dirt_bag;
    public static Item flower_kit;
    public static Item transplanter;
    
    public static double_houe double_houe;
    public static ToolMaterial super_diamond;
    
    //wheel barrows under here
    
    public static Block wheel_barrows_dirt;
    
    public static Block wheel_barrows_tulip;
    
    //armor
    public static Item basalt_helmet;
    public static Item basalt_chestplate;
    public static Item basalt_leggings;
    public static Item basalt_boots;
    
    /*
    public static Item obsidian_helmet;
    public static Item obsidian_chestplate;
    public static Item obsidian_leggings;
    public static Item obsidian_boots;
    */
    
    public static ArmorMaterial ARMOR_MATERIAL_BASALT;
    public static ArmorMaterial ARMOR_MATERIAL_OBSIDIAN;
    
    public static Block basalt_block = new basalt_block("basalt_block", Material.ROCK);
    
    public static CreativeTabs romodCreativeTabs = new RomodCreativeTabs("ROMOD");
    
    
   
    
    
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	
    	
    	if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
    		System.out.println("Verifictaion des fichiers de préferences");
        	try {
    			List<String> test = DataFileReader.ReadData(0,"");
    			System.out.println(test);
    			test = DataFileReader.ReadData(3, "3");
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    			System.exit(1);
    		}
    	}

    	
    	proxy.preInit(event.getSuggestedConfigurationFile());
    	biome_init.regsiterBiomes();
    	//déclaration des matériaux
        basalt = EnumHelper.addToolMaterial("basalt", 4, 6, 20.0F, 26.0F, 30);
        super_diamond = EnumHelper.addToolMaterial("super_diamond", 3, 1542, 20.0F, 10.0F, 20);
        
        
        //déclaration des armor material
        ARMOR_MATERIAL_BASALT = EnumHelper.addArmorMaterial("armor_material_basalt", MODID + ":basalt", 50, new int[] {3, 7, 5, 2}, 20, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);
        ARMOR_MATERIAL_OBSIDIAN = EnumHelper.addArmorMaterial("armor_material_obsidian", MODID + ":obsidian", 600, new int[] {6, 14, 10, 5}, 10, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 0.0F);
        
        		
        
        //déclaration des items/blocks
        
    	logger = event.getModLog();
        basalt_sword = new CustomSword();
        basalt_i = new basalt_item();
        //basalt_block = new basalt_block("basalt_block", Material.ROCK);
        basalt_block_item = new ItemBlock(random_objects_main.basalt_block).setRegistryName(random_objects_main.basalt_block.getRegistryName());
        basalt_ingot = new basic_item("basalt_ingot", "basalt_ingot", romodCreativeTabs);
        basalt_fragment = new basic_item("basalt_fragment", "basalt_fragment", romodCreativeTabs);
        double_houe = new double_houe();
        redstone_stick = new redstone_stick();
        wheel_barrows = new wheel_barrows("wheel_barrows", Material.WOOD);
        wheel_barrows_item = new ItemBlock(random_objects_main.wheel_barrows).setRegistryName(random_objects_main.wheel_barrows.getRegistryName());
        dirt_bag = new dirt_bag();
        flower_kit = new flower_kit();
        transplanter = new transplanter();
        
        System.out.println("Variables définies");
        
        
        //déclarations des differentes variables de brouettes :
        
        wheel_barrows_dirt = new wheel_barrows_dirt("wheel_barrows_dirt", Material.WOOD);
        wheel_barrows_tulip = new wheel_barrows_dirt("wheel_barrows_tulip", Material.WOOD);
        
        
        //armor
        
        basalt_helmet = new basalt_armor("basalt_helmet", ARMOR_MATERIAL_BASALT, EntityEquipmentSlot.HEAD);
        basalt_chestplate = new basalt_armor("basalt_chestplate", ARMOR_MATERIAL_BASALT, EntityEquipmentSlot.CHEST);
        basalt_leggings = new basalt_armor("basalt_leggings", ARMOR_MATERIAL_BASALT, EntityEquipmentSlot.LEGS);
        basalt_boots = new basalt_armor("basalt_boots",ARMOR_MATERIAL_BASALT, EntityEquipmentSlot.FEET);
        		
        //obsidian_helmet = new obsidian_armor("obsidian_helemet", ARMOR_MATERIAL_OBSIDIAN, EntityEquipmentSlot.HEAD);
        
        
        
        
        
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	proxy.init();
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        
        //activation of the generator
        GameRegistry.registerWorldGenerator(new worldGen(), 3);
        
        
        
    }
    /*
    @EventHandler
    public void init(FMLServerStartingEvent event)
    {
      logger.info("initalise FMLServerStartingEvent :" + NAME);
      event.registerServerCommand(new get_coord());
    }
    */
}

