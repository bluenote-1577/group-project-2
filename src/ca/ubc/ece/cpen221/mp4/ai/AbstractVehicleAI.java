package ca.ubc.ece.cpen221.mp4.ai;

import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.Location;
import ca.ubc.ece.cpen221.mp4.World;
import ca.ubc.ece.cpen221.mp4.commands.Command;
import ca.ubc.ece.cpen221.mp4.commands.DestroyCommand;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Vehicle;

public class AbstractVehicleAI implements VehicleAI {

	@Override
	public Command getNextAction(World world, Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * checks the if 2 items are not diagonal to each other, i.e on a straight line 
	 * west east or north south.
	 * @param location1
	 * @param location2
	 * @return true if the 2 items are not diagonal to each other.
	 */
	
	public Boolean notDiagonal(Location location1, Location location2) {
		if (location1.getY() - location2.getY() == 0)
			return true;

		if (location1.getX() - location2.getX() == 0) {
			return true;
		}

		return false;
	}
	
	/**
	 * Tries to move the vehicle in a certain direction. If the vehicle
	 * is going too fast, then the vehicle will decelerate
	 * but keep moving in the same direction.
	 * @param vehicle the vehicle being moved.
	 * @param goTo the direction we wish to go in 
	 * @return a destroy command
	 */
	public Command tryToMove(Vehicle vehicle, Direction toGo) {
		
		if (vehicle.setVehicleSpeed(toGo) == true) {
			return new DestroyCommand(vehicle, toGo);
		}

		else {
			return new DestroyCommand(vehicle, vehicle.getVelocityDirection());
		}
	}

}
