package org.firstinspires.ftc.teamcode.helper.controllers;

/**
 * This class only uses the proportional (kP) and derivative (kD) terms for control, with the integral (kI) term always set to 0.
 * So its simpler than a full PID controller because it doesn't account for accumulated errors over time.
 *
 * <p>
 *     So it is a proportional and derivative controller
 * </p>
 */
public class PDController extends PIDController {

    /**
     * Default constructor with just the coefficients
     */
    public PDController(double kp, double kd) {
        super(kp, 0, kd);
    }

    /**
     * The extended constructor.
     * <p>
     * That includes proportional (kP) and derivative (kD) (or PID terms in the case of PIDController), but also the setpoint (sp) and the measured value (pv).
     */
    public PDController(double kp, double kd, double sp, double pv) {
        super(kp, 0, kd, sp, pv);
    }
}
