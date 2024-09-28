package org.firstinspires.ftc.teamcode.tely;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.helper.MotorHelper;

@TeleOp(name = "SimpleDriveMotorHelper", group="Linear OpMode")
public class SimpleDriveMotorHelper extends LinearOpMode {

    private MotorHelper motorHelper;
    private Servo claw;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        double vertical;
        double horizontal;
        double pivot;

        motorHelper = new MotorHelper();

        // Initialize
        motorHelper.initializeWheels(hardwareMap);
        claw = hardwareMap.get(Servo.class, "claw");

        motorHelper.reverseMotorDirection("br");
        motorHelper.reverseMotorDirection("fr");

        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {

                //GAMEPAD 1

                // Drive using MotorHelper's omniDriveGamePadStick method
                motorHelper.omniDriveGamePadStick(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

                //GAMEPAD 2

                //intake
                if (gamepad2.a) {
                    motorHelper.setPower("clawArm", 0.9);
                } else if (gamepad2.b) {
                    motorHelper.setPower("clawArm", -0.9);
                } else {
                    motorHelper.setPower("clawArm", 0);
                }

                //linear slide
                if (gamepad2.left_bumper) {
                    motorHelper.setPower("slide", -0.5);
                } else if (gamepad2.right_bumper) {
                    motorHelper.setPower("slide", 0.5);
                } else {
                    motorHelper.setPower("slide", 0.0);
                }

                //claw
                if (gamepad2.x) {
                    claw.setPosition(0.0);
                } else if (gamepad2.y) {
                    claw.setPosition(1.0);
                } else {
                    claw.setPosition(0.5);
                }

                telemetry.addData("Status", "Run Time: " + runtime.toString());
                telemetry.addData("Motors", "horizontal (%.2f), vertical (%.2f), pivot (%.2f)");
                telemetry.update();
            }
        }
    }
}
