package org.firstinspires.ftc.teamcode.drive.opmode.testing;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class LinearSlideTest extends LinearOpMode {
    public Servo clawServo;
    public DcMotor linearSlideMotor;
    @Override
    public void runOpMode() throws InterruptedException {
        linearSlideMotor = hardwareMap.get(DcMotor.class, "linearSlide");

        linearSlideMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        clawServo = hardwareMap.get(Servo.class, "claw");

        waitForStart();

        while(!isStopRequested()){

            if(gamepad2.a){
                clawServo.setPosition(.8);
            }
            if(gamepad2.b){
                clawServo.setPosition(.5);
            }

            if(gamepad1.dpad_up){
                linearSlideMotor.setPower(.25);
            }
            else if(gamepad1.dpad_down){
                linearSlideMotor.setPower(-.25);
            }
            else{
                linearSlideMotor.setPower(0);
            }

            telemetry.addData("servo position", clawServo.getPosition());
            telemetry.update();
        }
    }
}
