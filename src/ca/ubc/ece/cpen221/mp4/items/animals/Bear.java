package ca.ubc.ece.cpen221.mp4.items.animals;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Bear extends AbstractArenaAnimal {
	
	private static final ImageIcon bearImage = Util.loadImage("bear.gif");
	
	//bear constructor 
	public Bear(AI bearAI, Location initial_location){
	 	
		this.setImage(bearImage); 
		
		
		
		//initialize all the protected methods from AbstractArenaAnimal 
		setINITIAL_ENERGY(100);
		//setEnergy(90);
		setMAX_ENERGY(150);
		setSTRENGTH(200);
		setVIEW_RANGE(1);
		setMIN_BREEDING_ENERGY(30);
		setCOOLDOWN(4); 
		setLocation(initial_location); 
		
		
	}

	@Override
	public LivingItem breed() {
		// TODO Auto-generated method stub
		Bear child = new Bear(getAi(), getLocation()); 
		child.setEnergy(getEnergy() / 2);
		this.setEnergy(getEnergy() / 2);
		return child;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Bear";
	}
}
