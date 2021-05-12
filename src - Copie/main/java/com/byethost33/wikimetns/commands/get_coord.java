package com.byethost33.wikimetns.commands;



import java.math.RoundingMode;
import java.text.NumberFormat;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;




public class get_coord extends CommandBase{

	  @Override
	  public void execute(MinecraftServer server, ICommandSender sender, String[] params) throws CommandException {
		  
		



		  	//récuperation du point x dans message_db
		    /*
	        Double message_db_x = Minecraft.getMinecraft().player.posX;
	        */
		  
		  	/*
		  	int message_db_x = MathHelper.floor(Minecraft.getMinecraft().player.posX);
	        Double message_db_y = Minecraft.getMinecraft().player.posY;
	        int message_db_z = MathHelper.floor(Minecraft.getMinecraft().player.posZ);
	        */
		  
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
	        
	        //transformation du double contenant les coordonnées en string
	        /*
	        String message_x = String.valueOf(message_db_x);
	     
	        String message_y = String.valueOf(message_db_y);
	        String message_z = String.valueOf(message_db_z);
	        */
	        
	        
	        //affichage graphique dans le chat\\
	        TextComponentString text = new TextComponentString("vous êtes en :" + message_x + "  |  " + message_y + "  |  " + message_z);
	        text.getStyle().setColor(TextFormatting.BLUE);
	        text.getStyle().setBold(true);
	        sender.sendMessage(text);
	      
	  }

	  @Override
	  public String getName() {
	    return "whereiam";
	  }
	  
	  @Override
	  public int getRequiredPermissionLevel()
	  {
	      return 0;
	  }

	  @Override
	  public String getUsage(ICommandSender sender) {
	    return "command.whereiam.usage";
	  }
	}

