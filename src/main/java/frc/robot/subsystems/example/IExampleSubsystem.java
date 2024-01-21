/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot.subsystems.example;

import frc.robot.subsystems.ISubsystem;

/**
 * DOCS: Add your docs here.
 */
public interface IExampleSubsystem extends ISubsystem {

   /**
    * Stop the subsystem from any motion it may have been running under.
    */
   public void stop();

   /**
    * Move the subsystem under 'manual' control.
    *
    * @param speed speed to move at ("-" is down, "+" is up)
    */
   public void move(double speed);

}
