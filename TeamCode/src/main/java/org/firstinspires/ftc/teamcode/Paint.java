package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;


import java.util.ArrayList;
import java.util.List;

/*
 * draw (yay)
 */
@Config
@Autonomous(group = "drive")
// TODO: make work (pls)
public class Paint extends LinearOpMode {

    xmltodraw XMLSystem;
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        try {
            XMLSystem = new xmltodraw("/sdcard/out.svg");
        }catch (Exception e){

        }

        while(!isStarted()) {
            telemetry.addData("File Name: ", XMLSystem.fileName);
            telemetry.addData("XMLSystem Points: ", XMLSystem.points.size());
            telemetry.addData("Error: ", XMLSystem.error);
            telemetry.update();
        }
        int j = 0, c = 0;
        List<Trajectory> trajectories = new ArrayList<>();
        for(int i = 0; i < XMLSystem.points.size(); i++){
            telemetry.addData("I = ", i);
            telemetry.addData("Errors: ", j);
            telemetry.addData("Completed: ", c);
            telemetry.update();
            try {
                trajectories.add(drive.trajectoryBuilder(new Pose2d())
                        .strafeTo(new Vector2d(XMLSystem.points.get(i).x, XMLSystem.points.get(i).y))
                        .build());
                c++;
            }catch (Exception e){
                j++;
            }
        }

        boolean first = true;
        c = 0;
        for (Trajectory trajectory:trajectories) {

            if (!first) {

                // TODO: implement code to check color and switch crayon if necisagry
                drive.followTrajectory(trajectory);
            } else {
                first = false;
                drive.followTrajectory(trajectory);
                // TODO: implement code to put crayon down
            }
            telemetry.addData("Drove: ", c);
            telemetry.update();
            c++;
        }
    }
}