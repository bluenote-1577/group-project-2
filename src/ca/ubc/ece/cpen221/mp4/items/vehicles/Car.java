package ca.ubc.ece.cpen221.mp4.items.vehicles;

import javax.swing.ImageIcon;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.ai.CarAI;
import ca.ubc.ece.cpen221.mp4.ai.VehicleAI;
import ca.ubc.ece.cpen221.mp4.commands.Command;

public class Car implements Vehicle {
	private static final ImageIcon carImage = Util.loadImage("cars.gif");

	private static final int MEAT_CALORIES = 0;
	private static final int STRENGTH = 130;

	private Location location;
	private boolean isDead;
	private int currentCooldown = COOLDOWN;
	private static final int VIEW_RANGE = 4;
	private static final int COOLDOWN = 20;
	private final VehicleAI AI;
	private Direction velocityDirection = null;
	private final int ACCELERATION = 3;

	public Car(Location loc) {
		location = loc;
		isDead = false;
		AI = new CarAI();
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
		return carImage;
	}

	@Override
	public String getName() {
		return "car";
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
		return 0;
	}

	@Override
	public int getMeatCalories() {
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
	public void setVelocityDirection(Direction direction) {
		velocityDirection = direction;

		if (direction == null) {
			this.velocityDirection = null;
		}
	}

	@Override
	public Boolean setVehicleSpeed(Direction direction) {
		// true is returned if the vehicle is allowed to turn, i.e. the cooldown
		// is at its max
		if (direction == null) {
			// decelerates
			if (currentCooldown < COOLDOWN) {
				currentCooldown = currentCooldown + ACCELERATION;
				return false;
			}

			else
				return true;
		}

		if (direction != velocityDirection) {
			if (currentCooldown < COOLDOWN) {
				currentCooldown = currentCooldown + ACCELERATION;
				return false;
			}

			else
				return true;
		}

		else { // accelerate in the same direction
			if (currentCooldown - ACCELERATION < 0) {
				return false;
			}
			;

			currentCooldown = currentCooldown - ACCELERATION;
			return false;
		}

	}

}
