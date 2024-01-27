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
import edu.wpi.first.wpilibj2.command.Command;

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
 * <p>
 * Because we are extending the {@link Robot} class, the
 * <code>build.gradle</code> file in the project references this one instead.
 */
public class Team501Robot extends Robot {

   /** Our classes' logger **/
   private static final Logger logger = PKLogger.getLogger(Team501Robot.class.getName());

   private RobotContainer robotContainer;

   private Command autonomousCommand;

   /**
    * Constructor for Robot.
    *
    * @param period Loop period in seconds.
    */
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

   /**
    * This function is run when the robot is first started up and should be used
    * for any initialization code.
    */
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

      // Init the superclass (only states & modes content)
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

   /**
    * This function is called periodically whilst disabled.
    */
   @Override
   public void disabledPeriodic() {
      Level level = loggerLevelChooser.getSelected();
      PKLogger.setLevel(level);

      // Has a "real" auto been selected yet?
      boolean realAutoSelected = robotContainer.isRealAutoSelected();
      SmartDashboard.putBoolean(TelemetryNames.Misc.realAuto, realAutoSelected);

      displayAutoSelectionStatus(realAutoSelected);

      super.disabledPeriodic();
   }

   private long autoModeCheckDelay = (long) (20.0 / getPeriod());
   private long autoErrorOnPeriod = (long) (1.5 / getPeriod());
   private long autoErrorOffPeriod = (long) (0.75 / getPeriod());
   private long autoErrorCount;
   private boolean autoErrorOn = true;

   /**
    * Displays a flashing red in the LEDs if a real auto hasn't been selected.
    * Waits a bit for transitioning from green though (so can see the state of
    * start-up).
    *
    * @param realAutoSelected
    */
   private void displayAutoSelectionStatus(boolean realAutoSelected) {
      if (autoModeCheckEnabled && !robotContainer.isRealAutoSelected()) {
         if (autoModeCheckDelay > 0) {
            --autoModeCheckDelay;
         } else if (!realAutoSelected) {
            if (--autoErrorCount <= 0) {
               if (autoErrorOn) {
                  LEDModuleFactory.getInstance().setColor(PKColor8Bit.blackRGB);
                  autoErrorCount = autoErrorOffPeriod;
               } else {
                  LEDModuleFactory.getInstance().setColor(PKColor8Bit.redRGB);
                  autoErrorCount = autoErrorOnPeriod;
               }
               autoErrorOn = !autoErrorOn;
            }
         }
      } else {
         autoModeCheckEnabled = false;
         LEDModuleFactory.getInstance().setColor(PKColor8Bit.greenRGB);
      }
   }

   /**
    * This function is called once each time the robot exits Disabled mode.
    */
   @Override
   public void disabledExit() {
      logger.info("exiting disabled");

      ModeFollowers.getInstance().exitDisabled();

      super.disabledExit();

      logger.info("exited disable");
   }

   /**
    * This function is called once when autonomous mode is entered.
    */
   @Override
   public void autonomousInit() {
      logger.info("initializing autonomous");

      ModeFollowers.getInstance().initAutonomous();

      // schedule the autonomous command (example)
      autonomousCommand = robotContainer.getAutonomousCommand();
      if (autonomousCommand != null) {
         autonomousCommand.schedule();
      }

      super.autonomousInit();

      logger.info("initialized autonomous");
   }

   /**
    * This function is called once when autonomous mode is exited.
    */
   @Override
   public void autonomousExit() {
      logger.info("exiting autonomous");

      ModeFollowers.getInstance().exitAutonomous();

      super.autonomousExit();

      logger.info("exited autonomous");
   }

   /**
    * This function is called once when teleop mode is entered.
    */
   @Override
   public void teleopInit() {
      logger.info("initializing teleop");

      // FIXME: Should be / is already elsewhere?
      // This makes sure that the autonomous stops running when teleop starts
      // running.
      if (autonomousCommand != null) {
         autonomousCommand.cancel();
      }

      ModeFollowers.getInstance().initTeleop();

      super.teleopInit();

      logger.info("initialized teleop");
   }

   /**
    * This function is called once when teleop mode is exited.
    */
   @Override
   public void teleopExit() {
      logger.info("exiting teleop");

      ModeFollowers.getInstance().exitTeleop();

      super.teleopExit();

      logger.info("exited teleop");
   }

}
