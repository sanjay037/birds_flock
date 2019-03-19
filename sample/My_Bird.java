package sample;
import java.util.*;
import flockbase.*;

public class My_Bird extends Bird{
	public My_Bird()
	{
		setPos(0,0);
		setTarget(0,0);
	}
	public String getName()
	{
		return " ";
	}
	// this bird is now nominated as the leader
	public void becomeLeader()
	{
		isleader = true;
	}

	// this bird is asked to move out of the leader position
	public void retireLead()
	{
		isleader = false;
	}

	// to be implemented by derived classes to make the bird move for one
	// timestep
	protected void updatePos()
	{
		// storing all birds of this flock into ArrayList
		ArrayList<Bird> bird_list = new ArrayList<Bird>();
		bird_list = getFlock().getBirds();
		Position currPos = getPos();
		double move_x=0,move_y=0,temp_x=0,temp_y=0,normal=0;
		// taking the current positions
    int x = currPos.getX();
    int y = currPos.getY();
    if (!isleader)
		{
      Position leader_pos = getFlock().getLeader().getPos();
      setTarget(leader_pos.getX(),leader_pos.getY());
    }
		// giving the target positions
    int x_target = getTarget().getX();
    int y_target = getTarget().getY();
		// if normal is zero give some initial value to normal
		normal = Math.pow(Math.pow(y_target-y,2)+Math.pow(x_target-x,2),0.5);
		if(normal==0) normal = 10;
		move_x = ((x_target-x)*getMaxSpeed())/normal;
		move_y = ((y_target-y)*getMaxSpeed())/normal;
		// checking all other birds in the flock for avoiding collisions
		for(Bird b:bird_list)
		{
			temp_x = b.getPos().getX();
			temp_y = b.getPos().getY();
			normal = Math.pow(Math.pow(temp_x-x,2)+Math.pow(temp_y-y,2),0.5);
			// if distance between two birds is less than 2*maxSpeed,giving the bird
			// velocity in other direction and atlast taking resultant
			if(normal<2*(getMaxSpeed()+5) && normal!=0)
			{
				move_x += ((x - temp_x)*getMaxSpeed())/normal;
				move_y += ((y - temp_y)*getMaxSpeed())/normal;
			}
		}
		// updating the position of the bird by avoiding collisions
		setPos(x+(int)move_x, y+(int)move_y);
	}
	private boolean isleader = false;
}
