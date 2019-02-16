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
    //TODO: Look for correlation between motor rotation and

    /*  D   A   T   A
        ~5.75 rotations for 80 Degrees of motion
        ~ < 90 degrees maximum rotation
     */



    fun init() {


        setAbsoluteTolerance(.2)
        pidController.setContinuous(false) //manipulating raw internal PID Controller

//        encoder.pidSourceType  = PIDSourceType.kDisplacement
//        encoder.reset()
//        encoder.setMaxPeriod(5.75)
        enable()
    }

    fun setRotationFromDistance(distance: Double, height: Double) {
        var angle = Math.atan((1-Math.sqrt(1+(64/(Math.pow(initVel,2.0))) * (13.02-height - (16*Math.pow(distance, 2.0))/Math.pow(initVel, 2.0))))/((32.2*distance)/Math.pow(initVel, 2.0)))

//        var angle = 0.0
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun returnPIDInput(): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return RobotMap.cargoSpool.getSelectedSensorPosition(0) as Double
    }

    override fun usePIDOutput(output: Double) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        RobotMap.cargoSpool.set(output)
    }
}