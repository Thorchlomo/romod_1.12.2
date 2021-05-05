package com.byethost33.wikimetns.gen;

import java.util.Random;



import com.byethost33.wikimetns.random_objects_main;

import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;



public class worldGen implements IWorldGenerator
{
	private WorldGenerator basalt_nether;
	private WorldGenerator basalt_classic;
	
	
	public worldGen() 
	{
		basalt_nether = new WorldGenMinable(random_objects_main.basalt_block.getDefaultState(), 9, BlockMatcher.forBlock(Blocks.NETHERRACK));
		basalt_classic = new WorldGenMinable(random_objects_main.basalt_block.getDefaultState(), 9, BlockMatcher.forBlock(Blocks.STONE));
		
	}
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) 
	{
		switch(world.provider.getDimension())
		{
		case -1:
			
			runGenerator(basalt_nether, world, random, chunkX, chunkZ, 75, 0, 100);
			
			break;
			
		case 0:
			
			runGenerator(basalt_classic, world, random, chunkX, chunkZ, 25, 0, 100);
			
			break;
			
		case 1:
			
			
		}
	}
	
	private void runGenerator(WorldGenerator gen, World world, Random rand, int chunkX, int chunkZ, int chance, int minHeight, int maxHeight)
	{
		if(minHeight > maxHeight || minHeight < 0 || maxHeight > 256) throw new IllegalArgumentException("Ore generated out of bounds");
		
		int heightDiff = maxHeight - minHeight + 1;
		for(int i = 0; i < chance; i++)
		{
			int x = chunkX * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunkZ * 16 + rand.nextInt(16);
			
			gen.generate(world, rand, new BlockPos(x,y,z));
		}
	}
}
/*
public class worldGen implements IWorldGenerator {
	
	

	
	
	
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) 
	{
		if(world.provider.getDimension() == 0) {
			generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
		
		else if(world.provider.getDimension() == -1){
			generateNether(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
		}
	}
	
	private void generateOverworld (Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkgenerator, IChunkProvider chunkprovider) 
	{
		generateOre(random_objects_main.basalt_block.getDefaultState(),world, random, chunkX * 16, chunkZ * 16, 16, 20, random.nextInt(7) + 4, 64);
	}
	
	
	private void generateNether (Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkgenerator, IChunkProvider chunkprovider) 
	{
		generateOre(random_objects_main.basalt_block.getDefaultState(),world, random, chunkX * 16, chunkZ * 16, 16, 20, random.nextInt(7) + 4, 64);
	}
	
	private void generateOre(IBlockState ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances) 
	{
	   int deltaY = maxY - minY; 
	   
	   for (int i = 0; i < chances; i++) {
		   BlockPos pos = new BlockPos(x + random.nextInt(16), minY + random.nextInt(deltaY), z + random.nextInt(16));
		   
		   WorldGenMinable generator = new WorldGenMinable(ore, size); 
		   generator.generate(world, random, pos);
	   }
	}
}
*/
