package frc.robot.Subsystems.Arm;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class armConfig {
    public static TalonFXConfiguration config() {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.CurrentLimits.StatorCurrentLimit = armConstants.STATOR_CURRENT_LIMIT;

        config.MotionMagic.MotionMagicCruiseVelocity = armConstants.CRUISE_VELOCITY;
        config.MotionMagic.MotionMagicAcceleration = armConstants.ACCELERATION;
        config.MotionMagic.MotionMagicJerk = armConstants.JERK;

        config.Slot0.kS = armConstants.kS;
        config.Slot0.kV = armConstants.kV;
        config.Slot0.kA = armConstants.kA;

        config.Slot0.kP = armConstants.kP;
        config.Slot0.kI = armConstants.kI;
        config.Slot0.kD = armConstants.kD;

        config.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        return config;
    }
}
