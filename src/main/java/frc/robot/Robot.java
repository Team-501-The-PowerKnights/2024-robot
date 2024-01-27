// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.preferences.PreferencesManager;
import frc.robot.telemetry.TelemetryNames;

import riolog.Level;
import riolog.PKLogger;
import riolog.ProblemTracker;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

   /** Our classes' logger **/
   private static final Logger logger = PKLogger.getLogger(Robot.class.getName());

   // Flag for started/running autonomous part of match
   @SuppressWarnings("unused")
   private boolean autonomousRunning;
   // Flag for having run first autonomous loop
   private boolean autonomousFirstRun;
   // Flag for having completed autonomous part of match
   private static boolean autonomousComplete;

   // Flag for whether to check if a valid auto mode is selected
   protected boolean autoModeCheckEnabled = true;

   // Flag for having started/running teleop part of match
   private boolean teleopRunning;
   // Flag for having run first teleop loop
   private boolean teleopFirstRun;
   // Flag for having completed teleop part of match
   private static boolean teleopComplete;

   /**
    * Constructor for Robot.
    *
    * @param period Loop period in seconds.
    */
   public Robot(double period) {
      super(period);
      logger.info("constructing w/ ");

      // Use the Debug level during start-up to get everything
      PKLogger.setLevel(Level.DEBUG);
   }

   /**
    * This function is run when the robot is first started up and should be used
    * for any initialization code.
    */
   @Override
   public void robotInit() {
      logger.info("initializing");

      // Initialize state variables
      autonomousRunning = false;
      autonomousFirstRun = false;
      autonomousComplete = false;

      autoModeCheckEnabled = !autonomousComplete;

      teleopRunning = false;
      teleopFirstRun = false;
      teleopComplete = false;

      // Set up end game determiner
      endGameStarted = false;
      SmartDashboard.putBoolean(TelemetryNames.Misc.endGameStarted, endGameStarted);
      addPeriodic(endGameDeterminer, 2.0);

      logger.info("initialized");
   }

   /**
    * This function is called every period, no matter the mode. Use this for items
    * like diagnostics that you want ran during disabled, autonomous, teleoperated
    * and test.
    *
    * <p>
    * This runs after the mode specific periodic functions, but before LiveWindow
    * and SmartDashboard integrated updating.
    */
   @Override
   public void robotPeriodic() {
      /*
       * Runs the Scheduler. This is responsible for polling buttons, adding
       * newly-scheduled commands, running already-scheduled commands, removing
       * finished or interrupted commands, and running subsystem periodic() methods.
       * This must be called from the robot's periodic block in order for anything in
       * the Command-based framework to work.
       */
      CommandScheduler.getInstance().run();
   }

   /**
    * This function is called once when the robot is disabled.
    */
   @Override
   public void disabledInit() {
      logger.info("initializing disabled");

      ModeFollowers.getInstance().initDisabled();

      if (isMatchComplete()) {
         logger.info("match complete");

         logFinalVisionData();

         logFinalPreferences();

         logMatchData();

         logErrorCounts();

         // for (IModule m : modules) {
         // m.disable();
         // }
         // for (ISensor s : sensors) {
         // s.disable();
         // }
         // for (ISubsystem s : subsystems) {
         // s.disable();
         // }
      }

      // (Re-)initialize end game state
      endGameStarted = false;
      SmartDashboard.putBoolean(TelemetryNames.Misc.endGameStarted, endGameStarted);

      // Should we be checking autonomous mode selection?
      autoModeCheckEnabled = !autonomousComplete;

      logger.info("initialized disable");
   }

   /**
    * Log the data associated with the vision to the tail of the log file.
    **/
   private void logFinalVisionData() {
      logger.info("vision data:");
   }

   /**
    * Log the data associated with the preferences to the tail of the log file.
    **/
   private void logFinalPreferences() {
      logger.info("preferences:");
      PreferencesManager.getInstance().logPreferences(logger);
   }

   /**
    * Log the data associated with the match to the tail of the log file. This
    * allows us to easily determine whether it is a real match, and what match it
    * was.
    **/
   private void logMatchData() {
      logger.info("EventName:     {}", DriverStation.getEventName());
      logger.info("MatchType:     {}", DriverStation.getMatchType());
      logger.info("MatchNumber:   {}", DriverStation.getMatchNumber());
      logger.info("ReplayNumber:  {}", DriverStation.getReplayNumber());
      logger.info("Alliance:      {}", DriverStation.getAlliance());
      logger.info("Location:      {}", DriverStation.getLocation());
   }

   /**
    * Log the count of errors and warnings from the logger to the tail of the log
    * file.
    */
   private void logErrorCounts() {
      long errorCount = ProblemTracker.getErrorCount();
      long warnCount = ProblemTracker.getWarnCount();
      logger.info("error counts: errorCount={}, warnCount={}", errorCount, warnCount);
   }

   /**
    * This function is called periodically whilst disabled.
    */
   @Override
   public void disabledPeriodic() {
   }

   /**
    * This function is called once when disabled mode is exited.
    */
   @Override
   public void disabledExit() {
      logger.info("exiting disabled");

      logger.info("exited disable");
   }

   /**
    * This function is called once when autonomous mode is entered.
    */
   @Override
   public void autonomousInit() {
      logger.info("initializing autonomous");

      autonomousRunning = true;
      autonomousFirstRun = false;
      autonomousComplete = false;

      logger.info("initialized autonomous");
   }

   /**
    * This function is called periodically whilst in autonomous mode.
    */
   @Override
   public void autonomousPeriodic() {
      if (!autonomousFirstRun) {
         autonomousFirstRun = true;
         logger.info("first run of autonomous periodic");
      }
   }

   /**
    * This function is called once when autonomous mode is exited.
    */
   @Override
   public void autonomousExit() {
      logger.info("exiting autonomous");

      autonomousRunning = false;
      autonomousComplete = true;

      logger.info("exited autonomous");
   }

   /**
    * This function is called once when teleop mode is entered.
    */
   @Override
   public void teleopInit() {
      logger.info("initializing teleop");

      teleopRunning = true;
      teleopFirstRun = false;
      teleopComplete = false;

      logger.info("initialized teleop");
   }

   /**
    * This function is called periodically whilst in teleop mode (operator
    * control).
    */
   @Override
   public void teleopPeriodic() {
      if (!teleopFirstRun) {
         teleopFirstRun = true;
         logger.info("first run of teleop periodic");
      }
   }

   /**
    * This function is called once when teleop mode is exited.
    */
   @Override
   public void teleopExit() {
      logger.info("exiting teleop");

      teleopRunning = false;
      teleopComplete = true;

      logger.info("exited teleop");
   }

   // Flag for in end game of match
   private static boolean endGameStarted;
   // Periodic runnable to do the determining off main loop
   private Runnable endGameDeterminer = new Runnable() {

      @Override
      public void run() {
         // Have to have field connected, otherwise remaining seconds counts up
         // Have to be running teleop
         // Have to not have triggered the end game start yet
         if (Team501Robot.isFieldConnected() && teleopRunning && !endGameStarted) {
            double remainingSeconds = DriverStation.getMatchTime();
            if (remainingSeconds <= 30) {
               endGameStarted = true;
               SmartDashboard.putBoolean(TelemetryNames.Misc.endGameStarted, endGameStarted);
            }
         }
      }

   };

   // TODO: Comment
   static public boolean isEndGameStarted() {
      return endGameStarted;
   }

   // TODO: Comment
   static public boolean isMatchComplete() {
      return (autonomousComplete && teleopComplete);
   }

   /**
    * This function is called once when test mode is enabled.
    *
    * <pre>
    * Note:
    *    https://www.chiefdelphi.com/t/running-a-command-in-test-mode/418286/3
    *    https://www.chiefdelphi.com/t/can-you-run-commands-in-test-methods-of-robot-java/349402
    * </pre>
    */
   @Override
   public void testInit() {
   }

   /** This function is called periodically during test mode. */
   @Override
   public void testPeriodic() {
   }

   /** This function is called once when test mode is exited. */
   @Override
   public void testExit() {
   }

   /** This function is called once when the robot is first started up. */
   @Override
   public void simulationInit() {
   }

   /** This function is called periodically whilst in simulation. */
   @Override
   public void simulationPeriodic() {
   }

}
