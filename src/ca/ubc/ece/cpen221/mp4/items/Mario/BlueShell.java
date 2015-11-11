package ca.ubc.ece.cpen221.mp4.items.Mario;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
import ca.ubc.ece.cpen221.mp4.items.animals.AbstractArenaAnimal;
import ca.ubc.ece.cpen221.mp4.items.animals.Bear;

public class BlueShell extends AbstractArenaAnimal {
	
	/*
	 * Blue shells are the fastest of all the shells. The rabbits released
	 * these shells to kill some of their predators. Specifically bears and hyenas. 
	 * When a shell kills an animal, it disappears.  
	 * 
	 */

	public BlueShell(AI blueShellAI, Location initialLocation){
		 
		//initialize all protected methods from AbstractArenaAnimal
		setImage(Util.loadImage("blueShell.gif")); 
		setAi(blueShellAI); 
		setINITIAL_ENERGY(300);
		setEnergy(300); 
		setMAX_ENERGY(300);
		setSTRENGTH(300);
		setVIEW_RANGE(10);
		setMIN_BREEDING_ENERGY(30);
		setCOOLDOWN(1);      // the blue shells are the fastest of the shells
		setLocation(initialLocation); 
		
		
	}

	@Override
	public LivingItem breed() {
		//Shells dont breed
		return null; 
	}

	@Override
	public String getName() {
		return "BlueShell";
	}

}
