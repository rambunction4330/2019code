/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4330.robot.IO


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.*


/**
 * Add your docs here.
 */
object RobotMap {

    //input (of xbox controller)
    var XboxPort = XboxController(0)
    var Stick = Joystick(1)

    //command
    var RobotCompressor = Compressor(-1)

    //wheels
    var frontRight = WPI_TalonSRX(-1) //not slaves (SRX)
    var frontLeft = WPI_TalonSRX(-1)
    var midRight = WPI_VictorSPX(-1) //slaves (SPX)
    var midLeft = WPI_VictorSPX(-1)
    var backRight = WPI_VictorSPX(-1)
    var backLeft = WPI_VictorSPX(-1)

    //shifter
    val shifterL = Solenoid(-1)
    val shifterR = Solenoid(-1)


    //elevator
    var elevatorMain = WPI_TalonSRX(-1)
    var elevatorSlave1 = WPI_VictorSPX(-1)
    var elevatorSlave2 = WPI_VictorSPX(-1)
    var elevatorEncoder = elevatorMain.getSelectedSensorPosition(0)

    //vision input


    //gyroscope - detects rotation of robot in general (purple thing on top of roborio)
    var gyro = AHRS(I2C.Port.kMXP)

    //optional USB
//    var gyro = AHRS(SerialPort.Port.kUSB)


    //cargo - 2 motors
    var cargoMotorL = WPI_VictorSPX(-1)
    var cargoMotorR = WPI_VictorSPX(-1)
    var cargoSolenoid = Solenoid(-1)



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

        //Start Compressor
        RobotCompressor.start()
    }
}
