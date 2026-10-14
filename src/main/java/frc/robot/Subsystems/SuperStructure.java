package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Subsystems.Arm.armSubsystem;
import frc.robot.Subsystems.Climb.climbSubsystem;
import frc.robot.Subsystems.Elevator.elevatorSubsystem;
import frc.robot.Subsystems.intake.intakeSubsystem;

public class SuperStructure extends SubsystemBase{
    private static SuperStructure instance;
    public static SuperStructure getInstance(){
        if(instance==null){
            instance=new SuperStructure();
        }
        return instance;
    }
    private final Runnable[]methods={
        ()->idleIMethods(),
        ()->intakeIMethods(),
        ()->grapIMethods(),
        ()->reversereleaseIMethods(),
        ()->releaseIMethods(),
        ()->climbingIMethods(),
    };
    private void idleIMethods(){
        changeSubsystemStates(intakeSubsystem.state.idle, elevatorSubsystem.state.idle, climbSubsystem.state.idle, armSubsystem.state.idle);
    }
    private void intakeIMethods(){
        changeSubsystemStates(intakeSubsystem.state.intaking, elevatorSubsystem.state.idle, climbSubsystem.state.idle, armSubsystem.state.idle);
    }
    private void grapIMethods(){
        changeSubsystemStates(intakeSubsystem.state.idle, elevatorSubsystem.state.HP, climbSubsystem.state.idle, armSubsystem.state.idle);
    }
    private void reversereleaseIMethods(){
        changeSubsystemStates(intakeSubsystem.state.outtaking, elevatorSubsystem.state.HP, climbSubsystem.state.idle, armSubsystem.state.turning);
    }

    private void releaseIMethods(){
        changeSubsystemStates(intakeSubsystem.state.outtaking, elevatorSubsystem.state.HP, climbSubsystem.state.idle, armSubsystem.state.idle);
    }
    private void climbingIMethods(){
        changeSubsystemStates(intakeSubsystem.state.idle, elevatorSubsystem.state.HP, climbSubsystem.state.climbing, armSubsystem.state.idle);
    }
    private void changeSubsystemStates(intakeSubsystem.state intakeState,elevatorSubsystem.state elevState,climbSubsystem.state climbState,armSubsystem.state armState){
        intakeSubsystem.subsystem().changeState(intakeState);
        elevatorSubsystem.subsystem().changeState(elevState);
        climbSubsystem.subsystem().changeState(climbState);
        armSubsystem.subsystem().changeState(armState);
    }
    private state currentState = state.idle;
    public enum state{
        idle(0),
        intaking(1),
        grapping(2),
        reverserelease(3),
        release(4),
        climbing(5);
        public int stateNum;
        state(int stateNum){
            this.stateNum=stateNum;
        }

    }
     @Override
    public void periodic() {

        SmartDashboard.putString("SuperStructure/State", currentState.toString());

    }

    public SuperStructure() {
        intakeSubsystem.subsystem();
        elevatorSubsystem.subsystem();
        armSubsystem.subsystem();
        climbSubsystem.subsystem();
    }

    public void changeState(state newState) {
        if (this.currentState == newState)
            return;
        currentState = newState;
        methods[currentState.stateNum].run();
        SmartDashboard.putNumber("ActiveState", currentState.stateNum);
    }

}
