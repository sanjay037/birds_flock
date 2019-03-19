package sample;
import flockbase.*;
import java.util.*;
public class my_bird extends Bird{
private boolean isLeader;
public my_bird(){
  Position pos = new Position(0,0);
  Position target_pos = new Position(0,0);
}
public void becomeLeader()
{
  isLeader = true;
}
public void retireLead()
{
  isLeader = false;
}
public void updatePos()
{
  int x_p = getPos().getX();
  int y_p = getPos().getY();
  double x;
  double y;
  ArrayList<Bird> my_bird_list = getFlock().getBirds();
  if(!isLeader){
    Position l = getFlock().getLeader().getPos();
    setTarget(l.getX(),l.getY());
  }
  int xt = getTarget().getX();
  int yt = getTarget().getY();
  double r = Math.sqrt(Math.pow(xt-x_p,2) + Math.pow(yt-y_p,2));
  x = ((xt-x_p)*getMaxSpeed())/r;
  y = ((yt-y_p)*getMaxSpeed())/r;
  for(Bird i:my_bird_list){
    Position p = i.getPos();
    r = Math.sqrt(Math.pow(p.getX()-x_p,2)+Math.pow(p.getY()-y_p,2));
    if(r<=2*getMaxSpeed() && r!=0){
      x += (x_p-p.getX())*getMaxSpeed()/r;
      y += (y_p-p.getY())*getMaxSpeed()/r;
    }
  }
  setPos(x_p+(int)x,y_p+(int)y);
  }
}
