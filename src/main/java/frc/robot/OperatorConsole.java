// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/** Add your docs here. */
public class OperatorConsole {
    /** Inner Class */
    public class Controller {
        /**Fields */
        private XboxController controller;
        private JoystickButton aButton, bButton, xButton, yButton, leftBumper, rightBumper;

        /** Constants */
        private double TRIGGER_DEADBAND = 0.06;
        private double STICK_DEADBAND = 0.06;
    
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

        public JoystickButton a() { return aButton; }
        public JoystickButton b() { return bButton; }
        public JoystickButton x() { return xButton; }
        public JoystickButton y() { return yButton; }

        public JoystickButton leftBumper() { return leftBumper; }
        public JoystickButton rightBumper() { return rightBumper; }

        public double getLeftStickY() {
            double leftY = -1.0 * controller.getLeftY();
            if (leftY <= -1.0 * STICK_DEADBAND || leftY >= STICK_DEADBAND) {
                return controller.getLeftY();
            }
            return 0.0;
        }

        public double getLeftStickX() {
            if (controller.getLeftX() <= -STICK_DEADBAND || controller.getLeftX() >= STICK_DEADBAND) {
                return controller.getLeftX();
            }
            return 0.0;
        }

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

        public double getLeftStickTheta() { return Math.atan2(this.getLeftStickY(), this.getLeftStickX()); }

        public double getLeftStickHyp() { return Math.sqrt(Math.pow(this.getLeftStickX(), 2) + Math.pow(this.getLeftStickY(), 2)); }

    }

    /** Fields */
    private Controller d_controller;
    /** Constructor */
    public OperatorConsole() {
        d_controller = new Controller(0);
    }

    public Controller getDController() { return d_controller; }

}
