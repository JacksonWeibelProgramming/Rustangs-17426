package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Claw;
import org.firstinspires.ftc.teamcode.LinearSlide;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous
public class RightSideOnePlusFive extends LinearOpMode {
    private ColorRangeSensor tapeSensor;
    private ColorRangeSensor coneSensor;
    private LinearSlide linearSlide;
    private Claw claw;


    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        linearSlide = new LinearSlide("linearSlide", (4.72441f/384.5f), hardwareMap);
        claw = new Claw(hardwareMap, "leftClaw", "rightClaw");

        waitForStart();

        if (isStopRequested()) return;

        Pose2d startPose = new Pose2d(-65, 32, 0);

        drive.setPoseEstimate(startPose);
        claw.Close();
        Trajectory traj = drive.trajectoryBuilder(startPose)

                .splineTo(new Vector2d(-65, 32), 0)
                .addDisplacementMarker(20, () ->{
                    linearSlide.Move(35, 0.8f);
                })
                .addSpatialMarker(new Vector2d(-65 + 28, 32), () -> {
                    claw.Open();
                    linearSlide.Move(5, 0.8f);
                })
                .splineTo(new Vector2d(-12, 60), Math.toRadians(180))
                .addSpatialMarker(new Vector2d(-12, 60), () -> {
                    claw.Close();
                    linearSlide.Move(35, 0.8f);
                })
                .build();

        drive.followTrajectory(traj);
    }
}
