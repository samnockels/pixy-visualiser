import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.serial.*; 
import java.util.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class visualise extends PApplet {

// Example by Tom Igoe




int MULT = 3;

Serial myPort;  // The serial port
int lf = 10;    // Linefeed in ASCII
String myString = null;

public void setup() {
  
  // List all the available serial ports
  printArray(Serial.list());
  // Open the port you are using at the rate you want:
  myPort = new Serial(this, Serial.list()[19], 9600);

  myString = myPort.readStringUntil(lf);
  myString = null;

  fill(255,0,0);

  frameRate(30);
}

// 9 bytes

// 1 byte  - signature
// 2 bytes - x
// 2 bytes - y
// 2 bytes - w
// 2 bytes - h

public void draw() {
    background(10);
    myString = myPort.readStringUntil(lf);

    if (myString != null) {
      String[] boxes = myString.split(";");
        println(boxes.length);
      for ( String box : boxes ){
        String[] n = box.split(",");
        if( n.length == 5 ){
          if(n[0].equals("1")){
            stroke(255,0,0);  // red          
          }else{
            stroke(0);
          }
          rect(PApplet.parseFloat(n[1])*MULT,PApplet.parseFloat(n[2])*MULT,PApplet.parseFloat(n[3])*MULT,PApplet.parseFloat(n[4])*MULT);
          fill(255);
          float area = PApplet.parseFloat(n[3]) * PApplet.parseFloat(n[4]);
          text(String.valueOf(area), PApplet.parseFloat(n[1])*MULT,PApplet.parseFloat(n[2])*MULT );
        }
      }
    }
  
}
  public void settings() {  size(900,900); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "visualise" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
