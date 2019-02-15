package frc.team4330.robot.Drive

import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.PIDSourceType
import edu.wpi.first.wpilibj.command.PIDSubsystem
import frc.team4330.robot.IO.RobotMap


class Elevator : PIDSubsystem("Elevator", 1.0, 0.0, 0.2, 0.005) {


    val levelsEngage = listOf(0.207490889, 1.52788745542, 2.84828402184)
    val levelsRemove = listOf(0.01886280808, 1.3392593745, 2.65965594092)
    var isEngaged = false
    var level = 0

    var motor = RobotMap.elevatorMain
    lateinit var encoder: Encoder

    //21.2057503875 in / rotation

    var error = 0
    var distance = 0.0


    fun init() {
        encoder = Encoder(RobotMap.elevatorEncoder, RobotMap.elevatorEncoder)

        setAbsoluteTolerance(.2)
        pidController.setContinuous(false) //manipulating raw internal PID Controller

        // Sets the PID parameter to distance
        encoder.pidSourceType = PIDSourceType.kDisplacement

        // Resets the encoder on initialization
        encoder.reset()

        // Sets up the PID parameter
        enable()
    }


    override fun initDefaultCommand() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    fun setElevatorSpeed_Manual(input: Double) {
        motor.set(input)
    }

    override fun returnPIDInput(): Double {
        //returns the input for the pid loop, so if this Subsystem was based off of a gyro, then it should return the angle of the gyro.
        return encoder.distance
    }

    override fun usePIDOutput(output: Double) {
        motor.set(output) // this is where the computed output value from the PIDController is applied to the motor
    }


    fun changeMode() {
        isEngaged = !isEngaged

        goToLevel(level)
    }

    fun goToLevel(tempLevel: Int) {
        level = tempLevel
        if (isEngaged) {
            setpoint = levelsEngage.get(tempLevel)
        } else {
            setpoint = levelsRemove.get(tempLevel)
        }
    }

}