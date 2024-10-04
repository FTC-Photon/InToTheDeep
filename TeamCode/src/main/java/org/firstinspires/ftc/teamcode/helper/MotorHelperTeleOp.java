package org.firstinspires.ftc.teamcode.helper;

import com.qualcomm.robotcore.hardware.DcMotor;

public class MotorHelperTeleOp extends MotorHelper{
    /**
     * Sets the power for the robot's motors based on joystick inputs.
     * Throws IllegalStateException if any motor is not initialized (null).
     *
     * @param left_stick_y The Y value from the left joystick (for forward/backward movement).
     * @param left_stick_x The X value from the left joystick (for strafing left/right).
     * @param right_stick_x The X value from the right joystick (for pivot/rotation control).
     */
    public void omniDriveGamePadStick(double left_stick_y, double left_stick_x, double right_stick_x) {
        double vertical = 0.6 * -left_stick_y;
        double horizontal = 0.6 * left_stick_x;
        double pivot = 0.6 * right_stick_x;

        // Retrieve motors from the dictionary
        DcMotor br = motors.get("br");
        DcMotor fr = motors.get("fr");
        DcMotor bl = motors.get("bl");
        DcMotor fl = motors.get("fl");

        // Throw exception if any motor is null
        if (br == null || fr == null || bl == null || fl == null) {
            throw new IllegalStateException("One or more motors are not initialized.");
        }

        br.setPower(-pivot + (-vertical + horizontal));
        fr.setPower(-pivot - vertical - horizontal);
        bl.setPower(pivot - vertical - horizontal);
        fl.setPower(pivot - vertical + horizontal);
    }
}
