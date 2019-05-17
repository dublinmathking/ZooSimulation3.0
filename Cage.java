/*************************************************************************** Class:  CageAuthor:  Greg King  Date:  October 21, 2004 (redesign 12-3-2018)This class is intended to be the environment in which our virtual animals will live. Date			Modification10-21-2004	Main Coding10-22-2004	addAnimal, removeAnimal methods12-03-2004	Modified the moveAnimal and removeAnimal methods to fix bugs03-27-2006  Added resources (food) to the cage12-03-2018	Redesigned the class to use (row, col) format rathen than (x, y)		    Also had Cage return ArrayLists rather Arrays of Animals****************************************************************************/import java.util.ArrayList;public class Cage{	private int cols;	private int rows;	private Animal [][] animals;	private int[][] resources;	private int numAnimals;		/**	*	No argument constructor creates a 1x1 empty cage.	*/	public Cage()	{		cols = 1;		rows = 1;		animals = new Animal[1][1];		resources = new int[1][1];		numAnimals = 0;	}		/**	*	Constructor creates a rows x columns sized array.	*	@param rows  number of rows (y coordinates) in cage	*	@param cols	 number of columns (x coordinate) in cage	*/	public Cage(int ro, int co)	{		cols = co;		rows = ro;		animals = new Animal[rows][cols];		resources = new int[rows][cols];		for(int r=0; r<rows; r++)		{			for(int c=0; c<cols; c++)			{				resources[r][c] = (int)(Math.random()*100+1);			}		}		numAnimals = 0;	}		/**	*	Method returns the Animal at position x,y.  Returns	*	a null Animal reference if the position is empty or	*	does not exist.	*	@return  Animals at Position x,y or a null Animal if 	*			 no such Position exists or Position is empty	*/	public Animal animalAt(int r, int c)	{		if (r<0 || r>=rows || c<0 || c>= cols)			return null;		else				return animals[r][c];	}		/**	*	Returns the amount of available resources from a given	*	position in the cage.  	*	@param x column coordinate	*	@param y row coordinate	*	@return amount of resources available for "harvest"	*/	public int resourcesAt(int r, int c)	{		if (r<0 || r>=rows || c<0 || c>= cols)			return -1;		int res = resources[r][c];				return res;	}		/**	*	Returns the amount of resources that can be taken by a given 	*	Animal, removing those resources	*	@param pos position to be "mined" for resources	*	@param max max resources this animal can "take"	*	@return amount of resources taken	*/	public int takeResources(Position pos, int max)	{		int c = pos.getCol();		int r = pos.getRow();		if (c<0 || c>=cols || r<0 || r>= rows)			return -1;		int res = resources[r][c];				if(res >= max)		{			resources[r][c] -= max;			return max;		} 		resources[r][c] = 0;		return res;	}		/**	*	Method returns true if Cage has no empty cells, false	*	if there empty cells remaining.	*	@return  true if Cage has no empty cells, false otherwise	*/	public boolean isFull()	{		if (numAnimals < cols*rows)			return false;		return true;	}		/**	*	Method returns a random empty Position in the Cage, or a	*	null Position if no empty Positions exist.	*	@return  a random empty position in the Cage	*/	public Position randomEmpty()	{		Position pos = new Position();		ArrayList<Position> empties = new ArrayList<Position>();		for(int r=0; r<rows; r++)		{			for(int c=0; c<cols; c++)			{				if(animals[r][c]==null)					empties.add(new Position(r,c));			}		}		//System.out.println(empties.size());		pos = empties.get((int)(Math.random()*empties.size()));		return pos;	}			/**	*	Method removes the given animal from the Cage.	*	@param removeThis  the animal to be removed	*	If given animal is not actually in the Cage then	*	nothing is done.	*/	public void removeAnimal(Animal removeThis)	{		int c = removeThis.getPosition().getCol();		int r = removeThis.getPosition().getRow();		if(animals[r][c].getID() == removeThis.getID())		{			numAnimals--;			animals[r][c] = null;		}	}			/**	*	Method adds the given Animal to the location	*	at which the Animal thinks it exists.  If that location	*	is already full, then no animal will be added and a	*	false value will be returned.  Otherwise true is returned.	*	@param  addThis  Animal to be added to the Cage	*	@return true if animal was added, false if not	*/	public boolean addAnimal(Animal addThis)	{		int c = addThis.getPosition().getCol();		int r = addThis.getPosition().getRow();		if(animals[r][c] != null)		{			return false;		}		animals[r][c] = addThis;		numAnimals++;		return true;	}			/**	*	Returns true if the given Position is empty (null), false if	*	it is not empty or is outside the Cage	*	@param  positionToCheck  Position to be checked	*	@return  true if Position is empty, false otherwise	*/	public boolean isEmptyAt(Position positionToCheck)	{		int c = positionToCheck.getCol();		int r = positionToCheck.getRow();		if(c<0 || r<0 || c>=cols || r>=rows)			return false;		if(animals[r][c] != null)		{			return false;		}		return true;	}			/**	*	Method moves the Animal to a new position in the cage.	*	Removes the reference to the Animal at the old position.	*	@param old Animal's old position	*	@param anim Animal to be moved to new position	*/	public void moveAnimal(Position old, Animal anim)	{		int newCol = anim.getPosition().getCol();		int newRow = anim.getPosition().getRow();		animals[old.getRow()][old.getCol()] = null;		animals[newRow][newCol] = anim;	}		/**	*	Method returns an ArrayList of all of the empty positions	*	adjacent to the given position.  In other words it is 	*	given a position and returns an array consisting of 	*	all of the positions next to the given position which	*	are also empty.	*	@param pos position to be checked	*	@return  ArrayList of all the empty positions adjacent 	*		to given position, returns an empty array if	*		pos is not inside the cage	*/	public ArrayList<Position> emptyNeighbors(Position pos)	{		ArrayList<Position> listOfEmpties= new ArrayList<Position>();		int c = pos.getCol();		int r = pos.getRow();				if(r-1>=0 && animals[r-1][c] == null)		{			listOfEmpties.add(new Position(r-1, c));		}		if(r+1<rows && animals[r+1][c] == null)		{			listOfEmpties.add(new Position(r+1, c));		}		if(c-1>=0 && animals[r][c-1] == null)		{			listOfEmpties.add(new Position(r, c-1));		}		if(c+1<cols && animals[r][c+1] == null)		{			listOfEmpties.add(new Position(r, c+1));		}				return listOfEmpties;				}		/**	*	Method returns an ArrayList of all of the non-empty positions	*	adjacent to the given position.  In other words it is 	*	given a position and returns an array consisting of 	*	all of the positions next to the given position which	*	contain some Animal.	*	@param pos position to be checked	*	@return  ArrayList of all the non-empty positions adjacent 	*		to given position, returns an empty array if	*		pos is not inside the cage	*/	public ArrayList<Position> nonEmptyNeighbors(Position pos)	{		ArrayList<Position> nonEmpties= new ArrayList<Position>();		int c = pos.getCol();		int r = pos.getRow();				if(r-1>=0 && animals[r-1][c] != null)		{			nonEmpties.add(new Position(r-1, c));		}		if(r+1<rows && animals[r+1][c] != null)		{			nonEmpties.add(new Position(r+1, c));		}		if(c-1>=0 && animals[r][c-1] != null)		{			nonEmpties.add(new Position(r, c-1));		}		if(c+1<cols && animals[r][c+1] != null)		{			nonEmpties.add(new Position(r, c+1));		}				return nonEmpties;				}		/**	 * Empties the Cage of all Animals	 */	public void clearCage()	{		for(int r=0; r<rows; r++)		{			for(int c=0; c<cols; c++)			{				animals[r][c] = null;			}		}	}			/**	*	Method returns an ArrayList of all of the non-empty positions	*	adjacent to the given position.  In other words it is 	*	given a position and returns an array consisting of 	*	all of the positions next to the given position which	*	contain some Animal.  This version looks at all 8 neighboring	*   positions, not just North-South-East-West	*	@param pos position to be checked	*	@return  ArrayList of all the non-empty positions adjacent 	*		to given position, returns an empty array if	*		pos is not inside the cage	*/	public ArrayList<Position> nonEmptyNeighborsBroad(Position pos)	{		ArrayList<Position> nonEmpties= new ArrayList<Position>();		int c = pos.getCol();		int r = pos.getRow();				if(r-1>=0 && animals[r-1][c] != null)		{			nonEmpties.add(new Position(r-1, c));		}		if(r+1<rows && animals[r+1][c] != null)		{			nonEmpties.add(new Position(r+1, c));		}		if(c-1>=0 && animals[r][c-1] != null)		{			nonEmpties.add(new Position(r, c-1));		}		if(c+1<cols && animals[r][c+1] != null)		{			nonEmpties.add(new Position(r, c+1));		}		if(r-1>=0 && c-1>=0 && animals[r-1][c-1] != null)		{			nonEmpties.add(new Position(r-1, c-1));		}		if(r-1>=0 && c+1<cols && animals[r-1][c+1] != null)		{			nonEmpties.add(new Position(r-1, c+1));		}		if(r+1<rows && c-1>=0 && animals[r+1][c-1] != null)		{			nonEmpties.add(new Position(r+1, c-1));		}		if(r+1<rows && c+1<cols && animals[r+1][c+1] != null)		{			nonEmpties.add(new Position(r+1, c+1));		}				return nonEmpties;				}			/**	*	Returns the number of columns 	*	of the cage.	*	@return number of columns in the cage	*/	public int getMax_X()	{		return cols;	}		/**	*	Returns the number of columns 	*	of the cage.	*	@return number of columns in the cage	*/	public int numCols()	{		return cols;	}		/**	*	Returns the number of rows	*	of the cage.	*	@return number of rows in the cage	*/	public int getMax_Y()	{		return rows;	}		/**	*	Returns the number of rows	*	of the cage.	*	@return number of rows in the cage	*/	public int numRows()	{		return rows;	}			/**	*	Returns the number of Animals in the Cage.	*	@return number of Animals in the cage	*/	public int getNumAnimals()	{		return numAnimals;	}			/**	*	Returns an array of all of the Animals in the cage.	*	@return an array of all animals in the cage	*/	public Animal[] allAnimals()	{		Animal[] finalList = new Animal[1];		ArrayList list = new ArrayList();		// This method uses a construction called an ArrayList 		// which you may not have seen previously.  Do not		// worry about how ArrayLists work.  Just know that in 		// the end the method will return an array of Animals.				for(int r=0; r<rows; r++)		{			for(int c=0; c<cols; c++)			{				if(animals[r][c] != null)				{					if(animals[r][c].isDead())						animals[r][c] = null;					else						list.add(animals[r][c]);				}			}		}		finalList = new Animal[list.size()];		for (int n=0; n<list.size(); n++)		{			finalList[n] = (Animal)(list.get(n));		}		//numAnimals = list.size();		return finalList;	}			/**	*	Returns an ArrayList of all of the Animals in the cage.	*	@return an ArrayList of all animals in the cage	*/	public ArrayList<Animal> getAllAnimals()	{		ArrayList<Animal> list = new ArrayList<Animal>();		// This method uses a construction called an ArrayList 		// which you may not have seen previously.  Do not		// worry about how ArrayLists work.  Just know that in 		// the end the method will return an array of Animals.				for(int r=0; r<cols; r++)		{			for(int c=0; c<cols; c++)			{				if(animals[r][c] != null)				{					if(animals[r][c].isDead())						animals[r][c] = null;					else						list.add(animals[r][c]);				}			}		}		return list;	}		public String toString()	{		String str = "Cage has " + rows + " rows and " + cols + " columns";		return str;	}}	