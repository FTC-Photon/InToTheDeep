package org.firstinspires.ftc.teamcode.helper;

import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Class that can hold helper methods related to motors for autonomous simplification
 */
public class MotorHelperAuto extends MotorHelper {

    /**
     * Moves robot left if left is true or right if left is false by powering {"br","bl","fl","fr}
     * @throws IllegalStateException if one or more of {"br","bl","fl","fr} are not initialized
     * @param goal to move all motors
     * @param power to move all motors
     * @param telemetry used for printing to the gamepad
     */
    public void moveToGoalStrafe(int goal, double power, boolean left, Telemetry telemetry){
        validateMovementMotors();
        if(left) {
            //-br bl -fl fr
            moveToGoal(MOVEMENT_MOTORS[0], -goal, power, telemetry);
            moveToGoal(MOVEMENT_MOTORS[2], -goal, power, telemetry);
            moveToGoal(MOVEMENT_MOTORS[1], goal, power, telemetry);
            moveToGoal(MOVEMENT_MOTORS[3], goal, power, telemetry);
        } else {
            //br -bl fl -fr
            moveToGoal(MOVEMENT_MOTORS[0], goal, power, telemetry);
            moveToGoal(MOVEMENT_MOTORS[2], goal, power, telemetry);
            moveToGoal(MOVEMENT_MOTORS[1], -goal, power, telemetry);
            moveToGoal(MOVEMENT_MOTORS[3], -goal, power, telemetry);
        }
    }

    /**
     * Moves robot straight by powering {"br","bl","fl","fr}
     * @throws IllegalStateException if one or more of {"br","bl","fl","fr} are not initialized
     * @param goal to move all motors
     * @param power to move all motors
     * @param telemetry used for printing to the gamepad
     */
    public void moveToGoalStraight(int goal, double power, Telemetry telemetry){
        validateMovementMotors();
        for(String name: MOVEMENT_MOTORS){
            moveToGoal(name, goal,power, telemetry);
        }
    }

    /**
     * moves motor to goal using defaultPower in
     * @see MotorHelper
     * @param name of motor to move
     * @param goal of rotation
     * @param telemetry used for printing to the gamepad
     */
    public void moveToGoal(String name, int goal, Telemetry telemetry) {
        moveToGoal(name,goal,defaultPower,telemetry);
    }


    /**
     * moves motor to goal using defaultPower in
     * @see MotorHelper
     * @param names of motor to move
     * @param goal of rotation
     * @param telemetry used for printing to the gamepad
     */
    public void moveToGoal(String[] names, int goal, Telemetry telemetry) {
        moveToGoal(names,goal,defaultPower,telemetry);
    }


    /**
     * moves motor to goal
     * @param names of motors to move
     * @param goal of rotation
     * @param power of rotation
     * @param telemetry used for printing to the gamepad
     */
    public void moveToGoal(String[] names, int goal, double power, Telemetry telemetry){
        for(String name:names) {
            if(motors.get(name) == null){
                throw new IllegalStateException("One name does not correspond to a current motor");
            }
            moveToGoal(name,goal,power, telemetry);
        }
    }


    /**
     * moves motor to goal
     * @param name of motor to move
     * @param goal of rotation
     * @param power of rotation
     * @param telemetry used for printing to the gamepad
     */
    public void moveToGoal(String name, int goal, double power, Telemetry telemetry) {
        if(motors.get(name) == null){
            throw new IllegalStateException("Name does not correspond to a current motor");
        }
        DcMotor motor = motors.get(name);


        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setTargetPosition(goal);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);




        telemetry.addData("Mode", "waiting");
        telemetry.update();
        telemetry.addData("Mode", "running");
        telemetry.update();




        motor.setPower(power);


        // set motor power to zero to turn off motors. The motors stop on their own but
        // power is still applied so turn off the power.
        motor.setPower(0.0);




        // wait 5 sec to you can observe the final encoder position.




        telemetry.addData("encoder-fnt-left-end", motor.getCurrentPosition());
    }
}