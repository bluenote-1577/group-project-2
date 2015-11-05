package ca.ubc.ece.cpen221.mp4.ai;

import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.WaitCommand;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Vehicle;

public class TruckAI implements VehicleAI {

	/**
	 * These trucks don't like bikers, nor do they like other truckers
	 */
	public Command getNextAction(World world, Vehicle vehicle) {
		// TODO Auto-generated method stub
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
