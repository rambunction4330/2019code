package frc.team4330.robot.Commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.subsystems.Shooter

class StopIntakeWheels : Command() {

    val shooter: Shooter

    init {
        shooter = Shooter()
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun execute() {
        shooter.stopLips()
    }
}