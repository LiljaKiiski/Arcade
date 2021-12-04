from tkinter import *
from time import *
from random import *
def within_x(co1, co2):
	if (co1[0] > co2[0] and co1[0] < co2[2]) \
		or (co1[2] > co2[0] and co1[2] < co2[2]) \
		or (co2[0] > co1[0] and co2[0] < co1[2]) \
		or (co2[2] > co1[0] and co2[2] < co1[2]):
		return True
	else:
		return False
def within_y(co1, co2):
	if (co1[1] > co2[1] and co1[1] < co2[3]) \
		or (co1[3] > co2[1] and co1[3] < co2[3]) \
		or (co2[1] > co1[1] and co2[1] < co1[3]) \
		or (co2[3] > co1[1] and co2[3] < co1[3]):
		return True
	else:
		return False
def collided_left(co1, co2):
	if within_y(co1,co2):
		if co1[0] <= co2[2] and co1[0] >= co2[0]:
			return True
	return False
def collided_right(co1, co2):
	if within_y(co1, co2):
		if co1[2] >= co2[0] and co1[2] <= co2[2]:
			return True
	return False
def collided_top(co1, co2):
	if within_x(co1, co2):
		if co1[1] <= co2[3] and co1[1] >= co2[1]:
			return True
	return False
def collided_bottom(y, co1, co2):
	if within_x(co1, co2):
		y_calc = co1[3] + y
		if y_calc >= co2[1] and y_calc <= co2[3]:
			return True
		return False
def collision_check(canvas, obj1, obj2, obj1w, obj1h, obj2w, obj2h):
	co1 = canvas.coords(obj1)
	co2 = canvas.coords(obj2)
	co1.append(co1[0] + obj1w)
	co1.append(co1[1] + obj1h)
	co2.append(co2[0] + obj2w)
	co2.append(co2[1] + obj2h)

	if collided_right(co1, co2) or collided_left(co1, co2) or collided_top(co1, co2) or collided_bottom(0, co1, co2):
		return True
	return False

def collision_border(canvas, obj, objw, objh):
	pos = canvas.coords(obj)

	pos.append(pos[0] + objw)
	pos.append(pos[1] + objh)
	canvas_width = canvas.winfo_width()
	canvas_height = canvas.winfo_height()
	if pos[0] <= 0:
		return 'left'
	if pos[1] <= 0:
		return 'top'
	if pos[2] >= canvas_width:
		return 'right'
	if pos[3] >= canvas_height:
		return 'bottom'

def high_score(save, score, rw):
	if rw == 'write':
		file = save
		file = open(save, 'rb')
		highscore = file.read()
		bytescore = score.encode(encoding = 'UTF-8')
		prevscore = highscore.decode(encoding = 'UTF-8')

		if int(score) > int(prevscore):
			file = open(save, 'wb')
			file.write(bytescore)
			file.close() 
		file.close()

def low_score(save, score, rw):
	if rw == 'write':
		file = save
		file = open(save, 'rb')
		highscore = file.read()
		bytescore = score.encode(encoding = 'UTF-8')
		prevscore = highscore.decode(encoding = 'UTF-8')

		if int(score) < int(prevscore):
			file = open(save, 'wb')
			file.write(bytescore)
			file.close()
		file.close()
#CREDITS:

#	MAIN PROGRAMMER 1: 	- Lilja Kiiski
#	MAIN PROGRAMMER 2:	- Petteri Kiiski
#	ASSISTANT PROGRAMMER 2: - Joona Kiiski

#	IDEA CREATOR:	        - Lilja Kiiski
