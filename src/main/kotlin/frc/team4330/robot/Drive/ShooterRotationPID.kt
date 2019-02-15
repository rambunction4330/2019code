package frc.team4330.robot.Drive

import com.sun.javafx.geom.transform.BaseTransform
import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.PIDSourceType
import edu.wpi.first.wpilibj.command.PIDSubsystem
import frc.team4330.robot.IO.RobotMap

class ShooterRotationPID : PIDSubsystem(1.0, 0.0, 0.2) {

    lateinit var encoder: Encoder
    var target = 0.0
    //TODO: Look for correlation between motor rotation and

    /*  D   A   T   A
        ~5.75 rotations for 80 Degrees of motion
        ~ < 90 degrees maximum rotation
     */



    fun init() {
        encoder = Encoder(RobotMap.elevatorEncoder, RobotMap.elevatorEncoder)

        setAbsoluteTolerance(.2)
        pidController.setContinuous(false) //manipulating raw internal PID Controller

        encoder.pidSourceType  = PIDSourceType.kDisplacement
        encoder.reset()
        encoder.setMaxPeriod(5.75)
        enable()
    }

    fun setRotationFromDistance(distance: Double, height: Double) {

        setRotation(Math.toDegrees(Math.PI / 2 - Math.atan(distance/height)))
    }

    fun setRotation(Degrees: Double) {
        target = Degrees

        if (Degrees < 0.0) target = 0.0
        if (Degrees > 75.0) target = 75.0

        setpoint = target * (5.75/80)
    }


    override fun initDefaultCommand() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun returnPIDInput(): Double {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return encoder.distance
    }

    override fun usePIDOutput(output: Double) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        RobotMap.elevatorEncoder
    }
}