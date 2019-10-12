import pyrebase
from credentials import firebaseConfig
import os

path = r'/home/joker/Programs/wie-hackathon/python-firebase/'
firebase = pyrebase.initialize_app(firebaseConfig)

database = firebase.database()
storage = firebase.storage()

# storage.child('Images/jok.jpeg').put(path+r'Images/jok22.jpeg')
database.child('imName').set('Dazz')