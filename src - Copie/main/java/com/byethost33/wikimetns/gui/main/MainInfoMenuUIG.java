package com.byethost33.wikimetns.gui.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.byethost33.wikimetns.random_objects_main;
import com.byethost33.wikimetns.util.DataFileReader;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

public class MainInfoMenuUIG extends GuiScreen {
	
	
	




	boolean est = true;
	
	boolean favorite_saturation = false;
	boolean favorite_friends = false;
	//ressources locations
	public static final ResourceLocation TEXTURE_SATURATION = new ResourceLocation(random_objects_main.MODID + ":textures/gui/saturation.png");
	public static final ResourceLocation TEXTURE_FAVORITE = new ResourceLocation("textures/gui/icons.png");
	
	public int baseYFriends = 48;
	public int indexFriends = 0;
	
 	List<String> listOfFriends = new ArrayList<String>();
	
	public void initGui() {
		super.initGui();
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(1, 60, 142, 100, 20, I18n.translateToLocal("romod.gui.menu.friends.add")));
	}
	

	

	//fonction utilisée pour afficher les dfferents composants 
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		baseYFriends = 50;
		//récuperation des favoris
		 
		List<String> favorite = null;
		try {
			favorite = DataFileReader.ReadData(1, "no_debug");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//recuperation de la saturation
		float sat = Minecraft.getMinecraft().player.getFoodStats().getSaturationLevel();
		int satu = (int)sat;
		
		//bind of the texture
		
		
		drawDefaultBackground();
		
		mc.renderEngine.bindTexture(TEXTURE_FAVORITE);
		
		//Favorite button Saturation
		if(favorite.get(1).contains("false")) {drawModalRectWithCustomSizedTexture(120, 1, 16, 0, 9, 9, 256,256);favorite_saturation = false;}
		if(favorite.get(1).contains("true")) {drawModalRectWithCustomSizedTexture(121, 2, 161, 1, 8, 8, 256, 256);favorite_saturation = true;}
		else {drawModalRectWithCustomSizedTexture(120, 1, 16, 0, 9, 9, 256,256);favorite_saturation = false;}
	
    	
    	//Favorite button Friends
    	if(favorite.get(2).contains("false")) {drawModalRectWithCustomSizedTexture(120, 31, 16, 0, 9, 9, 256,256);favorite_friends = false;}
		if(favorite.get(2).contains("true")) {drawModalRectWithCustomSizedTexture(121, 32, 161, 1, 8, 8, 256, 256);favorite_friends = true;}
		else {drawModalRectWithCustomSizedTexture(120, 31, 16, 0, 9, 9, 256,256);favorite_friends = false;}
		
		
		//put highlight in last due to a minecraft gui specification
		if(mouseX > 119 && mouseX < 129 && mouseY > 0 && mouseY < 9) {drawHoveringText(I18n.translateToLocal("romod.gui.menu.like"), mouseX, mouseY);}
		
    	if(mouseX > 119 && mouseX < 129 && mouseY > 31 && mouseY < 40) {drawHoveringText(I18n.translateToLocal("romod.gui.menu.like"), mouseX, mouseY);}
		
		drawString(fontRenderer, I18n.translateToLocal("romod.gui.menu.saturation"), 1, 1, 0xffffff);
		
		//higlight help for favorite
		if(mouseX > 119 && mouseX < 129 && mouseY > 0 && mouseY < 9) {drawHoveringText(I18n.translateToLocal("romod.gui.menu.like"), mouseX, mouseY);}
		for(int i = satu; i>0; i--) {
			int xpos = i*10;
			mc.renderEngine.bindTexture(TEXTURE_SATURATION);
			drawModalRectWithCustomSizedTexture(xpos + 8, 15, 0, 0, 16,16,16,16);
			
			
		}
		
		
		//partie qui s'ocupe de récuperer la saturation du joueur\\
		//System.out.println(Minecraft.getMinecraft().player.getFoodStats().getSaturationLevel());
		
		//super.drawScreen(mouseX, mouseY, partialTicks);	
		
		/**
		 * 		FRIENDS WIDGET PART
		 */
		
		
		/*
		 *	We recover the list of friends
		 */
		
		try {
			listOfFriends = DataFileReader.Friends(0, "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		drawString(fontRenderer, I18n.translateToLocal("romod.gui.menu.friends.list"), 1, 35, 0xffffff);
		drawString(fontRenderer, I18n.translateToLocal("romod.gui.menu.friends.connected"), 5, 42, 0xffffff);
		indexFriends = 0;
		Collection<NetworkPlayerInfo>playersC=Minecraft.getMinecraft().getConnection().getPlayerInfoMap();
		//System.out.println(Arrays.toString(playersC.toArray()));
    	playersC.forEach((loadedPlayer) -> {
    		
			if(indexFriends  < 11) {
				String loadedPlayerName = loadedPlayer.getGameProfile().getName();
				
				if (listOfFriends.contains(loadedPlayerName)) {
				
					//System.out.println(loadedPlayerName);
					drawString(fontRenderer, I18n.translateToLocal(loadedPlayerName), 10, baseYFriends, 0xffffff);
					
					
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
			        drawModalRectWithCustomSizedTexture(1, baseYFriends, 8, 8, 8,8,64,64);
			        
			        /**
			         * 		END PART INCREMENTATION FOR NEXT TURN
			         **/
					baseYFriends = baseYFriends + 8;
					indexFriends ++;
				}
    		}
			else {
				
				indexFriends ++;
			}
			
			
			
			
		});
		
    	
    	if (indexFriends > 11) {
			drawString(fontRenderer, indexFriends + I18n.translateToLocal("romod.gui.menu.friends.too"), 5, 150, 0xffffff);
		}
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	
	
	
	
	public void mouseClicked(int mouseX, int mouseY, int mousebutton) {
			if(mouseX > 119 && mouseX < 129 && mouseY > 0 && mouseY < 9) {
				try {
					DataFileReader.ReadData(2, "1");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(1);
				}
			}
			
			if(mouseX > 119 && mouseX < 129 && mouseY > 31 && mouseY < 40) {
				try {
					DataFileReader.ReadData(2, "2");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(1);
				}
			}
			
			
			if(mouseX > 60 && mouseX < 160 && mouseY > 142 && mouseY < 162) {
				Minecraft.getMinecraft().displayGuiScreen(new AddFriends());
			}
		}
	
	
	
}
