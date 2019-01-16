/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4330.robot

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import edu.wpi.first.wpilibj.Compressor
import edu.wpi.first.wpilibj.Solenoid
import edu.wpi.first.wpilibj.XboxController

/**
 * Add your docs here.
 */
object RobotMap {
    //command
//    var RobotCompressor = Compressor(5)

    //wheels
    var frontRight = WPI_TalonSRX(0)
    var frontLeft = WPI_TalonSRX(1)
    var backRight = WPI_VictorSPX(2)
    var backLeft = WPI_VictorSPX(3)

    //input
    var XboxPort = XboxController(0)

    //hangar
//    var armSolenoid = Solenoid(5)

    //cargo - 3 motors, 1 piston
    var cargoMotorOne = WPI_TalonSRX(10)
    var cargoMotorTwo = WPI_TalonSRX(11)
    var cargoMotorThree = WPI_TalonSRX(12)
//    var cargoSolenoid = Solenoid(13)

    //input - 2 motors
    var inputRight = WPI_TalonSRX(14)
    var inputLeft = WPI_TalonSRX(15)

    init {
        frontRight.set(ControlMode.Position, 0.5)
        backRight.follow(frontRight)
        backLeft.follow(frontLeft)
//        RobotCompressor.start()
    }
}
