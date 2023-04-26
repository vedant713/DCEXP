


###########################EXP6#################################
import java.util.*;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class lamport{
int e[][]=new int[10][10];
int en[][]=new int[10][10];
int ev[]=new int[10];
int i,p,j,k;
HashMap<Integer,Integer> hm=new HashMap<Integer,Integer>();
int xpoints[] =new int[5];
int ypoints[] =new int[5];
class draw extends JFrame{
private final int ARR_SIZE = 4;

            void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
                Graphics2D g = (Graphics2D) g1.create();

                double dx = x2 - x1, dy = y2 - y1;
                double angle = Math.atan2(dy, dx);
                int len = (int) Math.sqrt(dx*dx + dy*dy);
                AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
                at.concatenate(AffineTransform.getRotateInstance(angle));
                g.transform(at);

                // Draw horizontal arrow starting in (0, 0)
                g.drawLine(0, 0, len, 0);
                g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                              new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
            }

            public void paintComponent(Graphics g) {
                for (int x = 15; x < 200; x += 16)
                    drawArrow(g, x, x, x, 150);
                drawArrow(g, 30, 300, 300, 190);
            }




public void paint(Graphics g){
int h1,h11,h12;
Graphics2D go=(Graphics2D)g;
go.setPaint(Color.black);
for(i=1;i<=p;i++)
{
go.drawLine(50,100*i,450,100*i);
}
 for(i=1;i<=p;i++)
 {
  for(j=1;j<=ev[i];j++)
  {
   k=i*10+j;
   go.setPaint(Color.blue);
   go.fillOval(50*j,100*i-3,5,5);
   go.drawString("e"+i+j+"("+en[i][j]+")",50*j,100*i-5);
   h1=hm.get(k);
   if(h1!=0)
    {
          h11=h1/10;
      h12=h1%10;
      go.setPaint(Color.red);
          drawArrow(go,50*h12+2,100*h11,50*j+2,100*i);
    }
  }
 }

}
}
public void calc(){
Scanner sc=new Scanner(System.in);
System.out.println("Enter the number of process:");
p=sc.nextInt();
System.out.println("Enter the no of events per process:");
for(i=1;i<=p;i++)
{
  ev[i]=sc.nextInt();
}
System.out.println("Enter the relationship:");
for(i=1;i<=p;i++)
{
System.out.println("For process:"+i);
  for(j=1;j<=ev[i];j++)
    {
    System.out.println("For event:"+(j));
    int input=sc.nextInt();
    k=i*10+j;
    hm.put(k,input);
    if(j==1)
        en[i][j]=1;
    }
}

for(i=1;i<=p;i++)
{
  for(j=2;j<=ev[i];j++)
    {
     k=i*10+j;
     if(hm.get(k)==0)
     {
     en[i][j]=en[i][j-1]+1;
     }
     else
     {
     int a=hm.get(k);
         int p1=a/10;
     int e1=a%10;
      if(en[p1][e1]>en[i][j-1])
        en[i][j]=en[p1][e1]+1;
       else
        en[i][j]=en[i][j-1]+1;
     }
 }
}
for(i=1;i<=p;i++)
{
  for(j=1;j<=ev[i];j++)
    {
    System.out.println(en[i][j]);
   
    }
}
JFrame jf=new draw();
jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
jf.setSize(500,500);
jf.setVisible(true);
}
public static void main(String[] args){

lamport lam=new lamport();
lam.calc();

}
}
2 7 5 00000 22 24 00 12 0 22 24 0 0 12 0 16
###########################EXP 6#################################

###########################Theory-EXP 6#################################
Lamport’s Distributed Mutual Exclusion Algorithm is a permission based algorithm proposed by Lamport as an illustration of his synchronization scheme for distributed systems. In permission based timestamp is used to order critical section requests and to resolve any conflict between requests. In Lamport’s Algorithm critical section requests are executed in the increasing order of timestamps i.e a request with smaller timestamp will be given permission to execute critical section first than a request with larger timestamp. In this algorithm:

Three type of messages ( REQUEST, REPLY and RELEASE) are used and communication channels are assumed to follow FIFO order.
A site send a REQUEST message to all other site to get their permission to enter critical section.
A site send a REPLY message to requesting site to give its permission to enter the critical section.
A site send a RELEASE message to all other site upon exiting the critical section.
Every site Si, keeps a queue to store critical section requests ordered by their timestamps. request_queuei denotes the queue of site Si
A timestamp is given to each critical section request using Lamport’s logical clock.
Timestamp is used to determine priority of critical section requests. Smaller timestamp gets high priority over larger timestamp. The execution of critical section request is always in the order of their timestamp.
Algorithm:

To enter Critical section:
When a site Si wants to enter the critical section, it sends a request message Request(tsi, i) to all other sites and places the request on request_queuei. Here, Tsi denotes the timestamp of Site Si
When a site Sj receives the request message REQUEST(tsi, i) from site Si, it returns a timestamped REPLY message to site Si and places the request of site Si on request_queuej
To execute the critical section:
A site Si can enter the critical section if it has received the message with timestamp larger than (tsi, i) from all other sites and its own request is at the top of request_queuei
To release the critical section:
When a site Si exits the critical section, it removes its own request from the top of its request queue and sends a timestamped RELEASE message to all other sites
When a site Sj receives the timestamped RELEASE message from site Si, it removes the request of Si from its request queue

Message Complexity: Lamport’s Algorithm requires invocation of 3(N – 1) messages per critical section execution. These 3(N – 1) messages involves
###########################Theory-EXP 6#################################
