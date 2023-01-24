package org.firstinspires.ftc.teamcode.drive.opmode.testing;

import android.widget.Switch;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.opmode.vision.SleeveDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(group="testing")
public class ParkingTest extends LinearOpMode {

    private SleeveDetection sleeveDetection;
    private OpenCvCamera camera;

    // Name of the Webcam to be set in the config
    private String webcamName = "Webcam 1";

    private Vector2d leftParking = new Vector2d();
    private Vector2d centerParking = new Vector2d();
    private Vector2d rightParking = new Vector2d();
    private Vector2d targetParking;
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        //vision init
        {
            int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
            camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, webcamName), cameraMonitorViewId);
            sleeveDetection = new SleeveDetection();
            camera.setPipeline(sleeveDetection);

            camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
                @Override
                public void onOpened() {
                    camera.startStreaming(320, 240, OpenCvCameraRotation.SIDEWAYS_LEFT);
                }

                @Override
                public void onError(int errorCode) {
                }
            });

            SleeveDetection.ParkingPosition parkingPosition = sleeveDetection.getPosition();
            switch(parkingPosition)
            {
                case CENTER:
                    targetParking = centerParking;
                    break;
                case RIGHT:
                    targetParking = rightParking;
                    break;
                default:
                    targetParking = leftParking;
            }

            while (!isStarted()) {
                telemetry.addData("ROTATION: ", sleeveDetection.getPosition());
                telemetry.update();
            }
        }

        waitForStart();

        if (isStopRequested()) return;

        Pose2d startPose = new Pose2d(-65, 32, 0);

        drive.setPoseEstimate(startPose);

        Trajectory traj = drive.trajectoryBuilder(startPose)
                .strafeLeft(28)
                .strafeRight(28)
                .splineTo(targetParking, Math.toRadians(90))
                .build();

        drive.followTrajectory(traj);
    }
}
