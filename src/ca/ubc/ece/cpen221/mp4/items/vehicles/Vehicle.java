package ca.ubc.ece.cpen221.mp4.items.vehicles;

import ca.ubc.ece.cpen221.mp4.Actor;
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
	 * Returns the speed of this vehicle. The speed of the vehicle
	 * has to be below a certain point for the vehicle to turn.
	 * 
	 * @return the speed of the vehicle
	 */
	int getSpeed();
}
