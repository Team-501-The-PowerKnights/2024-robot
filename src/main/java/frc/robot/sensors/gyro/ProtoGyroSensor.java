/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot.sensors.gyro;

import org.slf4j.Logger;

import riolog.PKLogger;

public class ProtoGyroSensor extends BaseGyroSensor {

   /* Our classes logger */
   private static final Logger logger = PKLogger.getLogger(ProtoGyroSensor.class.getName());

   // Handle to the hardware sensor
   private final AHRSGyro mySensor;

   ProtoGyroSensor() {
      logger.info("constructing");

      mySensor = new AHRSGyro();

      logger.info("constructed");
   }

   @Override
   public double getRoll() {
      return mySensor.ahrs.getRoll();
   }

   @Override
   public double getPitch() {
      return -mySensor.ahrs.getPitch();
   }

   @Override
   public double getYaw() {
      return -mySensor.ahrs.getYaw();
   }

   @Override
   public double getAngle() {
      return -mySensor.ahrs.getAngle();
   }

}
