package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.MotorcycleAI;
import ca.ubc.ece.cpen221.mp4.ai.VehicleAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.DestroyCommand;

public class Motorcycle implements Vehicle{
	
	private static final ImageIcon motorcycleImage = Util.loadImage("motorcycles.gif");

	private static final int MEAT_CALORIES = 0;
	private static final int STRENGTH = 101;

	private Location location;
	private boolean isDead;
	private int currentCooldown = COOLDOWN;
	private static final int VIEW_RANGE = 5;
	private static final int COOLDOWN = 15;
	private final VehicleAI AI;
	private Direction velocityDirection = null;
	private final int ACCELERATION = 5;

	
	public Motorcycle(Location loc){
		location = loc;
		isDead = false;
		AI = new MotorcycleAI();
	}
	
	@Override
	public void moveTo(Location targetLocation) {
		this.location = targetLocation;
		
	}
	@Override
	public int getMovingRange() {
		// TODO Auto-generated method stub
		return 1;
	}
	@Override
	public ImageIcon getImage() {
		// TODO Auto-generated method stub
		return motorcycleImage;
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Motorcycle";
	}
	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return this.location;
	}
	@Override
	public int getStrength() {
		// TODO Auto-generated method stub
		return 80;
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
		return 0;
	}
	@Override
	public int getMeatCalories() {
		return 0;
	}
	@Override
	public int getCoolDownPeriod() {
		// TODO Auto-generated method stub
		return currentCooldown;
	}
	@Override
	public Command getNextAction(World world) {
		
		return AI.getNextAction(world, this);
		
	}
	@Override
	public int getViewRange() {
		// TODO Auto-generated method stub
		return VIEW_RANGE;
	}
	@Override
	public Direction getVelocityDirection() {
		// TODO Auto-generated method stub
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
			currentCooldown = currentCooldown - ACCELERATION;
			return false;
		}
		
	}

	
}
