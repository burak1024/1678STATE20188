package frc.robot.Subsystems.Climb;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class climbConfig {
    public static TalonFXConfiguration config() {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.CurrentLimits.StatorCurrentLimit = climbConstants.STATOR_CURRENT_LIMIT;

        config.MotionMagic.MotionMagicCruiseVelocity = climbConstants.CRUISE_VELOCITY;
        config.MotionMagic.MotionMagicAcceleration = climbConstants.ACCELERATION;
        config.MotionMagic.MotionMagicJerk = climbConstants.JERK;

        config.Slot0.kS = climbConstants.kS;
        config.Slot0.kV = climbConstants.kV;
        config.Slot0.kA = climbConstants.kA;

        config.Slot0.kP = climbConstants.kP;
        config.Slot0.kI = climbConstants.kI;
        config.Slot0.kD = climbConstants.kD;

        config.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        return config;
    }
}
