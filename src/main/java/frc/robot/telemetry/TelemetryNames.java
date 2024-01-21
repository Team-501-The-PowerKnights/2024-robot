/*------------------------------------------------------------------------*/
/*- Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/*- Open Source Software - may be modified and shared by other FRC teams  */
/*- under the terms of the Team501 license. The code must be accompanied  */
/*- by the Team 501 - The PowerKnights license file in the root directory */
/*- of this project.                                                      */
/*------------------------------------------------------------------------*/

package frc.robot.telemetry;

import frc.robot.modules.ModuleNames;
import frc.robot.sensors.SensorNames;
import frc.robot.subsystems.SubsystemNames;

/**
 * Provides a standard way of defining names for the <i>Telemetry</i> used in
 * the program. No code should define or use a hard-coded string outside of the
 * ones defined in this class.
 **/
public final class TelemetryNames {

   private TelemetryNames() {
   }

   public final class Misc {
      private static final String name = "Misc";

      public static final String programmer = name + ".programmer";
      public static final String codeVersion = name + ".codeVersion";

      public static final String robotName = name + ".robotName";
      public static final String robotImpl = name + ".robotImpl";
      public static final String robotStatus = name + ".robotStatus";
      public static final String robotImplClass = name + ".robotImplClass";

      public static final String dsConnected = name + ".dsConnected";
      public static final String fmsConnected = name + ".fmsConnected";

      public static final String realAuto = name + ".realAuto";

      public static final String initStatus = name + ".initStatus";

      public static final String endGameStarted = name + ".endGameStarted";
   }

   public final class Scheduler {
      public static final String name = "Scheduler";

      public static final String status = name + ".status";
      // The current commands running on the robot
      public static final String currentCommands = name + ".currentCommands";
   }

   /***************
    * Managers
    ***************/

   public final class Preferences {
      private static final String name = "Preferences";

      public static final String status = name + ".status";
   }

   public final class Properties {
      private static final String name = "Properties";

      public static final String status = name + ".status";
   }

   public final class Telemetry {
      public static final String name = "Telemetry";

      public static final String status = name + ".status";
   }

   /***************
    * Modules
    ***************/

   public final class LED {
      private static final String name = ModuleNames.ledName;

      public static final String status = name + ".status";
      public static final String implClass = name + ".implClass";
      public static final String defCommand = name + "defCommand";

      public static final String enabled = name + ".enabled";
      public static final String color = name + ".color";
   }

   public final class Pneumatic {
      private static final String name = ModuleNames.pneumaticName;

      public static final String status = name + ".status";
      public static final String implClass = name + ".implClass";
      public static final String defCommand = name + "defCommand";

      public static final String enabled = name + ".enabled";
      public static final String pressureGood = name + ".pressureGood";
   }

   public final class Power {
      private static final String name = ModuleNames.powerName;

      public static final String status = name + ".status";
      public static final String implClass = name + ".implClass";
      public static final String defCommand = name + "defCommand";

      public static final String busVoltage = name + ".busVoltage";
      public static final String totalCurrent = name + ".totalCurrent";
      public static final String totalEnergy = name + ".totalEnergy";
   }

   /***************
    * Sensors
    ***************/

   public final class Gyro {
      private static final String name = SensorNames.gyroName;

      public static final String status = name + ".status";
      public static final String implClass = name + ".implClass";

      public static final String roll = name + ".roll";
      public static final String pitch = name + ".pitch";
      public static final String yaw = name + ".yaw";
      public static final String angle = name + ".angle";
      public static final String heading = name + ".heading";
   }

   /***************
    * Subsystems
    ***************/

   public final class Example {
      private static final String name = SubsystemNames.exampleName;

      public static final String status = name + ".status";
      public static final String implClass = name + ".implClass";
      public static final String autoCommand = name + ".autoCommand";
      public static final String teleCommand = name + ".teleCommand";
   }

}
