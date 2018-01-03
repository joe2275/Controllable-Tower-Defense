package Control;
import java.util.ArrayList;
import javax.swing.*;

// ������ ǥ���ϴ� Ŭ����
class Area {
	// Ÿ���� �����ϴ��� ǥ��
	private boolean istower = false;
	// ������������ �����Ǿ� �ִ��� ǥ��
	private boolean isattack = false;
	// road(��)���� �ƴ��� ǥ��
	private boolean isroad = false;
	// start ������ end ���� ǥ��
	private boolean isstart = false;
	private boolean isend = false;
	// ������ ������ȯ �÷��׸� ǥ��
	private int direction = -1;
	
	// Ÿ������ ��
	final public static int TOWER0 = 0;
	final public static int TOWER1 = 1;
	final public static int TOWER2 = 2;
	final public static int TOWER3 = 3;
	final public static int TOWER4 = 4;
	final public static int TOWER5 = 5;
	
	public Area() {

	}
	
	public boolean isTower() {
		return istower;
	}
	public boolean isAttack() {
		return isattack;
	}
	public boolean isRoad() {
		return isroad;
	}
	public boolean isStart() {
		return isstart;
	}
	public boolean isEnd() {
		return isend;
	}
	public int getDirection() {
		return direction;
	}
	
	// Ÿ���� ���縦 �����ϴ� �Լ�
	public void setTower(boolean exist) {
		istower = exist;
	}
	// ��� �����ϴ� �Լ�
	public void setRoad(boolean exist) {
		isroad = exist;
	}
	// start �������� �����ϴ� �Լ�
	public void setStart(boolean exist) {
		isstart = exist;
	}
	// end �������� ����
	public void setEnd(boolean exist) {
		isend = exist;
	}
	
	// ������ ��θ� �����ϴ� �Լ�, Monster Ŭ������ static ������ �̿�
	public void setDirection(int _direction) {
		direction = _direction;
	}
}

//2������ �� 8 x 8�� ������ 2���� �迭�� ǥ�� 
class Map {
	protected Area[][] map;
	final public static int MAP_NUM = 10;
	final public static int SIZE = 8;
	final public static int MAP_WIDTH = 640, MAP_HEIGHT = 640;
	final public static int IMG_WIDTH = MAP_WIDTH/SIZE, IMG_HEIGHT = MAP_HEIGHT/SIZE;
	
	final public static ImageIcon ROAD = new ImageIcon("Image/Map/road.png");
	protected JLabel mapLabel;
	
	// start, end������ �����ϴ� Point ��ü
	protected Point start = null;
	protected Point end = null;
	
	public Map(JLabel map_label) {
		mapLabel = map_label;
		// ������ 2���� �迭 map�� �ʱ�ȭ �ϴ� �۾�
		map = new Area[SIZE][];
		for(int i=0; i<SIZE; i++) {
			map[i] = new Area[SIZE];
			for(int j=0; j<SIZE; j++) {
				map[i][j] = new Area();
			}
		}
	}
	
	
	public Area[][] getMap() {
		return map;
	}
	// start ��ġ�� ��ȯ�ϴ� �Լ�
	public Point getStartPosition() {
		return start;
	}
	// end ������ ��ȯ�ϴ� �Լ�
	public Point getEndPosition() {
		return end;
	}
	
	// �迭 ��ġ�� �����ϸ� �� �̹����� �������ִ� �Լ�
	public void setRoadImg(int x, int y) {
		JLabel roadLabel = new JLabel(ROAD);
		roadLabel.setBounds(x*IMG_WIDTH, y*IMG_HEIGHT, IMG_WIDTH, IMG_HEIGHT);
		mapLabel.add(roadLabel);
	}
}

class MapArray1 extends Map {
	public MapArray1(JLabel map_label) {
		super(map_label);
		
		// 1�ܰ� �� ����
		// ���� ���� ����
		map[0][0].setStart(true);
		start = new Point(0, 0);
		map[0][1].setEnd(true);
		end = new Point(0, 1);
		
		// �� �̹��� ����, �� ǥ��
		for(int i=0; i<SIZE; i++) {
			if(i==0 || i == SIZE-1) {
				for(int j=0; j<SIZE; j++) {
					map[i][j].setRoad(true);
					//setRoadImg(i, j);
				}
			}
			else {
				map[i][0].setRoad(true);
				//setRoadImg(i, 0);
				map[i][SIZE-1].setRoad(true);
				//setRoadImg(i, SIZE-1);
			}
		}
		
		// ���� ��� ����
		for(int i=0; i<SIZE; i++) {
			map[i][0].setDirection(Monster.DOWN);
			map[i][SIZE-1].setDirection(Monster.UP);
		}
		for(int i=1; i<SIZE; i++) {
			map[0][i].setDirection(Monster.LEFT);
			map[SIZE-1][SIZE-1-i].setDirection(Monster.RIGHT);
		}
	}
}

class MapArray2 extends Map {
	public MapArray2(JLabel map_label) {
		super(map_label);
		
		// 2�ܰ� �� ����
		// ���� ���� ����
		// ���� ���� ����
		map[0][0].setStart(true);
		start.setX(0);
		start.setY(0);
		map[0][1].setEnd(true);
		end.setX(0);
		end.setY(1);
				
		// �� �̹��� ����, �� ǥ��
		for(int i=0; i<SIZE; i++) {
			if(i==0 || i == SIZE-1) {
				for(int j=0; j<SIZE; j++) {
					map[i][j].setRoad(true);
					setRoadImg(i, j);
				}
			}
			else {
				map[i][0].setRoad(true);
				setRoadImg(i, 0);
				map[i][SIZE-1].setRoad(true);
				setRoadImg(i, SIZE-1);
			}
		}
	
		// ���� ��� ����
		map[0][0].setDirection(Monster.DOWN);
		map[SIZE-1][0].setDirection(Monster.RIGHT);
		map[SIZE-1][SIZE-1].setDirection(Monster.UP);
		map[0][SIZE-1].setDirection(Monster.LEFT);
	}
}

class MapArray3 extends Map {
	public MapArray3(JLabel map_label) {
		super(map_label);
		
		// 3�ܰ� �� ����
		// ���� ���� ����
		map[0][0].setStart(true);
		start.setX(0);
		start.setY(0);
		map[0][1].setEnd(true);
		end.setX(0);
		end.setY(1);
				
		// �� �̹��� ����, �� ǥ��
		for(int i=0; i<SIZE; i++) {
			if(i==0 || i == SIZE-1) {
				for(int j=0; j<SIZE; j++) {
					map[i][j].setRoad(true);
					setRoadImg(i, j);
				}
			}
			else {
				map[i][0].setRoad(true);
				setRoadImg(i, 0);
				map[i][SIZE-1].setRoad(true);
				setRoadImg(i, SIZE-1);
			}
		}
		
		// ���� ��� ����
		map[0][0].setDirection(Monster.DOWN);
		map[SIZE-1][0].setDirection(Monster.RIGHT);
		map[SIZE-1][SIZE-1].setDirection(Monster.UP);
		map[0][SIZE-1].setDirection(Monster.LEFT);
	}
}

class MapArray4 extends Map {
	public MapArray4(JLabel map_label) {
		super(map_label);
		
		// 4�ܰ� �� ����
		// ���� ���� ����
		map[0][0].setStart(true);
		start.setX(0);
		start.setY(0);
		map[0][1].setEnd(true);
		end.setX(0);
		end.setY(1);
				
		// �� �̹��� ����, �� ǥ��
		for(int i=0; i<SIZE; i++) {
			if(i==0 || i == SIZE-1) {
				for(int j=0; j<SIZE; j++) {
					map[i][j].setRoad(true);
					setRoadImg(i, j);
				}
			}
			else {
				map[i][0].setRoad(true);
				setRoadImg(i, 0);
				map[i][SIZE-1].setRoad(true);
				setRoadImg(i, SIZE-1);
			}
		}
		
		// ���� ��� ����
		map[0][0].setDirection(Monster.DOWN);
		map[SIZE-1][0].setDirection(Monster.RIGHT);
		map[SIZE-1][SIZE-1].setDirection(Monster.UP);
		map[0][SIZE-1].setDirection(Monster.LEFT);
	}
}

class MapArray5 extends Map {
	public MapArray5(JLabel map_label) {
		super(map_label);
		
		// 5�ܰ� �� ����
		// ���� ���� ����
		map[0][0].setStart(true);
		start.setX(0);
		start.setY(0);
		map[0][1].setEnd(true);
		end.setX(0);
		end.setY(1);
				
		// �� �̹��� ����, �� ǥ��
		for(int i=0; i<SIZE; i++) {
			if(i==0 || i == SIZE-1) {
				for(int j=0; j<SIZE; j++) {
					map[i][j].setRoad(true);
					setRoadImg(i, j);
				}
			}
			else {
				map[i][0].setRoad(true);
				setRoadImg(i, 0);
				map[i][SIZE-1].setRoad(true);
				setRoadImg(i, SIZE-1);
			}
		}
		
		// ���� ��� ����
		map[0][0].setDirection(Monster.DOWN);
		map[SIZE-1][0].setDirection(Monster.RIGHT);
		map[SIZE-1][SIZE-1].setDirection(Monster.UP);
		map[0][SIZE-1].setDirection(Monster.LEFT);
	}
}

class MapArray6 extends Map {
	public MapArray6(JLabel map_label) {
		super(map_label);
		
		// 6�ܰ� �� ����
		// ���� ���� ����
		map[0][0].setStart(true);
		start.setX(0);
		start.setY(0);
		map[0][1].setEnd(true);
		end.setX(0);
		end.setY(1);
		
		// �� �̹��� ����, �� ǥ��
		for(int i=0; i<SIZE; i++) {
			if(i==0 || i == SIZE-1) {
				for(int j=0; j<SIZE; j++) {
					map[i][j].setRoad(true);
					setRoadImg(i, j);
				}
			}
			else {
				map[i][0].setRoad(true);
				setRoadImg(i, 0);
				map[i][SIZE-1].setRoad(true);
				setRoadImg(i, SIZE-1);
			}
		}
		
		// ���� ��� ����
		map[0][0].setDirection(Monster.DOWN);
		map[SIZE-1][0].setDirection(Monster.RIGHT);
		map[SIZE-1][SIZE-1].setDirection(Monster.UP);
		map[0][SIZE-1].setDirection(Monster.LEFT);
	}
}

class MapArray7 extends Map {
	public MapArray7(JLabel map_label) {
		super(map_label);
		
		// 7�ܰ� �� ����
		// ���� ���� ����
		map[0][0].setStart(true);
		start.setX(0);
		start.setY(0);
		map[0][1].setEnd(true);
		end.setX(0);
		end.setY(1);
				
		// �� �̹��� ����, �� ǥ��
		for(int i=0; i<SIZE; i++) {
			if(i==0 || i == SIZE-1) {
				for(int j=0; j<SIZE; j++) {
					map[i][j].setRoad(true);
					setRoadImg(i, j);
				}
			}
			else {
				map[i][0].setRoad(true);
				setRoadImg(i, 0);
				map[i][SIZE-1].setRoad(true);
				setRoadImg(i, SIZE-1);
			}
		}
		
		// ���� ��� ����
		map[0][0].setDirection(Monster.DOWN);
		map[SIZE-1][0].setDirection(Monster.RIGHT);
		map[SIZE-1][SIZE-1].setDirection(Monster.UP);
		map[0][SIZE-1].setDirection(Monster.LEFT);
	}
}

class MapArray8 extends Map {
	public MapArray8(JLabel map_label) {
		super(map_label);
		
		// 8�ܰ� �� ����
		// ���� ���� ����
		map[0][0].setStart(true);
		start.setX(0);
		start.setY(0);
		map[0][1].setEnd(true);
		end.setX(0);
		end.setY(1);
		
		// �� �̹��� ����, �� ǥ��
		for(int i=0; i<SIZE; i++) {
			if(i==0 || i == SIZE-1) {
				for(int j=0; j<SIZE; j++) {
					map[i][j].setRoad(true);
					setRoadImg(i, j);
				}
			}
			else {
				map[i][0].setRoad(true);
				setRoadImg(i, 0);
				map[i][SIZE-1].setRoad(true);
				setRoadImg(i, SIZE-1);
			}
		}
		
		// ���� ��� ����
		map[0][0].setDirection(Monster.DOWN);
		map[SIZE-1][0].setDirection(Monster.RIGHT);
		map[SIZE-1][SIZE-1].setDirection(Monster.UP);
		map[0][SIZE-1].setDirection(Monster.LEFT);
	}
}

class MapArray9 extends Map {
	public MapArray9(JLabel map_label) {
		super(map_label);
		
		// 9�ܰ� �� ����
		// ���� ���� ����
		map[0][0].setStart(true);
		start.setX(0);
		start.setY(0);
		map[0][1].setEnd(true);
		end.setX(0);
		end.setY(1);
		
		// �� �̹��� ����, �� ǥ��
		for(int i=0; i<SIZE; i++) {
			if(i==0 || i == SIZE-1) {
				for(int j=0; j<SIZE; j++) {
					map[i][j].setRoad(true);
					setRoadImg(i, j);
				}
			}
			else {
				map[i][0].setRoad(true);
				setRoadImg(i, 0);
				map[i][SIZE-1].setRoad(true);
				setRoadImg(i, SIZE-1);
			}
		}
		
		// ���� ��� ����
		map[0][0].setDirection(Monster.DOWN);
		map[SIZE-1][0].setDirection(Monster.RIGHT);
		map[SIZE-1][SIZE-1].setDirection(Monster.UP);
		map[0][SIZE-1].setDirection(Monster.LEFT);
	}
}

class MapArray10 extends Map {
	public MapArray10(JLabel map_label) {
		super(map_label);
		
		// 10�ܰ� �� ����
		// ���� ���� ����
		map[0][0].setStart(true);
		start.setX(0);
		start.setY(0);
		map[0][1].setEnd(true);
		end.setX(0);
		end.setY(1);
		
		// �� �̹��� ����, �� ǥ��
		for(int i=0; i<SIZE; i++) {
			if(i==0 || i == SIZE-1) {
				for(int j=0; j<SIZE; j++) {
					map[i][j].setRoad(true);
					setRoadImg(i, j);
				}
			}
			else {
				map[i][0].setRoad(true);
				setRoadImg(i, 0);
				map[i][SIZE-1].setRoad(true);
				setRoadImg(i, SIZE-1);
			}
		}
		
		// ���� ��� ����
		map[0][0].setDirection(Monster.DOWN);
		map[SIZE-1][0].setDirection(Monster.RIGHT);
		map[SIZE-1][SIZE-1].setDirection(Monster.UP);
		map[0][SIZE-1].setDirection(Monster.LEFT);
	}
}