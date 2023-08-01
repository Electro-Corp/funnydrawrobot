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
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


import java.util.ArrayList;
import java.util.List;

/*
 * draw (yay)
 */
@Config
@Autonomous(group = "drive")
// TODO: make work (pls)
public class Paint extends LinearOpMode {

    xmltodraw XMLSystem = new xmltodraw("ftc.svg");
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        while(!isStarted()) {;;}



        List<Trajectory> trajectories = new ArrayList<>();
        for(int i = 0; i < XMLSystem.points.size(); i++){
            trajectories.add(drive.trajectoryBuilder(new Pose2d())
                    .lineTo(new Vector2d(XMLSystem.points.get(i).x, XMLSystem.points.get(i).y))
                    .build());
        }
        boolean first = true;
        for (Trajectory trajectory:trajectories) {

            if (!first) {

                // TODO: implement code to check color and switch crayon if necisagry
                drive.followTrajectory(trajectory);
            } else {
                first = false;
                drive.followTrajectory(trajectory);
                // TODO: implement code to put crayon down
            }
        }
    }
}