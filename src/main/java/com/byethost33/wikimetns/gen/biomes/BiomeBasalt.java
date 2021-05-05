package com.byethost33.wikimetns.gen.biomes;

import com.byethost33.wikimetns.random_objects_main;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

public class BiomeBasalt extends Biome {

	public BiomeBasalt() {
		super(new BiomeProperties("Basalt Land").setBaseHeight(1.1F).setHeightVariation(2.0F).setTemperature(0.8F).setRainDisabled());
		
		// TODO Auto-generated constructor stub
		//définition du bloc du haut
		topBlock = random_objects_main.basalt_block.getDefaultState();
		fillerBlock = Blocks.NETHERRACK.getDefaultState();
		
		//this.decorator.gravelGen = new WorldGenMinable((IBlockState) Blocks.NETHERRACK, 10);
		this.decorator.deadBushPerChunk = 6;
	}

}
