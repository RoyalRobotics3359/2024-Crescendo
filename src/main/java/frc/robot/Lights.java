// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

/**
 * Information for REV Blinkin controllet take 
 * from: https://www.revrobotics.com/content/docs/REV-11-1105-UM.pdf
 */
package frc.robot;

import edu.wpi.first.wpilibj.motorcontrol.Spark;

/** Add your docs here. */
public class Lights {

    // Fields
    private Spark leds;

    // Sets up the Blinkin as a spark motor controller
    // per manual directions for easy color assignment.
    public Lights() {
        // 0 is the PWM port # the Blinkin is connected to on the roboRIO
        if (Constants.LED_LIGHTS_EXIST)
            leds = new Spark(0);
    }


    // In order to assign color, the Blinkin reads it like a speed assignment to a spark motor.
    // Values for each color can be found in Constants class, and specified in manual above.
    public void setColorRed() {
        leds.set(Constants.LED_COLORS.RED.getColor());
    }

    public void setColorBlue() {
        leds.set(Constants.LED_COLORS.BLUE.getColor());
    }

    public void setColorGreen() {
        leds.set(Constants.LED_COLORS.GREEN.getColor());
    }

    public void setColorOrange() {
        leds.set(Constants.LED_COLORS.ORANGE.getColor());
    }

    public void setColorYellow() {
        leds.set(Constants.LED_COLORS.YELLOW.getColor());
    }

    public void setColorPurple() {
        leds.set(Constants.LED_COLORS.PURPLE.getColor());
    }

    public void setColorRainbow() {
        leds.set(Constants.LED_COLORS.RAINBOW.getColor());
    }

    public void setColorOff() {
        leds.set(0.0);
    }


}
