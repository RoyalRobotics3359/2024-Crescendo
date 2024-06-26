// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class OperatorConsole {
    /** Inner Class */
    // Class created to recreate multiple controllers without retyping code for another
    public class Controller {
        /**Fields */
        private XboxController controller;
        private JoystickButton aButton, bButton, xButton, yButton, leftBumper, rightBumper, back, start, leftStickButton, rightStickButton;

        /** Constants */
        private double TRIGGER_DEADBAND = 0.50;
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

            back = new JoystickButton(controller, XboxController.Button.kBack.value);
            start = new JoystickButton(controller, XboxController.Button.kStart.value);

            leftStickButton = new JoystickButton(controller, XboxController.Button.kLeftStick.value);
            rightStickButton = new JoystickButton(controller, XboxController.Button.kRightStick.value);
        }

        // Buttons that return the state of each button on xbox controller
        public JoystickButton a() { return aButton; }
        public JoystickButton b() { return bButton; }
        public JoystickButton x() { return xButton; }
        public JoystickButton y() { return yButton; }

        public JoystickButton leftBumper() { return leftBumper; }
        public JoystickButton rightBumper() { return rightBumper; }

        public JoystickButton back() { return back; }
        public JoystickButton start() { return start; }

        public JoystickButton leftStickButton() { return leftStickButton; }
        public JoystickButton rightStickButton() { return rightStickButton; }

        // Uses a lambda function to return boolean value if trigger has been pressed
        public Trigger leftTrigger() {
            return new Trigger(() -> (controller.getRawAxis(XboxController.Axis.kLeftTrigger.value) >= TRIGGER_DEADBAND));
        }

        public Trigger rightTrigger() {
            return new Trigger(() -> (controller.getRawAxis(XboxController.Axis.kRightTrigger.value) >= TRIGGER_DEADBAND));
        }

        // Trigger bindings for dpad

        public Trigger dPadUp() {
            return new Trigger(() -> (controller.getPOV() == 0));
        }

        public Trigger dPadDown() {
            return new Trigger(() -> (controller.getPOV() == 180));
        }

        public Trigger dPadRight() {
            return new Trigger(() -> (controller.getPOV() == 90));
        }

        public Trigger dPadLeft() {
            return new Trigger(() -> (controller.getPOV() == 270));
        }

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
    private Controller g_controller;

    /** Constructor */
    public OperatorConsole() {
        d_controller = new Controller(0);
        g_controller = new Controller(1);
    }

    public Controller getDController() { return d_controller; }

    public Controller getGController() { return g_controller; }

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
