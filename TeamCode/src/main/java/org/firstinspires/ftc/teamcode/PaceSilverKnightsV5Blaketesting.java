//Robotics Code for Pace Academy Team 15292 Silver - TeleOp v5 Blaketest - front wheels
    package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
//import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="PaceSilverV5Blaketesting", group="Silver Group")
//@Disabled
public class PaceSilverKnightsV5Blaketesting extends LinearOpMode {

    //Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    //Driving motors
    private static DcMotor backLeft = null;
    private static DcMotor backRight = null;
    private static DcMotor leftFront = null;
    private static DcMotor rightFront = null;

    //Strafe wheel
    private static DcMotor strafe = null;

    //Lift Arm
    private static DcMotor armLift = null;

    //Score arm - commenting out because not using
    /* private static DcMotor armScore = null;
    private static DcMotor armExtend = null; */

    //Claw Servos - commenting out because not using
    /*private static Servo claw1 = null;
    private static Servo claw2 = null;*/


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        backLeft = hardwareMap.get(DcMotor.class, "left_drive");
        backRight = hardwareMap.get(DcMotor.class, "right_drive");

        leftFront = hardwareMap.get(DcMotor.class, "left_front");
        rightFront = hardwareMap.get(DcMotor.class, "right_front");

        strafe = hardwareMap.get(DcMotor.class, "strafe_wheel");

        armLift = hardwareMap.get(DcMotor.class, "arm_lift");

        /*armScore = hardwareMap.get(DcMotor.class, "arm_score");
        armExtend = hardwareMap.get(DcMotor.class, "arm_extend");*/

        /*claw1 = hardwareMap.get(Servo.class, "clawL");
        claw2 = hardwareMap.get(Servo.class, "clawR");*/

        //Left drives
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            //Gamepad1:

            //Strafing (5th wheel)
            if (gamepad1.dpad_left) {
                strafe.setPower(.5);
            } else if (gamepad1.dpad_right) {
                strafe.setPower(-.5);
            } else {
                strafe.setPower(0);
            }

            //General driving
            backLeft.setPower(-(-gamepad1.left_stick_y-gamepad1.right_stick_x));
            backRight.setPower(-(-gamepad1.left_stick_y+gamepad1.right_stick_x));
            leftFront.setPower(-(-gamepad1.left_stick_y-gamepad1.right_stick_x));
            rightFront.setPower(-(-gamepad1.left_stick_y+gamepad1.right_stick_x));

            //Controlling the lift
            if(gamepad1.right_bumper)
                armLift.setPower(.5);
            else if(gamepad1.left_bumper)
                armLift.setPower(-.25);
            else
                armLift.setPower(0);


        /*    //Gamepad2:

            //Arm security
            if(gamepad2.dpad_left) {
                keepPosition();
            }
            if(gamepad2.dpad_right) {
                loosePosition();
            } */

        /*    //Lifting the scoring arm
            if(gamepad2.left_stick_y<0)
                armScore.setPower(.5);
            else if(gamepad2.left_stick_y>0)
                armScore.setPower(-.5);
            else
                armScore.setPower(0);

            //Extending the arm:
            if(gamepad2.right_bumper)
                armExtend.setPower(.5);
            else if(gamepad2.left_bumper)
                armExtend.setPower(-.5);
            else
                armExtend.setPower(0); */

          /*  //Opening/closing claw:
            if(gamepad2.left_stick_x > 0) {
                claw1.setPosition(claw1.getPosition() + 5);
            } else if(gamepad2.left_stick_x < 0) {
                claw1.setPosition(claw1.getPosition() - 5);
            }
            if(gamepad2.right_stick_x > 0) {
                claw2.setPosition(claw2.getPosition() - 5);
            } else if(gamepad2.right_stick_x < 0) {
                claw2.setPosition(claw2.getPosition() + 5);
            } */


            //Telemetry to phone
            telemetry.addData("Runtime", "" + runtime);
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", backLeft.getPower(), backRight.getPower());
            //telemetry.addData("Servo Positions", "Left: (%.2f), Right: (%.2f)", claw1.getPosition(), claw2.getPosition());
            telemetry.update();
        }
    }

    public static void keepPosition(){
        armLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armLift.setTargetPosition(armLift.getCurrentPosition());
        armLift.setPower(.5);
    }

    public static void loosePosition(){
        armLift.setPower(0);
        armLift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
