package frc.robot.Subsystems.intake;

import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.lib.Subsystem;

public class intakeSubsystem extends Subsystem {
    private final TalonFX IntakeMotor = new TalonFX(0);
    private final VoltageOut Voltage = new VoltageOut(0).withEnableFOC(true);
    private static intakeSubsystem instance;

    public static intakeSubsystem getInstance() {
        if (instance == null) {
            instance = new intakeSubsystem();
        }
        return instance;
    }

    public static intakeSubsystem subsystem() {
        return getInstance();
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        intaking(1),
        outtaking(2);

        public int stateNum;

        state(int stateNum) {
            this.stateNum = stateNum;
        }
    }

    public Runnable[][] methods = {
            {
                    () -> emptyMethod(),
                    () -> emptyMethod(),
                    () -> emptyMethod()
            },
            {
                    () -> intakingIMethods(),
                    () -> intakingPMethods(),
                    () -> intakingEMethods()
            },
            {
                    () -> outtakingIMethods(),
                    () -> outtakingPMethods(),
                    () -> outtakingEMethods()
            }
    };
    public void changeState(state newState) {
        methods[currentState.stateNum][2].run();
        currentState = newState;
        methods[currentState.stateNum][0].run();
        super.activeStatePeriodic = methods[newState.stateNum][1];
    }

    private void intakingIMethods() {
        IntakeMotor.setControl(Voltage.withOutput(8.0));
    }

    private void intakingPMethods() {
        IntakeMotor.setControl(Voltage.withOutput(8.0));
    }

    private void intakingEMethods() {
        IntakeMotor.setControl(Voltage.withOutput(0));
    }

    private void outtakingIMethods() {
        IntakeMotor.setControl(Voltage.withOutput(-8.0));
    }

    private void outtakingPMethods() {
        IntakeMotor.setControl(Voltage.withOutput(-8.0));
    }

    private void outtakingEMethods() {
        IntakeMotor.setControl(Voltage.withOutput(0));
    }

}
