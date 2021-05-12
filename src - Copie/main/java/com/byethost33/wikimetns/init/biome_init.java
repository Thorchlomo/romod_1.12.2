package com.byethost33.wikimetns.init;

import com.byethost33.wikimetns.gen.biomes.BiomeBasalt;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class biome_init {
	
	
	public static final Biome BASALT = new BiomeBasalt();
	
	
	
	public static void regsiterBiomes() {
		initBiome(BASALT,"Basalt Land",BiomeType.WARM, Type.HILLS, Type.MOUNTAIN, Type.DRY);
	}
	
	
	public static Biome initBiome(Biome biome,String name,BiomeType biome_type,Type... types) {
		System.out.println("Started biome registering");
		biome.setRegistryName(name);
		ForgeRegistries.BIOMES.register(biome);
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(biome_type, new BiomeEntry(biome, 10));
		BiomeManager.addSpawnBiome(biome);
		System.out.println("Ended biome registery");
		return biome;
	}
}
