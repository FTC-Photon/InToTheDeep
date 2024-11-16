package org.firstinspires.ftc.teamcode.autonomous;

import android.provider.SyncStateContract;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.helper.geomoetry.Pose2d;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;

@Autonomous(name = "DeepDiveAuto", group = "Linear Opmode")
public class EncoderTest extends LinearOpMode {

    double motorTicks = 537.7;


    //Inches
    double wheelCircumfrence = 96*Math.PI/25.4;
    double constantA = 537.7;
    DcMotor testMotor;
    double currentTargetPosition;

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
        //moveToFirstSample();
       looop();
    }
//private void moveToFirstSample(AprilTagPoseFtc poseFtc){
//
//    double xNeeded = ;
//    double yNeeded = ;
//    double zNeeded = ;
//    double yawNeeded = ;
//    double rollNeeded = ;
//    double pitchNeeded = ;
//    double rangeNeeded = ;
//    double bearingNeeded = ;
//    double elevationNeeded = 0;
//    AprilTagPoseFtc neededBeyondTag = new AprilTagPoseFtc(
//                xNeeded,
//                yNeeded,
//                zNeeded,
//                yawNeeded,
//                rollNeeded,
//                pitchNeeded,
//                rangeNeeded,
//                bearingNeeded,
//                elevationNeeded
//        );
//        double xDistance = 10/wheelCircumfrence;
//
//}
// start -538 -340 200
    public void looop() {
        long timeToWaitBeofreRunningAutonInMilaSeconds = 2;


        sleep(timeToWaitBeofreRunningAutonInMilaSeconds);
        runMotorUsingEncoder(1000/537.7, bl);
        runMotorUsingEncoder(1000/537.7, br);
        runMotorUsingEncoder(-1000/537.7, fl);
        runMotorUsingEncoder(1000/537.7, fr);

        sleep(5500);
        runMotorUsingEncoder(-242/537.7, bl);
        runMotorUsingEncoder(330/537.7, br);
        runMotorUsingEncoder(-872/537.7, fl);
        runMotorUsingEncoder(812/537.7, fr);
        sleep(2500);

        runMotorUsingEncoder(-242/537.7, bl);
        runMotorUsingEncoder(330/537.7, br);
        runMotorUsingEncoder(-872/537.7, fl);
        runMotorUsingEncoder(812/537.7, fr);
        sleep(2500);



       // moveArmDown();
        runMotorUsingEncoder(-0.25,clawArm);
        sleep(1000);

        runMotorTestUsingEncoder(0.3);
        sleep(1500);

//        moveArmUp();
//        sleep(1500);
//        clawArm.setPower(0);
//        /*
//        fl:1123
//        fr:505
//        br:-690
//        bl:-48
//
//        fl:-3
//        fr:-109
//        br:-19
//        bl:3
//
//        fl:
//        fr:
//        br:
//        bl:
//
//
//         */
//        runMotorUsingEncoder(-100/537.7, bl);
//        runMotorUsingEncoder(-100/537.7, br);
//        runMotorUsingEncoder(-100/537.7, fl);
//        runMotorUsingEncoder(-100/537.7, fr);
//        sleep(500);
//
//        runMotorTestUsingEncoder(-0.25);
//        sleep(500);
//
//        runMotorUsingEncoder(1123/537.7, bl);
//        runMotorUsingEncoder(505/537.7, br);
//        runMotorUsingEncoder(-690/537.7, fl);
//        runMotorUsingEncoder(-48/537.7, fr);
//        sleep(2500);
//
//        runMotorUsingEncoder(-3/537.7, bl);
//        runMotorUsingEncoder(-109/537.7, br);
//        runMotorUsingEncoder(-19/537.7, fl);
//        runMotorUsingEncoder(3/537.7, fr);
//        sleep(2500);


        telemetry.addData("ticks", testMotor.getCurrentPosition());
        telemetry.addData("ticks fl ", fl.getCurrentPosition());
        telemetry.addData("ticks fr ", fr.getCurrentPosition());
        telemetry.addData("ticks bl ", bl.getCurrentPosition());
        telemetry.addData("ticks br ", br.getCurrentPosition());
        telemetry.update();
    }

    public void runMotorUsingEncoder(double turnage){
        testMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        currentTargetPosition = motorTicks*turnage;
        testMotor.setTargetPosition((int)currentTargetPosition);
        testMotor.setPower(0.1);
        testMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


    }

    public void  runMotorTestUsingEncoder(double turnage){
        testMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        currentTargetPosition = motorTicks*turnage;
        testMotor.setTargetPosition((int)currentTargetPosition);
        testMotor.setPower(0.1);
        testMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void runMotorUsingEncoder(double turnage, DcMotor mot){
        mot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        currentTargetPosition = motorTicks*turnage;
        mot.setTargetPosition((int)currentTargetPosition);
        mot.setPower(0.1);
        mot.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void moveSlideUp(){
        double sum = 0;
        for(int i = 0; i < 100000; i++ ){
            sum += i * 2.0;

            testMotor.setPower(0.85);
        }
    }

    public void moveArmUp(){
        double sum = 0;
        for(int i = 0; i < 100000; i++ ){
            sum += i * 2.0;

            clawArm.setPower(-1);
        }
        clawArm.setPower(0);
    }
}
