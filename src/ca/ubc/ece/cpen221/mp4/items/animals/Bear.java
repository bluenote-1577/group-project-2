package ca.ubc.ece.cpen221.mp4.items.animals;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Bear extends AbstractArenaAnimal {

	@Override
	public LivingItem breed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Bear";
	}

	
	AbstractArenaAnimal.setINITIAL_ENERGY(100);
	

	protected void setEnergy(int i) {
		this.energy = i;
	}

	protected void setMAX_ENERGY(int i) {
		this.MAX_ENERGY = i;
	}

	protected void setSTRENGTH(int i) {
		this.STRENGTH = i;
	}

	protected void setVIEW_RANGE(int i) {
		this.VIEW_RANGE = i;
	}

	protected void setMIN_BREEDING_ENERGY(int i) {
		this.MIN_BREEDING_ENERGY = i;
	}

	protected void setCOOLDOWN(int i) {
		this.COOLDOWN = i;
	}

	protected void setLocation(Location l) {
		this.location = l;
	}
	

}
