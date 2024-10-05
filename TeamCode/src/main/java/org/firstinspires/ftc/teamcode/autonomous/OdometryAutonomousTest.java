package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.helper.DcMotorExHelperAuto;
import org.firstinspires.ftc.teamcode.helper.OdometryHelper;
import org.firstinspires.ftc.teamcode.helper.PositionTracker;
import org.firstinspires.ftc.teamcode.helper.geomoetry.Pose2d;
import org.firstinspires.ftc.teamcode.helper.geomoetry.Rotation2d;

@Autonomous(name = "OdometryAutonomousTest", group = "Linear Opmode")
public class OdometryAutonomousTest extends LinearOpMode {
    private DcMotorExHelperAuto motorHelper;
    private OdometryHelper odometryHelper;
    private PositionTracker positionTracker;

    @Override
    public void runOpMode() {
        // Initialize motor helper and odometry helper
        motorHelper = new DcMotorExHelperAuto();
        motorHelper.initializeWheels(hardwareMap);

        // Create an initial robot position
        Pose2d startPose = new Pose2d(0, 0, new Rotation2d(0));
        positionTracker = new PositionTracker(startPose);

        // Initialize the odometry helper with the robot's position tracker
        odometryHelper = new OdometryHelper(hardwareMap, positionTracker, 14.31, 0.477, 2.0);

        // Wait for the game to start
        waitForStart();

        if (opModeIsActive()) {
            // Move straight forward
            motorHelper.moveToGoalStraight(1000, 0.5, telemetry);

            // Update odometry and display the current pose
            odometryHelper.update();
            telemetry.addData("Position", positionTracker.getRobotPose());
            telemetry.update();

            // Pause
            sleep(1000);

            // Move right
            motorHelper.moveToGoalStrafe(500, 0.5, false, telemetry);

            // Update odometry and display the current pose after the turn
            odometryHelper.update();
            telemetry.addData("Position After strafe", positionTracker.getRobotPose());
            telemetry.update();

            // Sleep to observe the movement
            sleep(1000);

            // Move straight again
            motorHelper.moveToGoalStraight(1000, 0.5, telemetry);

            // Final update and display the pose
            odometryHelper.update();
            telemetry.addData("Final Position", positionTracker.getRobotPose());
            telemetry.update();
        }
    }
}
