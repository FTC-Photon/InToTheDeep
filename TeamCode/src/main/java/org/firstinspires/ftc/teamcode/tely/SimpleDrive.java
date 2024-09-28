package org.firstinspires.ftc.teamcode.tely;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "SimpleDrive", group="Linear OpMode")

public class SimpleDrive extends LinearOpMode {

    private DcMotor clawArm, slide, fr, fl, br, bl;
    private Servo claw;
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        double vertical;
        double horizontal;
        double pivot;

        clawArm = hardwareMap.get(DcMotor.class, "clawArm");
        slide = hardwareMap.get(DcMotor.class, "slide");
        fr = hardwareMap.get(DcMotor.class, "fr");
        fl = hardwareMap.get(DcMotor.class, "fl");
        br = hardwareMap.get(DcMotor.class, "br");
        bl = hardwareMap.get(DcMotor.class, "bl");
        claw = hardwareMap.get(Servo.class, "claw");

        //init
        br.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {

                //GAMEPAD 1
                vertical = 0.6 * -gamepad1.left_stick_y;
                horizontal = (0.6 * gamepad1.left_stick_x) + (gamepad2.left_stick_x * 0.4);
                pivot = 0.6 * gamepad1.right_stick_x;
                br.setPower(-pivot + (-vertical + horizontal));
                fr.setPower(-pivot - vertical - horizontal);
                bl.setPower(pivot - vertical - horizontal);
                fl.setPower(pivot - vertical + horizontal);

                //GAMEPAD 2

                //intake
                if (gamepad2.a) {
                    clawArm.setPower(0.9);
                } else if (gamepad2.b) {
                    clawArm.setPower(-0.9);
                } else {
                    clawArm.setPower(0);
                }


                //linear slide
                if (gamepad2.left_bumper) {
                    slide.setPower(-0.5);

                }else if(gamepad2.right_bumper){
                    slide.setPower(0.5);
                }else{
                    slide.setPower(0.0);
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
                telemetry.addData("Motors", "horizontal (%.2f), vertical (%.2f), pivot (%.2f)", horizontal, vertical, pivot);
                telemetry.update();
            }
        }
    }
}