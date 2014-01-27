import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;

public class PaternChange {
	
	static DifferentialPilot pilot;
	static int currentPattern = 0;

	public static void main(String[] args) {
		PaternChange viktor = new PaternChange();

		viktor.pilot = new DifferentialPilot(2.1f, 4.6f, Motor.B, Motor.C,
				false);

		Button.LEFT.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
				currentPattern = 1;
			}

			public void buttonReleased(Button b) {

			}
		});

		Button.RIGHT.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
				currentPattern = 2;
			}

			public void buttonReleased(Button b) {

			}
		});

		Button.ESCAPE.addButtonListener(new ButtonListener() {
			public void buttonPressed(Button b) {
				System.exit(0);
			}

			public void buttonReleased(Button b) {

			}
		});

		while (true) {
			if (currentPattern == 1) {
				runPatern1();
			} else if (currentPattern == 2) {
				runPatern2();
			}
		}
	}

	public static void runPatern1() {
		System.out.println("1");
		pilot.travel(5);
		pilot.rotate(90);
	}

	public static void runPatern2() {
		System.out.println("2");
		pilot.travel(5);
		pilot.rotate(-90);

	}

}
