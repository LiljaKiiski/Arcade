import java.util.ArrayList;

public class Snake {
	public ArrayList <Coordinate> blocks = new ArrayList<>(); //All blocks
	public boolean alive = false; //Snakes life
	public char direction; //Direction head is moving

	public final int gridSize; //Size of grid

	public Snake(int gridSize){
		this.gridSize = gridSize;
		blocks.add(new Coordinate(1, 7));
	}

	//Moves snake
	public void move(){
                int posX = blocks.get(0).posX;
                int posY = blocks.get(0).posY;

		switch (direction){
                        case 'u':
				blocks.add(0, new Coordinate(posX, posY-1));
                                break;

                        case 'd':
                        	blocks.add(0, new Coordinate(posX, posY+1));
                                break;

                        case 'l':
                                blocks.add(0, new Coordinate(posX-1, posY));
                                break;

                        case 'r':
                                blocks.add(0, new Coordinate(posX+1, posY));
                                break;
                }
		blocks.remove(blocks.size()-1);
	}

	//Sets direction of snake head
	public void setDirection(char direction){
		//Set direction
		if (!(this.direction == 'u' && direction == 'd') &&
			!(this.direction == 'd' && direction == 'u') &&
			!(this.direction == 'l' && direction == 'r') &&
			!(this.direction == 'r' && direction == 'l')){

			this.direction = direction;
		}
	}

	//Add block to snake chain
	public void addBlock(){
		Coordinate block = blocks.get(0);
		int lx = blocks.get(0).posX;
                int ly = blocks.get(0).posY;

                switch (direction){
                	case 'u':
                       		blocks.add(new Coordinate(lx, ly-1));
				direction = 'u';
				break;

                        case 'd':
                               	blocks.add(new Coordinate(lx, ly+1));
				direction = 'd';
                               	break;
                              
                       	case 'l':
                               	blocks.add(new Coordinate(lx-1, ly));
				direction = 'l';
                               	break;
                               
                        case 'r':
                        	blocks.add(new Coordinate(lx+1, ly));
				direction = 'r';
                               	break;
           	}
	}
}
