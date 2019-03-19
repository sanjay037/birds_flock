package sample;
import java.util.ArrayList;
import flockbase.*;

public class My_Flock extends Flock{
	public My_Flock()
	{
		bird_arraylist =  new ArrayList<Bird>();
	}
	public String getName()
	{
		return "37";
	}
// add a Bird to this Flock
	public void addBird(Bird b)
	{
		bird_arraylist.add(b);
		b.setFlock(this);
	}

	// Make this Bird the leader of the flock
	public void setLeader(Bird leader)
	{
		if(flock_leader!=null) flock_leader.retireLead();
		flock_leader = leader;
		leader.becomeLeader();
	}

	// get current list of birds in the flock
	public ArrayList<Bird> getBirds()
	{
		return bird_arraylist;
	}

	// return the current leader
	public Bird getLeader()
	{
		return flock_leader;
	}

	// split with the bird at pos as the leader of new flock
	// Returns the new flock formed
	public Flock split(int pos)
	{
		My_Flock f = new My_Flock();
		f.setLeader(bird_arraylist.get(pos));
		// removing some birds from original flock and copying into other flock
		for(int i=pos;i<bird_arraylist.size();i=i)
		{
			f.addBird(bird_arraylist.get(i));
			bird_arraylist.get(i).setFlock(f);
			bird_arraylist.remove(i);
		}
		return f;
	}

	// merges current flock with flock f - should join at end of f
	public void joinFlock(Flock f)
	{
		Bird b1 = getLeader();
		b1.retireLead();
		// adding birds to the flock f
		for(Bird b:bird_arraylist)
		{
			f.addBird(b);
			b.setFlock(f);
		}
	}

	private ArrayList<Bird> bird_arraylist;
	private Bird flock_leader;
}
