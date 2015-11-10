package ca.ubc.ece.cpen221.mp4.items.animals;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Tiger extends AbstractArenaAnimal {

		//tiger constructor 
		public Tiger(AI bearAI, Location initial_location){
			
			setImage(Util.loadImage("tiger.gif")); 
			setAi(bearAI); 
			
			//initialize all the protected methods from AbstractArenaAnimal 
			setINITIAL_ENERGY(120);
			setEnergy(120); 
			setMAX_ENERGY(170);
			setSTRENGTH(160);
			setVIEW_RANGE(3);
			setMIN_BREEDING_ENERGY(50);
			setCOOLDOWN(4); 
			setLocation(initial_location); 			
		}

		@Override
		public LivingItem breed() {
			Tiger child = new Tiger(getAi(), getLocation()); 
			child.setEnergy(getEnergy() / 2);
			this.setEnergy(getEnergy() / 2);
			return child;
		}

		@Override
		public String getName() {
			return "Tiger";
		}
		
	}
