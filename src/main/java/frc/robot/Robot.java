// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.TimedRobot;

import riolog.PKLogger;

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

   /**
    * Constructor for Robot.
    *
    * @param period Loop period in seconds.
    */
   public Robot(double period) {
      super(period);
      logger.info("constructing w/ ");
   }

   /**
    * This function is run when the robot is first started up and should be used
    * for any initialization code.
    */
   @Override
   public void robotInit() {
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
   }

   /**
    * This function is called once when the robot is disabled.
    */
   @Override
   public void disabledInit() {
      logger.info("initializing disabled");

      logger.info("initialized disable");
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

      logger.info("initialized autonomous");
   }

   /**
    * This function is called periodically whilst in autonomous mode.
    */
   @Override
   public void autonomousPeriodic() {
   }

   /**
    * This function is called once when autonomous mode is exited.
    */
   @Override
   public void autonomousExit() {
      logger.info("exiting autonomous");

      logger.info("exited autonomous");
   }

   /**
    * This function is called once when teleop mode is entered.
    */
   @Override
   public void teleopInit() {
      logger.info("initializing teleop");

      logger.info("initialized teleop");
   }

   /**
    * This function is called periodically whilst in teleop mode (operator
    * control).
    */
   @Override
   public void teleopPeriodic() {
   }

   /**
    * This function is called once when teleop mode is exited.
    */
   @Override
   public void teleopExit() {
      logger.info("exiting teleop");

      logger.info("exited teleop");
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
