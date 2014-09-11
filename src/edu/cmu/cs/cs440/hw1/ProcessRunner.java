package edu.cmu.cs.cs440.hw1;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProcessRunner {

	static boolean exit = false;
	static ProcessManager manager = new ProcessManager();

	public static void main(String args[]) {
		startServer();
		startCommandLine();
	}

	public static void startServer()
	{
		new Thread(new ProcessServerSocket(manager)).start();
	}
	public static void startCommandLine() {
		Scanner reader = new Scanner(System.in);
		System.out
				.println("Hi! Please run all of you migratable processes here!");
		System.out.println("Type 'help' to find out the commands!");
		while (!exit) {
			System.out.print("> ");
			String line = reader.nextLine();
			parseCommands(line);
		}
		System.exit(1);
	}

	public static void parseCommands(String line) {
		String[] args = line.split(" ");
		System.out.println(args.length);
		String command = args[0].toUpperCase();
		if (command.equals("HELP")) {
			help();
		} else if (command.equals("EXIT")) {
			exit();
		} else if (command.equals("PRINT")) {
			print();
		} else if (command.equals("START")) {
			start(args);
		} else if (command.equals("MIGRATE")) {
			migrate(args);
		} else if (command.equals("REMOVE")) {
			remove();
		}
	}

	private static void remove() {
		// TODO Auto-generated method stub

	}

	private static void migrate(String[] args) {
		if (args.length != 3) {
			System.out.println("Wrong usuage of command migrate");
			System.out.println("Try Again!");
		} else {
			MigratableProcess process = manager.findProcess(Integer
					.parseInt(args[1]));
			if (process != null)
				manager.migrateProcess(process, args[2]);
			else
				System.out.println("The process id was invalid, try again!");
		}
	}

	public static void help() {
		System.out
				.println("How to run \"Migratable Processes\" on the Command Line:");
		System.out.println();
		System.out
				.println("start <process-name> <args1> ..<argn>: Starts a new process of type \"processname\"");
		System.out.println();
		System.out
				.println("migrate <process-id> <destination-hostname>: Migrates the specified process to a new node with the given hostname");
		System.out.println();
		System.out
				.println("remove <process-name>: Removes the specified process from the current node");
		System.out.println();
		System.out
				.println("print: Prints all the processes currently running on your node");
		System.out.println();
		System.out.println("exit: Exits the program");
	}

	public static void exit() {
		System.out.println("Exiting...");
		System.out.println("Goodbye!");
		exit = true;
	}

	public static void print() {
		ArrayList<MigratableProcess> processes = manager.getProcesses();
		for (int x = 0; x < processes.size(); x++) {
			processes.toString();
		}
	}

	public static void start(String[] args) {
		if (args.length < 2) {
			System.out.println("Wrong # of arguments of Start command.");
			System.out.println("Try Again!");
		} else {
			ArrayList<String> arguments = new ArrayList<String>();
			for (int x = 2; x < args.length; x++) {
				arguments.add(args[x]);
			}
			try {
				manager.createAndStartProcess(args[1], arguments);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
