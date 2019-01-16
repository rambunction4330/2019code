/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4330.robot.drivetrain

import com.ctre.phoenix.motorcontrol.can.TalonSRX
import com.ctre.phoenix.motorcontrol.can.VictorSPX
import edu.wpi.first.wpilibj.Compressor

import edu.wpi.first.wpilibj.SpeedController
import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import frc.team4330.robot.RobotMap

/**
 * Add your docs here.
 */
class Drive {

    var left = SpeedControllerGroup(RobotMap.frontLeft, RobotMap.backLeft)
    var right = SpeedControllerGroup(RobotMap.frontRight, RobotMap.backRight)
    var drive = DifferentialDrive(left, right)

    var cargoInput = DifferentialDrive(RobotMap.inputLeft, RobotMap.inputRight)

    //Data Storage
    var inputRunning = false

    fun curveDrive(x: Double, y: Double, turn: Boolean) {
        drive.arcadeDrive(x, y, turn)
    }

//    fun moveArm(i: Boolean) {
//        RobotMap.armSolenoid.set(i)
//    }

    fun toggleCargoInput(isPressed:Boolean) {
        if (isPressed) {
            if (!inputRunning) {
                inputRunning = true
                cargoInput.tankDrive(1.0, 1.0)
            }
            else {
                inputRunning = false
                cargoInput.tankDrive(0.0,0.0)
            }
        }
    }
}
