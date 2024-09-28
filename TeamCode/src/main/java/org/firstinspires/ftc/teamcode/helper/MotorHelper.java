package org.firstinspires.ftc.teamcode.helper;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for managing DcMotors in an FTC robot using Dictionary.
 */
public class MotorHelper {
    Dictionary<String, DcMotor> motors;
    double defaultPower = 0.4;

    /**
     * Constructor initializes the motors dictionary.
     */
    public MotorHelper() {
        motors = new Hashtable<>();
    }

    /**
     * Initializes the wheels by assigning DcMotor instances from the hardware map to their respective names.
     *
     * @param hardwareMap The hardware map from which to retrieve motors
     */
    public void initializeWheels(HardwareMap hardwareMap) {
        motors.put("br", hardwareMap.get(DcMotor.class, "br"));
        motors.put("bl", hardwareMap.get(DcMotor.class, "bl"));
        motors.put("fl", hardwareMap.get(DcMotor.class, "fl"));
        motors.put("fr", hardwareMap.get(DcMotor.class, "fr"));
    }

    /**
     * Initializes motors using the HardwareMap.
     *
     * @param hardwareMap The hardware map containing the motor configurations
     * @param motorNames Array of motor names to initialize
     */
    public void initializeHardwareMap(HardwareMap hardwareMap, String[] motorNames) {
        for (String name : motorNames) {
            DcMotor motor = hardwareMap.get(DcMotor.class, name);
            if (motor != null) {
                motors.put(name, motor);
            }
        }
    }

    /**
     * Sets the power for a single motor by name
     *
     * @param name The name of the motor
     * @param power The power level to set for the motor (range from -1.0 to 1.0)
     */
    public void setPower(String name, double power) {
        DcMotor motor = motors.get(name);
        if (motor != null) {
            motor.setPower(power);
        }
    }

    /**
     * Sets the power for a single motor by name using the default power
     *
     * @param name The name of the motor
     */
    public void setPower(String name) {
        DcMotor motor = motors.get(name);
        if (motor != null) {
            motor.setPower(defaultPower);
        }
    }

    /**
     * Sets the power for multiple motors by name using a specified power level
     *
     * @param names Array of motor names.
     * @param power The power level to set for the motors (range from -1.0 to 1.0)
     */
    public void setPower(String[] names, double power) {
        for (String name : names) {
            DcMotor motor = motors.get(name);
            if (motor != null) {
                motor.setPower(power);
            }
        }
    }

    /**
     * Sets the power for multiple motors by name using the default power
     *
     * @param names Array of motor names
     */
    public void setPower(String[] names) {
        for (String name : names) {
            DcMotor motor = motors.get(name);
            if (motor != null) {
                motor.setPower(defaultPower);
            }
        }
    }

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

    /**
     * Optional: Method to update the default power.
     *
     * @param power The new default power to be set (range from -1.0 to 1.0).
     */
    public void setDefaultPower(double power) {
        this.defaultPower = power;
    }

    /**
     * Retrieves an array of DcMotor objects from an array of motor names.
     *
     * @param motorNames Array of motor names as strings
     * @return An array of DcMotor objects corresponding to the provided names
     */
    public DcMotor[] getMotorsFromNames(String[] motorNames) {
        List<DcMotor> motorList = new ArrayList<>();

        for (String name : motorNames) {
            DcMotor motor = motors.get(name);
            if (motor != null) {
                motorList.add(motor);
            }
        }

        return motorList.toArray(new DcMotor[0]); // Convert to array and return
    }

    /**
     * Reverses the direction of a specific motor by name.
     *
     * @param motorName The name of the motor to reverse
     */
    public void reverseMotorDirection(String motorName) {
        DcMotor motor = motors.get(motorName);
        if (motor != null) {
            motor.setDirection(DcMotor.Direction.REVERSE);
        }
    }

    /**
     * Sets the run mode of a specific motor by name.
     *
     * @param motorName The name of the motor to set the mode
     * @param mode The desired run mode
     */
    public void setMotorMode(String motorName, DcMotor.RunMode mode) {
        DcMotor motor = motors.get(motorName);
        if (motor != null) {
            motor.setMode(mode);
        }
    }

    /**
     * Retrieves a motor instance by its name.
     *
     * @param name The name of the motor to retrieve
     * @return The DcMotor corresponding to the provided name
     */
    public DcMotor getMotor(String name) {
        return motors.get(name);
    }
}
