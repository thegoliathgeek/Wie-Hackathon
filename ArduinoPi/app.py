import RPi.GPIO as gp
import pyrebase
from credentials import firebaseConfig
from time import sleep
import sys
gp.setmode(gp.BOARD)
inp = 7
#out = 11

if __name__ == '__main__':
 gp.setup(11,gp.OUT)
 gp.setup(inp,gp.IN)
 #gp.setup(out,gp.OUT)
 firebase = pyrebase.initialize_app(firebaseConfig)
 db = firebase.database()
 known = False
 allow = False
 while True:
  known = db.child('known').get().val()
  allow = db.child('allow').child('value').get().val()
  print(f' Known : {known}')
  gp.output(11,gp.LOW)
  if known or allow:
     #gp.output(11,gp.HIGH)
     print('Dooor Open')
     if gp.input(inp) == 1:
        #print('Door Open')
        #while (gp.input(inp) == 0)
        #gp.output(11,gp.LOW)
        print('Door Closed')
        db.child('allow').child('value').set(False)
        db.child('known').set(False)
        sys.exit()
  else:
     if gp.input(inp) == 1:
        print('Forcefully Entered')
        gp.output(11,gp.HIGH)
        sleep(2)
     gp.output(11,gp.LOW)
