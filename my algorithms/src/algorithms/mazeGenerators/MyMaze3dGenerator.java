package algorithms.mazeGenerators;

import java.util.ArrayList;
import java.util.Random;

/**
 * A class which generate maze with Prim's algorithm
 * 
 * @author Yotam Levy
 *
 */
public class MyMaze3dGenerator extends CommonMaze3dGenerator {

	private Random rand = new Random();

	@Override
	public Maze3d generate(int x, int y, int z) {
		// I used Prim's algorithm
		Maze3d maze = new Maze3d(x, y, z);
		ArrayList<Position> wallsList = new ArrayList<Position>(); // array of
																	// walls

		maze.fillWithWalls(); // fill 1 all over the maze

		// put in this cell a zero
		Position entrance = Position.getRandomPosition(maze);
		Position exit = null;
		maze.setHoleAt(entrance);

		// placing all the walls around the cell into array of walls
		fillArrayOfWalls(entrance, maze, wallsList);

		while (!wallsList.isEmpty()) {

			Position randomWall = takeRandomPosition(wallsList);

			// take the content of the cell,the current position we will be
			// working at. if the amount of holes around the current cell is 1
			if (countNumberOfHolesAroundCell(randomWall, maze) == 1) {
				maze.setHoleAt(randomWall);
				fillArrayOfWalls(randomWall, maze, wallsList);
			}
			wallsList.remove(randomWall);
			if (wallsList.size() == 1 && exit == null) {
				exit = wallsList.get(0);
				maze.setHoleAt(exit);
			}
		}

		maze.setStartPosition(entrance);
//		System.out.println("entrance " + entrance);
//		System.out.println("entrance value" + maze.getValueAt(entrance.getX(), entrance.getY(), entrance.getZ()));
//
//		System.out.println("exit" + exit);
//		System.out.println("exit value" + maze.getValueAt(exit.getX(), exit.getY(), exit.getZ()));
		maze.setGoalPosition(exit);

		return maze;
	}

	private Position takeRandomPosition(ArrayList<Position> wallsList) {
		// take a random wall from array of walls
		int randomIndex = rand.nextInt(rand.nextInt(wallsList.size()) + 1);
		Position randomWall = wallsList.get(randomIndex); // randomWall =>
															// randomly choosing
															// a Wall from the
															// array of walls
		return randomWall;
	}

	private void fillArrayOfWalls(Position p, Maze3d maze, ArrayList<Position> walls) {
		int x = p.getX();
		int y = p.getY();
		int z = p.getZ();

		// take a cell and fill his neighboring wall in the array
		if (x - 1 >= 0 && maze.getValueAt(x - 1, y, z) == Maze3d.WALL && !walls.contains(new Position(x - 1, y, z))) {
			walls.add(new Position(x - 1, y, z));
		}
		if (x + 1 < maze.getHeight() && maze.getValueAt(x + 1, y, z) == Maze3d.WALL
				&& !walls.contains(new Position(x + 1, y, z))) {
			walls.add(new Position(x + 1, y, z));
		}
		if (y - 1 >= 0 && maze.getValueAt(x, y - 1, z) == Maze3d.WALL && !walls.contains(new Position(x, y - 1, z))) {
			walls.add(new Position(x, y - 1, z));
		}
		if (y + 1 < maze.getDepth() && maze.getValueAt(x, y + 1, z) == Maze3d.WALL
				&& !walls.contains(new Position(x, y + 1, z))) {
			walls.add(new Position(x, y + 1, z));
		}
		if (z - 1 >= 0 && maze.getValueAt(x, y, z - 1) == Maze3d.WALL && !walls.contains(new Position(x, y, z - 1))) {
			walls.add(new Position(x, y, z - 1));
		}
		if (z + 1 < maze.getWidth() && maze.getValueAt(x, y, z + 1) == Maze3d.WALL
				&& !walls.contains(new Position(x, y, z + 1))) {
			walls.add(new Position(x, y, z + 1));
		}
	}

	private int countNumberOfHolesAroundCell(Position p, Maze3d maze) {

		int i = p.getX();
		int j = p.getY();
		int n = p.getZ();

		// count how much zeros there are around the cell
		int count = 0;
		if ((i - 1 >= 0) && (maze.getValueAt(i - 1, j, n) == Maze3d.HOLE)) {
			count++;
		}
		if ((i + 1 < maze.getHeight()) && (maze.getValueAt(i + 1, j, n) == Maze3d.HOLE)) {
			count++;
		}
		if ((j - 1 >= 0) && (maze.getValueAt(i, j - 1, n) == Maze3d.HOLE)) {
			count++;
		}
		if ((j + 1 < maze.getDepth()) && (maze.getValueAt(i, j + 1, n) == Maze3d.HOLE)) {
			count++;
		}
		if ((n - 1 >= 0) && (maze.getValueAt(i, j, n - 1) == 0)) {
			count++;
		}
		if ((n + 1 < maze.getWidth()) && (maze.getValueAt(i, j, n + 1) == Maze3d.HOLE)) {
			count++;
		}
		return count;
	}
}
