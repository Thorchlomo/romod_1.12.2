package com.byethost33.wikimetns.gui.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.byethost33.wikimetns.random_objects_main;
import com.byethost33.wikimetns.util.DataFileReader;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;

/**
 * This class is the GUI of the friends management menu, It take no argument
 * 
 * @since 1.2.0
 * @version 1.3
 * @author Thorchlomo
 *
 */

public class AddFriends extends GuiScreen{
	
	int DBwidth = 248;
 	int DBheight = 166;
 	
 	
 	List<String> listOfFriends = new ArrayList<String>();
 	
 		
 		
 	
    
	public static final ResourceLocation TEXTURE_FRIENDS_BACKGROUND = new ResourceLocation(random_objects_main.MODID + ":textures/gui/friends_menu.png");
	public static final ResourceLocation TEXTURE_FRIENDS_DELETE = new ResourceLocation(random_objects_main.MODID + ":textures/gui/icon_delete.png");
	
	
	
	private GuiTextField text;
	public void initGui() {
		
		super.initGui();
		
		try {
			listOfFriends = DataFileReader.Friends(0, "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	    
	    
		int i = (this.width - DBwidth) / 2;
	    int j = (this.height - DBheight) / 2;
	    
	    
		
        
        text = new GuiTextField(1, fontRenderer, i + 13, j + 11, 100, 18);
        text.setMaxStringLength(23);
        this.text.setFocused(true);
        this.buttonList.add(new GuiButton(1, i + 118, j + 10, 60, 20, I18n.translateToLocal("romod.gui.menu.friends.add.2")));	//test
        this.buttonList.add(new GuiButton(2, i + 182, j + 10, 56, 20, I18n.translateToLocal("romod.gui.menu.friends.remove")));
        
        
    }
	//test
	protected void actionPerformed(GuiButton button) throws IOException {
		
		switch (button.id) {
			case 1 :
				
				if (listOfFriends.contains(text.getText()) ||  text.getText() == "") {
					
				}
				else {
					System.out.println(text.getText());
					DataFileReader.Friends(2, text.getText());
					System.out.println(listOfFriends);
					initGui();
				}
				break;
			case 2 :
				System.out.println(text.getText());
				if (listOfFriends.contains(text.getText())){
					DataFileReader.Friends(1, text.getText());
					try {
						listOfFriends = DataFileReader.Friends(0, "");
					} catch (IOException e) {
						e.printStackTrace();
					}
					initGui();
				}
				else {
					System.out.println("Cet amis n'etait pas dans votre liste d'amis");
				}
				break;
			default :
				System.out.println("This button is not recognized" + button.id);
		}
		
	}
		
	//test
	protected void keyTyped(char par1, int par2)
    {
        try {
			super.keyTyped(par1, par2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.text.textboxKeyTyped(par1, par2);
    }
	
	public void updateScreen()
    {
        super.updateScreen();
        this.text.updateCursorCounter();
    }
	
	protected void mouseClicked(int x, int y, int btn) {
		
		this.text.mouseClicked(x, y, btn);
		try {
			super.mouseClicked(x, y, btn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		 * Part used to check click in the delete buttons
		 */
		
		
		
	}
	   
	   
	
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		    
		try {
			listOfFriends = DataFileReader.Friends(0, "");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int i = (this.width - DBwidth) / 2;
	    int j = (this.height - DBheight) / 2;
             
        // We create integers who contains the widht and height of "demo_background.png" 
		
		  
     	// We show the background here
     	mc.renderEngine.bindTexture(TEXTURE_FRIENDS_BACKGROUND);
     	drawModalRectWithCustomSizedTexture(i, j, 0, 0, DBwidth, DBheight, 256,256);
		
     	this.text.drawTextBox();
     	
     	int i2 = 37;
           
	    
	    for (int i1 = listOfFriends.size() - 1; i1 != -1; i1--) {
		   drawString(fontRenderer, listOfFriends.get(i1), i + 16, j + i2, 0xffffff);
		   i2 = i2 + 8;
	   }
	    
	    
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		
		
		
	    
	    
	   
	    
	    
	  
	   
	   
	   
	   
	   
        
	}
	
	public void drawBackground(int tint) {
     	super.drawBackground(tint);
     	
     
	}
	
   
}
