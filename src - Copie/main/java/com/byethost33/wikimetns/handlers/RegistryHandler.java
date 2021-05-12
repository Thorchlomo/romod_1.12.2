package com.byethost33.wikimetns.handlers;

import com.byethost33.wikimetns.init.biome_init;

import net.minecraftforge.fml.common.Mod.EventBusSubscriber;


@EventBusSubscriber
public class RegistryHandler {
	public static void otherRegistries() {
		
		//enregistrement des biomes
		biome_init.regsiterBiomes();
		}
}
