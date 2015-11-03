package ca.ubc.ece.cpen221.mp4.ai;

import java.util.Set;

import ca.ubc.ece.cpen221.mp4.ArenaWorld;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.items.Item;
import ca.ubc.ece.cpen221.mp4.items.animals.ArenaAnimal;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Vehicle;

public class MotorcycleAI implements VehicleAI {

	@Override
	public Command getNextAction(ArenaWorld world, Vehicle vehicle) {
		
		Set<Item> surroundings = world.searchSurroundings((ArenaAnimal)vehicle);
		
		
		
		return null;
	}

	
}
