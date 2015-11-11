package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Iterator;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.AbstractArenaAnimal;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;

public class ShellAI implements AI {
	
	/*
	 * Shells behave the same way as other arena animals(bears, tigers and hyenas except 
	 * they target bears and hyenas instead of rabbits and gnats. 
	 */
	private int energy;

	public ShellAI(int energy) {
		this.energy = energy;
	}

	public boolean isLocationEmpty(ArenaWorld world, ArenaAnimal animal, Location location) {
		if (!Util.isValidLocation(world, location)) {
			return false;
		}
		Set<Item> possibleMoves = world.searchSurroundings(animal);
		Iterator<Item> it = possibleMoves.iterator();
		while (it.hasNext()) {
			Item item = it.next();
			if (item.getLocation().equals(location)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
		Direction dir = Util.getRandomDirection();
		Location targetLocation = new Location(animal.getLocation(), dir);
		Set<Item> possibleEats = world.searchSurroundings(animal);
		Location current = animal.getLocation();
		Iterator<Item> it = possibleEats.iterator();
		while (it.hasNext()) {
			Item item = it.next();
			if ((item.getName().equals("Hyena") || item.getName().equals("Bear"))
					&& (current.getDistance(item.getLocation()) == 1)) {
				animal.loseEnergy(Integer.MIN_VALUE); 
				return new EatCommand(animal, item); // shells kill hyenas
														// and bears
			}
		}
		if (Util.isValidLocation(world, targetLocation) && this.isLocationEmpty(world, animal, targetLocation)) {
			return new MoveCommand(animal, targetLocation);
		}
		return new WaitCommand();
	}
}

	