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
    private static DcMotor leftDrive = null;
    private static DcMotor rightDrive = null;
    private static DcMotor leftFront = null;
    private static DcMotor rightFront = null;
   
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        leftDrive = hardwareMap.get(DcMotor.class, "left_drive");
        rightDrive = hardwareMap.get(DcMotor.class, "right_drive");

        leftFront = hardwareMap.get(DcMotor.class, "left_front");
        rightFront = hardwareMap.get(DcMotor.class, "right_front");        

        //Left drives
        leftDrive.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            leftDrive.setPower(-gamepad1.left_stick_y+gamepad1.right_stick_x);
            rightDrive.setPower(-gamepad1.left_stick_y-gamepad1.right_stick_x);

            leftFront.setPower(gamepad1.right_stick_x);
            rightFront.setPower(gamepad1.right_stick_x);

            //Telemetry to phone
            telemetry.addData("Runtime", "" + runtime);
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftDrive.getPower(), rightDrive.getPower());
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
