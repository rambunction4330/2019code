package frc.team4330.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import frc.team4330.robot.IO.RobotMap

class Shooter : SubsystemBase(){

        private var mouthUp: Boolean
//    private var mouthOpen: Boolean

        init {
            mouthUp = false
            //       mouthOpen = false
        }

        //    fun moveMouthUp(xbox: Input) {            //moves mouth Up and down with x button
//        if (!mouthUp && xbox.xButtonPressed) {
//            RobotMap.JAW.set(true)
//            mouthUp = true
//        }
//        else {
//            RobotMap.JAW.set(false)
//            mouthUp = false
//        }
//    }
        fun moveMouthUp() {            //moves mouth Up and down
            //    if (!mouthUp) {
            RobotMap.ballYeeter.set(true)
            mouthUp = true
            //        mouthUp = true
            //    }
            //    else {
            //        RobotMap.JAW.set(false)
            //        mouthUp = false
            //    }
        }

        fun moveMouthDown() {
            RobotMap.ballYeeter.set(false)
            mouthUp = false
        }

        fun succ() {                            //succs in cube
            RobotMap.leftLip.set(ControlMode.PercentOutput, -0.3)
            RobotMap.rightLip.set(ControlMode.PercentOutput, 0.3)
        }

        fun spit() {                            //spits out cube
            RobotMap.leftLip.set(ControlMode.PercentOutput, 0.3)
            RobotMap.rightLip.set(ControlMode.PercentOutput, -0.3)
        }

    fun stopLips() {
        RobotMap.leftLip.set(ControlMode.PercentOutput, 0.0)
        RobotMap.rightLip.set(ControlMode.PercentOutput, 0.0)
    }
}
