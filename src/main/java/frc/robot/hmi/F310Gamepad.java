/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot.hmi;

import org.slf4j.Logger;

import riolog.PKLogger;

abstract class F310Gamepad extends BaseGamepad {

   /** Our classes' logger **/
   private static final Logger logger = PKLogger.getLogger(F310Gamepad.class.getName());

   protected F310Gamepad(String name, int port) {
      super(name, port);
      logger.info("constructing {} for {}", name, port);

      logger.info("constructed");
   }

   protected final int leftYAxis = 1;

   /**
    * Return the value of the left Y Axis joystick. Note - the sign is inverted,
    * so that '+' is up and '-' is down.
    *
    * @return value of joystick (inverted to '+' up and '-' down)
    */
   protected double getLeftYAxis() {
      return -stick.getRawAxis(leftYAxis);
   }

   protected final int leftXAxis = 0;

   protected double getLeftXAxis() {
      return stick.getRawAxis(leftXAxis);
   }

   protected final int rightYAxis = 5;

   /**
    * Return the value of the right Y Axis joystick. Note - the sign is inverted,
    * so that '+' is up and '-' is down.
    *
    * @return value of joystick (inverted to '+' up and '-' down)
    */
   protected double getRightYAxis() {
      return -stick.getRawAxis(rightYAxis);
   }

   protected final int rightXAxis = 4;

   protected double getRightXAxis() {
      return stick.getRawAxis(rightXAxis);
   }

   protected final int leftTrigger = 2;

   protected double getLeftTrigger() {
      return stick.getRawAxis(leftTrigger);
   }

   protected final int rightTrigger = 3;

   protected double getRightTrigger() {
      return stick.getRawAxis(rightTrigger);
   }

   protected final int greenButton = 1;
   protected final int abutton = greenButton;

   protected boolean getGreenButton() {
      return stick.getRawButton(greenButton);
   }

   protected final int redButton = 2;
   protected final int bButton = redButton;

   protected boolean getRedButton() {
      return stick.getRawButton(redButton);
   }

   protected final int blueButton = 3;
   protected final int xButton = blueButton;

   protected boolean getBlueButton() {
      return stick.getRawButton(blueButton);
   }

   protected final int yellowButton = 4;
   protected final int yButton = yellowButton;

   protected boolean getYellowButton() {
      return stick.getRawButton(yellowButton);
   }

   protected final int leftBumper = 5;

   protected boolean getLeftBumper() {
      return stick.getRawButton(leftBumper);
   }

   protected final int rightBumper = 6;

   protected boolean getRightBumper() {
      return stick.getRawButton(rightBumper);
   }

   protected final int backButton = 7;

   protected boolean getBackButton() {
      return stick.getRawButton(backButton);
   }

   protected final int startButton = 8;

   protected boolean getStartButton() {
      return stick.getRawButton(startButton);
   }

   protected final boolean isPov0() {
      return (stick.getPOV() == 0);
   }

   protected final boolean isPov90() {
      return (stick.getPOV() == 90);
   }

   protected final boolean isPov180() {
      return (stick.getPOV() == 180);
   }

   protected final boolean isPov270() {
      return (stick.getPOV() == 270);
   }

}
