/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot.robot;

import edu.wpi.first.wpilibj2.command.Command;

public interface IRobot {

   /**************************
    * Subsystem Configuration
    **************************/

   // Common Subsystems

   public default boolean hasDrive() {
      return false;
   };

   // Robot Common Subsystems

   // Proto-Bot Unique Subsystems

   // Real-Bot Unique Subsystems

   // Example Subsystems

   public default boolean hasExample() {
      return false;
   }

   /**
    * Use this method to define your trigger->command mappings. Triggers can be
    * created via the {@link Trigger#Trigger(java.util.function.BooleanSupplier)}
    * constructor with an arbitrary predicate, or via the named factories in
    * {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses
    * for {@link CommandXboxController
    * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
    * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick
    * Flight joysticks}.
    */
   public void configureBindings();

   /**
    * Create the <code>AutoChooser</code> with the list of available
    * <i>autonomous</i> options and put it on the dashboard.
    */
   public void createAutoChooser();

   /**
    * 
    * 
    * @return whether a "real" Auto has been selected
    */
   public boolean isRealAutoSelected();

   /**
    * Use this to pass the autonomous command to the main {@link Robot} class.
    *
    * @return the command to run in autonomous
    */
   public Command getAutonomousCommand();

}
