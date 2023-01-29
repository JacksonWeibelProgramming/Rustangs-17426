package org.firstinspires.ftc.teamcode.drive.opmode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="CompleteControl")

public class CompleteControl extends LinearOpMode{
    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor rightFront;
    private DcMotor rightRear;

    private DcMotor leftSlide;
    private CRServo claw;

    @Override
    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        //leftSlide = hardwareMap.get(DcMotor.class, "leftSlide");
        //claw = hardwareMap.get(CRServo.class, "claw");
        // Put initialization blocks here.
        //motor.setPower(0);
        waitForStart();
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                // Robot Movement Code
                double r = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
                double robotAngle = Math.atan2(gamepad1.left_stick_y, -1*gamepad1.left_stick_x) - Math.PI/4;
                double rightX = -1*gamepad1.right_stick_x;
                final double v1 = r*Math.cos(robotAngle) + rightX;
                final double v2 = r*Math.sin(robotAngle) - rightX;
                final double v3 = r*Math.sin(robotAngle) + rightX;
                final double v4 = r*Math.cos(robotAngle) - rightX;
                leftFront.setPower(v1);
                rightFront.setPower(v2);
                leftRear.setPower(v3);
                rightRear.setPower(-v4);

                // Robot Arm Code
//                if (gamepad2.left_trigger>0) {
//                    leftSlide.setPower(gamepad2.left_trigger);
//                }
//                else if (gamepad2.right_trigger>0) {
//                    leftSlide.setPower(-gamepad2.right_trigger);
//                }
//                else {
//                    leftSlide.setPower(0);
//                }
//                if (gamepad2.x){
//                    claw.setPower(0.05f);
//                }
//                else if (gamepad2.b){
//                    claw.setPower(-0.05f);
//                }
//                else {
//                    claw.setPower(0f);
//                }
                telemetry.update();
            }
        }
    }
}