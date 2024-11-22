package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * use when trying to find values of moters with encoders
 */
@TeleOp(name = "EncoderTracking", group = "Linear Opmode")
public class EncoderTracking extends LinearOpMode {

    private DcMotor clawArm, fr, fl, br, bl;

    @Override
    public void runOpMode() throws InterruptedException {
        fr = hardwareMap.get(DcMotor.class, "fr");
        fl = hardwareMap.get(DcMotor.class, "fl");
        br = hardwareMap.get(DcMotor.class, "br");
        bl = hardwareMap.get(DcMotor.class, "bl");
        clawArm = hardwareMap.get(DcMotor.class, "clawArm");
        testMotor = hardwareMap.get(DcMotor.class, "slide");


        telemetry.addData("Hardware", "Initialized");
        testMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        br.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.REVERSE);

        testMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();
        while(opModeIsActive()) {
            looop();
        }
    }
    public void looop() {
        telemetry.addData("ticks slide", testMotor.getCurrentPosition());
        telemetry.addData("ticks claw narm", testMotor.getCurrentPosition());

        telemetry.addData("ticks fl ", fl.getCurrentPosition());
        telemetry.addData("ticks fr ", fr.getCurrentPosition());
        telemetry.addData("ticks bl ", bl.getCurrentPosition());
        telemetry.addData("ticks br ", br.getCurrentPosition());
        telemetry.update();
    }
}
