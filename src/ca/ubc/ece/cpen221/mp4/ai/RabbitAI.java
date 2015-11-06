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
import ca.ubc.ece.cpen221.mp4.commands.BreedCommand;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.EatCommand;
import ca.ubc.ece.cpen221.mp4.commands.MoveCommand;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;
import ca.ubc.ece.cpen221.mp4.items.animals.Fox;
import ca.ubc.ece.cpen221.mp4.items.animals.Rabbit;

/**
 * Your Rabbit AI.
 */
public class RabbitAI extends AbstractAI {

	private int closest = 10; // max number; greater than rabbit's view range
	private int temp;
	private boolean foxFound;

	public RabbitAI() {
	}

	/**
	 * General AI:
	 * 
	 * I find an optimal location for breeding and moving. We want to move and
	 * breed as far away as possible from incomming foxes. We also want to breed
	 * and move away from rabbits. By having a low density of rabbits, we can
	 * eat more grass from different places, have more space to breed, and run
	 * away from foxes better.
	 */
	@Override
	public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
		foxFound = false;
		List<Item> grasses = new ArrayList<Item>();
		Set<Item> otherRabbits = new HashSet<Item>();
		Set<Item> foxes = new HashSet<Item>();
		Set<Direction> validDirections = getValidDirections(world, animal);

		Location foxCenter = new Location(0, 0);
		Location rabbitCenter = new Location(0, 0);

		for (Item item : world.searchSurroundings(animal)) {
			if (item.getName().equals("grass")) {
				grasses.add(item);
			}

			else {
				if (item.getName().equals("Fox")) {
					foxes.add(item);
					foxFound = true;
					foxCenter = new Location(foxCenter.getX() + item.getLocation().getX(),
							foxCenter.getY() + item.getLocation().getY());
				}

				else {
					if (item.getName().equals("Rabbit")) {
						otherRabbits.add(item);
						rabbitCenter = new Location(rabbitCenter.getX() + item.getLocation().getX(),
								rabbitCenter.getY() + item.getLocation().getY());
					}
				}
			}
		}

		/**
		 * first, we find the ideal directions for breeding, moving. That is all
		 * of this first block of code. If there are no valid directions, we
		 * wait.
		 */
		ArrayList<Item> validGrass = grassAvailable(grasses, animal);
		rabbitCenter = new Location(rabbitCenter.getX() / otherRabbits.size(),
				rabbitCenter.getY() / otherRabbits.size());
		Direction breedingDirection;
		Direction moveDirection;

		if (foxFound) {
			foxCenter = new Location(foxCenter.getX() / foxes.size(), foxCenter.getY() / foxes.size());
			// Direction foxDirectionWE = Util
			// .getDirectionTowards(new Location(animal.getLocation().getX(),
			// foxCenter.getX()), foxCenter);
			// Direction foxDirectionNS = Util
			// .getDirectionTowards(new Location(animal.getLocation().getY(),
			// foxCenter.getY()), foxCenter);
			breedingDirection = oppositeDir(Util.getDirectionTowards(animal.getLocation(), foxCenter));
			Location totalCenter = new Location((foxCenter.getX()  + rabbitCenter.getX()) / 2,
					(foxCenter.getY() + rabbitCenter.getY()) / 2);
			moveDirection = oppositeDir(Util.getDirectionTowards(animal.getLocation(), totalCenter));

			// eat grass as last resort, otherwise wait.
			// ascertain the best direction to breed/move;
			if (validDirections.isEmpty()) {
				return new WaitCommand();
			}
			if (!validDirections.contains(breedingDirection) ||
					!validDirections.contains(moveDirection)) {
				while (!validDirections.contains(breedingDirection)) {
					breedingDirection = Util.getRandomDirection();
				}

				if (!validDirections.contains(moveDirection)) {
					moveDirection = breedingDirection;
				}
			}
		} else {
			if (otherRabbits.isEmpty() || rabbitCenter.equals(animal.getLocation())) {
				breedingDirection = Util.getRandomDirection();
			} else
				breedingDirection = oppositeDir(Util.getDirectionTowards(animal.getLocation(), rabbitCenter));

			if (validDirections.isEmpty()) {
				if (validGrass.isEmpty()) {
					return new WaitCommand();
				}

				else
					return new EatCommand(animal, validGrass.get(0));
			}
			if (!validDirections.contains(breedingDirection)) {
				while (!validDirections.contains(breedingDirection)) {
					breedingDirection = Util.getRandomDirection();
				}
			}
			moveDirection = breedingDirection;
		}

		/**
		 * now we move on to actions. When no foxes are seen, breed whenever
		 * possible, eat when you can't breed, and move when you can't do
		 * either.
		 * 
		 * If foxes are seen, moving takes priority over eating.
		 */

		if (animal.getEnergy() >= animal.getMinimumBreedingEnergy() && otherRabbits.size() < 2 &&
				grasses.size() > 1) {
			return new BreedCommand(animal, new Location(animal.getLocation(), breedingDirection));
		}

		if (animal.getEnergy() <= animal.getMinimumBreedingEnergy() * 2) {

			if (validGrass.isEmpty()) {
				if (!grasses.isEmpty()) {
					Direction grassDirection = Util.getDirectionTowards(animal.getLocation(),
							grasses.get(0).getLocation());
					if (!validDirections.contains(grassDirection))
						grassDirection = moveDirection;
					return new MoveCommand(animal, new Location(animal.getLocation(), grassDirection));
				}

				else
					return new MoveCommand(animal, new Location(animal.getLocation(), moveDirection));
			} else {
				return new EatCommand(animal, validGrass.get(0));
			}
		}

		else {
			if (validGrass.isEmpty()) {
				return new MoveCommand(animal, new Location(animal.getLocation(), moveDirection));

			} else {
				return new EatCommand(animal, validGrass.get(0));
			}
		}

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

	private ArrayList<Item> grassAvailable(List<Item> grasses, ArenaAnimal animal) {
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
