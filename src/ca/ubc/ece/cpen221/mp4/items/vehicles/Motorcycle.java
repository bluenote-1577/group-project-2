package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;

public class Motorcycle implements Vehicle{
	
	private static final ImageIcon motorcycleImage = Util.loadImage("motorcycles.gif");

	private static final int MEAT_CALORIES = 100;
	private static final int STRENGTH = 10;

	private Location location;
	private boolean isDead;

	@Override
	public void moveTo(Location targetLocation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMovingRange() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ImageIcon getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getStrength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void loseEnergy(int energy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPlantCalories() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMeatCalories() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCoolDownPeriod() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Command getNextAction(World world) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getViewRange() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

}
