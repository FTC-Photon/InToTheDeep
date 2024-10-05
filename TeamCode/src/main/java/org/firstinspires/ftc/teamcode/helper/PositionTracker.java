package org.firstinspires.ftc.teamcode.helper;

import org.firstinspires.ftc.teamcode.helper.geomoetry.Pose2d;

public class PositionTracker {
    private Pose2d robotPose;
    public PositionTracker(Pose2d pose2d){
        this.robotPose = pose2d;
    }

    public void updateRobotPose(Pose2d pose2d){
        this.robotPose = pose2d;
    }

    public Pose2d getRobotPose(){
        return robotPose;
    }
}
