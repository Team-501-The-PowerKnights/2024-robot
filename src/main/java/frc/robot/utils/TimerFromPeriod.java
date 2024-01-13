/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved. */
/* Open Source Software - may be modified and shared by other FRC teams */
/* under the terms of the Team501 license. The code must be accompanied */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project. */
/*-----------------------------------------------------------------------*/

package frc.robot.utils;

import org.slf4j.Logger;

import frc.robot.Team501Robot;

import riolog.PKLogger;

/**
 * 
 */
public class TimerFromPeriod {

   /** Our classes' logger **/
   private static final Logger logger = PKLogger.getLogger(TimerFromPeriod.class.getName());

   // Period retreived from robot (shouldn't ever change)
   private static final double period;

   static {
      period = Team501Robot.getLoopPeriod();
   }

   // Duration of timer (seconds)
   private final double duration;
   // Duration of timer (clicks)
   private long count;

   public TimerFromPeriod(double seconds) {
      logger.info("constructing for {}", seconds);

      duration = seconds;
      count = secondsToClicks(seconds);
      logger.trace("duration = {}, count = {}", duration, count);

      logger.info("constructed");
   }

   private long secondsToClicks(double seconds) {
      return Double.valueOf(Math.ceil(seconds / period)).longValue();
   }

   /**
    * 
    * @return
    */
   public void nextTic() {
      --count;
   }

   /**
    * 
    * @return
    */
   public boolean isExpired() {
      return (count >= 0 ? false : true);
   }

}
