package ca.ubc.ece.cpen221.mp4.items.Mario;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;
import ca.ubc.ece.cpen221.mp4.items.animals.AbstractArenaAnimal;
import ca.ubc.ece.cpen221.mp4.items.animals.Bear;

public class redShell extends AbstractArenaAnimal {

	public redShell(AI red_shellAI, Location initial_location){
		 
		setImage(Util.loadImage("RedShell.gif")); 
		setAi(red_shellAI); 
		
		//initialize all the protected methods from AbstractArenaAnimal 
		setINITIAL_ENERGY(100);
		setEnergy(100); 
		setMAX_ENERGY(150);
		setSTRENGTH(300);
		setVIEW_RANGE(2);
		setMIN_BREEDING_ENERGY(30);
		setCOOLDOWN(4); 
		setLocation(initial_location); 
		
		
	}

	@Override
	public LivingItem breed() {
		//redShells dont breed
		return null; 
	}

	@Override
	public String getName() {
		return "RedShell";
	}

}
