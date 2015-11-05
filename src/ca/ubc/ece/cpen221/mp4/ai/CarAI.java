package ca.ubc.ece.cpen221.mp4.ai;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Vehicle;

public class CarAI implements VehicleAI{
	/**
	 * The cars don't want to kill anything, so it just
	 * tries to move into empty spaces.
	 */

	@Override
	public Command getNextAction(World world, Vehicle vehicle) {
		return new WaitCommand();
	}
	
	public Boolean notDiagonal(Location location1, Location location2){
		if(location1.getY() - location2.getY() == 0)
			return true;
		
		if(location1.getX()- location2.getX() == 0){
			return true;
		}
		
		return false;
	}
	

}
