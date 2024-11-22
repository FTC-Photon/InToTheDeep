package org.firstinspires.ftc.teamcode.tely;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
@TeleOp(name = "EncoderDrive", group="Linear OpMode")
public class EncoderDrive extends LinearOpMode {
    private DcMotor intakeArm, slide, fr, fl, br, bl;
    private DcMotor intake;
    private ElapsedTime runtime = new ElapsedTime();
    double motorTicks = 537.7;


    //Inches
    double wheelCircumfrence = 96*Math.PI/25.4;
    double currentTargetPosition;

    @Override
    public void runOpMode() {
        double vertical;
        double horizontal;
        double pivot;
        intakeArm = hardwareMap.get(DcMotor.class, "clawArm");
        slide = hardwareMap.get(DcMotor.class, "slide");
        fr = hardwareMap.get(DcMotor.class, "fr");
        fl = hardwareMap.get(DcMotor.class, "fl");
        br = hardwareMap.get(DcMotor.class, "br");
        bl = hardwareMap.get(DcMotor.class, "bl");
        intake = hardwareMap.get(DcMotor.class, "claw");
        //init
        br.setDirection(DcMotor.Direction.REVERSE);
        fr.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                //GAMEPAD 1
                //drive
                vertical = 0.6 * -gamepad1.left_stick_y;
                horizontal = (0.6 * gamepad1.left_stick_x) + (gamepad2.left_stick_x * 0.4);
                pivot = 0.6 * gamepad1.right_stick_x;
                br.setPower(-pivot + (-vertical + horizontal));
                fr.setPower(-pivot - vertical - horizontal);
                bl.setPower(pivot - vertical - horizontal);
                fl.setPower(-1*(pivot - vertical + horizontal));
                //GAMEPAD 2
                //intake arm
                if (gamepad2.a) {
                    intakeArm.setPower(0.9);
                } else if (gamepad2.b) {
                    intakeArm.setPower(-0.9);
                } else {
                    intakeArm.setPower(0);
                }
                //Score
                if(gamepad2.dpad_up){
                    score();
                }

                //claw
                if (gamepad2.x) {
                    intake.setPower(-0.7);
                } else {
                    intake.setPower(0);
                }
                telemetry.addData("Status", "Run Time: " + runtime.toString());
                telemetry.addData("Motors", "horizontal (%.2f), vertical (%.2f), pivot (%.2f)", horizontal, vertical, pivot);
                telemetry.update();
            }
        }
    }

    private void score(){
        intake.setPower(0);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake.setPower(-0.7);
        sleep(100);


        //claw arm down
        runMotorUsingEncoder(-0.25, intakeArm);
        sleep(1000);
        intakeArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        //slide up
        runMotorUsingEncoder(0.3, slide);
        sleep(1500);
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void stopAndRestAll(){
        intakeArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void runMotorUsingEncoder(double turnage, DcMotor mot){
        runMotorUsingEncoder(turnage, mot, 0.1);
    }

    public void runMotorUsingEncoder(double turnage, DcMotor mot, double speed){
        mot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        currentTargetPosition = motorTicks*turnage;
        mot.setTargetPosition((int)currentTargetPosition);
        mot.setPower(speed);
        mot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}