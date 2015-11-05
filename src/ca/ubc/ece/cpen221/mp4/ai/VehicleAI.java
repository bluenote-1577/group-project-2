package ca.ubc.ece.cpen221.mp4.ai;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Vehicle;

public interface VehicleAI {

	/**
	 * Decides the next action to be taken, given the state of the World and the
	 * animal.
	 *
	 * @param world
	 *            the current World
	 * @param vehicle
	 *            the vehicle waiting for the next action
	 * @return the next action for animal
	 */
	Command getNextAction(World world, Vehicle vehicle);
	
	/**
	 * checks the if 2 items are not diagonal to each other, i.e on a straight line 
	 * west east or north south.
	 * @param location1
	 * @param location2
	 * @return true if the 2 items are not diagonal to each other.
	 */
	Boolean notDiagonal(Location location1, Location location2);
}
