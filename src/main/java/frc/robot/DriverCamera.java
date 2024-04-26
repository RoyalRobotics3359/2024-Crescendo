// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;

/** Add your docs here. */
public class DriverCamera {

    private UsbCamera camera;
    private Thread visionThread;
    private CvSink sink;
    private CvSource source;

    public DriverCamera() {
        visionThread = 
            new Thread(
                () -> {
                    camera = CameraServer.startAutomaticCapture();

                    camera.setResolution(640, 480);

                    camera.setFPS(10);

                    source = CameraServer.putVideo("Driver Camera", 640, 480);
                }
            );
        visionThread.setDaemon(true);
        visionThread.start();
    }


}
