//Robotics Code for Pace Academy Team #### Silver - TeleOp v3 - front wheels
    package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="PaceSilverV3", group="Silver Group")
//@Disabled
public class PaceSilverKnightsV3 extends LinearOpMode {

    // Declare OpMode members. 
    private ElapsedTime runtime = new ElapsedTime();
    //Driving motors
    private static DcMotor backLeft = null;
    private static DcMotor backRight = null;
    private static DcMotor leftFront = null;
    private static DcMotor rightFront = null;

    private static DcMotor strafe = null;

    //Lift Arm
    private static DcMotor armLift = null;

    //Score arm
    private static DcMotor armScore = null;

   
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

        armScore = hardwareMap.get(DcMotor.class, "arm_score");

        //Left drives
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            if (gamepad1.dpad_left) {
                strafe.setPower(.5);
            } else if (gamepad1.dpad_right) {
                strafe.setPower(-.5);
            } else {
                strafe.setPower(0);
            }
            backLeft.setPower(-gamepad1.left_stick_y+gamepad1.right_stick_x);
            backRight.setPower(-gamepad1.left_stick_y-gamepad1.right_stick_x);
            leftFront.setPower(-gamepad1.left_stick_y+gamepad1.right_stick_x);
            rightFront.setPower(-gamepad1.left_stick_y-gamepad1.right_stick_x);

            if(gamepad2.left_stick_y<0)
                armScore.setPower(.5);
            else if(gamepad2.left_stick_y>0)
                armScore.setPower(-.5);
            else
                armScore.setPower(0);
            

            if(gamepad1.right_bumper)
                armLift.setPower(.5);
            else if(gamepad1.left_bumper)
                armLift.setPower(-.25);
            else
                armLift.setPower(0);

            //Telemetry to phone
            telemetry.addData("Runtime", "" + runtime);
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", backLeft.getPower(), backRight.getPower());
            telemetry.update();
        }
    }

    /*public static void keepPosition(){
        armLiftLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armLiftRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armLiftLeft.setTargetPosition(armLiftLeft.getCurrentPosition());
        armLiftRight.setTargetPosition(armLiftRight.getCurrentPosition());
        armLiftLeft.setPower(.5);
        armLiftRight.setPower(.5);
    }

    public static void loosePosition(){
        armLiftRight.setPower(0);
        armLiftLeft.setPower(0);
        armLiftLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armLiftRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }*/
}
