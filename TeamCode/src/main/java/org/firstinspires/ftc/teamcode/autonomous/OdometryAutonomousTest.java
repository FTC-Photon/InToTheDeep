package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.helper.DcMotorExHelperAuto;
import org.firstinspires.ftc.teamcode.helper.MotorHelperAuto;
import org.firstinspires.ftc.teamcode.helper.OdometryHelper;
import org.firstinspires.ftc.teamcode.helper.PositionTracker;
import org.firstinspires.ftc.teamcode.helper.geomoetry.Pose2d;
import org.firstinspires.ftc.teamcode.helper.geomoetry.Rotation2d;

@Autonomous(name = "OdometryAutonomousTest", group = "Linear Opmode")
public class OdometryAutonomousTest extends LinearOpMode {
    private MotorHelperAuto motorHelper;
    private OdometryHelper odometryHelper;
    private PositionTracker positionTracker;

    private DcMotorEx fr;

    @Override
    public void runOpMode() {
        // Initialize motor helper and odometry helper
        motorHelper = new MotorHelperAuto();
        motorHelper.initializeWheels(hardwareMap);

        if(motorHelper.areWheelsNull()){
            telemetry.addLine("Wheels are null");
            telemetry.update();
        } else {
            telemetry.addLine("Wheels are not null");
            telemetry.update();
        }

        fr = hardwareMap.get(DcMotorEx.class, "fr");
        // Create an initial robot position
        Pose2d startPose = new Pose2d(0, 0, new Rotation2d(0));
        positionTracker = new PositionTracker(startPose);

        // Initialize the odometry helper with the robot's position tracker
        odometryHelper = new OdometryHelper(hardwareMap, positionTracker, 14.31, 0.477, 2.0);

        // Wait for the game to start
        waitForStart();

        if (opModeIsActive()) {
            odometryHelper.update();
            telemetry.addData("Position before trying to move forward", positionTracker.getRobotPose());
            telemetry.update();


            // Pause
            sleep(10000);

            fr.setTargetPosition(1000);
            fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fr.setPower(0.5);

            // Move straight forward
            motorHelper.moveToGoalStraight(1000, 0.5, telemetry, this);

            // Update odometry and display the current pose
            odometryHelper.update();
            telemetry.addData("Position after trying to move forward", positionTracker.getRobotPose());
            telemetry.update();

            // Pause
            sleep(10000);

            // Move right
            motorHelper.moveToGoalStrafe(500, 0.5, false, telemetry, this);

            // Update odometry and display the current pose after the turn
            odometryHelper.update();
            telemetry.addData("Position After strafe", positionTracker.getRobotPose());
            telemetry.update();

            // Sleep to observe the movement
            sleep(10000);

            // Move straight again
            motorHelper.moveToGoalStraight(1000, 0.5, telemetry, this);

            // Final update and display the pose
            odometryHelper.update();
            telemetry.addData("Final Position", positionTracker.getRobotPose());
            telemetry.update();
        }
    }
}
