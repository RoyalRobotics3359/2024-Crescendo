// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OperatorConsole {
    /** Inner Class */
    // Class created to recreate multiple controllers without retyping code for another
    public class Controller {
        /**Fields */
        private XboxController controller;
        private JoystickButton aButton, bButton, xButton, yButton, leftBumper, rightBumper;

        /** Constants */
        private double TRIGGER_DEADBAND = 0.20;
        private double STICK_DEADBAND = 0.20;
    
        /**Constructor */
        public Controller(int id) {
            controller = new XboxController(id);
    
            aButton = new JoystickButton(controller, XboxController.Button.kA.value);
            bButton = new JoystickButton(controller, XboxController.Button.kB.value);
            xButton = new JoystickButton(controller, XboxController.Button.kX.value);
            yButton = new JoystickButton(controller, XboxController.Button.kY.value);

            leftBumper = new JoystickButton(controller, XboxController.Button.kLeftBumper.value);
            rightBumper = new JoystickButton(controller, XboxController.Button.kRightBumper.value);
        }

        // Buttons that return the state of each button on xbox controller
        public JoystickButton a() { return aButton; }
        public JoystickButton b() { return bButton; }
        public JoystickButton x() { return xButton; }
        public JoystickButton y() { return yButton; }

        public JoystickButton leftBumper() { return leftBumper; }
        public JoystickButton rightBumper() { return rightBumper; }

        // For all controll sticks on controller, they have a range set - deadband >= control stick >= deadband

        // Left stick data read with applied deadband range
        public double getLeftStickY() {
            // Multiply by -1 to compensate for reversal of left stick data when read
            double leftY = -1.0 * controller.getLeftY();
            if (leftY <= -1.0 * STICK_DEADBAND || leftY >= STICK_DEADBAND) {
                return leftY;
            }
            return 0.0;
        }

        public double getLeftStickX() {
            if (controller.getLeftX() <= -STICK_DEADBAND || controller.getLeftX() >= STICK_DEADBAND) {
                return controller.getLeftX();
            }
            return 0.0;
        }

        // Right stick data read with applied deadband range
        public double getRightStickY() {
            if (controller.getRightY() <= -STICK_DEADBAND || controller.getRightY() >= STICK_DEADBAND) {
                return controller.getRightY();
            }
            return 0.0;
        }

        public double getRightStickX() {
            if (controller.getRightX() <= -STICK_DEADBAND || controller.getRightX() >= STICK_DEADBAND) {
                return controller.getRightX();
            }
            return 0.0;
        }

        // Returns the angle created by the leftX and leftY control stick vector. Uses Math.atan2 to return all possible angles 
        // between [0, 2pi).
        public double getLeftStickTheta() { return Math.atan2(this.getLeftStickY(), this.getLeftStickX()); }

        // Returns the hypotenuse created by the leftX and leftY control stick vector. Used to calculate the percentage of speed sent
        // to the mecanum drive.
        public double getLeftStickHyp() { return Math.sqrt(Math.pow(this.getLeftStickX(), 2) + Math.pow(this.getLeftStickY(), 2)); }

    }

    /** Fields */
    private Controller d_controller;
    /** Constructor */
    public OperatorConsole() {
        d_controller = new Controller(0);
    }

    public Controller getDController() { return d_controller; }

    /**
     * returns the button to use deploy the intake
     * @return JoystickButton Button mapped to deploy the intake
     */
    public JoystickButton getIntakeDeployButton() {
        return d_controller.rightBumper();
    }

    /**
     * returns the button to use retract the intake
     * @return JoystickButton Button mapped to retract the intake
     */
    public JoystickButton getIntakeRetractButton() {
        return d_controller.leftBumper();
    }
}
