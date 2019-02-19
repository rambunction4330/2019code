package frc.team4330.robot.Drive

import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.PIDSourceType
import edu.wpi.first.wpilibj.command.PIDSubsystem
import frc.team4330.robot.IO.RobotMap


class Elevator : PIDSubsystem("Elevator", 0.8, 0.0, 0.95 , 0.005) {


    val levelsEngage = listOf(2.75439453125, 14.41064453125, 26.8583984375)
    val levelsRemove = listOf(0.96262305, 12.62890625, 24.93188476562)

    var isEngaged = false
    var level = 0

    var motor = RobotMap.elevatorMain
    var offset = 0.0
    //21.2057503875 in / rotation

    fun topHit() {
        offset = levelsEngage.get(2)*4096 - RobotMap.elevatorMain.getSelectedSensorPosition(0)
    }

    fun bottomHit() {
        offset = 0.0 - RobotMap.elevatorMain.getSelectedSensorPosition(0)
    }

    fun setGoal(num: Double) {
        setpoint = num
    }

    fun init() {
        setAbsoluteTolerance(.2)
        pidController.setContinuous(false) //manipulating raw internal PID Controller
        pidController.setInputRange(0.0, levelsEngage.get(2))

        // Sets the PID parameter to distance
        // Resets the encoder on initialization

        // Sets up the PID parameter
        enable()
    }


    override fun initDefaultCommand() {
        init()
    }


    fun setElevatorSpeed_Manual(input: Double) {
        motor.set(input)
    }

    override fun returnPIDInput(): Double {
        //returns the input for the pid loop, so if this Subsystem was based off of a gyro, then it should return the angle of the gyro.
        return (RobotMap.elevatorMain.getSelectedSensorPosition(0) + 0.0 + offset)/4096
    }

    override fun usePIDOutput(output: Double) {
        motor.set(output) // this is where the computed output value from the PIDController is applied to the motor
    }


    fun changeMode() {
        isEngaged = !isEngaged

        if(isEngaged) println("The Elevator is now ENGAGED")
        else println("The Elevator is now DISENGAGED")
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