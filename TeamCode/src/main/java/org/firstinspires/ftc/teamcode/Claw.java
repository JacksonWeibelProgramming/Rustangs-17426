package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw {
    public Servo leftServo;
    public Servo rightServo;

    public Claw(HardwareMap hardwareMap, String leftServoName, String rightServoName)
    {
        leftServo = hardwareMap.get(Servo.class, leftServoName);
        rightServo = hardwareMap.get(Servo.class, rightServoName);
    }

    public void Open()
    {
        leftServo.setPosition(0.0f);
        rightServo.setPosition(0.0f);
    }

    public void Close()
    {
        leftServo.setPosition(0.5f);
        rightServo.setPosition(-0.5f);
    }
}
