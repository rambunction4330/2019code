/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4330.robot

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.DemandType
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX
import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.cameraserver.CameraServer
import edu.wpi.first.vision.VisionPipeline
import edu.wpi.first.vision.VisionRunner
import edu.wpi.first.vision.VisionThread
import edu.wpi.first.wpilibj.Compressor
import edu.wpi.first.wpilibj.I2C
import edu.wpi.first.wpilibj.Solenoid
import edu.wpi.first.wpilibj.XboxController
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import java.io.File
import jaci.pathfinder.modifiers.TankModifier;
import javax.swing.Spring.width
import org.opencv.imgproc.Imgproc
import javax.swing.Spring.width







/**
 * Add your docs here.
 */
object RobotMap {
    //command
//    var RobotCompressor = Compressor(5)

    //wheels
    var frontRight = WPI_TalonSRX(0) //not slaves (SRX)
    var frontLeft = WPI_TalonSRX(1)
    var backRight = WPI_VictorSPX(2) //slaves (SPX)
    var backLeft = WPI_VictorSPX(3)

    //input (of xbox controller)
    var XboxPort = XboxController(0)

    //hangar
    //var armSolenoid = Solenoid(5)

    //vision


    //gyroscope - detects rotation of robot in general (purple thing on top of roborio)
    var gyro = AHRS(I2C.Port.kMXP)

    //cargo - 3 motors, 1 piston  ///I presume cargo are like motors(wheels)/arms/etc.
    var cargoMotorOne = WPI_TalonSRX(10)
    var cargoMotorTwo = WPI_TalonSRX(11)
    var cargoMotorThree = WPI_TalonSRX(12) //Is this the solenoid?
//    var cargoSolenoid = Solenoid(13)

    //input - 2 motors
    var inputRight = WPI_TalonSRX(14)
    var inputLeft = WPI_TalonSRX(15)

    //what does 'init' do/mean (initialize?)
    init {


//      frontRight.set(ControlMode.Position, 1.0, DemandType.ArbitraryFeedForward, 1.0)
//      frontLeft.set(ControlMode.Position, 1.0)
//      backRight.follow(frontRight)
//      backLeft.follow(frontLeft)
//      RobotCompressor.start()
    }
}
