package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="CraterAuto", group="SilverKnights")

public class WhitesAutoCrater extends LinearOpMode{
    public DcMotor arm;
    public DcMotor rearLeft1;
    public DcMotor rearRight1;
    public DcMotor frontLeft1;
    public DcMotor frontRight1;
    
    public DcMotor armLiftLeft;
    public DcMotor armLiftLeft;
    
    public void runOpMode() {

        telemetry.addData("Status", "AutoBot Ready");
        telemetry.update();

        waitForStart();

        //wheel motor variables
        rearLeft1 = hardwareMap.get(DcMotor.class, "back_left_drive");
        rearRight1 = hardwareMap.get(DcMotor.class, "back_right_drive");
        frontLeft1 = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontRight1 = hardwareMap.get(DcMotor.class, "front_right_drive");
        
        //plow servos
        //plowOne = hardwareMap.servo.get("plowOne");
        //plowTwo = hardwareMap.servo.get("plowTwo");
        
        //plowMotor=hardwareMap.dcMotor.get("plowMotor");
        
        //arm motor
        DcMotor arm = hardwareMap.get(DcMotor.class, "lowJoint");

        if(opModeIsActive()){
            //plowOne.setPosition(.5);
            driveForward(0.5,1500);
            //moveArm(true);
            sleep(900);
            //plowOne.setPosition(1);
            //moveArm(false);
        }
    }
    
    public void driveForward(double speed, double time){
        rearLeft1.setPower(-speed);
        rearRight1.setPower(speed);
        frontLeft1.setPower(-speed);
        frontRight1.setPower(speed);
        sleep((long)time);
        rearLeft1.setPower(0);
        rearRight1.setPower(0);
        frontLeft1.setPower(0);
        frontRight1.setPower(0);
        
    }
    public void moveArm(boolean pos){
        if(pos){
            armLiftLeft.setPower(-0.6);
            armLiftRight.setPower(-0.6);
            sleep(400);
        } else {
            plowMotor.setPower(0.6);
            sleep(650);
        }
        plowMotor.setPower(0);
    }
    public void turnRight(double speed, int time){
        rearLeft1.setPower(-speed);
        rearRight1.setPower(-speed);
        frontLeft1.setPower(-speed);
        frontRight1.setPower(-speed);
        sleep(time);
        rearLeft1.setPower(0);
        rearRight1.setPower(0);
        frontLeft1.setPower(0);
        frontRight1.setPower(0);
    }
}
