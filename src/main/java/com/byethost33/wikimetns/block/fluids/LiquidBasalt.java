package com.byethost33.wikimetns.block.fluids;

import com.byethost33.wikimetns.random_objects_main;

import net.minecraft.util.ResourceLocation;

public class LiquidBasalt {
	public static final RomodFluid LiquidBasalt = (RomodFluid) new RomodFluid(
		"liquid_basalt",
		new ResourceLocation (random_objects_main.MODID + "liquid_basalt_still"),
		new ResourceLocation (random_objects_main.MODID + "liquid_basalt_flow")
	)
	.setMaterial(random_objects_main.LIQUID_BASALT)
	.setDensity(2000)
	.setGaseous(false)
	.setLuminosity(2)
	.setViscosity(20000)
	.setTemperature(1200);
}
