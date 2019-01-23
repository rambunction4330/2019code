/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team4330.robot.drivetrain

import edu.wpi.first.cameraserver.CameraServer
import edu.wpi.first.vision.VisionRunner
import edu.wpi.first.vision.VisionThread
import frc.team4330.robot.GripPipeline
import org.opencv.imgproc.Imgproc

/**
 * Add your docs here.
 */
class Vision {
    internal lateinit var grip: GripPipeline
    private lateinit var visionThread: Thread
    internal var centerX = 0.0
    private val imgLock = Any()
    var isEmpty = true
        internal set

    val heading: Double
        get() {
            val centerX: Double
            val turn: Double
            synchronized(imgLock) {
                centerX = this.centerX
            }
            turn = centerX - 160 / 2
            return turn
        }

    fun init() {
        val camera = CameraServer.getInstance().startAutomaticCapture()
        //        camera.setResolution(640, 480);
        camera.setFPS(10)

//        visionThread = VisionThread(camera, GripPipeline(), { pipeline ->

        visionThread = VisionThread(camera, grip, VisionRunner.Listener {
            if (!it.filterContoursOutput().isEmpty()) {
                val r = Imgproc.boundingRect(it.filterContoursOutput().get(0))
                synchronized(imgLock) {
                    centerX = (r.x + r.width / 2).toDouble()
                    isEmpty = false
                }
            } else {
                isEmpty = true
            }
        })
        visionThread.start()
    }
}