/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.config.VersionInfo;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;

/**
 * This class is used to provide a wrapper on the WPILib stuff, and a way to get
 * some personalization and configuration info injected into the dashboard and
 * log files.
 * 
 * It should not be controlled in Git, and be sure to add to the .gitignore
 */
public class Team501Robot extends Robot {

   /** Our classes' logger **/
   private static final Logger logger = PKLogger.getLogger(Team501Robot.class.getName());

   public Team501Robot() {
      super(loopPeriod);
      logger.info("constructing");

      SmartDashboard.putString(TelemetryNames.Misc.codeVersion, VersionInfo.version);
      logger.info("codeVersion={}", VersionInfo.version);

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

}
