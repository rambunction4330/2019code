package frc.team4330.robot.drivetrain


import com.kauailabs.navx.frc.AHRS
import com.sun.org.apache.xpath.internal.operations.Bool

import edu.wpi.cscore.UsbCamera
import edu.wpi.first.wpilibj.I2C
import frc.team4330.robot.GripPipeline
import edu.wpi.first.cameraserver.CameraServer
import edu.wpi.first.vision.VisionRunner
import edu.wpi.first.vision.VisionThread
import edu.wpi.first.wpilibj.command.PIDSubsystem

import org.opencv.core.*
import org.opencv.imgproc.*

// need to vision extends PIDSubsystem? how do you do that in kotlin
class Vision : PIDSubsystem {
    internal var grip = GripPipeline()
    private var visionThread = Thread()
    internal var centerX = 0.0
        private val imgLock = Any()
    var isEmpty = false

    internal var realAngle: Double = 0.toDouble()
    internal var boundedAngle: Double = 0.toDouble()
    internal var navx = AHRS(I2C.Port.kMXP)

    // constructor(entity: Entity) : super(entity) <---- in order to call a super constructor from secondary constructor
    // I'm not familiar with kotlin enough to know how to implement this in kotlin, but you're referencing the constructor in the extended
    // PIDSubsystem in order to create the Vision object
    // wait nvm I think I did it lol - egg

    constructor(name: String, p: Double, i: Double, d: Double): super("Vision", .11, 0.0, .01)

     fun init() {
        val camera = CameraServer.getInstance().startAutomaticCapture()
        camera.setResolution(640, 480);
        camera.setFPS(10)

         // yeah, I don't know any kotlin so I don't know what to do here
        visionThread = VisionThread(camera, grip, VisionRunner.Listener {
             if (!grip.filterContoursOutput().isEmpty()) {
                 val r = Imgproc.boundingRect(grip.filterContoursOutput().get(0))
                 synchronized(imgLock) {
                     System.out.println("Detected!")
                     centerX = (r.x + r.width / 2).toDouble()
                     isEmpty = false
                 }
             } else {
                 isEmpty = true
             }
         })
        visionThread.start()
    }


    fun getHeading(): Double {
        val centerX: Double
        val turn: Double
        synchronized(imgLock) {
            centerX = this.centerX
        }
        turn = centerX - 160 / 2
        return turn
    }

    fun getRealAngle(): Double {
        return navx.angle
    }

    override fun returnPIDInput(): Double {
        return 0.0
    }

    override fun usePIDOutput(output: Double) {

    }

    override fun initDefaultCommand() {

    }
}
