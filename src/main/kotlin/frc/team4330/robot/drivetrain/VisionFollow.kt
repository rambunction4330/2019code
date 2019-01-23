package frc.team4330.robot.drivetrain

//  U   N   W   O   R   K   I   N   G
class VisionFollow {
    lateinit var vision: Vision

    fun visionDrive(drive: Drive) {
        System.out.println(vision.centerX)
        if (vision.centerX > 0) {
            drive.drive.arcadeDrive(0.0, 0.5)
        } else if (vision.centerX < 0) {
            drive.drive.arcadeDrive(0.0, -0.5)
        } else {
            drive.drive.arcadeDrive(0.0, 0.0)
        }
    }

    fun initialize() {
        vision.init()
    }

}
