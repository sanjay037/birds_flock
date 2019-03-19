package sample;
import flockbase.*;
import java.util.*;
public class MyBird extends Bird{
  public MyBird(){
    pos = new Position(0, 0);
		target = new Position(0, 0);
  }

  public void becomeLeader(){
    //flock.setLeader(this);
    iam_leader = true;
  }

  public void retireLead(){
    iam_leader= false;
  }

  public String getName(){
    return "harshith";
  }
  public double[] getUnitVector(Position p1,Position p2){
    int x1 = p1.getX();
    int x2 = p2.getX();
    int y1 = p1.getY();
    int y2 = p2.getY();
  //  System.out.println("x1 = "+ x1 + "x2 = " + x2 + "y1 = " + y1 + "y2 = " + y2);
    double[] unit_vector  = new double[2];
    double magnitude = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
  //  System.out.println("magnitude = "+ magnitude);
    unit_vector[0] = (((double)(x2-x1))/magnitude);
    unit_vector[1] = (((double)(y2-y1))/magnitude);
    //System.out.println("i = "+unit_vector[0]+" "+"j = "+ unit_vector[1]);
    return unit_vector;
  }

  public void updatePos(){
    //System.out.println("here bird");
    //System.out.println(getFlock().getLeader().getName());
    Position pos = getPos();
    //Flock flock = getFlock();
  	Position target = getTarget();
    if(iam_leader){
      //double move_x,move_y;
      //double[] array = new double[2];
      //System.out.println("target"+getTarget());
      //System.out.println(pos);
      // array = getUnitVector(pos,getTarget());
      // move_x = array[0];
      // move_y = array[1];
      // int x = pos.getX();
      // int y = pos.getY();
      // System.out.println("x*speed" + (int)(move_x*maxSpeed));
      // setPos(x+(int)(move_x*maxSpeed),y+(int)(move_y*maxSpeed));
      // System.out.println(getPos());
      ArrayList<Bird> array_bird = new ArrayList<Bird>(getFlock().getBirds());
      // array_bird =
        double[] unit_vector = new double[2];
      unit_vector = getUnitVector(pos,getTarget());
      for(Bird b: array_bird){
        Position neighbour = b.getPos();
        double distance = Math.sqrt(Math.pow(pos.getX()-neighbour.getX(),2)+Math.pow(pos.getY()-neighbour.getY(),2));
        if(distance <= 2*maxSpeed && distance!= 0 ){
          double[] speed_vector = new double[2];
          speed_vector = getUnitVector(pos,neighbour);
          unit_vector[0] = unit_vector[0]-speed_vector[0];
          unit_vector[1] = unit_vector[1]-speed_vector[1];
        }
      }
      int x = pos.getX();
      int y = pos.getY();
      setPos(x+(int)(unit_vector[0]*maxSpeed),y+(int)(unit_vector[1]*maxSpeed));

      return;
    }

    else{
      Position flock = getFlock().getLeader().getPos();
      //System.out.println(flock.getX());
      //System.out.println(getTarget().getX());
      ArrayList<Bird> array_bird = new ArrayList<Bird>(getFlock().getBirds());
      // array_bird =
      double[] unit_vector = new double[2];
      unit_vector = getUnitVector(pos,flock);
      for(Bird b: array_bird){
        Position neighbour = b.getPos();
        double distance = Math.sqrt(Math.pow(pos.getX()-neighbour.getX(),2)+Math.pow(pos.getY()-neighbour.getY(),2));
        if(distance <= 2*maxSpeed-1 && distance!= 0 ){
          double[] speed_vector = new double[2];
          speed_vector = getUnitVector(pos,neighbour);
          unit_vector[0] = unit_vector[0]-speed_vector[0];
          unit_vector[1] = unit_vector[1]-speed_vector[1];
        }
      }
      int x = pos.getX();
      int y = pos.getY();
      setPos(x+(int)(unit_vector[0]*maxSpeed),y+(int)(unit_vector[1]*maxSpeed));
    }
  }
  private boolean iam_leader;
  private static int maxSpeed = 15;
  // private Position pos = getPos();
	// private Flock flock = getFlock();
	// private Position target = getTarget();
}
