/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot.hmi;

import org.slf4j.Logger;

import riolog.PKLogger;

public class StubOperatorGamepad extends BaseOperatorGamepad {

   /** Our classes' logger **/
   private static final Logger logger = PKLogger.getLogger(StubOperatorGamepad.class.getName());

   public StubOperatorGamepad() {
      super("StubOperatorGamepad", 1);
      logger.info("constructing");

      logger.info("constructed");
   }

   @Override
   public void updateTelemetry() {
   }

}
