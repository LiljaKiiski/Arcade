from TkModules import *
from random import *
from sys import *
from time import *
from tkinter import *

tk = Tk()
tk.title('Space Game')
canvas = Canvas(tk, width=1700, height=850)
canvas.pack()
img =  PhotoImage(file='Stars1.gif')
Photo = canvas.create_image(0,0, image=img, anchor='nw')

class Useless:
	def __init__(self):
		self.keysym = None
class Spaceship:
	def __init__(self, canvas, x, y):
		self.canvas = canvas
		self.canvas_height = self.canvas.winfo_height()
		self.canvas_width = self.canvas.winfo_width()

		self.img2 = PhotoImage(file='Spaceship1.gif')
		self.item = canvas.create_image(x, y, image=self.img2, anchor='nw')

		canvas.bind_all('<KeyPress-Up>', self.move)
		canvas.bind_all('<KeyPress-Down>', self.move)

	def move(self, event):
		if event.keysym == 'Up':
			canvas.move(self.item,0,-10)
		elif event.keysym == 'Down':
			canvas.move(self.item, 0,10)

class Lazers:
	def __init__(self, canvas, x, y):
		self.canvas = canvas
		self.img3 = PhotoImage(file='Lazer1.gif')
		self.lazer = self.canvas.create_image(x,y, image=self.img3, anchor='nw')

	def move(self):
		self.canvas.move(self.lazer,-5,0)

tk.update()
ship = Spaceship(canvas, 350, 150)
t = 0
x = 0
lazers = []
text = canvas.create_text(100, 100, text=t, font=('Helvetica', 50))
num = float(0.01)
stopped = False
speed = 35
try:
	while True:
		if stopped != True:
			tk.update() #Updating
			tk.update_idletasks()
			if x == 35:
				lazers.append(Lazers(canvas, 2100, randint(0, 950))) #Adding lazers
				x = 0

			ship.move(Useless()) #Moving ship

			for lazer in lazers:
				lazer.move()
				if collision_check(canvas, ship.item, lazer.lazer, 300, 150, 98, 32):
					stopped = True

			cmd = collision_border(canvas, ship.item, 300, 150)
			if cmd == 'top':
				canvas.move(ship.item, 0, 10)
			if cmd == 'bottom':
				canvas.move(ship.item, 0, -10)

			if t % 1000 == 999: #Speed Up
				num = num - 0.001

			canvas.itemconfig(text, text = t + 1) #Timer
			t += 1
			x += 1
			sleep(num)

		else:
			canvas.create_text(900, 540, text='You Lose!', font=('Helvetica', 100))
			tk.update()

			t = str(t) #High score
			high_score('SpaceGameHC.txt', t, 'write')

except TclError:
	t = str(t)
	high_score('SpaceGameHC.txt', t, 'write')

#CREDITS:

#	MAIN PROGRAMMER:        - Lilja Kiiski
#	ASSISTANT PROGRAMMER 1: - Petteri Kiiski
#	ASSISTANT PROGRAMMER 2: - Joona Kiiski

#	GRAPHICS DESIGNER:      - Lilja Kiiski

#	IDEA CREATOR:           - Lilja Kiiski
