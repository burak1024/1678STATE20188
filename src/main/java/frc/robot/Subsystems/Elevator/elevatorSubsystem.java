package frc.robot.Subsystems.Elevator;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.lib.Subsystem;

public class elevatorSubsystem extends Subsystem {
    private final TalonFX elevatorMotor1 = new TalonFX(0);
    private final TalonFX elevatorMotor2 = new TalonFX(0);
    private final MotionMagicVoltage Motion = new MotionMagicVoltage(0).withEnableFOC(true);
    private static elevatorSubsystem instance;

    public static elevatorSubsystem getInstance() {
        if (instance == null) {
            instance = new elevatorSubsystem();
        }
        return instance;
    }

    public static elevatorSubsystem subsystem() {
        return getInstance();
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        HP(1);

        public int stateNum;

        state(int stateNum) {
            this.stateNum = stateNum;
        }

    }
    public Runnable [][]methods = {
        {
            ()->emptyMethod(),
            ()->emptyMethod(),
            ()->emptyMethod()
        },
        {
            ()->HPIMethods(),
            ()->emptyMethod(),
            ()->HPEMethods()
        }
    };
    public void changeState(state newState) {
        methods[currentState.stateNum][2].run();
        currentState = newState;
        methods[currentState.stateNum][0].run();
        super.activeStatePeriodic = methods[newState.stateNum][1];
    }
    private void HPIMethods(){
        elevatorMotor1.setControl(Motion.withPosition(6));
        elevatorMotor2.setControl(Motion.withPosition(6));
    }
    private void HPEMethods(){
        elevatorMotor1.setControl(Motion.withPosition(0));
        elevatorMotor2.setControl(Motion.withPosition(0));
    }
    

}
