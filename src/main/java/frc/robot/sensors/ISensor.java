/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot.sensors;

import frc.robot.IModeFollower;
import frc.robot.telemetry.ITelemetryProvider;

/**
 * Add your docs here.
 */
public interface ISensor extends IModeFollower, ITelemetryProvider {

   /**
    * Enable the sensor.
    **/
   default public void enable() {
   };

   /**
    * Disable the sensor.
    **/
   default public void disable() {
   };

   /**
    * Called to update any preferences associated with the sensor.
    **/
   default public void updatePreferences() {
   };

}
