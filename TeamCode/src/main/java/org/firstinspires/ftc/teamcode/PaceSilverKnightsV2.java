//Robotics Code for Pace Academy Team #### Silver - TeleOp v2
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

@TeleOp(name="PaceSilverV2", group="Silver Group")
//@Disabled
public class PaceSilverKnightsV2 extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    //Driving motors
    private static DcMotor frontLeftDrive = null;
    private static DcMotor backLeftDrive = null;
    private static DcMotor frontRightDrive = null;
    private static DcMotor backRightDrive = null;

    //Arm motors
    private static DcMotor armLiftLeft = null;
    private static DcMotor armLiftRight = null;
    private static DcMotor spinner = null;

    //RackPinLift
    private static DcMotor rackPinLift = null;
   
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        backLeftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        backRightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");

        armLiftLeft = hardwareMap.get(DcMotor.class, "arm_left");
        armLiftRight = hardwareMap.get(DcMotor.class, "arm_right");

        spinner = hardwareMap.get(DcMotor.class, "spinner");
        rackPinLift = hardwareMap.get(DcMotor.class, "rack_pin");

        //Left drives
        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        //Right drives
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);


        //Make sure arm lifts same direction
        armLiftRight.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            double leftMotors = -gamepad1.left_stick_y+gamepad1.right_stick_x;
            double rightMotors = -gamepad1.right_stick_y-gamepad1.right_stick_x;
            double armLift = -gamepad2.left_stick_y;

            frontLeftDrive.setPower(leftMotors);
            backLeftDrive.setPower(leftMotors);
            frontRightDrive.setPower(rightMotors);
            backRightDrive.setPower(rightMotors);

            if(Math.abs(gamepad2.left_stick_y)>0) {
                armLiftLeft.setPower(armLift);
                armLiftRight.setPower(armLift);
            }
            else {
                keepPosition();
            }

            if(gamepad1.right_bumper)
                rackPinLift.setPower(1);
            else if(gamepad1.left_bumper)
                rackPinLift.setPower(-.5);
            else
                rackPinLift.setPower(0);

            if(gamepad2.right_bumper)
                spinner.setPower(.5);
            else if(gamepad2.left_bumper)
                spinner.setPower(-.5);
            else
                spinner.setPower(0);

            //Telemetry to phone
            telemetry.addData("Runtime", ""+runtime);
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", backLeftDrive.getPower(), backRightDrive.getPower());
            telemetry.update();
        }
    }

    public static void keepPosition(){
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
    }
}
