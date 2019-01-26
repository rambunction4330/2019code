/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4330.robot.Drive

import com.kauailabs.navx.frc.AHRS

/**
 * Add your docs here.
 */
class DriveStraight {

    internal var error: Double = 0.toDouble()
    internal var speeds = DoubleArray(2)


    fun init(navx: AHRS) {
        // navx = new AHRS(Port.kMXP);
        navx.reset()
        println("Heading" + navx.angle)

    }


    fun smth(navx: AHRS, speed: Double, heading: Double, k: Double): DoubleArray {
        error = navx.angle - heading
        speeds[0] = -speed - k * error
        speeds[1] = speed - k * error
        return speeds
    }

    fun getAngle(navx: AHRS): Double {
        return navx.angle
    }

    fun turnToAngle(navx: AHRS) {

    }

}
