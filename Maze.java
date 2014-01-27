import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class Maze {

	static DifferentialPilot pilot;
	TouchSensor bump = new TouchSensor(SensorPort.S1);
	static UltrasonicSensor sonar = new UltrasonicSensor(SensorPort.S4);
	static int[] scanArray = new int[21];
	static int rotateValue = 21;
	static int scanCount = 0;
	static int largestScan;
	static int selfRightValue; 
	static int distance;
	static int pastDirection = 0;
	static int newDirection = 0;
	static boolean backTrack;

	public static void main(String[] args) {
		Maze traveler = new Maze();
		Maze.pilot = new DifferentialPilot(2.1f, 4.6f, Motor.B, Motor.C, false);

		Button.waitForAnyPress();

		while (!Button.ESCAPE.isDown()) {
			pilot.setTravelSpeed(7);
			traveler.go();
		}
	}

	public void go() {
		Delay.msDelay(1000);
		
		pilot.travel(10000000, true);

		while (pilot.isMoving()) {
			if (bump.isPressed()) {
				pilot.stop();
				pilot.travel(-3);
				detectPastDirection();
				selfRight();
				scanArea();
				getLargestScan();
				detectNewDirection();
				backTrack = detectBackTrack();
				if(backTrack){
					pilot.rotate(-90);
					selfRightValue = 90;
				}
				else{
					direction();
				}
				scanReset();
				pilot.travel(5, true);
				while (pilot.isMoving()){
					if (bump.isPressed()){
						pilot.stop();
						pilot.travel(-3);
						pilot.rotate(90);
						selfRightValue = -90;
					}
				}
				
			}
		}
	}
	
	public static void detectPastDirection(){
		if((rotateValue >= 4) && (rotateValue <= 9)){
			pastDirection = 1; //Right
		}
		else if((rotateValue >= 10) && (rotateValue <= 14)){ 
			pastDirection = 2; //Left
		}
		else if(rotateValue == 21){
			pastDirection = 3; //Forwards
		}
		else{
			pastDirection = 4; // Doesnt Matter
		}
	}
	
	public static void detectNewDirection(){
		if((rotateValue >= 4) && (rotateValue <= 9)){
			newDirection = 1; //Right
		}
		else if((rotateValue >= 10) && (rotateValue <= 14)){ 
			newDirection = 2; //Left
		}
		else if(rotateValue == 21){
			newDirection = 3; //Forwards
		}
		else{
			newDirection = 4; // Doesnt Matter
		}
		
	}
	
	public static boolean detectBackTrack(){
		if((newDirection == 1) && (pastDirection == 2)){
			return true;
		}
		else if((newDirection == 2) && (pastDirection == 1)){
			return true;
		}
		return false;
	}

	public static void selfRight() {
		pilot.rotate(selfRightValue);
	}

	public static void direction() {
		if (rotateValue == 0) {
			pilot.rotate(10);
			selfRightValue = -10;
		}
		if (rotateValue == 1) {
			pilot.rotate(20);
			selfRightValue = -20;
		}
		if (rotateValue == 2) {
			pilot.rotate(30);
			selfRightValue = -30;
		}
		if (rotateValue == 3) {
			pilot.rotate(40);
			selfRightValue = -40;
		}
		if (rotateValue == 4) {
			pilot.rotate(50);
			selfRightValue = -50;
		}
		if (rotateValue == 5) {
			pilot.rotate(60);
			selfRightValue = -60;
		}
		if (rotateValue == 6) {
			pilot.rotate(70);
			selfRightValue = -70;
		}
		if (rotateValue == 7) {
			pilot.rotate(80);
			selfRightValue = -80;
		}
		if (rotateValue == 8) {
			pilot.rotate(90);
			selfRightValue = -90;
		}
		if (rotateValue == 9) {
			pilot.rotate(100);
			selfRightValue = -100;
		}
		if (rotateValue == 10) {
			pilot.rotate(-100);
			selfRightValue = 100;
		}
		if (rotateValue == 11) {
			pilot.rotate(-90);
			selfRightValue = 90;
		}
		if (rotateValue == 12) {
			pilot.rotate(-80);
			selfRightValue = 80;
		}
		if (rotateValue == 13) {
			pilot.rotate(-70);
			selfRightValue = 70;
		}
		if (rotateValue == 14) {
			pilot.rotate(-60);
			selfRightValue = 60;
		}
		if (rotateValue == 15) {
			pilot.rotate(-50);
			selfRightValue = 50;
		}
		if (rotateValue == 16) {
			pilot.rotate(-40);
			selfRightValue = 40;
		}
		if (rotateValue == 17) {
			pilot.rotate(-30);
			selfRightValue = 30;
		}
		if (rotateValue == 18) {
			pilot.rotate(-20);
			selfRightValue = 20;
		}
		if (rotateValue == 19) {
			pilot.rotate(-10);
			selfRightValue = 10;
		}
	}

	public static void scanArea() {
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(-200);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
		Motor.A.rotate(10);
		scan();
	}

	public static void scan() {
		scanArray[scanCount] = sonar.getDistance();
		System.out.println(sonar.getDistance());
		scanCount++;
	}

	public static void getLargestScan() {
		largestScan = 0;
		for (int i = 0; i < 21; i++) {
			if (scanArray[i] > largestScan) {
				largestScan = scanArray[i];
				rotateValue = i;
			}
		}
		Delay.msDelay(1000);
	}

	public static void scanReset() {
		for (int i = 0; i < 21; i++) {
			scanArray[i] = 0;
		}
		scanCount = 0;
	}

}