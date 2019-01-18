package frc.team4330.robot

import frc.team4330.robot.drivetrain.Drive
import jaci.pathfinder.Pathfinder
import jaci.pathfinder.Trajectory
import java.io.File
import jaci.pathfinder.Waypoint
import jaci.pathfinder.modifiers.TankModifier





class Autopath {
    //    autoPath00
    lateinit var pathfinder: Pathfinder
    lateinit var fileLeft: File
    lateinit var fileRight: File
    lateinit var trajectoryLeft: Trajectory
    lateinit var trajectoryRight: Trajectory
    var i = 0

    fun initialize() {
        pathfinder = Pathfinder()


        //  T   E   S   T
        //Turn into waypoint system, see Chrome
        val config = Trajectory.Config(
                Trajectory.FitMethod.HERMITE_CUBIC,
                Trajectory.Config.SAMPLES_HIGH,
                0.05, 0.8, 1.5, 100.0
        )

        val points = arrayOf(
                Waypoint(25.0, 8.0, Pathfinder.d2r(270.0)),
                Waypoint(27.0, 10.0, Pathfinder.d2r(180.0)),
                Waypoint(29.0, 12.0, Pathfinder.d2r(270.0))
        )

        val trajectory = Pathfinder.generate(points, config)
        val modifier = TankModifier(trajectory).modify(0.735)

//        fileLeft = File("test1_left.csv")
//        fileRight = File("test1_right.csv")
//        trajectoryLeft = Pathfinder.readFromCSV(fileLeft)
//        trajectoryRight = Pathfinder.readFromCSV(fileRight)


        trajectoryLeft = modifier.leftTrajectory
        trajectoryRight = modifier.rightTrajectory
    }

    fun autoPath(drive: Drive) {
//        i.rem(trajectoryLeft.length())
        if(i.rem(trajectoryLeft.length()) <= trajectoryLeft.length()) {
            drive.drive.tankDrive(trajectoryLeft.get(i.rem(trajectoryLeft.length())).velocity, trajectoryRight.get(i.rem(trajectoryLeft.length())).velocity)
        } else {
            drive.drive.tankDrive(0.0, 0.0)
        }
        i++
    }
}
