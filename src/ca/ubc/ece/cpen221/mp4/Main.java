package ca.ubc.ece.cpen221.mp4;

import javax.swing.SwingUtilities;

import ca.ubc.ece.cpen221.mp4.ai.*;
import ca.ubc.ece.cpen221.mp4.items.Gardener;
import ca.ubc.ece.cpen221.mp4.items.Grass;
import ca.ubc.ece.cpen221.mp4.items.Mario.BlueShell;
import ca.ubc.ece.cpen221.mp4.items.Mario.GreenShell;
import ca.ubc.ece.cpen221.mp4.items.Mario.RedShell;
import ca.ubc.ece.cpen221.mp4.items.animals.*;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Car;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Motorcycle;
import ca.ubc.ece.cpen221.mp4.items.vehicles.Truck;
import ca.ubc.ece.cpen221.mp4.staff.WorldImpl;
import ca.ubc.ece.cpen221.mp4.staff.WorldUI;

/**
 * The Main class initialize a world with some {@link Grass}, {@link Rabbit}s,
 * {@link Fox}es, {@link Gnat}s, {@link Gardener}, etc.
 *
 * You may modify or add Items/Actors to the World.
 *
 */
public class Main {

	static final int X_DIM = 40;
	static final int Y_DIM = 40;
	static final int SPACES_PER_GRASS = 7;
	static final int INITIAL_GRASS = X_DIM * Y_DIM / SPACES_PER_GRASS;
	static final int INITIAL_GNATS = INITIAL_GRASS / 4;
	static final int INITIAL_RABBITS = INITIAL_GRASS / 4;
	static final int INITIAL_FOXES = INITIAL_GRASS / 32;
	static final int INITIAL_TIGERS = INITIAL_GRASS / 32;
	static final int INITIAL_BEARS = INITIAL_GRASS / 40;
	static final int INITIAL_HYENAS = INITIAL_GRASS / 32;
	static final int INITIAL_CARS = INITIAL_GRASS / 100;
	static final int INITIAL_TRUCKS = INITIAL_GRASS / 150;
	static final int INITIAL_MOTORCYCLES = INITIAL_GRASS / 25;
	static final int INITIAL_MANS = INITIAL_GRASS / 150;
	static final int INITIAL_WOMANS = INITIAL_GRASS / 100;
	static final int INITIAL_HUNTERS = INITIAL_GRASS / 150;
	static final int INITIAL_SHELLS = 7; 

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Main().createAndShowWorld();
			}
		});
	}

	public void createAndShowWorld() {
		World world = new WorldImpl(X_DIM, Y_DIM);
		initialize(world);
		new WorldUI(world).show();
	}

	public void initialize(World world) {
		addGrass(world);
		world.addActor(new Gardener());

		addGnats(world);
		addRabbits(world);
		addFoxes(world);
		addMotorcycles(world);
		addCars(world);
		addTrucks(world);
		addBears(world); 
		addHyenas(world);
		addTigers(world); 
		addRedShells(world); 
		addGreenShells(world);
		addBlueShells(world); 
		// TODO: You may add your own creatures here!
	}

	private void addGrass(World world) {
		for (int i = 0; i < INITIAL_GRASS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			world.addItem(new Grass(loc));
		}
	}

	private void addGnats(World world) {
		for (int i = 0; i < INITIAL_GNATS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Gnat gnat = new Gnat(loc);
			world.addItem(gnat);
			world.addActor(gnat);
		}
	}

	private void addFoxes(World world) {
		FoxAI foxAI = new FoxAI();
		for (int i = 0; i < INITIAL_FOXES; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Fox fox = new Fox(foxAI, loc);
			world.addItem(fox);
			world.addActor(fox);
		}
	}

	private void addRabbits(World world) {
		RabbitAI rabbitAI = new RabbitAI();
		for (int i = 0; i < INITIAL_RABBITS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Rabbit rabbit = new Rabbit(rabbitAI, loc);
			world.addItem(rabbit);
			world.addActor(rabbit);
		}
	}
	
	private void addMotorcycles(World world) {
		for (int i = 0; i < INITIAL_MOTORCYCLES; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Motorcycle motorcycle = new Motorcycle(loc);
			world.addItem(motorcycle);
			world.addActor(motorcycle);
		}
	}
	
	private void addCars(World world) {
		for (int i = 0; i < INITIAL_CARS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Car car = new Car(loc);
			world.addItem(car);
			world.addActor(car);
		}
	}
	
	private void addTrucks(World world) {
		for (int i = 0; i < INITIAL_TRUCKS ; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Truck truck = new Truck(loc);
			world.addItem(truck);
			world.addActor(truck);
		}
	}
	
	private void addBears(World world) {
		AI bearAI = new ArenaAnimalAI(100); 
		for (int i = 0; i < INITIAL_BEARS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Bear bear = new Bear(bearAI, loc);
			world.addItem(bear);
			world.addActor(bear);
		}
	}
	
	private void addHyenas(World world) {
		AI hyenaAI = new ArenaAnimalAI(80); 
		for (int i = 0; i < INITIAL_BEARS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Hyena hyena = new Hyena(hyenaAI, loc);
			world.addItem(hyena);
			world.addActor(hyena);
		}
	}
	
	private void addTigers(World world) {
		AI tigerAI = new ArenaAnimalAI(120); 
		for (int i = 0; i < INITIAL_BEARS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			Tiger tiger = new Tiger(tigerAI, loc);
			world.addItem(tiger);
			world.addActor(tiger);
		}
	}
	
	private void addRedShells(World world) {
		AI redShellAI = new ShellAI(120); 
		for (int i = 0; i < INITIAL_SHELLS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			RedShell redShell = new RedShell(redShellAI, loc);
			world.addItem(redShell);
			world.addActor(redShell);
		}
	}
	
	private void addGreenShells(World world) {
		AI greenShellAI = new ShellAI(150); 
		for (int i = 0; i < INITIAL_SHELLS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			GreenShell greenShell = new GreenShell(greenShellAI, loc);
			world.addItem(greenShell);
			world.addActor(greenShell);
		}
	}
	
	private void addBlueShells(World world) {
		AI blueShellAI = new ShellAI(300); 
		for (int i = 0; i < INITIAL_SHELLS; i++) {
			Location loc = Util.getRandomEmptyLocation(world);
			BlueShell blueShell = new BlueShell(blueShellAI, loc);
			world.addItem(blueShell);
			world.addActor(blueShell);
		}
	}
}