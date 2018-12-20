from p5 import *
import serial
s = serial.Serial(port='/dev/cu.usbmodem14331', baudrate=9600)
SIZE = 6

def setup():
    size(350*SIZE, 200*SIZE)

def draw():

    fill(20)
    stroke(20)
    rect((0, 0), width, height)

    read = s.readline().decode().strip()

    if (read):
        boxes = read.split(';')
        for box in boxes:
            if (box == ''):
                break
            data = box.split(',')
            if(len(data) == 4):
                print(data)
                stroke(0,200,0)
                fill(50)
                rect((int(data[0]), int(data[1])), int(data[2])*SIZE, int(data[3])*SIZE)
                
if __name__ == '__main__':
    run()