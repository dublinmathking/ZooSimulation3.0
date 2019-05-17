/******************************************************************** Class:  Gazelle  (extends Prey which extends Animal)Author:  Greg King  Date:  December 1, 2004 (redesigned 12-3-2018)Models the behavior of Gazelles in the simulationDate			Modification12-01-2004	Main coding on this class started12-03-2004	Started making Gazelle specific movement (running away from 			predators)11-22-2013	Redesigned to implement Prey interface12-03-2018	Redesigned the class for (row, col) rather than (x, y)*********************************************************************/import java.awt.*;import java.util.*;public class Gazelle extends Animal implements Prey{		private double visualRange = 30.0;		/**	*	Constructor creates a Gazelle with Position 0,0.  Animal	*	has no cage in which to live.	*/	public Gazelle()	{		super();	}		/**	*	Constructor creates a Gazelle in a random empty spot in	*	the given cage.	*	@param cage  the cage in which Gazelle will be created.	*/	public Gazelle(Cage cage)	{		super(cage, Color.blue);	}		/**	*	Constructor creates a Gazelle in a random empty spot in	*	the given cage with the specified Color.	*	@param cage  the cage in which Gazelle will be created.	*	@param color  the color of the Gazelle	*/	public Gazelle(Cage cage, Color color)	{		super(cage, color);	}		/**	*	Constructor creates a Gazelle in the given Position	*	in the given Cage	*	@param cage  the cage in which Gazelle will be created.	*	@param color  the color of the Gazelle	*	@param pos	the position of the Gazelle	*/	public Gazelle(Cage cage, Position pos)	{		super(cage, Color.blue, pos);	}		/**	*	Constructor creates a Gazelle in the given Position	*	the given cage with the specified Color.	*	@param cage  the cage in which Gazelle will be created.	*	@param color  the color of the Gazelle	*	@param pos	the position of the Gazelle	*/	public Gazelle(Cage cage, Color color, Position pos)	{		super(cage, color, pos);	}		/**	*	Method sets the Gazelle's visual range to the given value.	*	@param range  sets the Gazelle's visual range to 'range'	*/	public void setVisualRange(double range)	{		visualRange = range;	}		/**	*	Returns String form of Animal, which is its position	*	and its type.	*	@return String form of Animal	*/	public String toString()	{		return (myPos.toString() + " is a  Gazelle.  ");	}		/**	*	Method overwrites the Act method in Animal.  	*/	public void act()	{		// Gazelle acts as a generic Animal would act, 		// meaning random movement		super.act();	}		/**	*	Method returns true if obj is a type that can eat 	*	this Animal, returns false otherwise	*	@param	obj	object to be evaluated	*	@return true if obj can eat this Animal, false otherwise	*/	public boolean canItEatMe(Animal obj)	{		if(obj instanceof Predator)			return true;		return false;	}						/**	*	Method returns the String form of the Animal's	*	species, in this case "Gazelle"	*	@return	the String "Gazelle"	*/	public String getSpecies()	{		return "Gazelle";	}	}