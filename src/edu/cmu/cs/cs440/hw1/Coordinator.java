package edu.cmu.cs.cs440.hw1;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Coordinator {

	boolean exit = false;
	public static void main(String args[])
   {
	   
   }

   public void startCommandLine()
   {
	   Scanner reader = new Scanner(System.in);
	   System.out.println("Hi! Please run all of you migratable processes here!");
	   System.out.println("Type 'help' to find out the commands!");
	   while(!exit)
	   {
		 String line =  reader.nextLine();
		 parseCommands(line);
	   }
   }

    public void parseCommands(String line)
    {
    	String [] args = line.split(" ");
    	String command = args[0].toUpperCase();
    	if(command.equals("HELP"))
    	{
    		help();
    	}
    	else if(command.equals("EXIT"))
    	{
    		exit();
    	}
    	else if(command.equals("PRINT"))
    	{
    		print();
    	}
    	else if(command.equals("START"))
    	{
    		start(args);
    	}
    	
    	
    	
    }
    
    public void help()
    {
    	
    }
    
    public void exit()
    {
    	exit = true;
    }
    
    public void print()
    {
    	
    }
    
    public void start(String[] args)
    {
    	
    }
	public void evalCommands() {
		/*
		 * Help command, running processes/which nodes they are on , prints all
		 * types of migratable processes, start a process , migrate a process, exit this tool, 
		 */
	}

}
