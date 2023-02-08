package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LinearSlide {
    private DcMotor slideMotor;
    private float ticksPerInch; // (4.72441/384.5)
    private HardwareMap hardwareMap;
    private float height;

    public LinearSlide(String slideMotorName, float ticksPerInch, HardwareMap hardwareMap)
    {
        this.hardwareMap = hardwareMap;
        slideMotor = hardwareMap.get(DcMotor.class, slideMotorName);
        this.ticksPerInch = ticksPerInch;

        slideMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void Move(float inches, float speed) {
        float currentPos = slideMotor.getCurrentPosition();
        slideMotor.setTargetPosition(Math.round(inches * ticksPerInch));

        while (slideMotor.isBusy()) {
            slideMotor.setPower(speed);
        }
    }

    public float getHeight()
    {
        height = slideMotor.getCurrentPosition() * ticksPerInch;
        return height;
    }
}
