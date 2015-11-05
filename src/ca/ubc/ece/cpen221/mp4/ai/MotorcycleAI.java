package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.DestroyCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Vehicle;

public class MotorcycleAI implements VehicleAI {

	@Override
	/**
	 * We want the motorcycle to kill gnats. I designed the AI so that the
	 * motorcycle would kill the closest gnat that is directly
	 * vertical or horizontal, because if it killed a gnat
	 * really far away then we might accelerate too much and the vehicle might
	 * kill rabbits or foxes. However, the motorcycle will kill animals or
	 * destroy other vehicles if it has to in order to kill gnats.
	 */
	public Command getNextAction(World world, Vehicle vehicle) {

		Set<Item> surroundings = world.searchSurroundings(vehicle.getLocation(),vehicle.getViewRange());
		Direction closestGnat = null;
		int closestGnatDistance = 0;

		for (Item item : surroundings) {
			//search for gnats in our view range
			if (item.getName() == "Gnat") {
				if(notDiagonal(item.getLocation(), vehicle.getLocation())){
					//discern which gnat is the closest,
					//and which direction it is.
					int south = item.getLocation().getY() - vehicle.getLocation().getY();
					int east = item.getLocation().getX() - vehicle.getLocation().getX();
					//get the direction the item is in and its distance
					int distance = Math.max(Math.abs(south), Math.abs(east));
					Direction gnatDirection = null;
					if (south > 0){
						gnatDirection = Direction.SOUTH;
					}
					else if (south < 0){
						gnatDirection = Direction.NORTH;
					}
					else if (east > 0){
						gnatDirection = Direction.EAST;
					}
					else if (east < 0){
						gnatDirection = Direction.WEST;
					}
					
					if (distance < closestGnatDistance || closestGnatDistance == 0){
						closestGnatDistance = distance;
						closestGnat = gnatDirection;
					}
				}
			}
		}
		//do nothing if no gnats are scanned
		if (closestGnat == null){
			if(vehicle.setVehicleSpeed(null) == true){
				//vehicle.setVelocityDirection(null);
				return new WaitCommand();
			}
			
			else{
				return new DestroyCommand(vehicle,vehicle.getVelocityDirection());
				
			}

			
		}
		//seek to destroy the closest gnat
		else{
			if(vehicle.setVehicleSpeed(closestGnat) == true){
			//	vehicle.setVelocityDirection(closestGnat);
				return new DestroyCommand(vehicle,closestGnat);
			}
			
			else{
				return new DestroyCommand(vehicle,vehicle.getVelocityDirection());
			}
		}
	
	}

	@Override
	public Boolean notDiagonal(Location location1, Location location2){
		if(location1.getY() - location2.getY() == 0)
			return true;
		
		if(location1.getX()- location2.getX() == 0){
			return true;
		}
		
		return false;
	}

}
