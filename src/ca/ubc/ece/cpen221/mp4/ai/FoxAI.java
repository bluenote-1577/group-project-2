package ca.ubc.ece.cpen221.mp4.ai;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.Util;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.*;

/**
 * Your Fox AI.
 */
public class FoxAI extends AbstractAI {
	private int closest = 2; // max number; greater than fox's view range

	public FoxAI() {

	}

	@Override
	public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
		Boolean rabbitFound = false;
		List<Item> grasses = new ArrayList<Item>();
		Set<Item> otherFoxes = new HashSet<Item>();
		Set<Item> rabbits = new HashSet<Item>();
		Set<Direction> validDirections = getValidDirections(world, animal);

		Location foxCenter = new Location(0, 0);
		Location rabbitCenter = new Location(0, 0);
		return new WaitCommand();
	}

	private Set<Direction> getValidDirections(ArenaWorld world, ArenaAnimal animal) {

		Set<Direction> toReturn = new HashSet<>();

		toReturn.add(Direction.EAST);
		toReturn.add(Direction.WEST);
		toReturn.add(Direction.NORTH);
		toReturn.add(Direction.SOUTH);

		if (!Util.isValidLocation(world, new Location(animal.getLocation(), Direction.EAST))) {
			toReturn.remove(Direction.EAST);
		}
		if (!Util.isValidLocation(world, new Location(animal.getLocation(), Direction.WEST))) {
			toReturn.remove(Direction.WEST);
		}
		if (!Util.isValidLocation(world, new Location(animal.getLocation(), Direction.NORTH))) {
			toReturn.remove(Direction.NORTH);
		}
		if (!Util.isValidLocation(world, new Location(animal.getLocation(), Direction.SOUTH))) {
			toReturn.remove(Direction.SOUTH);
		}

		for (Item item : world.searchSurroundings(animal)) {
			if (item.getLocation().equals(new Location(animal.getLocation(), Direction.EAST))) {
				toReturn.remove(Direction.EAST);
			} else if (item.getLocation().equals(new Location(animal.getLocation(), Direction.WEST))) {
				toReturn.remove(Direction.WEST);
			} else if (item.getLocation().equals(new Location(animal.getLocation(), Direction.NORTH))) {
				toReturn.remove(Direction.NORTH);
			} else if (item.getLocation().equals(new Location(animal.getLocation(), Direction.SOUTH))) {
				toReturn.remove(Direction.SOUTH);
			}
		}

		return toReturn;
	}

	private ArrayList<Item> rabbitsAvailable(List<Item> rabbits, ArenaAnimal animal) {
		ArrayList<Item> toReturn = new ArrayList<Item>();
		for (Item grass : grasses) {
			if (grass.getLocation().equals(new Location(animal.getLocation(), Direction.EAST))) {
				toReturn.add(grass);
			}
			if (grass.getLocation().equals(new Location(animal.getLocation(), Direction.WEST))) {
				toReturn.add(grass);
			}
			if (grass.getLocation().equals(new Location(animal.getLocation(), Direction.SOUTH))) {
				toReturn.add(grass);
			}
			if (grass.getLocation().equals(new Location(animal.getLocation(), Direction.NORTH))) {
				toReturn.add(grass);
			}
		}
		return toReturn;
	}
}
