/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import edu.wpi.first.wpilibj.XboxController

/**
 * Add your docs here.
 */
object RobotMap {
    var frontRight = WPI_TalonSRX(0)
    var frontLeft = WPI_TalonSRX(1)
    var backRight = WPI_VictorSPX(2)
    var backLeft = WPI_VictorSPX(3)
    var XboxPort = XboxController(0)

    init {
        backRight.follow()
    }

}
