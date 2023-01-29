package org.firstinspires.ftc.teamcode.drive.opmode.testing;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(group="testing")
public class ClawTest extends LinearOpMode {

    public Servo clawServo;

    @Override
    public void runOpMode() throws InterruptedException {
        clawServo = hardwareMap.get(Servo.class, "claw");

        waitForStart();

        while(!isStopRequested()){
            telemetry.addData("servo position", clawServo.getPosition());
            if(gamepad2.a){
                clawServo.setPosition(.9);
            }
            if(gamepad2.b){
                clawServo.setPosition(.5);
            }
            telemetry.update();
        }


    }
}
