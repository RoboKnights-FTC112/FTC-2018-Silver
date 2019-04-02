//Henry and Blake code - shooter robot code

    package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="HenryBlakeCode", group="Silver")
//@Disabled
public class HenryBlakeCode extends LinearOpMode {

    //Declare OpMode members
    private ElapsedTime runtime = new ElapsedTime();
    //Driving motors
    private static DcMotor backLeft = null;
    private static DcMotor backRight = null;
    private static DcMotor leftFront = null;
    private static DcMotor rightFront = null;

    //Strafe wheel
    private static DcMotor strafe = null;

    //Collector/sweeper control
    private static DcMotor collector = null;

    //Launcher control
    private static DcMotor launcher = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        backLeft = hardwareMap.get(DcMotor.class, "left_drive");
        backRight = hardwareMap.get(DcMotor.class, "right_drive");

        leftFront = hardwareMap.get(DcMotor.class, "left_front");
        rightFront = hardwareMap.get(DcMotor.class, "right_front");

        strafe = hardwareMap.get(DcMotor.class, "strafe_wheel");

        collector = hardwareMap.get(DcMotor.class, "ball_collector");

        launcher = hardwareMap.get(DcMotor.class, "ball_launcher")

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

            //General driving - stil need to test this
            backLeft.setPower(-(-gamepad1.left_stick_y-gamepad1.right_stick_x));
            backRight.setPower(-(-gamepad1.left_stick_y+gamepad1.right_stick_x));
            leftFront.setPower(-(-gamepad1.left_stick_y-gamepad1.right_stick_x));
            rightFront.setPower(-(-gamepad1.left_stick_y+gamepad1.right_stick_x));

            //Controlling the collector - LT for power, LB for reverse
            if (gamepad1.left_trigger) {
              collector.setPower(.5);
            } else if (gamepad1.left_bumper) {
              collector.setPower(-.5);
            } else {
              collector.setPower(0);
            }

            //Controlling the Launcher - RT for power, RB for reverse
            if (gamepad1.right_trigger) {
              launcher.setPower(.5);
            } else if (gamepad1.right_bumper) {
              launcher.setPower(-.5);
            } else {
              launcher.setPower(0);
            }


            //Rotating in place - b rotate right, x rotate left
            if (gamepad1.b) {
              backLeft.setPower(.5);
              leftFront.setPower(.5);
              backRight.setPower(-.5);
              rightFront.setPower(-.5);
            } else if (gamepad1.x) {
              backLeft.setPower(-.5);
              leftFront.setPower(-.5);
              backRight.setPower(.5);
              rightFront.setPower(.5);
            } else {
              backLeft.setPower(0);
              leftFront.setPower(0);
              backRight.setPower(0);
              rightFront.setPower(0);
            }

            //Telemetry to phone
            telemetry.addData("Runtime", "" + runtime);
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", backLeft.getPower(), backRight.getPower())
            telemetry.addData("Mechanisms", "collector (%.2f), launcher (%.2f)", launcher.getPower(), collector.getPower())
            telemetry.update();
        }
    }

}
