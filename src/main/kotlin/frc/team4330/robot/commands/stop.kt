package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.IO.RobotMap

class stop : Command() {
    override fun isFinished(): Boolean {
        return false
    }

    override fun end() {

    }

    override fun execute() {
        RobotMap.frontLeft.set(0.0)
        RobotMap.frontRight.set(0.0)
        RobotMap.backLeft.set(0.0)
        RobotMap.backRight.set(0.0)
    }


}