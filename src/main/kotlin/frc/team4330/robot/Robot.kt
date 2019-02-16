/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4330.robot

import edu.wpi.first.wpilibj.GenericHID
import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj.command.Scheduler
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team4330.robot.Drive.Drive
import frc.team4330.robot.Drive.Elevator
import frc.team4330.robot.Drive.ShooterRotationPID
import frc.team4330.robot.IO.RobotMap
import frc.team4330.robot.Motion.Autopath
import frc.team4330.robot.Vision.VisionFollow
import frc.team4330.robot.subsystems.Shooter


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
class Robot : TimedRobot() {

    companion object {


        // it's the intake

        val mRobot: Scheduler = Scheduler.getInstance() // this is the scheduluer; you add commands to it that it will execute, simple as that. Don't overthink it

        private val kDefaultAuto = "Default"
        private val kCustomAuto = "My Auto"
    }


    private var m_autoSelected: String? = null
    private val m_chooser = SendableChooser<String>()
    var drive = Drive()
    var xbox = RobotMap.XboxPort
    lateinit var pathfinding : Autopath
    lateinit var follow : VisionFollow
    lateinit var shooter : Shooter
//    val shooterAngler = ShooterRotationPID()

    val elevator = Elevator()



    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    override fun robotInit() {

        m_chooser.setDefaultOption("Default Auto", kDefaultAuto)
        m_chooser.addOption("My Auto", kCustomAuto)
        SmartDashboard.putData("Auto choices", m_chooser)
        RobotMap.init()


        RobotMap.gyro.reset()

        shooter = Shooter()
        pathfinding = Autopath()
        pathfinding.initialize()
        elevator.init()
//        shooterAngler.init()
    }

    /**
     * This function is called every robot packet, no matter the mode. Use
     * this for items like diagnostics that you want ran during disabled,
     * autonomous, teleoperated and test.
     *
     *
     * This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    override fun robotPeriodic() {

    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and Comment the
     * getString line to get the auto name from the text box below the Gyro
     *
     *
     * You can add additional auto modes by adding additional comparisons to
     * the switch structure below with additional strings. If using the
     * SendableChooser make sure to add them to the chooser code above as well.
     */
    override fun autonomousInit() {
        follow = VisionFollow()

        RobotMap.gyro.reset()

        m_autoSelected = m_chooser.selected
        m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
        println("Auto selected: " + m_autoSelected!!)
        RobotMap.gyro.reset()
        RobotMap.frontRight.selectedSensorPosition = 0
        RobotMap.frontLeft.selectedSensorPosition = 0
//        follow.initialize()



    }

    /**
     * This function is called periodically during autonomous.
     */
    override fun autonomousPeriodic() {
//        when (m_autoSelected) {
//            kCustomAuto -> {
//                drive.drive.tankDrive(0.0, 0.0)
//            }
//            kDefaultAuto -> {
//                pathfinding.autoPath(drive)
//            }
//            else -> {
//                drive.drive.tankDrive(0.0,0.0)
//            }
//        }

//        follow.visionDrive(drive)


        pathfinding.autoPath(drive)

        // Put custom auto code here
        // Put default auto code here
    }


    /**
     * This function is called periodically durin
    g operator control.
     */
    override fun teleopPeriodic() {
        drive.curveDrive(xbox.getY(GenericHID.Hand.kLeft), xbox.getX(GenericHID.Hand.kRight), xbox.getX(GenericHID.Hand.kRight) <= 0.5)

//        RobotMap.elevatorMain.set(RobotMap.XboxPort.getY(GenericHID.Hand.kRight))

//        println("\nelevator Rotations: " + (RobotMap.elevatorMain.selectedSensorPosition as Double) / 4096)
//        println("Shooter Rotations: " + (RobotMap.cargoSpool.selectedSensorPosition as Double) / 4096)

//        if (RobotMap.XboxPort.getBumper(GenericHID.Hand.kRight)) RobotMap.elevatorMain.set(0.3)
//        else if (RobotMap.XboxPort.getBumper(GenericHID.Hand.kLeft)) RobotMap.elevatorMain.set(-0.3)
//        else RobotMap.elevatorMain.set(0.0)

        RobotMap.cargoSpool.set(RobotMap.Stick.y)

        if (RobotMap.Stick.trigger) RobotMap.ballPusher.set(true)
        else RobotMap.ballPusher.set(false)

        if (RobotMap.XboxPort.bButtonReleased) drive.toggleShift()



        //Shooter Collection
        if (RobotMap.XboxPort.getTriggerAxis(GenericHID.Hand.kRight) > 0.8) {
            RobotMap.cargoMotorR.set(1.0)
            RobotMap.cargoMotorL.set(1.0)
        }
        else if (RobotMap.XboxPort.getTriggerAxis(GenericHID.Hand.kLeft) > 0.8) {
            RobotMap.cargoMotorR.set(-0.6)
            RobotMap.cargoMotorL.set(-0.6)
        }
        else {
            RobotMap.cargoMotorR.set(0.0)
            RobotMap.cargoMotorL.set(0.0)
        }

/**        Shooter AutoTarget **/
//        Rocket lvl 1
//        if (RobotMap.Stick.getRawButtonReleased(5)) shooterAngler.setRotationFromDistance(RobotMap.cheddar.getDistance() as Double / 2.54, 26.8)
//        Rocket lvl 2
//        else if (RobotMap.Stick.getRawButtonReleased(3)) shooterAngler.setRotationFromDistance(RobotMap.cheddar.getDistance() as Double / 2.54, 54.8)
//        Rocket lvl 3
//        else if (RobotMap.Stick.getRawButtonReleased(6)) shooterAngler.setRotationFromDistance(RobotMap.cheddar.getDistance() as Double / 2.54, 82.8)
//        Hab lvl
//        else if (RobotMap.Stick.getRawButtonReleased(4)) shooterAngler.setRotationFromDistance(RobotMap.cheddar.getDistance() as Double / 2.54, 42.0)
//        Return to Default
//        else if (RobotMap.Stick.getRawButtonReleased(2)) shooterAngler.setRotation(1.0)


        if (RobotMap.Stick.getRawButtonReleased(7)) {
            elevator.goToLevel(0)
        }
        else if (RobotMap.Stick.getRawButtonReleased(9)) {
            elevator.goToLevel(1)
        }
        else if (RobotMap.Stick.getRawButtonReleased(11)) {
            elevator.goToLevel(2)
        }
        else if (RobotMap.Stick.getRawButtonReleased(12)) {
            elevator.changeMode()
        }



    }

    /**
     * This function is called periodically during test mode.
     */
    override fun testPeriodic() {}


}
