package frc.team4330.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import frc.team4330.robot.IO.RobotMap

class Shooter : SubsystemBase(){
    init {

    }

    fun succ() {                            //succs in cube
            RobotMap.cargoMotorL.set(ControlMode.PercentOutput, -0.3)
            RobotMap.cargoMotorR.set(ControlMode.PercentOutput, 0.3)
    }

    fun spit() {                            //spits out cube
            RobotMap.cargoMotorL.set(ControlMode.PercentOutput, 0.3)
            RobotMap.cargoMotorR.set(ControlMode.PercentOutput, -0.3)
    }

    fun stopLips() {
        RobotMap.cargoMotorL.set(ControlMode.PercentOutput, 0.0)
        RobotMap.cargoMotorR.set(ControlMode.PercentOutput, 0.0)
    }
}
