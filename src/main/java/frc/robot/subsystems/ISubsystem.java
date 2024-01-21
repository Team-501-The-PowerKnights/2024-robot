/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Subsystem;

import frc.robot.IModeFollower;
import frc.robot.telemetry.ITelemetryProvider;

/**
 * Note that this now extends the official <i>WPILib</i> interface of
 * <code>Subsystem</code>.
 *
 * @see edu.wpi.first.wpilibj2.command.Subsystem
 **/
public interface ISubsystem extends Subsystem, IModeFollower, ITelemetryProvider {

   /**
    * Called to load the default commands for the subsystem (both
    * autonomous and teleop); the values are determined from the
    * properties file and loaded dynamically.
    **/
   public void loadDefaultCommands();

   /**
    * Called to validate that the calibration values in the properties match the
    * physical values from the subsystem itself.
    **/
   public void validateCalibration();

   /**
    * Called to update any preferences associated with the subsystem. This will be
    * used at a minimum to update any PID values.
    **/
   public void updatePreferences();

   /**
    * Disable any active components in the subsystem.
    **/
   public void disable();

}
