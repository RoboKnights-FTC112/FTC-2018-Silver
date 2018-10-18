//Robotics Code for Pace Academy Team #### Silver - TeleOp v1
package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robot.Robot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="HenryTimBlakeFTC", group="Silver Group")
//@Disabled
public class PRKSilverLOM extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backRightDrive = null;
    private static DcMotor lowJoint = null;
    private static DcMotor highJoint = null;
    private DcMotor pulley = null;
    private static float gp2leftY;
    private static float gp2rightY;
    private static float lowPos;
    private static float highPos;
    private Servo leftClaw = null;
    private Servo rightClaw = null;
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        backLeftDrive = hardwareMap.get(DcMotor.class, "back_left_drive");
        backRightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        lowJoint = hardwareMap.get(DcMotor.class, "lowJoint");
        highJoint = hardwareMap.get(DcMotor.class, "highJoint");
        leftClaw = hardwarMap.servo.get("");
        rightClaw = hardwareMap.servo.get("");

        gp2leftY = gamepad2.left_stick_y;
        gp2rightY = gamepad2.right_stick_y;


        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);

        resetEncoders();
        armModePower();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            double updown = -gamepad1.left_stick_y;
            double rightleft = -gamepad1.right_stick_x;
            //double pulleyValue;
            double moveArm;
            double lowJointPower = -(gamepad2.left_stick_y);
            double highJointPower = -(gamepad2.right_stick_y);

            /*if(gamepad2.right_bumper) pulleyValue=1;
            else if(gamepad2.left_bumper) pulleyValue=-.25;
            else pulleyValue=0;
            pulley.setPower(pulleyValue);*/

            if(gamepad2.dpad_left){
                setupArmPickup();
                sleep(1000);
            }
            else if(gamepad2.dpad_right){
                setupArmScore();
            }
            else if(gamepad2.dpad_down){
                armModePower();
            }
            else if(gamepad2.left_bumper){
                lowJoint.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                lowJoint.setPower(0);
            }

            backleftDrive.setPower(updown+rightleft);
            backrightDrive.setPower(updown-rightleft);
            frontrightDrive.setPower(updown-rightleft);
            frontleftDrive.setPower(updown+rightleft);

            if(highJoint.getMode()==DcMotor.RunMode.RUN_WITHOUT_ENCODER) highJoint.setPower(highJointPower);
            if(lowJoint.getMode()==DcMotor.RunMode.RUN_TO_POSITION&&highJoint.getMode()==DcMotor.RunMode.RUN_TO_POSITION) updatePos();

            lowPos = (float)lowJoint.getCurrentPosition();
            highPos = (float)highJoint.getCurrentPosition();

            telemetry.addData("Positions","Low Joint: " + lowPos + ", High Joint: " + highPos);
            telemetry.addData("Runtime", ""+runtime);
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftDrive.getPower(), rightDrive.getPower());
            telemetry.update();
        }
    }
    public static void resetEncoders(){
            lowJoint.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            highJoint.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            lowJoint.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            highJoint.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        }

        public static void armModePos(){
            lowJoint.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            highJoint.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        }

        public static void armModePower(){
            highJoint.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }

        public static void updatePos(){
            lowJoint.setTargetPosition((int)((int)lowPos-(int)gp2leftY));
            highJoint.setTargetPosition((int)((int)highPos-(int)gp2leftY));
        }

        public static void setupArmPickup(){
            armModePos();
            //will have to test arm to find ideal positions, power can start as .5 and see if change is necessary
            lowJoint.setTargetPosition(-320);//tested pos
            lowJoint.setPower(.05);

            highJoint.setTargetPosition(285);//tested pos
            highJoint.setPower(.05);
            armModePower();
        }

        /*public static void setupArmScore(){
            armModePos();
            lowJoint.setTargetPosition(1);//placeholder
            highJoint.setTargetPosition(-1);//placeholder
            lowJoint.setPower(1);
            highJoint.setPower(1);
            armModePower();
        }*/
}
