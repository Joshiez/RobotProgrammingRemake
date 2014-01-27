import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.SensorPortListener;
import lejos.nxt.TouchSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.Delay;

public class BumpAndTouch2 {

	static DifferentialPilot pilot;

	TouchSensor bump = new TouchSensor(SensorPort.S1);

	public BumpAndTouch2() {
		pilot = new DifferentialPilot(2.1f, 4.6f, Motor.B, Motor.C, false);
	}

	public static void main(String[] args) {
		Button.waitForAnyPress();

		BumpAndTouch2 viktor = new BumpAndTouch2();

		viktor.run();

		Button.ESCAPE.waitForPressAndRelease();

	}

	public void run() {
		
		Delay.msDelay(1000);
		
		pilot.travel(1000000, true);
		
		SensorPort.S1.addSensorPortListener(new SensorPortListener() {

			@Override
			public void stateChanged(SensorPort aSource, int aOldValue,
					int aNewValue) {
				if (aNewValue == 1023 && aOldValue > 180) {
					pilot.stop();
					pilot.travel(-5);
					pilot.rotate(90);
					pilot.travel(100000000, true);
				}
				
			}
			
			
		});

	}

}
