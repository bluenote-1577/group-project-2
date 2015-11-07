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
	
	/**
	 * My Fox AI:
	 * The fox first scans everything in its view.
	 * The fox then breeds if it has maximum energy. If not, it goes
	 * hunting for rabbits near by. Otherwise, it moves in random directions
	 * until it sees a rabbit to chase.
	 */

	@Override
	public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
		Boolean rabbitFound = false;
		List<Item> nearbyRabbits = new ArrayList<Item>();
		Set<Item> otherFoxes = new HashSet<Item>();
		Set<Item> rabbits = new HashSet<Item>();
		Set<Direction> validDirections = getValidDirections(world, animal);
		int closestRabbit = Integer.MAX_VALUE;
		
		Location rabbitCenter = new Location(0,0);
		Location foxCenter = new Location(0, 0);
		Direction moveDirection;
		Direction breedDirection;

		for (Item item : world.searchSurroundings(animal)) {

			if (item.getName().equals("Fox")) {
				otherFoxes.add(item);
				foxCenter = new Location(foxCenter.getX() + item.getLocation().getX(),
						foxCenter.getY() + item.getLocation().getY());
			}

			else {
				if (item.getName().equals("Rabbit")) {
					rabbits.add(item);
					rabbitFound = true;
					rabbitCenter = new Location(rabbitCenter.getX() + item.getLocation().getX(),
							rabbitCenter.getY() + item.getLocation().getY());

				}
			}

		}
		foxCenter = new Location(foxCenter.getX()/otherFoxes.size(), 
				foxCenter.getY()/otherFoxes.size());
		
		if (validDirections.isEmpty()) return new WaitCommand();
		if ((animal.getEnergy() > animal.getMinimumBreedingEnergy() && 
				otherFoxes.size() > animal.getViewRange()) ||
				animal.getEnergy() == animal.getMaxEnergy()){
			breedDirection = oppositeDir(Util.getDirectionTowards(animal.getLocation(),
					foxCenter));
			while (!validDirections.contains(breedDirection)){
				breedDirection = Util.getRandomDirection();
			}
			return new BreedCommand(animal,new Location(animal.getLocation(),breedDirection));
		}
		//this branch executes if a rabbit is found
		if(rabbitFound){

			nearbyRabbits = rabbitsAvailable(rabbits, animal);
			
			if(!nearbyRabbits.isEmpty()) return new EatCommand(animal, nearbyRabbits.get(0));
			
			for(Item rabbit : rabbits){
				int rabbitDistance = animal.getLocation().getDistance(rabbit.getLocation());
				if(rabbitDistance <= closestRabbit){
					rabbitCenter = rabbit.getLocation();
					closestRabbit = rabbitDistance;
				}
			}
			
			moveDirection = Util.getDirectionTowards(animal.getLocation(),
					rabbitCenter);
			while (!validDirections.contains(moveDirection)){
				moveDirection = Util.getRandomDirection();
				
			}
			
			return new MoveCommand(animal, new Location(animal.getLocation(),moveDirection));
			
		}
		
		//this branch executes if a rabbit is not found
		else{
			Direction randomDirection = Util.getRandomDirection();
			moveDirection = oppositeDir(Util.getDirectionTowards(animal.getLocation(),
					foxCenter));
			while (!validDirections.contains(moveDirection)){
				moveDirection = Util.getRandomDirection();
			}
			
			while(!validDirections.contains(randomDirection)){
				randomDirection = Util.getRandomDirection();
			}
			if (otherFoxes.size() > 1) return new MoveCommand(animal,
					new Location (animal.getLocation(), moveDirection));
			
			return new MoveCommand (animal,
					new Location(animal.getLocation(),randomDirection));
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

	private ArrayList<Item> rabbitsAvailable(Set<Item> rabbits, ArenaAnimal animal) {
		ArrayList<Item> toReturn = new ArrayList<Item>();
		for (Item rabbit : rabbits) {
			if (rabbit.getLocation().equals(new Location(animal.getLocation(), Direction.EAST))) {
				toReturn.add(rabbit);
			}
			if (rabbit.getLocation().equals(new Location(animal.getLocation(), Direction.WEST))) {
				toReturn.add(rabbit);
			}
			if (rabbit.getLocation().equals(new Location(animal.getLocation(), Direction.SOUTH))) {
				toReturn.add(rabbit);
			}
			if (rabbit.getLocation().equals(new Location(animal.getLocation(), Direction.NORTH))) {
				toReturn.add(rabbit);
			}
		}
		return toReturn;
	}
}
