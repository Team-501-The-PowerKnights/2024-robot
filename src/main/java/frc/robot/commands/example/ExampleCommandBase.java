/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot.commands.example;

import org.slf4j.Logger;

import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.example.ExampleFactory;
import frc.robot.subsystems.example.IExampleSubsystem;

import riolog.PKLogger;

/**
 * DOCS: Add your docs here.
 */
abstract public class ExampleCommandBase extends PKCommandBase {

   /** Our classes' logger **/
   private static final Logger logger = PKLogger.getLogger(ExampleCommandBase.class.getName());

   // Handle to our subsystem
   protected final IExampleSubsystem subsys;

   public ExampleCommandBase() {
      logger.info("constructing {}", getName());

      subsys = ExampleFactory.getInstance();
      addRequirements(subsys);

      logger.info("constructed");
   }

}
