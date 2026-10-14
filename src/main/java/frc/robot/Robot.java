// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import com.ctre.phoenix6.swerve.SwerveRequest;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import frc.robot.Subsystems.CommandSwerveDrivetrain;
import frc.robot.Subsystems.SuperStructure;
import frc.robot.Subsystems.SuperStructure.state;
import frc.robot.Subsystems.generated.TunerConstants;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
  private CommandPS4Controller driver =new CommandPS4Controller(0);
  private CommandPS4Controller driver2 = new CommandPS4Controller(1);
  public final CommandSwerveDrivetrain drivetrain = CommandSwerveDrivetrain.getSwerve();
  private final Field2d field = new Field2d();
private Command m_autonomousCommand;
  private double MaxSpeed = 1.0 * TunerConstants.kSpeedAt12Volts.in(MetersPerSecond);
  private double MaxAngularRate = RotationsPerSecond.of(0.75).in(RadiansPerSecond);
  public Robot() {
    drivetrain.setDefaultCommand(
        drivetrain.applyRequest(() -> new SwerveRequest.FieldCentric()
            .withVelocityX(-driver.getLeftY() * MaxSpeed)
            .withVelocityY(-driver.getLeftX() * MaxSpeed)
            .withRotationalRate(MathUtil.applyDeadband(-driver.getRightX(), 0.5) * MaxAngularRate)));
    driver2.R2().whileTrue(new InstantCommand(() -> SuperStructure.getInstance().changeState(state.intaking)));
    driver2.R1().whileTrue(new InstantCommand(() -> SuperStructure.getInstance().changeState(state.climbing)));
    driver2.L1().onTrue(new InstantCommand(()->SuperStructure.getInstance().changeState(state.grapping)));
    driver2.L2().onTrue(new InstantCommand(()->SuperStructure.getInstance().changeState(state.release)));
    driver2.povUp().onTrue(new InstantCommand(()->SuperStructure.getInstance().changeState(state.reverserelease)));
    driver2.povDown()
        .onTrue(new InstantCommand(() -> SuperStructure.getInstance().changeState(SuperStructure.state.idle)))
        .onFalse(new InstantCommand(() -> SuperStructure.getInstance().changeState(SuperStructure.state.idle)));
    SmartDashboard.putData("Field", field);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {}

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
