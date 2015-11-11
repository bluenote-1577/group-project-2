package ca.ubc.ece.cpen221.mp4.items.Mario;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.ai.ShellAI;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
import ca.ubc.ece.cpen221.mp4.items.animals.AbstractArenaAnimal;
import ca.ubc.ece.cpen221.mp4.items.animals.Bear;

public class RedShell extends AbstractArenaAnimal {
	
	/*
	 * Red shells are the slowest of all the shells. They also targets the bears and hyenas
	 * in oder to protect the rabbits. The shell disappears when it kills an animal. 
	 */

	public RedShell(AI redShellAI, Location initialLocation){
		 
		//initialize all protected methods from AbstractArenaAnimal
		setImage(Util.loadImage("RedShell.gif")); 
		setAi(redShellAI);
		setINITIAL_ENERGY(100);
		setEnergy(100); 
		setMAX_ENERGY(150);
		setSTRENGTH(300);
		setVIEW_RANGE(2);
		setMIN_BREEDING_ENERGY(30);
		setCOOLDOWN(4);     //red shells are the slowest shells
		setLocation(initialLocation); 
		
		
	}

	@Override
	public LivingItem breed() {
		//Shells dont breed
		return null; 
	}

	@Override
	public String getName() {
		return "RedShell";
	}

}
