/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot.subsystems.example;

import org.slf4j.Logger;

import riolog.PKLogger;

/**
 * DOCS: Add your docs here.
 */
class StubExampleSubsystem extends BaseExampleSubsystem {

   /** Our classes' logger **/
   private static final Logger logger = PKLogger.getLogger(StubExampleSubsystem.class.getName());

   StubExampleSubsystem() {
      logger.info("constructing");

      logger.info("constructed");
   }

   @Override
   public void disable() {
      // TODO Auto-generated method stub
   }

   @Override
   public void updateTelemetry() {
      // TODO Auto-generated method stub
   }

   @Override
   public void stop() {
      // TODO Auto-generated method stub
   }

   @Override
   public void move(double speed) {
      // TODO Auto-generated method stub
   }

}
