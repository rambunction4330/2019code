package frc.team4330.robot.Motion

import frc.team4330.robot.Drive.Drive
import frc.team4330.robot.IO.RobotMap
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import jaci.pathfinder.Waypoint
import jaci.pathfinder.followers.EncoderFollower
import jaci.pathfinder.modifiers.TankModifier
import java.io.File


class Autopath {
    //    autoPath00
    lateinit var pathfinder: Pathfinder
    lateinit var fileLeft: File
    lateinit var fileRight: File
    lateinit var trajectoryLeft: Trajectory
    lateinit var trajectoryRight: Trajectory
    var i = 0
    lateinit var left: EncoderFollower
    lateinit var right: EncoderFollower
    var encLeft = RobotMap.frontLeft.getSelectedSensorPosition(0)
    var encRight = RobotMap.frontRight.getSelectedSensorPosition(0)
    var maxVelocity = 3.21564

    fun initialize() {
        pathfinder = Pathfinder()


        //  T   E   S   T
        //Turn into waypoint system, see Chrome
        //Darren: just a question, do we *really* need a 'waypoint system'? Has it been practical previously?
        val config = Trajectory.Config(
                Trajectory.FitMethod.HERMITE_CUBIC,
                Trajectory.Config.SAMPLES_HIGH,
                0.05, 1.7, 2.0, 60.0
        )

        val points = arrayOf(
                Waypoint(0.0, 0.0, Pathfinder.d2r(0.0)),
                Waypoint(2.5, 1.0, Pathfinder.d2r(0.0))
        )

        val trajectory = Pathfinder.generate(points, config)
        val modifier = TankModifier(trajectory).modify(0.686)

//        fileLeft = File("test1_left.csv")
//        fileRight = File("test1_right.csv")
//        trajectoryLeft = Pathfinder.readFromCSV(fileLeft)
//        trajectoryRight = Pathfinder.readFromCSV(fileRight)


        trajectoryLeft = modifier.leftTrajectory
        trajectoryRight = modifier.rightTrajectory
        left = EncoderFollower(trajectoryLeft)
        right = EncoderFollower(trajectoryRight)

        left.configureEncoder(encLeft, 4096, 0.1524)
        right.configureEncoder(encRight, 4096, 0.1524)
        left.configurePIDVA(1.0, 0.1, 0.5, 1 / maxVelocity, 0.0)
        right.configurePIDVA(1.0, 0.1, 0.5, 1 / maxVelocity, 0.0)

        println("finished")
    }

    fun autoPath(drive: Drive) {
//        RobotMap.frontRight
        if (!left.isFinished && !right.isFinished) {

            var l = left.calculate(encLeft)
            var r = right.calculate(encRight)

            var gyro_heading = RobotMap.gyro.angle    // Assuming the gyro is giving a value in degrees
            var desired_heading = Pathfinder.r2d(left.heading)  // Should also be in degrees

            var angleDifference = Pathfinder.boundHalfDegrees(desired_heading - gyro_heading)
            var turn = 0.8 * (-1.0 / 50) * angleDifference
//              var turn = 0
            drive.drive.tankDrive(l + turn, r - turn)
            println(gyro_heading)
        } else {
            println("Done!")
        }
    }
}
