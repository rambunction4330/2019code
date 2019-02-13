package frc.team4330.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.subsystems.Shooter

class shoot : Command() {
    val shooter: Shooter
    init {

        shooter = Shooter()
        requires(shooter)
        setTimeout(1.5)
    }

    override fun execute() {
        shooter.spit()

    }

    override fun isFinished(): Boolean {
        return isTimedOut
    }

    override fun end() {
        shooter.stopLips()
    }
}