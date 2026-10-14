package frc.robot.Subsystems.intake;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class intakeConfig {
    public static TalonFXConfiguration config() {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.CurrentLimits.StatorCurrentLimit = intakeConstants.STATOR_CURRENT_LIMIT;

        config.MotionMagic.MotionMagicCruiseVelocity = intakeConstants.CRUISE_VELOCITY;
        config.MotionMagic.MotionMagicAcceleration = intakeConstants.ACCELERATION;
        config.MotionMagic.MotionMagicJerk = intakeConstants.JERK;

        config.Slot0.kS = intakeConstants.kS;
        config.Slot0.kV = intakeConstants.kV;
        config.Slot0.kA = intakeConstants.kA;

        config.Slot0.kP = intakeConstants.kP;
        config.Slot0.kI = intakeConstants.kI;
        config.Slot0.kD = intakeConstants.kD;

        config.MotorOutput.NeutralMode = NeutralModeValue.Coast;
        return config;
    }
}
