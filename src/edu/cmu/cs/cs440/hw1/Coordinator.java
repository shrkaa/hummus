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
    	else if (command.equals("MIGRATE"))
    	{
    		migrate();
    	}
    	else if (command.equals("REMOVE"))
    	{
    		remove();
    	}
    }
    
    private void remove() {
		// TODO Auto-generated method stub
		
	}

	private void migrate() {
		// TODO Auto-generated method stub
		
	}

	public void help()
    {
    	System.out.println("How to run \"Migratable Processes\" on the Command Line:");
    	System.out.println();
    	System.out.println("start <process-name>: Starts a new process of type \"processname\"");
    	System.out.println();
    	System.out.println("migrate <process-name> <destination-hostname>: Migrates the specified process to a new node with the given hostname");
    	System.out.println();
    	System.out.println("remove <process-name>: Removes the specified process from the current node");
    	System.out.println();
    	System.out.println("print: Prints all the processes currently running on your node");
    	System.out.println();
    	System.out.println("exit: Exits the program");
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
