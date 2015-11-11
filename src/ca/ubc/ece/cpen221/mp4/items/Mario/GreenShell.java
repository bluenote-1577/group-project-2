package ca.ubc.ece.cpen221.mp4.items.Mario;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
import ca.ubc.ece.cpen221.mp4.items.animals.AbstractArenaAnimal;

public class GreenShell extends AbstractArenaAnimal {
	
	/*
	 * Green shells are slower than blue shells but they are faster than red shells.
	 * The rabbits also released these in order to kill bears and hyenas. The shell 
	 * disappears when it kills an animal. 
	 */
	
	
	public GreenShell(AI greenShellAI, Location initial_location){
		 
		//initialize all protected methods from AbstractArenaAnimal
		setImage(Util.loadImage("greenShell.gif")); 
		setAi(greenShellAI); 
		setINITIAL_ENERGY(100);
		setEnergy(100); 
		setMAX_ENERGY(150);
		setSTRENGTH(300);
		setVIEW_RANGE(2);
		setMIN_BREEDING_ENERGY(30);
		setCOOLDOWN(3); 
		setLocation(initial_location); 
		
		
	}

	@Override
	public LivingItem breed() {
		//Shells don't breed
		return null; 
	}

	@Override
	public String getName() {
		return "GreenShell";
	}
	

}
