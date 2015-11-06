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

public class TruckAI extends AbstractVehicleAI {

	/**
	 * These trucks don't like bikers, nor do they like other truckers. They try
	 * to run into other bikers and truckers. Trucks can track down bikes that
	 * are diagonal to them. They only choose to run into other trucks if there
	 * are no bikes in sight.
	 */

	@Override
	public Command getNextAction(World world, Vehicle vehicle) {
		Set<Item> surroundings = world.searchSurroundings(vehicle.getLocation(), vehicle.getViewRange());
		Direction closestMotorcycle = null;
		Direction closestTruck = null;
		int closestMotorcycleDistance = 0;
		int closestTruckDistance = 0;

		for (Item item : surroundings) {
			// search for motorcycles in our view range
			if (item.getName() == "Motorcycle") {
				//find out which motorcycle is the closest,
				// and which direction it is.
				
				int distance = vehicle.getLocation().getDistance(item.getLocation());
				Direction direction = Util.getDirectionTowards(vehicle.getLocation(), item.getLocation());

				if (distance < closestMotorcycleDistance || closestMotorcycleDistance == 0) {
					closestMotorcycleDistance = distance;
					closestMotorcycle = direction;
				}
			}

			if (item.getName() == "Truck") {
				// discern which motorcycle is the closest,

				int distance = vehicle.getLocation().getDistance(item.getLocation());
				Direction direction = Util.getDirectionTowards(vehicle.getLocation(), item.getLocation());

				if (distance < closestTruckDistance || closestTruckDistance == 0) {
					closestTruckDistance = distance;
					closestTruck = direction;
				}
			}
		}
		
		if (closestTruckDistance == 0 && closestMotorcycleDistance == 0) {
			return tryToMove(vehicle,Util.getRandomDirection());

		}

		if (closestMotorcycle != null) {
			return tryToMove(vehicle,closestMotorcycle);
		}

		else {
			return tryToMove(vehicle,closestTruck);
		}
	}

}
