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
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.config.VersionInfo;
import frc.robot.modules.led.LEDModuleFactory;
import frc.robot.telemetry.TelemetryManager;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKColor8Bit;
import riolog.Level;
import riolog.PKLogger;
import riolog.ProblemTracker;

/**
 * This class is used to provide a wrapper on the WPILib stuff, and a way to get
 * some personalization and configuration info injected into the dashboard and
 * log files.
 */
public class Team501Robot extends Robot {

   /** Our classes' logger **/
   private static final Logger logger = PKLogger.getLogger(Team501Robot.class.getName());

   private RobotContainer robotContainer;

   public Team501Robot() {
      super(loopPeriod);
      logger.info("constructing");

      SmartDashboard.putString(TelemetryNames.Misc.codeVersion, VersionInfo.version);
      logger.info("codeVersion={}", VersionInfo.version);

      // Create the chooser for FMS connected override
      createFmsOverrideChooser();

      // Create the chooser for the Logger level
      createLoggerLevelChooser();

      logger.info("constructed");
   }

   @Override
   public void robotInit() {
      logger.info("initializing");

      // Wait until we get the configuration data from driver station
      waitForDriverStationData();

      // Initialize the dashboard to false for status
      SmartDashboard.putBoolean(TelemetryNames.Misc.initStatus, false);

      // Instantiate our RobotContainer. This will perform all our button bindings,
      // and put our autonomous chooser on the dashboard.
      robotContainer = new RobotContainer();

      // Set up the telemetry reporter
      addPeriodic(telemetryReporter, 5 * Team501Robot.getLoopPeriod());

      // WPILib default is to disable this now; but just in case ...
      LiveWindow.setEnabled(false);
      // But we want access to the CommandScheduler telemetry content
      // LiveWindow.disableAllTelemetry();
      // LiveWindow.enableTelemetry(CommandScheduler.getInstance());

      // Put indication of initialization status on dash
      determineInitStatus();

      super.robotInit();
   }

   /**
    * Holds the constructor until we receive notice the DriverStation (laptop) is
    * attached, as it holds the run-time configuration.
    **/
   private void waitForDriverStationData() {
      // FIXME Work with new API methods
      // long count = 0;
      // FIXME: This might not be right (there are handlers to install?)
      // while (DriverStation.isDSAttached()) { // isNewControlData()
      // if ((count % 100) == 0) {
      // logger.trace("Waiting ...");
      // }
      // try {
      // Thread.sleep(100);
      // } catch (InterruptedException ex) {
      // logger.error("exception for sleep: ", ex);
      // }
      // count++;
      // }
   }

   // Periodic runnable to do the reporting off main loop
   private Runnable telemetryReporter = new Runnable() {

      @Override
      public void run() {
         // Update the telemetry
         TelemetryManager.getInstance().sendTelemetry();
      }

   };

   private void determineInitStatus() {
      // TODO: Make tri-color status when implemented
      long errorCount = ProblemTracker.getErrorCount();
      long warnCount = ProblemTracker.getWarnCount();
      logger.info("init status: errorCount={}, warnCount={}", errorCount, warnCount);
      // red for bad, green for good (so reverse sense)
      boolean status = !((errorCount != 0) || (warnCount != 0));
      SmartDashboard.putBoolean(TelemetryNames.Misc.initStatus, status);

      if (errorCount != 0) {
         LEDModuleFactory.getInstance().setColor(PKColor8Bit.redRGB);
      } else if (warnCount != 0) {
         LEDModuleFactory.getInstance().setColor(PKColor8Bit.yellowRGB);
      } else {
         LEDModuleFactory.getInstance().setColor(PKColor8Bit.greenRGB);
      }
      // TODO: Parse network tables for all status and do a roll-up
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

   // Chooser for overriding logger level
   private static SendableChooser<Level> loggerLevelChooser;

   // TODO: Comment
   // TODO: Move to 501 Robot?
   private static void createLoggerLevelChooser() {
      loggerLevelChooser = new SendableChooser<>();

      loggerLevelChooser.addOption("ERROR", Level.ERROR);
      loggerLevelChooser.addOption("WARN", Level.WARN);
      loggerLevelChooser.addOption("INFO", Level.INFO);
      loggerLevelChooser.setDefaultOption("DEBUG", Level.DEBUG);
      loggerLevelChooser.addOption("TRACE", Level.TRACE);

      SmartDashboard.putData("Logger Level", loggerLevelChooser);
   }

}
