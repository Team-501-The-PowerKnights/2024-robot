/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.config.VersionInfo;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;

/**
 * This class is used to provide a wrapper on the WPILib stuff, and a way to get
 * some personalization and configuration info injected into the dashboard and
 * log files.
 */
public class Team501Robot extends Robot {

   /** Our classes' logger **/
   private static final Logger logger = PKLogger.getLogger(Team501Robot.class.getName());

   public Team501Robot() {
      super(loopPeriod);
      logger.info("constructing");

      SmartDashboard.putString(TelemetryNames.Misc.codeVersion, VersionInfo.version);
      logger.info("codeVersion={}", VersionInfo.version);

      // Create the chooser for FMS connected override
      createFmsOverrideChooser();

      logger.info("constructed");
   }

   private static final double loopPeriod = 0.200;

   /**
    * Returns the loop period in use. It is set at construction time, and can't be
    * modified on the fly.
    * 
    * @return the loop period in seconds
    */
   static public double getLoopPeriod() {
      return loopPeriod;
   }

   // Chooser for overriding field connection in pit
   private static SendableChooser<Boolean> fmsOverrideChooser;

   /**
    * Creates a dashboard chooser to determine whether to override the FMS connect
    * state or not. This can be used in the pits to make the robot think it is
    * actually on and connected to the field.
    *
    * NOTE: This should be used with caution of course ...
    */
   private static void createFmsOverrideChooser() {
      fmsOverrideChooser = new SendableChooser<>();

      fmsOverrideChooser.setDefaultOption("Use Real FMS Connect", Boolean.FALSE);
      fmsOverrideChooser.addOption("Override FMS Connect", Boolean.TRUE);

      SmartDashboard.putData("FMS Override", fmsOverrideChooser);
   }

   /**
    * Returns whether the robot is connected to the <i>Field</i>. The default is
    * based on whether the FMS is connected to the <i>Driver Station</i> laptop or
    * GUI from NI. But with the override chooser, it can be overridden.
    *
    * @return true if connected or overridden from dashboard, false otherwise
    */
   static public boolean isFieldConnected() {
      if (DriverStation.isFMSAttached()) {
         return true;
      } else {
         return fmsOverrideChooser.getSelected();
      }
   }

}
