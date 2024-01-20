/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot.sensors;

import org.slf4j.Logger;

import riolog.PKLogger;

/**
 * A base class for sensors that provides default methods for the
 * {@link frc.robot.IModeFollower IRobotModes} interface for notifications of
 * mode transitions.
 */
public abstract class BaseSensor implements ISensor {

   /** Our classes' logger **/
   private static final Logger logger = PKLogger.getLogger(BaseSensor.class.getName());

   /** Our sensors's name **/
   protected final String myName;

   public BaseSensor(String name) {
      logger.info("constructing");

      myName = name;

      logger.info("constructed");
   }

   @Override
   public void autonomousInit() {
      logger.info("initializing auto for {}", myName);

      updatePreferences();

      logger.info("initialized auto for {}", myName);
   }

   @Override
   public void teleopInit() {
      logger.info("initializing teleop for {}", myName);

      updatePreferences();

      logger.info("initialized teleop for {}", myName);
   }

   @Override
   public void testInit() {
      logger.info("initializing test for {}", myName);

      updatePreferences();

      logger.info("initialized test for {}", myName);
   }

}
