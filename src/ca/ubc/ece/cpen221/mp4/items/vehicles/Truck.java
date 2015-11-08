package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.MotorcycleAI;
import ca.ubc.ece.cpen221.mp4.ai.TruckAI;
import ca.ubc.ece.cpen221.mp4.ai.VehicleAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;

public class Truck implements Vehicle{

	private static final ImageIcon truckImage = Util.loadImage("trucks.gif");

	private static final int MEAT_CALORIES = 0;
	private int STRENGTH = 150;

	private Location location;
	private boolean isDead;
	private int currentCooldown = COOLDOWN;
	private static final int VIEW_RANGE = 20;
	private static final int COOLDOWN = 25;
	private final VehicleAI AI;
	private Direction velocityDirection = null;
	private final int ACCELERATION = 4;

	
	public Truck(Location loc){
		location = loc;
		isDead = false;
		AI = new TruckAI();
	}
	@Override
	public void moveTo(Location targetLocation) {
		location = targetLocation;
		
	}

	@Override
	public int getMovingRange() {
		return 1;
	}

	@Override
	public ImageIcon getImage() {
		return truckImage;
	}

	@Override
	public String getName() {
		return "Truck";
	}

	@Override
	public Location getLocation() {
		return location;
	}

	@Override
	public int getStrength() {
		return STRENGTH;
	}

	@Override
	public void loseEnergy(int energy) {
		isDead = true;
		
	}

	@Override
	public boolean isDead() {
		return isDead;
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
		return currentCooldown;
	}

	@Override
	public Command getNextAction(World world) {
		return AI.getNextAction(world, this);
	}

	@Override
	public int getViewRange() {
		return VIEW_RANGE;
	}

	@Override
	public Direction getVelocityDirection() {
		return velocityDirection;
	}

	@Override
	public void setVelocityDirection(Direction direction){
		this.velocityDirection = direction;
		
		if (direction == null){
			this.velocityDirection = null;
		}
		
	}

	@Override
	public Boolean setVehicleSpeed(Direction direction) {
		//true is returned if the vehicle is allowed to turn, i.e. the cooldown is at its max
		if (direction == null){
			//decelerates
			if(currentCooldown < COOLDOWN){
				currentCooldown = currentCooldown + ACCELERATION;
				return false;
			}
			
			else return true;
		}
		
		if (direction != velocityDirection){
			if(currentCooldown < COOLDOWN){
				currentCooldown = currentCooldown + ACCELERATION;
				return false;
			}
			
			else return true;
		}
		
		else{ //accelerate in the same direction
			if(currentCooldown - ACCELERATION < 0){
				return false;
			};
			
			currentCooldown = currentCooldown - ACCELERATION;
			return false;
		}
		
	}


}
