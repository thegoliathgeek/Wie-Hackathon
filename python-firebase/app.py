import pyrebase
from credentials import firebaseConfig

firebase = pyrebase.initialize_app(firebaseConfig)

storage = firebase.storage()

storage.child('some.jpeg').put('jok.jpeg')