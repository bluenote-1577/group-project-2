package ca.ubc.ece.cpen221.mp4.items.animals;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.ai.AI;
import ca.ubc.ece.cpen221.mp4.items.LivingItem;

public class Hyena extends AbstractArenaAnimal {

	// hyena constructor
	public Hyena(AI hyenaAI, Location initial_location) {

		// initialize all protected methods from AbstractArenaAnimal
		setImage(Util.loadImage("hyena.gif"));
		setAi(hyenaAI);
		setINITIAL_ENERGY(80);
		setEnergy(80);
		setMAX_ENERGY(110);
		setSTRENGTH(120);
		setVIEW_RANGE(2);
		setMIN_BREEDING_ENERGY(30);
		setCOOLDOWN(3);
		setLocation(initial_location);

	}

	@Override
	public LivingItem breed() {
		Hyena child = new Hyena(getAi(), getLocation());
		child.setEnergy(getEnergy() / 2);
		this.setEnergy(getEnergy() / 2);
		return child;
	}

	@Override
	public String getName() {
		return "Hyena";
	}

}
