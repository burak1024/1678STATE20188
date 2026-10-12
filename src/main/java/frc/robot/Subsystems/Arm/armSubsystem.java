package frc.robot.Subsystems.Arm;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.lib.Subsystem;

public class armSubsystem extends Subsystem {
    private final TalonFX armMotor = new TalonFX(0);
    private final MotionMagicVoltage Motion = new MotionMagicVoltage(0).withEnableFOC(true);
    private static armSubsystem instance;

    public static armSubsystem getInstance() {
        if (instance == null) {
            instance = new armSubsystem();
        }
        return instance;
    }

    public static armSubsystem subsystem() {
        return getInstance();
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        turning(1);

        public int stateNum;

        state(int stateNum) {
            this.stateNum = stateNum;
        }
    }

    public Runnable [][]methods={
        {
            ()->emptyMethod(),
            ()->emptyMethod(),
            ()->emptyMethod()
        },
        {
            ()->turningIMethods(),
            ()->emptyMethod(),
            ()->turningEMethods()
        }
    };
    public void changeState(state newState) {
        methods[currentState.stateNum][2].run();
        currentState = newState;
        methods[currentState.stateNum][0].run();
        super.activeStatePeriodic = methods[newState.stateNum][1];
    }
    private void turningIMethods(){
        armMotor.setControl(Motion.withPosition(3));
    }
    private void turningEMethods(){
        armMotor.setControl(Motion.withPosition(0));
    }   
}
