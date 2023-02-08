package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MecanumDriveTrain {
    public DcMotor leftFront;
    public DcMotor leftRear;
    public DcMotor rightFront;
    public DcMotor rightRear;
    private boolean usingEncoders;

    public MecanumDriveTrain(String leftFrontName, String leftRearName, String rightFrontName, String rightRearName, HardwareMap hardwareMap, boolean UsingEncoders)
    {
        leftFront = hardwareMap.get(DcMotor.class, leftFrontName);
        leftRear = hardwareMap.get(DcMotor.class, leftRearName);
        rightFront = hardwareMap.get(DcMotor.class, leftFrontName);
        rightRear = hardwareMap.get(DcMotor.class, rightRearName);
        usingEncoders = UsingEncoders;
        ResetEncoders();
        SetRunUsingEncoders(usingEncoders);
    }

    public void ResetEncoders()
    {
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void SetRunUsingEncoders(boolean value)
    {
        if(value){
            leftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightRear.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }else{
            leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            leftRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            rightRear.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        }
    }

    public void Strafe()
    {

    }

    public void StrafeUsingEncoders()
    {

    }

    public void Drive()
    {

    }

    public void DriveUsingEncoders()
    {

    }
}
