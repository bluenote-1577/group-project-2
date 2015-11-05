package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.DestroyCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Vehicle;

public class MotorcycleAI extends AbstractVehicleAI {

	@Override
	/**
	 * We want the motorcycle to kill gnats. I designed the AI so that the
	 * motorcycle would kill the closest gnat that is directly vertical or
	 * horizontal, because if it killed a gnat really far away then we might
	 * accelerate too much and the vehicle might kill rabbits or foxes. However,
	 * the motorcycle will kill animals or destroy other vehicles if it has to
	 * in order to kill gnats.
	 * 
	 * Also, the motorcycles run away from trucks. Trucks want to kill
	 * motorcycles.
	 */
	public Command getNextAction(World world, Vehicle vehicle) {

		Set<Item> surroundings = world.searchSurroundings(vehicle.getLocation(), vehicle.getViewRange());
		Direction closestGnat = null;
		int closestGnatDistance = 0;

		for (Item item : surroundings) {
			// search for gnats in our view range
			if (item.getName() == "Gnat") {
				if (notDiagonal(item.getLocation(), vehicle.getLocation())) {
					// discern which gnat is the closest,
					// and which direction it is.
					int south = item.getLocation().getY() - vehicle.getLocation().getY();
					int east = item.getLocation().getX() - vehicle.getLocation().getX();
					// get the direction the item is in and its distance
					int distance = Math.max(Math.abs(south), Math.abs(east));
					Direction gnatDirection = null;
					gnatDirection = Util.getDirectionTowards(vehicle.getLocation(),item.getLocation());

					if (distance < closestGnatDistance || closestGnatDistance == 0) {
						closestGnatDistance = distance;
						closestGnat = gnatDirection;
					}
				}
			}
		}
		// move in a random direction if no gnats are scanned
		if (closestGnat == null) {
			return tryToMove(vehicle,Util.getRandomDirection());

		}
		// seek to destroy the closest gnat
		else {
			return tryToMove(vehicle,closestGnat);
		}

	}

}
