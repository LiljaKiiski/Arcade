from TkModules import * 
from random import *
from sys import *
from time import *
from tkinter import *
tk = Tk()
tk.title('Bounce!')
canvas = Canvas(tk, width=800, height=800)
canvas.pack()
x = 5 #speed

class Useless:
	def __init__(self):
		self.keysym = None

class Ball:
	def __init__(self, canvas, color):
		self.canvas = canvas
		self.canvas_height = self.canvas.winfo_height()
		self.canvas_width = self.canvas.winfo_width()

		self.ball = canvas.create_oval(20, 20, 50, 50, fill=color)

		self.x = randint(-x,x)
		self.y = randint(-x,x)

	def move(self):
		self.canvas.move(self.ball, self.x, self.y)

		cmd = collision_border(canvas, ball.ball, 50, 50)
		if cmd == 'top':
			self.x = randint(-x,x)
			self.y = randint(0, x)
		if cmd == 'bottom':
			global stopped
			self.x = randint(-x,x)
			self.y = randint(-x, 0)
			stopped = True
		if cmd == 'right':
			self.x = randint(-x, 0)
			self.y = randint(-x, x)
		if cmd == 'left':
			self.x = randint(0, x)
			self.y = randint(0, x)

		if collision_check(self.canvas, self.ball, paddle.paddle, 50, 50, 200, 20):
			self.x = randint(-x, x)
			self.y = randint(-x, 0)

class Paddle:
	def __init__(self, canvas, color):
		self.canvas = canvas
		self.paddle = canvas.create_rectangle(0, 0, 200, 20, fill=color) 
		self.canvas.move(self.paddle, 400, 600)

		canvas.bind_all('<KeyPress-Right>', self.move)
		canvas.bind_all('<KeyPress-Left>', self.move)

	def move(self, event):
		if event.keysym == 'Right':
			self.canvas.move(self.paddle,15, 0)
		if event.keysym == 'Left':
			self.canvas.move(self.paddle,-15, 0)

		cmd = collision_border(canvas, paddle.paddle, 200, 20)
		if cmd == 'right':
			canvas.move(self.paddle, -15, 0)
		if cmd == 'left':
			canvas.move(self.paddle, 15, 0)

ball = Ball(canvas, 'red')
paddle = Paddle(canvas, 'blue')
stopped = False
try:
	while True:
		if stopped != True:
			tk.update()
			tk.update_idletasks()

			paddle.move(Useless())
			ball.move()
			sleep(0.01)

		else:
			canvas.create_text(400, 400, text='You Lost!', font=('Helvetica', 50))
			tk.update()

except TclError:
	pass

#CREDITS:

#MAIN PROGRAMMER:   - Lilja Kiiski

#GRAPHICS DESIGNER: - Lilja Kiiski
