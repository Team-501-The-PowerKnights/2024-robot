/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.robot.IRobot;
import frc.robot.robot.RobotFactory;
import frc.robot.subsystems.example.ExampleFactory;
import frc.robot.telemetry.TelemetryManager;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKStatus;

import riolog.PKLogger;

/**
 * 
 **/
public class SubsystemsFactory {

   /** Our classes' logger **/
   private static final Logger logger = PKLogger.getLogger(SubsystemsFactory.class.getName());

   public static List<ISubsystem> constructSubsystems() {
      logger.info("constructing");

      ArrayList<ISubsystem> subsystems = new ArrayList<ISubsystem>();

      TelemetryManager tlmMgr = TelemetryManager.getInstance();

      logger.info("constructing subsystems ...");

      IRobot robot = RobotFactory.getInstance();

      // FIXME: Comment correctly
      // ** Drive **
      // Always do drive first
      if (robot.hasExample()) {
         logger.info("construct Example");
         SmartDashboard.putNumber(TelemetryNames.Example.status,
               PKStatus.unknown.tlmValue);
         {
            ExampleFactory.constructInstance();
            ISubsystem ss = ExampleFactory.getInstance();
            tlmMgr.addProvider(ss);
            subsystems.add(ss);
         }
         logger.info("constructed Example");
      }

      // Load and update the preferences now that all the subsystems are created
      logger.info("updating subsystem preferences ...");
      for (ISubsystem ss : subsystems) {
         ss.updatePreferences();
      }

      // Load the default commands now that all subsystems are created
      logger.info("loading subsystem default commands ...");
      for (ISubsystem ss : subsystems) {
         ss.loadDefaultCommands();
      }

      logger.info("constructed");
      return subsystems;
   }

}
