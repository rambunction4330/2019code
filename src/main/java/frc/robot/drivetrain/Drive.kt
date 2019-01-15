/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.drivetrain

import com.ctre.phoenix.motorcontrol.can.TalonSRX
import com.ctre.phoenix.motorcontrol.can.VictorSPX

import edu.wpi.first.wpilibj.SpeedController
import edu.wpi.first.wpilibj.SpeedControllerGroup
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import frc.robot.RobotMap
import frc.robot.input

/**
 * Add your docs here.
 */
class Drive {

    var left = SpeedControllerGroup(RobotMap.frontLeft, RobotMap.backLeft)
    var right = SpeedControllerGroup(RobotMap.frontRight, RobotMap.backRight)
    var drive = DifferentialDrive(left, right)

    fun curveDrive(x: Double, y: Double, turn: Boolean) {
        drive.arcadeDrive(x, y, turn)
    }
}
