package frc.robot.Subsystems.Climb;

import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import frc.robot.lib.Subsystem;

public class climbSubsystem extends Subsystem {
    private final TalonFX climbMotor = new TalonFX(0);
    private final MotionMagicVoltage Motion = new MotionMagicVoltage(0).withEnableFOC(true);
    private static climbSubsystem instance;

    public static climbSubsystem getInstance() {
        if (instance == null) {
            instance = new climbSubsystem();
        }
        return instance;
    }

    public static climbSubsystem subsystem() {
        return getInstance();
    }

    private state currentState = state.idle;

    public enum state {
        idle(0),
        climbing(1);

        public int stateNum;

        state(int stateNum) {
            this.stateNum = stateNum;
        }
    }
    public Runnable [][]methods ={
        {
            ()->emptyMethod(),
            ()->emptyMethod(),
            ()->emptyMethod()
        },{
            ()->climbingIMethods(),
            ()->emptyMethod(),
            ()->climbingEMethods()
        }
    };
    public void changeState(state newState) {
        methods[currentState.stateNum][2].run();
        currentState = newState;
        methods[currentState.stateNum][0].run();
        super.activeStatePeriodic = methods[newState.stateNum][1];
    }
    private void climbingIMethods(){
        climbMotor.setControl(Motion.withPosition(5));
    }
    private void climbingEMethods(){
        climbMotor.setControl(Motion.withPosition(0));
    }

}
