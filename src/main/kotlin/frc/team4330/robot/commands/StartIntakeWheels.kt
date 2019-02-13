package frc.team4330.robot.commands

import edu.wpi.first.wpilibj.command.Command
import frc.team4330.robot.subsystems.Shooter

class StartIntakeWheels : Command() {

        val shooter : Shooter
        init {
            shooter = Shooter()
            requires(shooter)
            setTimeout(1.5)
        }

        override fun execute() {
            shooter.succ()
        }

        override fun isFinished(): Boolean {
            return isTimedOut
        }

        override fun end() {
            shooter.stopLips()
        }
}