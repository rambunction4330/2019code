/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4330.robot.IO


import com.ctre.phoenix.motorcontrol.can.VictorSPX
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.I2C
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.XboxController


/**
 * Add your docs here.
 */
object RobotMap {
    //command
//    var RobotCompressor = Compressor(5)

    //wheels
    var frontRight = WPI_TalonSRX(0) //not slaves (SRX)
    var frontLeft = WPI_TalonSRX(1)

    var midRight = WPI_VictorSPX(-1) //slaves (SPX)
    var midLeft = WPI_VictorSPX(-1)
    var backRight = WPI_VictorSPX(2)
    var backLeft = WPI_VictorSPX(3)

    //input (of xbox controller)
    var XboxPort = XboxController(0)
    var Stick = Joystick(1)
    //hangar
    //var armSolenoid = Solenoid(5)

    //vision

    //pneumatics
    val ballYeeter: Solenoid = Solenoid(0)


    //gyroscope - detects rotation of robot in general (purple thing on top of roborio)
    var gyro = AHRS(I2C.Port.kMXP)

    //cargo - 2 motors
//    var cargoMotor1 = WPI_TalonSRX(5)
//    var cargoMotor2 = WPI_TalonSRX(6)

    //elevator
    var elevatorMain = WPI_TalonSRX(-1)
    var elevatorSlave1 = WPI_VictorSPX(-1)
    var elevatorSlave2 = WPI_VictorSPX(-1)
    var elevatorEncoder = elevatorMain.getSelectedSensorPosition(0)
//    var elevatorEncoder2

    fun init() {

        frontLeft.setSensorPhase(true)

        //driveTrain Init
        backRight.follow(frontRight)
        midRight.follow(frontRight)
        midLeft.follow(frontLeft)
        backLeft.follow(frontLeft)

        //elevator Init
        elevatorSlave1.follow(elevatorMain)
        elevatorSlave2.follow(elevatorMain)

    }
}
