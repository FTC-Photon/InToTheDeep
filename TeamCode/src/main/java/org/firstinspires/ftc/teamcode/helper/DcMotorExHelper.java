package org.firstinspires.ftc.teamcode.helper;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for managing DcMotorEx motors in an FTC robot using Dictionary.
 */
public class DcMotorExHelper {
    Dictionary<String, DcMotorEx> motors;
    protected double defaultPower = 0.4;

    /**
     * Motors for movement:
     * {"br","bl","fl","fr"}
     * DO NOT CHANGE METHODS DEPEND ON ORDER
     * @see org.firstinspires.ftc.teamcode.helper moveToGoalStrafe()
     */
    public final String[] MOVEMENT_MOTORS = {"br","bl","fl","fr"};

    /**
     * Constructor initializes the motors dictionary.
     */
    public DcMotorExHelper() {
        motors = new Hashtable<>();
    }

    /**
     * Initializes the wheels by assigning DcMotorEx instances from the hardware map to their respective names.
     *
     * @param hardwareMap The hardware map from which to retrieve motors
     */
    public void initializeWheels(HardwareMap hardwareMap) {
        motors.put("br", hardwareMap.get(DcMotorEx.class, "br"));
        motors.put("bl", hardwareMap.get(DcMotorEx.class, "bl"));
        motors.put("fl", hardwareMap.get(DcMotorEx.class, "fl"));
        motors.put("fr", hardwareMap.get(DcMotorEx.class, "fr"));
    }

    /**
     * Initializes DcMotorEx motors using the HardwareMap.
     *
     * @param hardwareMap The hardware map containing the motor configurations
     * @param motorNames Array of motor names to initialize
     */
    public void initializeHardwareMap(HardwareMap hardwareMap, String[] motorNames) {
        for (String name : motorNames) {
            DcMotorEx motor = hardwareMap.get(DcMotorEx.class, name);
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
        DcMotorEx motor = motors.get(name);
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
        DcMotorEx motor = motors.get(name);
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
            DcMotorEx motor = motors.get(name);
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
            DcMotorEx motor = motors.get(name);
            if (motor != null) {
                motor.setPower(defaultPower);
            }
        }
    }

    /**
     * Moves the motor to a specific target position.
     *
     * @param name      the motor name from the map
     * @param goal      target position in ticks
     * @param power     power at which to move the motor
     * @param telemetry used for displaying telemetry information
     */
    public void moveToGoal(String name, int goal, double power, Telemetry telemetry) {
        DcMotorEx motor = motors.get(name);
        if (motor == null) {
            throw new IllegalStateException("Motor " + name + " is not initialized");
        }

        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(goal);
        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        motor.setPower(power);

        while (motor.isBusy()) {
            telemetry.addData(name + " Target", goal);
            telemetry.addData(name + " Current", motor.getCurrentPosition());
            telemetry.update();
        }

        motor.setPower(0); // stop motor
        telemetry.addData(name + " Final", motor.getCurrentPosition());
        telemetry.update();
    }

    /**
     * Moves the motor to a specific velocity.
     *
     * @param name      the motor name from the map
     * @param velocity  the desired velocity in ticks per second
     * @param telemetry used for displaying telemetry information
     */
    public void moveToVelocity(String name, double velocity, Telemetry telemetry) {
        DcMotorEx motor = motors.get(name);
        if (motor == null) {
            throw new IllegalStateException("Motor " + name + " is not initialized");
        }

        motor.setVelocity(velocity);
        telemetry.addData(name + " Velocity", motor.getVelocity());
        telemetry.update();
    }

    /**
     * Moves the motor to a specific angular velocity.
     *
     * @param name       the motor name from the map
     * @param velocity   the desired angular velocity
     * @param angleUnit  the angle unit (radians or degrees)
     * @param telemetry  used for displaying telemetry information
     */
    public void moveToAngularVelocity(String name, double velocity, AngleUnit angleUnit, Telemetry telemetry) {
        DcMotorEx motor = motors.get(name);
        if (motor == null) {
            throw new IllegalStateException("Motor " + name + " is not initialized");
        }

        motor.setVelocity(velocity, angleUnit);
        telemetry.addData(name + " Angular Velocity", motor.getVelocity());
        telemetry.update();
    }

    /**
     * Sets the run mode of a specific motor by name.
     *
     * @param motorName The name of the motor to set the mode
     * @param mode The desired run mode
     */
    public void setMotorMode(String motorName, DcMotorEx.RunMode mode) {
        DcMotorEx motor = motors.get(motorName);
        if (motor != null) {
            motor.setMode(mode);
        }
    }

    /**
     * Reverses the direction of a specific motor by name.
     *
     * @param motorName The name of the motor to reverse
     */
    public void reverseMotorDirection(String motorName) {
        DcMotorEx motor = motors.get(motorName);
        if (motor != null) {
            motor.setDirection(DcMotorEx.Direction.REVERSE);
        }
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
     * Retrieves an array of DcMotorEx objects from an array of motor names.
     *
     * @param motorNames Array of motor names as strings
     * @return An array of DcMotorEx objects corresponding to the provided names
     */
    public DcMotorEx[] getMotorsFromNames(String[] motorNames) {
        List<DcMotorEx> motorList = new ArrayList<>();

        for (String name : motorNames) {
            DcMotorEx motor = motors.get(name);
            if (motor != null) {
                motorList.add(motor);
            }
        }

        return motorList.toArray(new DcMotorEx[0]); // Convert to array and return
    }

    /**
     * Validates that all the necessary movement motors (br, bl, fl, fr) are initialized.
     *
     * @throws IllegalStateException if one or more motors are not initialized
     */
    protected void validateMovementMotors() {
        for (String name : MOVEMENT_MOTORS) {
            DcMotorEx motor = motors.get(name);
            if (motor == null) {
                throw new IllegalStateException("One or more MOVEMENT_MOTORS are not initialized");
            }
        }
    }
}
