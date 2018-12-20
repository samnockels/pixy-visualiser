// Example by Tom Igoe

import processing.serial.*;
import java.util.*;

int MULT = 3;

Serial myPort;  // The serial port
int lf = 10;    // Linefeed in ASCII
String myString = null;

void setup() {
  size(900,900);
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

void draw() {
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
          rect(float(n[1])*MULT,float(n[2])*MULT,float(n[3])*MULT,float(n[4])*MULT);
          fill(255);
          float area = float(n[3]) * float(n[4]);
          text(String.valueOf(area), float(n[1])*MULT,float(n[2])*MULT );
        }
      }
    }
  
}