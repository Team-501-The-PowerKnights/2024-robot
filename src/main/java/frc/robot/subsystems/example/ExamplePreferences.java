/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot.subsystems.example;

import org.slf4j.Logger;

import frc.robot.preferences.BasePreferences;
import frc.robot.subsystems.SubsystemNames;

import riolog.PKLogger;

/**
 * Defines the names and values of preferences for this package.
 * <p>
 * The name uses dot notation for the hierarchy. The first part is the name of
 * the subsystem and the second is the name of the preference retreivable from
 * the {@link edu.wpi.first.wpilibj.Preferences}.
 *
 * @see edu.wpi.first.networktables.NetworkTable
 */
public class ExamplePreferences extends BasePreferences {

   /** Our classes' logger **/
   private static final Logger logger = PKLogger.getLogger(ExamplePreferences.class.getName());

   private ExamplePreferences() {
      super(SubsystemNames.exampleName);
      logger.info("constructing");

      logger.info("constructed");
   }

   public static ExamplePreferences getInstance() {
      return Holder.INSTANCE;
   }

   private static class Holder {
      private static final ExamplePreferences INSTANCE = new ExamplePreferences();
   }

   // FIXME: Make perferences & NetworkTables right
   public void initialize() {
      logger.info("initializing");

      logger.info("preferences as initialized:");
      logPreferences(logger);

      logger.info("initialized");
   }

}
