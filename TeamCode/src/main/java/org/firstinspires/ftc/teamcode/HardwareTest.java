/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@com.qualcomm.robotcore.eventloop.opmode.Autonomous(name="Template: Linear OpMode", group="Linear Opmode")  // @Autonomous(...) is the other common choice
public class HardwareTest extends LinearOpMode {

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    Drive drive;
    DcMotor motorGun1;
    DcMotor motorGun2;

    DcMotor motorLeftBack;
    DcMotor motorLeftFront;
    DcMotor motorRightBack;
    DcMotor motorRightFront;

    DcMotor motorIntake;

    DcMotor motorArm;

    @Override
    public void runOpMode() {

        drive = new Drive(hardwareMap, "gyro", telemetry);
        drive.resetEncoders();

        Gyro gyro = new Gyro(hardwareMap, "gyro");

        motorGun1 = hardwareMap.dcMotor.get("gun 1");
        motorGun2 = hardwareMap.dcMotor.get("gun 2");
        motorGun1.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorGun2.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        motorGun1.setDirection(DcMotorSimple.Direction.REVERSE);
        motorGun2.setDirection(DcMotorSimple.Direction.FORWARD);

        motorIntake = hardwareMap.dcMotor.get("intake");
        motorIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorArm = hardwareMap.dcMotor.get("catapult");
        motorArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorLeftBack = hardwareMap.dcMotor.get("back left");
        motorLeftFront = hardwareMap.dcMotor.get("front left");
        motorRightBack = hardwareMap.dcMotor.get("back right");
        motorRightFront = hardwareMap.dcMotor.get("front right");

        motorLeftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorLeftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorLeftBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorLeftFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightBack.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorRightFront.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        motorLeftBack.setPower(0.618);
        sleep(500);
        motorLeftBack.setPower(0);
        motorLeftFront.setPower(0.618);
        sleep(500);
        motorLeftFront.setPower(0);
        motorRightBack.setPower(0.618);
        sleep(500);
        motorRightBack.setPower(0);
        motorRightFront.setPower(0.618);
        sleep(500);
        motorRightFront.setPower(0);

        if (Math.abs(motorLeftBack.getCurrentPosition()) - 30 > 0) {
            telemetry.addData("leftBack: ", "good");
        } else {
            telemetry.addData("leftBack: ", "bad");
        }

        if (Math.abs(motorRightBack.getCurrentPosition()) - 30 > 0) {
            telemetry.addData("rightBack: ", "good");
        } else {
            telemetry.addData("rightBack: ", "bad");
        }

        if (Math.abs(motorLeftFront.getCurrentPosition()) - 30 > 0) {
            telemetry.addData("LeftFront: ", "good");
        } else {
            telemetry.addData("LeftFront: ", "bad");
        }

        if (Math.abs(motorRightFront.getCurrentPosition()) - 30 > 0) {
            telemetry.addData("RightFront: ", "good");
        } else {
            telemetry.addData("RightFront: ", "bad");
        }



        waitForStart();
        runtime.reset();

        //start gun test
        motorGun1.setPower(.5);
        motorGun2.setPower(.5);

        //The dpad down exits the entire system, just in case.
        while (!gamepad1.y || gamepad1.dpad_down) { //wait until the y button is pressed
            sleep(1000);
        }

        //end gun test
        motorGun1.setPower(0);
        motorGun2.setPower(0);

        //start intake test
        motorIntake.setPower(.75);

        while (!gamepad1.b || gamepad1.dpad_down) { //wait until the b button is pressed
            sleep(1000);
        }

        //end intake test
        motorIntake.setPower(0);

        //start catapult test
        motorArm.setPower(.75);

        while (!gamepad1.a || gamepad1.dpad_down) { //wait until the a button is pressed
            sleep(1000);
        }

        //end catapult test
        motorArm.setPower(0);

        //y, b, a, x (buttons clockwise from top)
    }
}
