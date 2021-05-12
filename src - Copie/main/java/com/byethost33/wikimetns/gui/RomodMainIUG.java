package com.byethost33.wikimetns.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.byethost33.wikimetns.random_objects_main;
import com.byethost33.wikimetns.gui.main.MainInfoMenuUIG;
import com.byethost33.wikimetns.util.DataFileReader;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;

public class RomodMainIUG extends GuiScreen{
	
	

 	List<String> listOfFriends = new ArrayList<String>();
	
	
	//ressource location
	public static final ResourceLocation TEXTURE_INFO = new ResourceLocation(random_objects_main.MODID + ":textures/gui/info_button.png");
	public static final ResourceLocation TEXTURE_INFO_HOOVER = new ResourceLocation(random_objects_main.MODID + ":textures/gui/info_button_hoover.png");
	public static final ResourceLocation TEXTURE_SATURATION = new ResourceLocation(random_objects_main.MODID + ":textures/gui/saturation.png");
	public static final ResourceLocation TEXTURE_FAVORITE = new ResourceLocation("textures/gui/icons.png");

	//private boolean FirstBoucle;
	
	public int baseX = 50;
	public int baseY = 5;
	
	
	public int indexFriends = 0;
	
	//fonction utilisée pour afficher les dfferents composants 
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		
		/**
		 * 		RECOVER OF THE FACORITE
		 **/
		System.out.println(baseY);
		
		List<String> favorite = null;
		try {
			favorite = DataFileReader.ReadData(1, "no_debug");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
		//TODO more ergonomic widgets code
		/** 
		 * 		THIS IS THE CODE FOR ALL THE WIDGETS 
		**/
		//recuperation de la saturation
		if(favorite.get(1).contains("true")) {
			float sat = Minecraft.getMinecraft().player.getFoodStats().getSaturationLevel();
			int satu = (int)sat;
					
			//bind of the texture
			
			baseY = baseY + 6;
			
					
			//drawDefaultBackground();
			drawString(fontRenderer, I18n.translateToLocal("romod.gui.menu.saturation"), baseX, baseY, 0xffffff);
			baseY = baseY + 20;
			for(int i = satu; i>0; i--) {
				int xpos = i*10+baseX;
				mc.renderEngine.bindTexture(TEXTURE_SATURATION);
				drawModalRectWithCustomSizedTexture(xpos, baseY, 0, 0, 16,16,16,16);
			}
			
			baseY = baseY + 10;
		}
				
		System.out.println(baseY);
		
		
		//recuperation des amis
		if(favorite.get(2).contains("true")) {
			
			baseY = baseY + 10;
			
			try {
				listOfFriends = DataFileReader.Friends(0, "");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			drawString(fontRenderer, I18n.translateToLocal("romod.gui.menu.friends.list"), baseX, baseY, 0xffffff);
			baseY = baseY + 10;
			drawString(fontRenderer, I18n.translateToLocal("romod.gui.menu.friends.connected"), baseX, baseY, 0xffffff);
			
			Collection<NetworkPlayerInfo>playersC=Minecraft.getMinecraft().getConnection().getPlayerInfoMap();
			
			
			//System.out.println(Arrays.toString(playersC.toArray()));
			
			baseY = baseY + 10;
		
	    	playersC.forEach((loadedPlayer) -> {
	    		
				if(indexFriends  < 11) {
					String loadedPlayerName = loadedPlayer.getGameProfile().getName();
					
					if (listOfFriends.contains(loadedPlayerName)) {
						
						
						//System.out.println(loadedPlayerName);
						drawString(fontRenderer, I18n.translateToLocal(loadedPlayerName), baseX + 10, baseY, 0xffffff);
						
						
						/**
						 * 		PART WHO SHOW UP FRIENDS HEAD
						 **/
						ResourceLocation resourcelocation = DefaultPlayerSkin.getDefaultSkinLegacy();
				    	
				    	Minecraft minecraft = Minecraft.getMinecraft();
				        Map<Type, MinecraftProfileTexture> map = minecraft.getSkinManager().loadSkinFromCache(loadedPlayer.getGameProfile());//Minecraft.getMinecraft().player.getGameProfile());
				        
				        
				        
				        if (map.containsKey(Type.SKIN))
				        {
				            resourcelocation = minecraft.getSkinManager().loadSkin(map.get(Type.SKIN), Type.SKIN);
				        }
				        else
				        {
				            UUID uuid = EntityPlayer.getUUID(Minecraft.getMinecraft().player.getGameProfile());
				            resourcelocation = DefaultPlayerSkin.getDefaultSkin(uuid);
				        }
				        
				        mc.renderEngine.bindTexture(resourcelocation);
				        drawModalRectWithCustomSizedTexture(baseX, baseY, 8, 8, 8,8,64,64);
				        
				        /**
				         * 		END PART INCREMENTATION FOR NEXT TURN
				         **/
						baseY = baseY + 8;
						indexFriends ++;
					}
	    		}
				else {
					
					indexFriends ++;
				}
				
				
				
				
			});
			if (indexFriends > 11) {
				drawString(fontRenderer, indexFriends + I18n.translateToLocal("romod.gui.menu.friends.too"), baseX, baseY, 0xffffff);
				baseY = baseY + 80;
			}
		}
			
		
		/**
		 * 			END OF CODE FOR WIDGETS
		 **/
		
		//Resets Var
		
		baseX = 50;
		baseY = 5;
		
		indexFriends = 0;
		
		
		mc.renderEngine.bindTexture(TEXTURE_INFO);
		drawModalRectWithCustomSizedTexture(3, 5, 0, 0, 32, 32, 32, 32);
		
		if( mouseX > 3 && mouseX < 35 && mouseY > 5 && mouseY < 37) {
			mc.renderEngine.bindTexture(TEXTURE_INFO_HOOVER);
			drawModalRectWithCustomSizedTexture(3, 5, 0, 0, 32, 32, 32, 32);
			
		}
		
		
		//drawString(fontRenderer, "Hello Minecraft World", mouseX, mouseY, 0xede728);
		//drawString(fontRenderer, "|", 50, 10, 0xede728);//d92323);
		int i;
		for(i = 0; i < 512; i++) {
			drawString(fontRenderer, "|", 39, i, 0xd92323);
		}
		super.drawScreen(mouseX, mouseY, partialTicks);
		
				
	}
	
	
	
	
	//mouse clicked
	public void mouseClicked(int mouseX, int mouseY, int mousebutton) {
		/** click on info button**/
		if( mouseX > 3 && mouseX < 35 && mouseY > 5 && mouseY < 37) {
	
		    System.out.println("players :");
		    
		    
        	
		    	
		    try {
				System.out.println(DataFileReader.ReadData(1, ""));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				TextComponentString error_dfr = new TextComponentString("[ROMOD]: Something went wrong in DFR, please restart your game");
				error_dfr.getStyle().setColor(TextFormatting.RED);
				Minecraft.getMinecraft().player.sendMessage(error_dfr);
			}
		    Minecraft.getMinecraft().displayGuiScreen(new MainInfoMenuUIG());
		}
	}
}	
		
    
