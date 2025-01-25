package org.firstinspires.ftc.teamcode.helper;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.helper.hardware.MotorEx;

public class OdometryHelper {
    private double TRACKWIDTH = 14.31;
    private double CENTER_WHEEL_OFFSET = 0.477;
    private double WHEEL_DIAMETER = 2.0;
    private final double TICKS_PER_REV = 8192;
    private double DISTANCE_PER_PULSE;

    private MotorEx leftEncoder, rightEncoder, perpEncoder;
    private HolonomicOdometry odometry;
    private PositionTracker robtPos;

    /**
     * Creates OdometryHelper hardwareMap must have
     * @param hardwareMap
     * @throws IllegalStateException if there is no left odometer, right odometer or center odometer in the current hardware config
     */
    public OdometryHelper(HardwareMap hardwareMap, PositionTracker robotPos, double trackWidth, double centerWheelOffset, double wheelDiameter) {
        this.TRACKWIDTH = trackWidth;
        this.CENTER_WHEEL_OFFSET = centerWheelOffset;
        this.WHEEL_DIAMETER = wheelDiameter;
        this.DISTANCE_PER_PULSE = Math.PI * WHEEL_DIAMETER / TICKS_PER_REV;

        leftEncoder = new MotorEx(hardwareMap, "fl");
        rightEncoder = new MotorEx(hardwareMap, "fr");
        perpEncoder = new MotorEx(hardwareMap, "bl");

        leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        perpEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);

        //Uses :: to pass the methods as suppliers
        odometry = new HolonomicOdometry(
                leftEncoder::getDistance,
                rightEncoder::getDistance,
                perpEncoder::getDistance,
                TRACKWIDTH,
                CENTER_WHEEL_OFFSET
        );
        this.robtPos = robotPos;
    }

    public void update(){
        odometry.updatePose();
        robtPos.updateRobotPose(odometry.getPose());
    }


}
