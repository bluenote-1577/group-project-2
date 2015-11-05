package ca.ubc.ece.cpen221.mp4.ai;

import java.util.HashSet;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.DestroyCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Vehicle;

public class CarAI extends AbstractVehicleAI {
	/**
	 * The cars don't want to kill anything, so it just tries to move into empty
	 * spaces. It wants to move in the direction of the item that is the
	 * furthest away.
	 */

	@Override
	public Command getNextAction(World world, Vehicle vehicle) {
		Set<Item> surroundings = world.searchSurroundings(vehicle.getLocation(), vehicle.getViewRange());
		Set<Direction> directionOfActors = new HashSet<Direction>();
		directionOfActors.add(Direction.NORTH);
		directionOfActors.add(Direction.SOUTH);
		directionOfActors.add(Direction.WEST);
		directionOfActors.add(Direction.EAST);

		Direction furthestActor = null;
		int furthestActorDistance = 0;

		for (Item item : surroundings) {
			// we can run over grass, so ignore them
			if (item.getName().equals("grass"))
				;
			else {
				if (notDiagonal(item.getLocation(), vehicle.getLocation())) {
					// find out which actor is furthest and move towards it.
					int south = item.getLocation().getY() - vehicle.getLocation().getY();
					int east = item.getLocation().getX() - vehicle.getLocation().getX();
					// get the direction the item is in and its distance
					int distance = Math.max(Math.abs(south), Math.abs(east));
					Direction furthestActorTemp = null;
					
					furthestActorTemp = Util.getDirectionTowards(vehicle.getLocation(), item.getLocation());

					// if the item is further away than another item, then we
					// want
					// to go to
					// the further item
					if (distance > furthestActorDistance || furthestActorDistance == 0) {
						furthestActorDistance = distance;
						furthestActor = furthestActorTemp;
					}
				}
			}
		}
		// go to a random place is nothing are scanned.
		if (furthestActor == null) {
			return tryToMove(vehicle, Util.getRandomDirection());

		}
		// seek to move towards the furthest actor
		else {
			// if there are actors in all directions, we move towards the
			// furthest one
			if (directionOfActors.isEmpty()) {
				return tryToMove(vehicle, furthestActor);
			}
			// there might be directions without actors, we want to go
			// to a random direction without an actor.
			else {
				Direction randomDirection = Util.getRandomDirection();
				while (!directionOfActors.contains(randomDirection)) {
					randomDirection = Util.getRandomDirection();
				}
				return tryToMove(vehicle, randomDirection);
			}
		}
	}

}
