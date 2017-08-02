/*
 * ============================================================================
 * GNU General Public License
 * ============================================================================
 *
 * Copyright (C) 2013 
 * Institute of Computer Aided Automation, Automation Systems Group, TU Wien.
 * All rights reserved.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package at.ac.tuwien.auto.iotsys.gateway.connectors.bacnet;

import java.util.HashMap;

public class BacnetUnits {
	private static final HashMap<Integer, String> units = new HashMap<Integer, String>();

	static {
		// Acceleration
		units.put(166, "metersPerSecondPerSecond");
		// Area
		units.put(0, "squareMeters");
		units.put(116, "squareCentimeters");
		units.put(1, "squareFeet");
		units.put(115, "squareInches");
		// Currency
		units.put(105, "currency1");
		units.put(106, "currency2");
		units.put(107, "currency3");
		units.put(108, "currency4");
		units.put(109, "currency5");
		units.put(110, "currency6");
		units.put(111, "currency7");
		units.put(112, "currency8");
		units.put(113, "currency9");
		units.put(114, "currency10");
		// Electrical
		units.put(2, "milliamperes");
		units.put(3, "amperes");
		units.put(167, "amperesPerMeter");
		units.put(168, "amperesPerSquareMeter");
		units.put(169, "ampereSquareMeters");
		units.put(170, "farads");
		units.put(171, "henrys");
		units.put(4, "ohms");
		units.put(172, "ohmMeters");
		units.put(145, "milliohms");
		units.put(122, "kilohms");
		units.put(123, "megohms");
		units.put(173, "siemens"); // 1 mho equals 1 siemens
		units.put(174, "siemensPerMeter");
		units.put(175, "teslas");
		units.put(5, "volts");
		units.put(124, "millivolts");
		units.put(6, "kilovolts");
		units.put(7, "megavolts");
		units.put(8, "voltAmperes");
		units.put(9, "kilovoltAmperes");
		units.put(10, "megavoltAmperes");
		units.put(11, "voltAmperesReactive");
		units.put(12, "kilovoltAmperesReactive");
		units.put(13, "megavoltAmperesReactive");
		units.put(176, "voltsPerDegreeKelvin");
		units.put(177, "voltsPerMeter");
		units.put(14, "degreesPhase");
		units.put(15, "powerFactor");
		units.put(178, "webers");
		// Energy
		units.put(16, "joules");
		units.put(17, "kilojoules");
		units.put(125, "kilojoulesPerKilogram");
		units.put(126, "megajoules");
		units.put(18, "wattHours");
		units.put(19, "kilowattHours");
		units.put(146, "megawattHours");
		units.put(20, "btus");
		units.put(147, "kiloBtus");
		units.put(148, "megaBtus");
		units.put(21, "therms");
		units.put(22, "tonHours");
		// Enthalpy
		units.put(23, "joulesPerKilogramDryAir");
		units.put(149, "kilojoulesPerKilogramDryAir");
		units.put(150, "megajoulesPerKilogramDryAir");
		units.put(24, "btusPerPoundDryAir");
		units.put(117, "btusPerPound");
		// Entropy
		units.put(127, "joulesPerDegreeKelvin");
		units.put(151, "kilojoulesPerDegreeKelvin");
		units.put(152, "megajoulesPerDegreeKelvin");
		units.put(128, "joulesPerKilogramDegreeKelvin");
		// Force
		units.put(153, "newton");
		// Frequency
		units.put(25, "cyclesPerHour");
		units.put(26, "cyclesPerMinute");
		units.put(27, "hertz");
		units.put(129, "kilohertz");
		units.put(130, "megahertz");
		units.put(131, "perHour");
		// Humidity
		units.put(28, "gramsOfWaterPerKilogramDryAir");
		units.put(29, "percentRelativeHumidity");
		// Length
		units.put(30, "millimeters");
		units.put(118, "centimeters");
		units.put(31, "meters");
		units.put(32, "inches");
		units.put(33, "feet");
		// Light
		units.put(179, "candelas");
		units.put(180, "candelasPerSquareMeter");
		units.put(34, "wattsPerSquareFoot");
		units.put(35, "wattsPerSquareMeter");
		units.put(36, "lumens");
		units.put(37, "luxes");
		units.put(38, "footCandles");
		// Mass
		units.put(39, "kilograms");
		units.put(40, "poundsMass");
		units.put(41, "tons");
		// Mass Flow
		units.put(154, "gramsPerSecond");
		units.put(155, "gramsPerMinute");
		units.put(42, "kilogramsPerSecond");
		units.put(43, "kilogramsPerMinute");
		units.put(44, "kilogramsPerHour");
		units.put(119, "poundsMassPerSecond");
		units.put(45, "poundsMassPerMinute");
		units.put(46, "poundsMassPerHour");
		units.put(156, "tonsPerHour");
		// Power
		units.put(132, "milliwatts");
		units.put(47, "watts");
		units.put(48, "kilowatts");
		units.put(49, "megawatts");
		units.put(50, "btusPerHour");
		units.put(157, "kiloBtusPerHour");
		units.put(51, "horsepower");
		units.put(52, "tonsRefrigeration");
		// Pressure
		units.put(53, "pascals");
		units.put(133, "hectopascals");
		units.put(54, "kilopascals");
		units.put(134, "millibars");
		units.put(55, "bars");
		units.put(56, "poundsForcePerSquareInch");
		units.put(57, "centimetersOfWater");
		units.put(58, "inchesOfWater");
		units.put(59, "millimetersOfMercury");
		units.put(60, "centimetersOfMercury");
		units.put(61, "inchesOfMercury");
		// Temperature
		units.put(62, "degreesCelsius");
		units.put(63, "degreesKelvin");
		units.put(181, "degreesKelvinPerHour");
		units.put(182, "degreesKelvinPerMinute");
		units.put(64, "degreesFahrenheit");
		units.put(65, "degreeDaysCelsius");
		units.put(66, "degreeDaysFahrenheit");
		units.put(120, "deltaDegreesFahrenheit");
		units.put(121, "deltaDegreesKelvin");
		// Time
		units.put(67, "years");
		units.put(68, "months");
		units.put(69, "weeks");
		units.put(70, "days");
		units.put(71, "hours");
		units.put(72, "minutes");
		units.put(73, "seconds");
		units.put(158, "hundredthsSeconds");
		units.put(159, "milliseconds");
		// Torque
		units.put(160, "newtonMeters");
		// Velocity
		units.put(161, "millimetersPerSecond");
		units.put(162, "millimetersPerMinute");
		units.put(74, "metersPerSecond");
		units.put(163, "metersPerMinute");
		units.put(164, "metersPerHour");
		units.put(75, "kilometersPerHour");
		units.put(76, "feetPerSecond");
		units.put(77, "feetPerMinute");
		units.put(78, "milesPerHour");
		// Volume
		units.put(79, "cubicFeet");
		units.put(80, "cubicMeters");
		units.put(81, "imperialGallons");
		units.put(82, "liters");
		units.put(83, "usGallons");
		// Volumetric Flow
		units.put(142, "cubicFeetPerSecond");
		units.put(84, "cubicFeetPerMinute");
		units.put(85, "cubicMetersPerSecond");
		units.put(165, "cubicMetersPerMinute");
		units.put(135, "cubicMetersPerHour");
		units.put(86, "imperialGallonsPerMinute");
		units.put(87, "litersPerSecond");
		units.put(88, "litersPerMinute");
		units.put(136, "litersPerHour");
		units.put(89, "usGallonsPerMinute");
		// Other
		units.put(90, "degreesAngular");
		units.put(91, "degreesCelsiusPerHour");
		units.put(92, "degreesCelsiusPerMinute");
		units.put(93, "degreesFahrenheitPerHour");
		units.put(94, "degreesFahrenheitPerMinute");
		units.put(183, "jouleSeconds");
		units.put(186, "kilogramsPerCubicMeter");
		units.put(137, "kilowattHoursPerSquareMeter");
		units.put(138, "kilowattHoursPerSquareFoot");
		units.put(139, "megajoulesPerSquareMeter");
		units.put(140, "megajoulesPerSquareFoot");
		units.put(187, "newtonSeconds");
		units.put(188, "newtonsPerMeter");
		units.put(96, "partsPerMillion");
		units.put(97, "partsPerBillion");
		units.put(98, "percent");
		units.put(143, "percentObscurationPerFoot");
		units.put(144, "percentObscurationPerMeter");
		units.put(99, "percentPerSecond");
		units.put(100, "perMinute");
		units.put(101, "perSecond");
		units.put(102, "psiPerDegreeFahrenheit");
		units.put(103, "radians");
		units.put(184, "radiansPerSecond");
		units.put(104, "revolutionsPerMinute");
		units.put(185, "squareMetersPerNewton");
		units.put(189, "wattsPerMeterPerDegreeKelvin");
		units.put(141, "wattsPerSquareMeterDegreeKelvin");
	}

	public static String getUnit(int unit) {
		return units.get(unit);
	}
}
