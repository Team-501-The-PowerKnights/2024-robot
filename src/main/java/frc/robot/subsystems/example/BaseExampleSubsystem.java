/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot.subsystems.example;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.example.ExampleDoNothing;
import frc.robot.subsystems.BaseSubsystem;
import frc.robot.subsystems.SubsystemNames;
import frc.robot.telemetry.TelemetryNames;
import riolog.PKLogger;

/**
 * DOCS: Add your docs here.
 */
abstract class BaseExampleSubsystem extends BaseSubsystem implements IExampleSubsystem {

   /** Our classes' logger **/
   private static final Logger logger = PKLogger.getLogger(BaseExampleSubsystem.class.getName());

   @SuppressWarnings("unused")
   private final ExamplePreferences prefs;

   BaseExampleSubsystem() {
      super(SubsystemNames.exampleName);
      logger.info("constructing");

      prefs = ExamplePreferences.getInstance();

      logger.info("constructed");
   }

   // TODO Should this be in base class or interface somewhere?
   protected void loadPreferences() {
      @SuppressWarnings("unused")
      double dV;

      logger.info("new preferences for {}:", myName);
   }

   @Override
   public void loadDefaultCommands() {
      loadDefaultCommands(ExampleDoNothing.class);
      SmartDashboard.putString(TelemetryNames.Example.autoCommand, defaultAutoCommand.getClass().getSimpleName());
      SmartDashboard.putString(TelemetryNames.Example.teleCommand, defaultTeleCommand.getClass().getSimpleName());
   }

   @Override
   public void validateCalibration() {
      // Default implementation is empty
   }

   @Override
   public void updatePreferences() {
      // Default implementation is to just (re-)load them
      loadPreferences();
   }

}
