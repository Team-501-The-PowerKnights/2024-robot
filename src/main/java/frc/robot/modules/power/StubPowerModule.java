/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot.modules.power;

import org.slf4j.Logger;

import riolog.PKLogger;

class StubPowerModule extends BasePowerModule {

   /** Our classes' logger **/
   private static final Logger logger = PKLogger.getLogger(StubPowerModule.class.getName());

   public StubPowerModule() {
      logger.info("constructing");

      logger.info("constructed");
   }

   @Override
   public void updateTelemetry() {
      // No stub implementation
   }

   @Override
   public void updatePreferences() {
      // No stub implementation
   }

   @Override
   public void disable() {
      // No stub implementation

   }

   @Override
   public double getBusVoltage() {
      // No stub implementation
      return 0;
   }

   @Override
   public double getTotalCurrent() {
      // No stub implementation
      return 0;
   }

   @Override
   public double getTotalEnergy() {
      // No stub implementation
      return 0;
   }

   @Override
   public double getCurrent(int deviceID) {
      // No stub implementation
      return 0;
   }

}
