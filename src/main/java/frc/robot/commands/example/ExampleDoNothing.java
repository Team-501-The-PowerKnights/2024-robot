/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot.commands.example;

import org.slf4j.Logger;

import riolog.PKLogger;

/**
 * DOCS: Add your docs here.
 */
public class ExampleDoNothing extends ExampleCommandBase {

   /** Our classes' logger **/
   private static final Logger logger = PKLogger.getLogger(ExampleDoNothing.class.getName());

   public ExampleDoNothing() {
      logger.info("constructing {}", getName());

      logger.info("constructed");
   }

}
