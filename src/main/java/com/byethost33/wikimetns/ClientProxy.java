package com.byethost33.wikimetns;

import java.io.File;
import java.math.RoundingMode;
import java.text.NumberFormat;

import org.lwjgl.input.Keyboard;

import com.byethost33.wikimetns.commands.get_coord;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;

public class ClientProxy extends CommonProxy{
	
	
	//####################UTIL##################\\
	//code pour obtenir les coordonnés :
	public TextComponentString TransformPlayerCoords (EntityPlayerSP player) {
		  //récuperation du point x dans message_db
		    
		  
		  	Double message_db_x = Minecraft.getMinecraft().player.posX;
		  	Double message_db_y = Minecraft.getMinecraft().player.posY;
		  	Double message_db_z = Minecraft.getMinecraft().player.posZ;
		    
		    
		    //on arrondit pour éviter 14 décimales
		    
		    
		    NumberFormat nf = NumberFormat.getNumberInstance();
		    nf.setMaximumFractionDigits(0);
		     
		    nf.setRoundingMode(RoundingMode.DOWN);
		    String message_x = nf.format(message_db_x);
		    
		    nf.setRoundingMode(RoundingMode.DOWN);
		    String message_y = nf.format(message_db_y);
		    
		    nf.setRoundingMode(RoundingMode.DOWN);
		    String message_z = nf.format(message_db_z);
		    
		    
		    TextComponentString text = new TextComponentString("vous êtes en :" + message_x + "  |  " + message_y + "  |  " + message_z);
		    text.getStyle().setColor(TextFormatting.BLUE);
		    return text;
	    }
	    
	    
	//#####################REST#########################\\
	
	@Override
    public void preInit(File e) {
        super.preInit(e);
        System.out.println("random_objects : start preinitialisation of the client proxy");
        
        
    }
	
	
	@Override
	public void init() {
		super.init();
		System.out.println("random_objects : start initialisation of the client proxy");
		MinecraftForge.EVENT_BUS.register(this);
		ClientCommandHandler.instance.registerCommand(new get_coord());
	}
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent e) {
		
    }
	
	//keybindings
	private static KeyBinding getCoords;
	private static KeyBinding RomodMainIUG;
	 
	
	//définition des touches pour le menu de configuration
    public ClientProxy()
    {
        getCoords = new KeyBinding("romod.get_coords.key", Keyboard.KEY_W, "key.categories.gameplay");
        RomodMainIUG = new KeyBinding("romod.main_menu.key", Keyboard.KEY_U, "key.categories.gameplay");
        ClientRegistry.registerKeyBinding(getCoords);
        ClientRegistry.registerKeyBinding(RomodMainIUG);
    }
	
    //gestionnaire des evenement de touche
    @SubscribeEvent
    public void onEvent(KeyInputEvent event)
    {
        if(getCoords.isPressed())
        {
            keyGetCoords();
        }
        if(RomodMainIUG.isKeyDown())
        {
        	KeyShowMainIuG();
        }
    }
    
    


	private void keyGetCoords()
    {
    	TextComponentString text_go;
    	text_go = TransformPlayerCoords(Minecraft.getMinecraft().player);
        Minecraft.getMinecraft().player.sendMessage(text_go);			//thePlayer.addChatComponentMessage(new ChatComponentText("lol, it works"));
    }
    
    
    private void KeyShowMainIuG() {
		System.out.println("Showing Main IUG of random objects mod");
		Minecraft.getMinecraft().displayGuiScreen(new com.byethost33.wikimetns.gui.RomodMainIUG());
	}
    
    
    
    
    
    
}




