package frc.team4330.robot.Drive

import com.sun.javafx.geom.transform.BaseTransform
import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.PIDSourceType
import edu.wpi.first.wpilibj.command.PIDSubsystem
import frc.team4330.robot.IO.RobotMap
import java.awt.Robot

class ShooterRotationPID : PIDSubsystem(1.0, 0.0, 0.2) {

    var target = 0.0
    var initVel = 0.0

    /*  D   A   T   A
        ~5.75 rotations for 80 Degrees of motion
        ~ < 90 degrees maximum rotation
     */



    fun init() {
        setAbsoluteTolerance(.2)
        pidController.setContinuous(false) //manipulating raw internal PID Controller

        enable()
    }

    fun setRotationFromDistance(distance: Double, height: Double) {
        var angle = Math.atan((1-Math.sqrt(1+(64/(Math.pow(initVel,2.0))) * (13.02-height - (16*Math.pow(distance, 2.0))/Math.pow(initVel, 2.0))))/((32.2*distance)/Math.pow(initVel, 2.0)))

        setRotation(Math.toDegrees(angle))
    }

    fun setRotation(Degrees: Double) {
        target = Degrees

        if (Degrees < 0.0) target = 0.0
        if (Degrees > 75.0) target = 75.0

        setpoint = target * (5.75/80)
    }

    fun getCurrentAngle() : Double {
        return RobotMap.cargoSpool.getSelectedSensorPosition(0)*(80/5.75)
    }


    override fun initDefaultCommand() {
        init()
    }

    override fun returnPIDInput(): Double {
        return RobotMap.cargoSpool.getSelectedSensorPosition(0) as Double / 4096
    }

    override fun usePIDOutput(output: Double) {
        RobotMap.cargoSpool.set(output)
    }
}