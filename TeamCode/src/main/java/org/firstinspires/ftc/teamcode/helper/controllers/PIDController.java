package org.firstinspires.ftc.teamcode.helper.controllers;

/**
 * Similar to PIDFController but does not have the feed-forward term kF
 * (feed-forward: anticipates system changes and adjusts control preemptively)
 * <p>
 * This includes all three PID terms [kP(proportional), kI(integral), kD(derivative)]
 */
public class PIDController extends PIDFController {

    /**
     * Default constructor with just the coefficients
     */
    public PIDController(double kp, double ki, double kd) {
        super(kp, ki, kd, 0);
    }

    /**
     * The extended constructor.
     */
    public PIDController(double kp, double ki, double kd, double sp, double pv) {
        super(kp, ki, kd, 0, sp, pv);
    }

    public void setPID(double kp, double ki, double kd) {
        setPIDF(kp, ki, kd, 0);
    }

}