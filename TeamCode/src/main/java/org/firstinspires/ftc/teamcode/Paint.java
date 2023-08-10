package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

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
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        leftFrontDrive = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");


        //set directions
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);


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
        int j = 0, c = 0, d = 0; // Debug Vars
        int old_x = 0, old_y =0;
        String latestError = "";
        List<Trajectory> trajectories = new ArrayList<>();
        for(int i = 0; i < XMLSystem.points.size(); i++){
            telemetry.addData("I = ", i);
            telemetry.addData("Errors: ", c);
            telemetry.addData("Duplicates removed: ", d);
            telemetry.addData("Newest Error Message: ", latestError);
            telemetry.addData("Completed: ", j);

            try {
                if(old_x != XMLSystem.points.get(i).x && old_y != XMLSystem.points.get(i).y){
                    float heading = (float) Math.atan2(Math.abs(XMLSystem.points.get(i).x - old_x), Math.abs(XMLSystem.points.get(i).y - old_y));
                    old_x = XMLSystem.points.get(i).x;
                    old_y = XMLSystem.points.get(i).y;
                    trajectories.add(drive.trajectoryBuilder(new Pose2d())
                            .splineTo(new Vector2d(XMLSystem.points.get(i).x, XMLSystem.points.get(i).y), heading)
                            .build());
                    telemetry.addData("X,Y, HEADING", String.valueOf(XMLSystem.points.get(i).x) + ", " + String.valueOf(XMLSystem.points.get(i).y) + ", " + String.valueOf(heading));
                    j++;
                }else{
                    d++;
                }
            }catch (Exception e){
                latestError = e.getMessage();
                c++;
            }
            telemetry.update();
        }

        boolean first = true;
        c = 0;
        old_x = 0;
        old_y =0;
        for (Trajectory trajectory: trajectories) {

            /*if (!first) {

                // TODO: implement code to check color and switch crayon if necisagry
                drive.followTrajectory(trajectory);
            } else {
                first = false;
                drive.followTrajectory(trajectory);
                // TODO: implement code to put crayon down
            }*/
            drive.followTrajectory(trajectory);
            float heading = (float) Math.atan2(Math.abs(XMLSystem.points.get(c).x - old_x), Math.abs(XMLSystem.points.get(c).y - old_y));
            old_x = XMLSystem.points.get(c).x;
            old_y = XMLSystem.points.get(c).y;
            telemetry.addData("Drove: ", c);
            telemetry.addData("Out of: ", j);
            telemetry.addData("X,Y, HEADING", XMLSystem.points.get(c).x + ", " + XMLSystem.points.get(c).y + ", "+ heading);
            //telemetry.addData("% = ", (float)((float)c/(float)j) * 100.0f);
            telemetry.update();
            c++;
        }
    }
}