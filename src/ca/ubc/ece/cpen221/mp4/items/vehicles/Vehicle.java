package ca.ubc.ece.cpen221.mp4.items.vehicles;

import ca.ubc.ece.cpen221.mp4.Actor;
import ca.ubc.ece.cpen221.mp4.Direction;
import ca.ubc.ece.cpen221.mp4.items.MoveableItem;

public interface Vehicle extends MoveableItem, Actor {

	/**
	 * Returns the range of the animal's vision. The range is measured in
	 * Manhattan Distance, for example, if an animal has view range of 2, then
	 * it can see all valid locations in the rectangle
	 * {(x-2,y-2),(x+2,y-2),(x-2,y+2),(x+2,y+2)}, where (x,y) are the
	 * coordinates of its current location.
	 *
	 * @return the view range of this vehicle
	 */
	int getViewRange();
	
	/**
	 * Returns the direction of velocity of this vehicle. 
	 * i.e. the way the vehicle has been moving.
	 * 
	 * @return the direction which this vehicle was most
	 * recently moving.
	 */
	Direction getVelocityDirection();
	
	/**
	 * sets the direction of the velocity of the vehicle.
	 * i.e which way it was last moving. Used in AI.
	 * @param direction
	 */
	void setVelocityDirection (Direction direction);
	
	/**
	 * changes the vehicle speed. Either reduces the cooldown
	 * of the vehicle when it is accelerating in one direction,
	 *  or makes the cooldown larger if the vehicle is decelerating and
	 *  trying to turn or to stop.
	 * @param direction
	 * @return true if the vehicle is at max cooldown, the minimum speed for turning.
	 * 
	 */
	
	Boolean setVehicleSpeed(Direction direction);
}
