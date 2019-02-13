package frc.team4330.robot.IO

import edu.wpi.first.networktables.NetworkTableInstance


class NetworkData {

    var table = NetworkTableInstance.getDefault()

    fun init() {
        table.loadEntries("", "")
        table.
    }

}