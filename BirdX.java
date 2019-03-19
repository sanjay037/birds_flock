package sample;

import flockbase.Position;
import java.io.PrintStream;

public class BirdX extends flockbase.Bird
{
  private int speed = 10;

  private boolean amLeader;


  public BirdX() {}

  public String getName()
  {
    return "BirdX";
  }

  protected void updatePos()
  {
    Position currPos = getPos();
    int x = currPos.getX();
    int y = currPos.getY();





    if (!amLeader) {
      Position lpos = getFlock().getLeader().getPos();
      setTarget(lpos.getX(), lpos.getY());
    }

    int xt = getTarget().getX();
    int yt = getTarget().getY();
    double dy;
    double dx;
    double dy; if ((xt == x) && (yt == y)) {
      double dx = 0.0D;
      dy = 0.0D; } else { double dx;
      if (xt == x) { double dy;
        double dy; if (yt > y) {
          dy = 1.0D;
        } else
          dy = -1.0D;
        dx = 0.0D; } else { double dy;
        if (yt == y) { double dx;
          double dx; if (xt > x) {
            dx = 1.0D;
          } else
            dx = -1.0D;
          dy = 0.0D;
        } else {
          double m = (yt - y) / (xt - x);
          System.out.println(xt + "," + yt + "  " + currPos + " m = " + m);
          double dx; if (xt > x) {
            dx = 1.0D;
          } else
            dx = -1.0D;
          dx *= speed;
          dy = m * dx;
        } } }
    System.out.println("dx dy" + dx + "-" + dy);

    setPos(x + (int)dx, y + (int)dy);
  }


  public void becomeLeader()
  {
    System.out.println("lead " + this);
    amLeader = true;
  }

  public void retireLead()
  {
    System.out.println("retire " + this);
    amLeader = false;
  }
}
