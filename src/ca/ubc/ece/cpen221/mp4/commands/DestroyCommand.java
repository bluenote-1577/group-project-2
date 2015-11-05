package ca.ubc.ece.cpen221.mp4.commands;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Vehicle;

/**
 * 
 * @author Jim
 * Command for vehicles to move one block. It also destroys 
 * everything in that block with a lower strength.
 */
public class DestroyCommand implements Command {
	

	private final Vehicle vehicle;
	private final Location targetLocation;
	
	/**
	 * This command moves the vehicle one block and destroys anything in it's
	 * way when executed if this vehicle has a higher strength.
	 * @param vehicle the vehicle the command is being called on
	 * @param direction the direction we want to go by one block
	 */
	
	public DestroyCommand(Vehicle vehicle, Direction direction){

		if(direction == null)
			targetLocation = null;
		else{
			Location target = new Location(vehicle.getLocation(),direction);
			targetLocation = target;
		}
		this.vehicle = vehicle;
		this.vehicle.setVelocityDirection(direction);
		
	}


	@Override
	public void execute(World world) throws InvalidCommandException {
		//kill the item
		if (targetLocation == null)
			return;
		if(vehicle.isDead())
			return;
		if (!Util.isValidLocation(world, targetLocation)) {
			return;
			//throw new InvalidCommandException("Invalid MoveCommand: Invalid/non-empty target location");
		}
		if (vehicle.getMovingRange() < targetLocation.getDistance(targetLocation)) {
			throw new InvalidCommandException("Invalid MoveCommand: Target location farther than moving range");
		}

		
		for (Item item : world.getItems()) {
			if (item.getLocation().equals(targetLocation)) {
				if (vehicle.getStrength() == item.getStrength()){
					item.loseEnergy(Integer.MAX_VALUE);
					vehicle.loseEnergy(Integer.MAX_VALUE);
				}
				if (vehicle.getStrength() > item.getStrength()){
					item.loseEnergy(Integer.MAX_VALUE);
				}
				
				else{
					vehicle.loseEnergy(Integer.MAX_VALUE);
				}
			}
		}
		vehicle.moveTo(targetLocation);
		
		
	}


}
