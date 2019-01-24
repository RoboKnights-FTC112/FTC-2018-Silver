package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="CraterAutoBlaketest", group="Silver")
//@Disabled
public class CraterAutoBlaketest extends LinearOpMode {
    private static DcMotor backLeft; //left_drive
    private static DcMotor backRight; //right_drive
    private static DcMotor frontLeft; //left_front
    private static DcMotor frontRight; //right_front
    private static DcMotor liftArm; //arm_lift
    private static DcMotor strafeWheel; //strafe_wheel

    public void runOpMode() {
        telemetry.addData("Status", "Initialzed");
        telemetry.update();

        backLeft = hardwareMap.get(DcMotor.class, "left_drive");
        backRight = hardwareMap.get(DcMotor.class, "right_drive");
        frontLeft = hardwareMap.get(DcMotor.class, "left_front");
        frontRight = hardwareMap.get(DcMotor.class, "right_front");

        liftArm = hardwareMap.get(DcMotor.class, "arm_lift");

        strafeWheel = hardwareMap.get(DcMotor.class, "strafe_wheel");

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        if(opModeIsActive()) {
            lowerLift(3400);
            strafeLeft(650);
            moveForward(4000);
        }
    }

    private void lowerLift(int time) {
        liftArm.setPower(.5);
        sleep(time);
        liftArm.setPower(0);
    }

    private void strafeLeft(int time) {
        strafeWheel.setPower(.5);
        sleep(time);
        strafeWheel.setPower(0);
    }
    private void strafeRight(int time) {
        strafeWheel.setPower(-.5);
        sleep(time);
        strafeWheel.setPower(0);
    }

    private void moveForward(int time) {
        backLeft.setPower(.5);
        backRight.setPower(.5);
        frontLeft.setPower(.5);
        frontRight.setPower(.5);

        sleep(time);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
    }
    private void moveBackwards(int time) {
        backLeft.setPower(-.5);
        backRight.setPower(-.5);
        frontLeft.setPower(-.5);
        frontRight.setPower(-.5);

        sleep(time);
        backLeft.setPower(0);
        backRight.setPower(0);
        frontLeft.setPower(0);
        frontRight.setPower(0);
    }

    private void turnRight(int time) {
        backLeft.setPower(.5);
        frontLeft.setPower(.5);
        backRight.setPower(-.5);
        frontRight.setPower(-.5);
        sleep(time);
        backLeft.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(0);
    }
    private void turnLeft(int time) {
        backLeft.setPower(-.5);
        frontLeft.setPower(-.5);
        backRight.setPower(.5);
        frontRight.setPower(.5);
        sleep(time);
        backLeft.setPower(0);
        frontLeft.setPower(0);
        backRight.setPower(0);
        frontRight.setPower(0);
    }
}
