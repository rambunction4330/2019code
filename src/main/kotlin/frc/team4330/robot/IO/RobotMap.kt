/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4330.robot.IO


import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.*
import java.net.Socket
import java.net.SocketAddress
import javax.sound.sampled.Port


/**
 * Add your docs here.
 */
object RobotMap {

    //input (of xbox controller)
    var XboxPort = XboxController(0)
    var Stick = Joystick(4)

    //command
    var RobotCompressor = Compressor(0)

    //wheels
    var frontRight = WPI_TalonSRX(6) //not slaves (SRX)
    var frontLeft = WPI_TalonSRX(13)
    var midRight = WPI_VictorSPX(4) //slaves (SPX)
    var midLeft = WPI_VictorSPX(9)
    var backRight = WPI_VictorSPX(8)
    var backLeft = WPI_VictorSPX(3)

    //shifter
    val shifterL = Solenoid(0)
    val shifterR = Solenoid(1)


    //elevator
    var elevatorMain = WPI_TalonSRX(5)
    var elevatorSlave1 = WPI_TalonSRX(7)
    var elevatorSlave2 = WPI_VictorSPX(12)


    //vision input
    val ElevatorSocket = Socket("10.40.30.20", 9001)
    val ShooterSocket = Socket("10.40.30.20", 9002)

    //gyroscope - detects rotation of robot in general (purple thing on top of roborio)
    var gyro = AHRS(I2C.Port.kMXP)


    //cargo - 2 motors
    var cargoSpool = WPI_TalonSRX(2)


    var cargoMotorL = WPI_VictorSPX(10)
    var cargoMotorR = WPI_VictorSPX(11)
    var ballPusher = Solenoid(2)

    var cheddar = LidarLite(I2C.Port.kOnboard)


    fun init() {

        frontLeft.setSensorPhase(true)

        //driveTrain Init
        backRight.follow(frontRight)
        midRight.follow(frontRight)
        midLeft.follow(frontLeft)
        backLeft.follow(frontLeft)

        elevatorMain.selectedSensorPosition = 0
        cargoSpool.selectedSensorPosition = 0
        //elevator Init
        elevatorSlave1.follow(elevatorMain)
        elevatorSlave2.follow(elevatorMain)

//        ElevatorSocket.getOutputStream()
    }
}