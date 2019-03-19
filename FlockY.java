package sample;

import flockbase.Bird;
import flockbase.Flock;
import java.util.ArrayList;

public class FlockY extends Flock
{

    public FlockY()
    {
        birds = new ArrayList();
        lead = null;
    }

    public void addBird(Bird b)
    {
        birds.add(b);
        b.setFlock(this);
    }

    public void setLeader(Bird leader)
    {
        if(lead != null)
            lead.retireLead();
        lead = leader;
        lead.becomeLeader();
    }

    public ArrayList getBirds()
    {
        return birds;
    }

    public Bird getLeader()
    {
        return lead;
    }

    public Flock split(int pos)
    {
        return null;
    }

    public void joinFlock(Flock flock)
    {
    }

    private ArrayList birds;
    Bird lead;
}
