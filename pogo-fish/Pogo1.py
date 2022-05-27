import pygame, sys

pygame.init()
WinWidth = 1800
WinHeight = 900
win = pygame.display.set_mode((WinWidth, WinHeight))
clock = pygame.time.Clock()
bg = pygame.image.load('Level1.png')
pygame.display.set_caption("Pogo Fish")
sound1 = pygame.mixer.Sound('Sound1.wav')
pygame.mixer.music.load('Music.mp3')
pygame.mixer.music.play(-1, 0.0)
ground = 600
pGround = 550
vel = 7
heartNum = 2
recoverTime = 0

class Player:
	def __init__(self, imageRight, imageLeft, imageStill, imageHit, imageDie, x, y, width, height):
		self.player = pygame.Rect(x, y, width, height)
		self.moveRight = pygame.image.load(imageRight)
		self.moveLeft = pygame.image.load(imageLeft)
		self.moveStill = pygame.image.load(imageStill)
		self.Dying = pygame.image.load(imageHit)
		self.Dead = pygame.image.load(imageDie)
		self.x = int(x)
		self.y = int(y)
		self.width = width
		self.height = height
		self.vel = 15
		self.isJump = True
		self.jumpCount = 10
		self.left = False
		self.right = False
		self.dying = False
		self.dead = False
		self.standing = True

	def draw(self, win):
		self.x = int(self.x)
		self.y = int(self.y)

		if self.dead:
			win.blit(self.Dead,(self.x, self.y))

		elif self.dying:
			win.blit(self.Dying,(self.x, self.y))

		elif self.left:
			win.blit(self.moveLeft,(self.x, self.y))

		elif self.right:
			win.blit(self.moveRight,(self.x, self.y))

		elif self.standing:
			win.blit(self.moveStill, (self.x, self.y))

		self.player = pygame.Rect(self.x, self.y, self.width, self.height)

class Objects:
	def __init__(self, x, y, width, height, imageRight = 'Blank.png', imageLeft = 'Blank.png',  image = 'Blank.png'):
		self.img = pygame.image.load(image)
		self.object = pygame.Rect(x, y, width, height)
		self.moveRight = pygame.image.load(imageRight)
		self.moveLeft = pygame.image.load(imageLeft)
		self.image = image
		self.x = int(x)
		self.y = int(y)
		self.left = False
		self.right = False
		self.width = width
		self.height = height
		self.vel = 3

	def collided_coral(self, coral1, coral2):
		if self.object.colliderect(coral1.object):
			self.right = True
			self.left = False

		elif self.object.colliderect(coral2.object):
			self.left = True
			self.right = False

class Container:
	def __init__(self):
		self.krabs = [krab1, krab2]
		self.sands = [sand1, sand2, sand3, sand4, sand5, sand6, sand7, sand8, sand9, sand10, sand11, sand12, sand13]
		self.corals = [coral1, coral2, coral3, coral4, coral5, coral6, coral7, coral8, coral9]
		self.coralsB = [coralB1, coralB2, coralB3, coralB4]
		self.coralsC = [coralC1]
		self.holes = [hole1, hole2]
		self.hearts = [heart1, heart2, heart3]
		self.all =  self.hearts + self.sands + self.krabs + self.corals + self.coralsB + self.coralsC + self.holes

	def moveRight(self, num):
		for item in self.krabs + self.corals + self.coralsB + self.coralsC + self.holes:
			item.x += num

	def moveLeft(self, num):
		for item in self.krabs + self.corals + self.coralsB + self.coralsC + self.holes:
			item.x -= num

	def draw(self, win):
		for sand in self.sands:
			win.blit(sand.img,(sand.x, sand.y))

		for item in self.corals + self.coralsB + self.coralsC + self.holes:
			win.blit(item.img,(item.x, item.y))

		for heart in self.hearts:
			win.blit(heart.img,(heart.x, heart.y))

		for krab in self.krabs:
			if krab.right == True:
				win.blit(krab.moveRight,(krab.x, krab.y))
				krab.x += krab.vel
			else:
				win.blit(krab.moveLeft,(krab.x, krab.y))
				krab.x -= krab.vel

	def collision(self):
		global recoverTime
		global heartNum

		for sand in self.sands:
			if player.player.colliderect(sand.object):
				player.y = pGround
				player.jumpCount = 10
		for coral in self.corals:
			if player.player.colliderect(coral.object):
				player.jumpCount = 10
				player.y = ground - 190

		for coralB in self.coralsB:
			if player.player.colliderect(coralB.object):
				player.jumpCount = 10
				player.y = ground - 340

		for coralC in self.coralsC:
			if player.player.colliderect(coralC.object):
				player.jumpCount = 10
				player.y = ground - 495

		for hole in self.holes:
			if player.player.colliderect(hole.object):
				if hole == hole1:
					container.moveLeft(900)

		krab2.collided_coral(coral9, coral5)
		krab1.collided_coral(coral2, coral3)

		for krab in self.krabs:
			if player.player.colliderect(krab.object) and heartNum >= 0:
				if recoverTime == 0 or recoverTime >= 100:
					del container.hearts[heartNum]
					heartNum -= 1
					recoverTime = 0
					sound1.play()

				player.dying = True

			else:
				player.dying = False
				recoverTime += 1

		if heartNum < 0:
			player.dead = True
			player.dying = False

	def update(self):
		for item in self.all:
			item.object = pygame.Rect(item.x, item.y, item.width, item.height)

def redraw():
	win.blit(bg, (0,0))
	container.draw(win)
	container.update()
	player.draw(win)
	pygame.display.update()

player = Player('FishR.png', 'FishL.png', 'FishS.png', 'FishD.png', 'FishX.png', 275, pGround, 150, 200)

coral1 = Objects(425, ground, 150, 150, image='CoralPink.png')
coral2 = Objects(775, ground, 150, 150, image='CoralRed.png')
coral3 = Objects(1725, ground, 150, 150, image='CoralBlue.png')
coral4 = Objects(2350, ground, 150, 150, image='CoralYellow.png')
coral5 = Objects(4000, ground, 150, 150, image='CoralYellow.png', )
coral6 = Objects(5150, ground, 150, 150, image='CoralPink.png', )
coral7 = Objects(5300, ground, 150, 150, image='CoralBlue.png', )
coral8 = Objects(5450, ground, 150, 150, image='CoralPurple.png', )
coral9 = Objects(2500, ground, 150, 150, image='CoralGreen.png', )

coralB1 = Objects(2500, ground-150, 150, 150, image='CoralPurple.png')
coralB2 = Objects(2650, ground-150, 150, 150, image='CoralPink.png')
coralB3 = Objects(5300, ground-150, 150, 150, image='CoralGreen.png')
coralB4 = Objects(5450, ground-150, 150, 150, image='CoralYellow.png')

coralC1 = Objects(5450, ground-300, 150, 150, image='CoralRed.png')

krab1 = Objects(1000, ground, 225, 150, 'KrabR.png', 'KrabL.png')
krab2 = Objects(3000, ground, 225, 150, 'KrabR.png', 'KrabL.png')

hole1 = Objects(600, ground+149, 150, 50, image='Hole1.png')
hole2 = Objects(1500, ground+149, 150, 50, image='Hole1.png')

heart1 = Objects(1700,0,50,50,image='Heart1.png')
heart2 = Objects(1600,0,50,50,image='Heart1.png')
heart3 = Objects(1500,0,50,50,image='Heart1.png')

sand1 = Objects(0, 750, 150, 150,image='Sand.png')
sand2 = Objects(150, 750, 150, 150,image='Sand.png')
sand3 = Objects(300, 750, 150, 150,image='Sand.png')
sand4 = Objects(450, 750, 150, 150,image='Sand.png')
sand5 = Objects(600, 750, 150, 150,image='Sand.png')
sand6 = Objects(750, 750, 150, 150,image='Sand.png')
sand7 = Objects(900, 750, 150, 150,image='Sand.png')
sand8 = Objects(1050, 750, 150, 150,image='Sand.png')
sand9 = Objects(1200, 750, 150, 150,image='Sand.png')
sand10 = Objects(1350, 750, 150, 150,image='Sand.png')
sand11 = Objects(1500, 750, 150, 150,image='Sand.png')
sand12 = Objects(1650, 750, 150, 150,image='Sand.png')
sand13 = Objects(1800, 750, 150, 150,image='Sand.png')

container = Container()

while True:
	clock.tick(50)
	container.collision()
	for event in pygame.event.get():
		if event.type == pygame.QUIT:
			pygame.quit()
			sys.exit()

	if not player.dead:
		keys = pygame.key.get_pressed()

		if keys[pygame.K_LEFT] and player.x > player.vel:
			player.left = True
			player.right = False
			player.standing = False
			container.moveRight(vel)

		elif keys[pygame.K_RIGHT] and player.x < WinWidth - player.width - player.vel:
			player.right = True
			player.left = False
			player.standing = False
			container.moveLeft(vel)

		else:
			player.standing = True
			player.right = False
			player.left = False

		if player.isJump:
			if player.jumpCount >= -10:
				neg = 1
				if player.jumpCount < 0:
					neg = -1
				player.y -= (player.jumpCount ** 2) * 0.5 * neg
				player.jumpCount -= 1

			else:
				for coral in container.corals + container.coralsB + container.coralsC:
					if not (player.player.bottom <= coral.object.top and \
						((player.player.left >= coral.object.left and player.player.left <= coral.object.right) or \
						(player.player.right <= coral.object.right and player.player.right >= coral.object.left))):
						player.y = coral.object.top
						print ('over a coral')
						break
						pass
				else:
					player.y = pGround
				player.jumpCount = 10
	redraw()
