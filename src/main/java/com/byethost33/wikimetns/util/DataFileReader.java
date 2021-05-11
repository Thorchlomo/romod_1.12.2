package com.byethost33.wikimetns.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.byethost33.wikimetns.random_objects_main;

/**
 *  <strong>DataFileReader</strong> is used in some class to interact with files on disk
 *  
 *  
 *  There's 2 mods:
 *  <ul>
 *  <li>ReadData, for the Favorite and for debug to avoid crahs
 *  <li>Friends, for save, update and read friends on disk
 *  </ul>
 * 
 * 
 * @author Thorchlomo
 *
 */
public class DataFileReader {
	
	public final static String pathToFriends = "list_of_friends_romod.txt";
	
	/** 
	 * truth table :
	 * 
	 * <ul>
	 * <li> 0 : Debug</li>
	 * <li> 1 : Read all the file</li>
	 * <li> 2 : Modify the specified line</li>
	 * <li> 3 : Initialize</li>
	 * </ul>
	 * 
	 * 
	 * P.S. : Mode take the following value, listed above
	 * 
	 * @param [int] mode, [String] data
	 * 
	 * 
	 * @author Thorchlomo
	 * @version 1.3
	 * 
	 */
	public static List<String> ReadData(int mode,String data) throws IOException{
		
		//creating the variables
		
		File fichier = new File("romod_data.txt");
		List<String> list=new ArrayList<String>(); //list used to store data of the file 
		
		if(fichier.exists() == true) {
			if(data == "") {
			System.out.println("romod_data.txt exist");
			}
		}
		if(fichier.exists() == false) {
			System.out.println("Creating romod_data.txt");
			System.out.println("If it is'nt your first login, please contact developpers");
			try {
	            // Créer un nouveau fichier
	            // Vérifier s'il n'existe pas
	            if (fichier.createNewFile())
	                System.out.println("File created");
	            else
	                System.out.println("File already exists");
	            
	        }
	        catch (Exception e) {
	            System.err.println(e);
	            System.exit(1);
	        }
		
		}
		if(data == "") {
		System.out.println(fichier.getAbsolutePath());
		}
		//mode de debug (first run)
		if(mode == 0) {
			try {
				BufferedReader in = new BufferedReader(new FileReader("romod_data.txt"));
				String line;
				while ((line = in.readLine()) != null)
				{
					// Afficher le contenu du fichier
					if(data == "") {
	   			  	System.out.println (line);
					}
				}
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		
		if(mode == 1) {
			try {
				BufferedReader in = new BufferedReader(new FileReader("romod_data.txt"));
				String line;
				while ((line = in.readLine()) != null)
				{
			      // Afficher le contenu du fichier
	   			  list.add(line);
				}
				in.close();
				if(data == "") {
					System.out.println("Debug romod:");
					System.out.print(list);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Something went wrong, please try restart your game and close config file,[error in romod_data.txt reading]");
			}
		}
		
		
		if(mode == 2) {
			BufferedReader in = new BufferedReader(new FileReader("romod_data.txt"));
			String line;
			while ((line = in.readLine()) != null)
			{
		      // Afficher le contenu du fichier
   			  list.add(line.replace(System.lineSeparator(), ""));
			}
			in.close();
			FileWriter fw = new FileWriter("romod_data.txt");
			
			int index = Integer.parseInt(data);  
			if(list.get(index).contains("true")){
				//if it's true set to false
				list.set(index, "false");
				System.out.println(list);
				for(String str: list) {
					  fw.write(str + System.lineSeparator());
				}	
			}
			else{
				//else (in any other case) set to true
				list.set(index, "true");
				System.out.println(list);
				for(String str: list) {
					  fw.write(str + System.lineSeparator());
				}
			}
			fw.close();
		}
		
		
		if (mode == 3) {
			// Lecture du fichier pour prendre connaissance de son contenu
			try {
				BufferedReader in = new BufferedReader(new FileReader("romod_data.txt"));
				String line;
				while ((line = in.readLine()) != null)
				{
					System.out.println(line);
					list.add(line);
				}
				in.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			
			//We check theres an normal amount of line
			if (list.size() != Integer.valueOf(data)) {
				System.out.println("An major error has been encounted, we will repair your file");
				int rest;
				rest = Integer.valueOf(data);
				System.out.println("Il manque " + rest + " lignes");
				System.out.println(list.size());
				while (rest != list.size()) {
					if(list.size() == 0) {list.add(random_objects_main.VERSION);}
					if(rest > list.size()) {
						list.add("debuged");
						System.out.println("added a line !");
					}
					
					if(rest < list.size()) {
						rest = list.size();
					}
					
				}
				
				FileWriter fw = new FileWriter("romod_data.txt"); // We create the file writer
				
				// And then we rewrite with the supplied lines
				for(String str: list) {
					  fw.write(System.lineSeparator() + str);
					  System.out.println(str);
				}
				
				// And we close the FileWriter
				fw.close();
			}
		}
		
		
		
		return list;
	}
	
	
	/**
	 * 
	 * Friends is used to interact with the friends stored on disk
	 * 
	 * Truth table :
	 * <ul>
	 * <li> 1 : Get the list of current Friends</li>
	 * <li> 2 : Remove a Friends :(</li>
	 * <li> 3 : Add a Friends :)</li>
	 * <ul>
	 * 
	 * 
	 * 
	 * @param mode
	 * @param data
	 * @return An list of current friends
	 * @throws IOException
	 */
	public static List<String> Friends(int mode,String data) throws IOException{
		
		
		File fichier = new File(pathToFriends);
		List<String> list=new ArrayList<String>(); //list used to store data of the file 
		
		if(fichier.exists() == true) {
			if(data == "") {
			System.out.println("assets\\romod_friends.txt exist");
			}
		}
		if(fichier.exists() == false) {
			System.out.println(fichier);
			System.out.println("If it is'nt your first login, please contact developpers");
			try {
	            // Créer un nouveau fichier
	            // Vérifier s'il n'existe pas
	            if (fichier.createNewFile())
	                System.out.println("File created");
	            else
	                System.out.println("File already exists");
	            
	        }
	        catch (Exception e) {
	            System.err.println(e);
	            //System.exit(1);
	        }
		
		}
		
		
		try {
			BufferedReader in = new BufferedReader(new FileReader(pathToFriends));
			String line;
			while ((line = in.readLine()) != null)
			{
				System.out.println(line);
				if(line != "\n") {
					list.add(line.replace(System.lineSeparator(), ""));
				}
				
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		switch (mode) {
			case 0 :
				System.out.println("list of friends readed");
				break;
			case 1 :
				list.remove(data);
				FileWriter fw = new FileWriter(pathToFriends); // We create the file writer
				
				// And then we rewrite with the supplied lines
				for(String str: list) {
					  fw.write(str + System.lineSeparator());
					  System.out.println(str);
				}
				
				// And we close the FileWriter
				fw.close();
				break;
			case 2 :
				list.add(data);
				FileWriter fw1 = new FileWriter(pathToFriends); // We create the file writer
				
				// And then we rewrite with the supplied lines
				for(String str: list) {
					  fw1.write(str + System.lineSeparator());
					  System.out.println(str);
				}
				
				// And we close the FileWriter
				fw1.close();
				break;
			default :
				System.out.println("ReadFriends : the mode sended didn't matche to any entry");
				break;
		
		
		}
		System.out.println(list);
		return list;
	}
	
}
