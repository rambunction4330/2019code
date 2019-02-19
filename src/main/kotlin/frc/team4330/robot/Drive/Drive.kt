/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4330.robot.Drive

import edu.wpi.first.wpilibj.drive.DifferentialDrive
import frc.team4330.robot.IO.RobotMap


class Drive {
    var drive = DifferentialDrive(RobotMap.frontLeft, RobotMap.frontRight)
    var shifted = false

    fun curveDrive(x: Double, y: Double, turn: Boolean) {
        drive.arcadeDrive(x, y * (-1))
    }

    fun toggleShift() {
        shifted = !shifted

            RobotMap.shifterL.set(false)
            RobotMap.shifterR.set(false)
    }

}
