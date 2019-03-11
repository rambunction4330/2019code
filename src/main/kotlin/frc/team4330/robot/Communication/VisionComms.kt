package frc.team4330.robot.Communication

import jdk.nashorn.internal.runtime.ScriptingFunctions.readLine
import java.io.StringReader
import java.io.LineNumberReader
import java.util.HashMap
import java.io.ByteArrayOutputStream
import java.io.IOException
import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.close
import com.sun.xml.internal.ws.streaming.XMLStreamWriterUtil.getOutputStream
import java.net.InetSocketAddress
import java.net.Socket
import java.io.InputStream
import java.io.OutputStream
import kotlin.collections.Map

class VisionComms(host: String, port: Int) {
    val CONNECTION_TIMEOUT_SEC = 10
    // TODO maybe needs .local ?
    val DEFAULT_VISION_BOARD_HOST = "tegra-ubuntu.local"
    val DEFAULT_VISION_BOARD_PORT = 9001

    // TODO update after know the mDNS name of the vision processing host
    private var host = host
    private var port = port

    private var socket: Socket? = null
    private var `is`: InputStream? = null
    private var os: OutputStream? = null
    private var active = false
    private val GET_DATA_COMMAND = "DATA\n".toByteArray()
    private val STOP_COMMAND = "STOP\n".toByteArray()

    val KEY_RELATIVE_BEARING = "rb"
    val KEY_VERTICAL_ANGLE = "nya"

    /**
     *Constructors removed to fit with Kotlin programming
     */

    @Synchronized
    @Throws(IOException::class)
    fun startUp() {

        println("Opening connection to Jetson on " + host + ":" + port +
                " with " + CONNECTION_TIMEOUT_SEC + " second timeout")

        socket = Socket()

        // we don't want to block the calling thread for long time in case the
        // connection doesn't work immediately, so try to connect to the vision board
        // on another thread
        val connectThread = object : Thread() {

            override fun run() {
                try {
                    // connect is a blocking call which will throw a SocketException if the socket
                    // is asked to close while blocked, so this thread will die
                    socket!!.connect(InetSocketAddress(host, port), CONNECTION_TIMEOUT_SEC * 1000)
                    `is` = socket!!.getInputStream()
                    os = socket!!.getOutputStream()
                    active = true
                    os!!.write(GET_DATA_COMMAND)
                    os!!.flush()

                    println("Successfully connected to Jetson")
                } catch (e: Exception) {
                    System.err.println("Error connecting to Jetson on startup. " + e.message)
                }

            }

        }

        connectThread.isDaemon = true
        connectThread.start()

    }

    @Synchronized
    @Throws(IOException::class)
    fun shutDown() {
        println("Disconnecting from Jetson.")
        active = false

        if (os != null) {
            try {
                os!!.write(STOP_COMMAND)
                os!!.flush()
                os!!.close()
            } catch (e: Exception) {
                //				e.printStackTrace();
            } finally {
                os = null
            }
        }
        if (`is` != null) {
            try {
                `is`!!.close()
            } catch (e: Exception) {

            } finally {
                `is` = null
            }
        }
        if (socket != null) {
            try {
                socket!!.close()
            } catch (e: Exception) {

            } finally {
                socket = null
            }
        }
        println("Successfully disconnected from Jetson.")
    }

    @Synchronized
    fun retrieveData(): Map<String, Double> {
        if (!active) {
            // the client may still be trying to connect
            return HashMap<String, Double>()
        }
        try {
            return getMessages(os!!, `is`)
        } catch (e: Exception) {
            //			e.printStackTrace();
            System.err.println("Error getting messages from the Jetson. " + e.message)
            return HashMap()
        }

    }

    @Throws(IOException::class)
    protected fun getMessages(os: OutputStream, `is`: InputStream?): Map<String, Double> {
        if (os == null || `is` == null)
            return HashMap()

        // send a request to server for data
        os!!.write("DATA\n".toByteArray())
        os!!.flush()

        // read the response from the server
        val binaryData = ByteArrayOutputStream()
        var lastChar = Integer.MIN_VALUE
        val endLine: Int = '\n'.toInt()
        while (true) {
            val thisChar = `is`!!.read()
            if (thisChar == -1 || thisChar == endLine && (lastChar == endLine || lastChar == Integer.MIN_VALUE)) {
                break
            }
            binaryData.write(thisChar)
            lastChar = thisChar
        }

        // had no data
        if (binaryData.size() == 0) {
            //			System.out.println("Vision has no data.");
            return HashMap()
        }

        // get the data as a string
        val data = binaryData.toString()
        val map = HashMap<String, Double>()

        // parse the data into map
        val reader = LineNumberReader(StringReader(data))
        var line: String? = null
        line = reader.readLine()
        while (line != null) {
            //			System.out.println("Vision has read line " + line);
            val index = line!!.indexOf("=")
            if (index == -1) {
                continue
            }
            val key = line.substring(0, index)
            val value = java.lang.Double.parseDouble(line.substring(index + 1))
            map[key] = value
            line = reader.readLine()
        }

        return map
    }
}