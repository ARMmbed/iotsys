package at.ac.tuwien.auto.iotsys.gateway.util;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

import javax.xml.transform.TransformerConfigurationException;

import org.json.JSONException;
import org.openexi.proc.common.EXIOptionsException;
import org.openexi.sax.TransmogrifierException;
import org.xml.sax.SAXException;

import at.ac.tuwien.auto.iotsys.commons.util.CsvCreator;
import obix.Bool;
import obix.Int;
import obix.Obj;
import obix.Real;
import obix.Str;
import obix.io.BinObixDecoder;
import obix.io.BinObixEncoder;
import obix.io.ObixDecoder;
import obix.io.ObixEncoder;

/*******************************************************************************
 * Copyright (c) 2013 Institute of Computer Aided Automation, Automation Systems
 * Group, TU Wien. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer. 2. Redistributions in
 * binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other
 * materials provided with the distribution. 3. Neither the name of the
 * Institute nor the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE INSTITUTE AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE INSTITUTE OR CONTRIBUTORS BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * 
 * This file is part of the IoTSyS project.
 ******************************************************************************/

public class CPUEval {
	public static void main(String[] args) {
		int RUNS_PER_OBJ_ENC = 10000;
		int RUNS_PER_OBJ_ENC_REDUCED = 500;
		int REPEAT = 1;

		if (args.length == 3) {
			System.out.println("Modifying parameters: " + args[0] + " " + args[1] + " " + args[2]);
			RUNS_PER_OBJ_ENC = Integer.parseInt(args[0]);
			RUNS_PER_OBJ_ENC_REDUCED = Integer.parseInt(args[1]);
			REPEAT = Integer.parseInt(args[2]);
		}

		System.out.println("Starting eval: " + RUNS_PER_OBJ_ENC + ", " + RUNS_PER_OBJ_ENC_REDUCED + "," + REPEAT);

		Bool bool = new Bool(false);
		Int i = new Int(58);
		Real real = new Real(58.12);
		Str str = new Str("helloworld");

		ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
		threadMXBean.setThreadCpuTimeEnabled(true);
		threadMXBean.setThreadContentionMonitoringEnabled(true);
		long mainThreadId = 1;

		String xml = "";
		long startTime = 0;
		long sTime = 0;
		long endTime = 0;
		long eTime = 0;
		long encTime = 0;
		String line = "";
		byte[] payload = null;
		double cpuTime = 0;
		double timePerRequest = 0;
		// oBIX to XML
		// bool
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
		// bool = new Bool(false);
		// xml = ObixEncoder.toString(bool);
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		//
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
		//
		// line = "oBIX to XML; bool; startTime;" + startTime + ";endTime;"
		// + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		//
		// }
		//
		// // int
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
		// i = new Int(58);
		// xml = ObixEncoder.toString(real);
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
		//
		// line = "oBIX to XML; int; startTime;" + startTime + ";endTime;"
		// + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }
		//
		// // real
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
		// real = new Real(58.12);
		// xml = ObixEncoder.toString(real);
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		//
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
		//
		// line = "oBIX to XML; real; startTime;" + startTime + ";endTime;"
		// + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }
		//
		// // str
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
		// str = new Str("helloworld");
		// xml = ObixEncoder.toString(str);
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
		// line = "oBIX to XML; str; startTime;" + startTime + ";endTime;"
		// + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }
		//
		// // oBIX to JSON
		// // bool
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
		// bool = new Bool(false);
		// try {
		// xml = JsonUtil.fromXMLtoJSON(ObixEncoder.toString(bool));
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
		// line = "oBIX to JSON; bool; startTime;" + startTime + ";endTime;"
		// + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		//
		// }
		//
		// // int
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
		// i = new Int(58);
		// try {
		// xml = JsonUtil.fromXMLtoJSON(ObixEncoder.toString(i));
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
		// line = "oBIX to JSON; int; startTime;" + startTime + ";endTime;"
		// + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }
		//
		// // real
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
		// real = new Real(58.12);
		// try {
		// xml = JsonUtil.fromXMLtoJSON(ObixEncoder.toString(real));
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
		// line = "oBIX to JSON; real; startTime;" + startTime + ";endTime;"
		// + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }
		//
		// // str
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
		// str = new Str("helloworld");
		// try {
		// xml = JsonUtil.fromXMLtoJSON(ObixEncoder.toString(str));
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
		// line = "oBIX to JSON; str; startTime;" + startTime + ";endTime;"
		// + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }
		//
		// // oBIX to oBIX Binary
		// // bool
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
		// bool = new Bool(false);
		//
		// payload = BinObixEncoder.toBytes(bool);
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
		// line = "oBIX to oBIX binary; bool; startTime;" + startTime
		// + ";endTime;" + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		//
		// }
		//
		// // int
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
		// i = new Int(58);
		//
		// payload = BinObixEncoder.toBytes(i);
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
		// line = "oBIX to oBIX binary; int; startTime;" + startTime
		// + ";endTime;" + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }
		//
		// // real
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
		// real = new Real(58.12);
		//
		// payload = BinObixEncoder.toBytes(real);
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
		// line = "oBIX to oBIX binary; real; startTime;" + startTime
		// + ";endTime;" + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }
		//
		// // str
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
		// str = new Str("helloworld");
		//
		// payload = BinObixEncoder.toBytes(str);
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
		// line = "oBIX to oBIX binary; str; startTime;" + startTime
		// + ";endTime;" + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }
		//
		// // oBIX to EXI
		// // bool
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
		// bool = new Bool(false);
		//
		// try {
		// payload = ExiUtil.getInstance().encodeEXI(
		// ObixEncoder.toString(bool), false);
		// } catch (TransmogrifierException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (EXIOptionsException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
		// line = "oBIX to EXI (non schema); bool; startTime;" + startTime
		// + ";endTime;" + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		//
		// }
		//
		// // int
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
		// i = new Int(58);
		// try {
		// payload = ExiUtil.getInstance().encodeEXI(
		// ObixEncoder.toString(i), false);
		// } catch (TransmogrifierException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (EXIOptionsException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
		// line = "oBIX to EXI (non schema); int; startTime;" + startTime
		// + ";endTime;" + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }
		//
		// // real
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
		// real = new Real(58.12);
		// try {
		// payload = ExiUtil.getInstance().encodeEXI(
		// ObixEncoder.toString(real), false);
		// } catch (TransmogrifierException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (EXIOptionsException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
		// line = "oBIX to EXI (non schema); real; startTime;" + startTime
		// + ";endTime;" + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }
		//
		// // str
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
		// str = new Str("helloworld");
		// try {
		// payload = ExiUtil.getInstance().encodeEXI(
		// ObixEncoder.toString(str), false);
		// } catch (TransmogrifierException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (EXIOptionsException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
		// line = "oBIX to EXI (non schema); str; startTime;" + startTime
		// + ";endTime;" + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }
		//
		// // oBIX to EXI
		// // bool
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
		// bool = new Bool(false);
		//
		// try {
		// payload = ExiUtil.getInstance().encodeEXI(
		// ObixEncoder.toString(bool), true);
		// } catch (TransmogrifierException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (EXIOptionsException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
		// line = "oBIX to EXI (schema); bool; startTime;" + startTime
		// + ";endTime;" + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		//
		// }
		//
		// // int
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		//
		// for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
		// i = new Int(58);
		// try {
		// payload = ExiUtil.getInstance().encodeEXI(
		// ObixEncoder.toString(i), true);
		// } catch (TransmogrifierException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (EXIOptionsException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
		// line = "oBIX to EXI (schema); int; startTime;" + startTime
		// + ";endTime;" + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }
		//
		// // real
		// for (int x = 0; x < REPEAT; x++) {
		//
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
		// real = new Real(58.12);
		// try {
		// payload = ExiUtil.getInstance().encodeEXI(
		// ObixEncoder.toString(real), true);
		// } catch (TransmogrifierException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (EXIOptionsException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
		// line = "oBIX to EXI (schema); real; startTime;" + startTime
		// + ";endTime;" + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }
		//
		// // str
		// for (int x = 0; x < REPEAT; x++) {
		//
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
		// str = new Str("helloworld");
		// try {
		// payload = ExiUtil.getInstance().encodeEXI(
		// ObixEncoder.toString(str), true);
		// } catch (TransmogrifierException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (EXIOptionsException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
		// line = "oBIX to EXI (schema); str; startTime;" + startTime
		// + ";endTime;" + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }
		//
		// // oBIX to direct EXI
		// // bool
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
		// bool = new Bool(false);
		//
		// try {
		// payload = EXIEncoder.getInstance().toBytes(bool, true);
		// } catch (EXIOptionsException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (TransmogrifierException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (SAXException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
		// line = "oBIX to EXI (direct); bool; startTime;" + startTime
		// + ";endTime;" + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		//
		// }
		//
		// // int
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
		// i = new Int(58);
		// try {
		// payload = EXIEncoder.getInstance().toBytes(i, true);
		// } catch (EXIOptionsException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (TransmogrifierException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (SAXException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
		// line = "oBIX to EXI (direct); int; startTime;" + startTime
		// + ";endTime;" + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }
		//
		// // real
		// for (int x = 0; x < REPEAT; x++) {
		//
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
		// real = new Real(58.12);
		// try {
		// payload = EXIEncoder.getInstance().toBytes(real, true);
		// } catch (EXIOptionsException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (TransmogrifierException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (SAXException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
		// line = "oBIX to EXI (direct); real; startTime;" + startTime
		// + ";endTime;" + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }
		//
		// // str
		// for (int x = 0; x < REPEAT; x++) {
		// sTime = System.currentTimeMillis();
		// startTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
		// str = new Str("helloworld");
		// try {
		// payload = EXIEncoder.getInstance().toBytes(str, true);
		// } catch (EXIOptionsException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (TransmogrifierException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (SAXException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		//
		// endTime = threadMXBean.getThreadCpuTime(mainThreadId);
		// eTime = System.currentTimeMillis();
		//
		// encTime = endTime - startTime;
		// System.out.println("" + (eTime - sTime));
		// cpuTime = ((double) encTime) / 1000;
		// timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
		// line = "oBIX to EXI (direct); str; startTime;" + startTime
		// + ";endTime;" + endTime + ";encTime (micro seconds);" + cpuTime + ";"
		// + timePerRequest;
		// System.out.println(line);
		// CsvCreator.instance.writeLine(line);
		// }

		/********************************************************************************************************************/
		/********************************************************************************************************************/
		/********************************************************************************************************************/
		// oBIX from direct EXI

		CsvCreator.instance.writeLine("From EXI direct");
		try {
			payload = EXIEncoder.getInstance().toBytes(bool, true);
		} catch (EXIOptionsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransmogrifierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
				try {
					Obj fromBytes = EXIDecoder.getInstance().fromBytesSchema(payload);
				} catch (EXIOptionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
			line = "oBIX from EXI (direct); bool; startTime;" + startTime + ";endTime;" + endTime
					+ ";encTime (micro seconds);" + cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);

		}

		try {
			payload = EXIEncoder.getInstance().toBytes(i, true);
		} catch (EXIOptionsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransmogrifierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
				try {
					Obj fromBytes = EXIDecoder.getInstance().fromBytesSchema(payload);
				} catch (EXIOptionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
			line = "oBIX from EXI (direct); int; startTime;" + startTime + ";endTime;" + endTime
					+ ";encTime (micro seconds);" + cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);

		}

		try {
			payload = EXIEncoder.getInstance().toBytes(real, true);
		} catch (EXIOptionsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransmogrifierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
				try {
					Obj fromBytes = EXIDecoder.getInstance().fromBytesSchema(payload);
				} catch (EXIOptionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
			line = "oBIX from EXI (direct); real; startTime;" + startTime + ";endTime;" + endTime
					+ ";encTime (micro seconds);" + cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);

		}

		try {
			payload = EXIEncoder.getInstance().toBytes(str, true);
		} catch (EXIOptionsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransmogrifierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
				try {
					Obj fromBytes = EXIDecoder.getInstance().fromBytesSchema(payload);
				} catch (EXIOptionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
			line = "oBIX from EXI (direct); str; startTime;" + startTime + ";endTime;" + endTime
					+ ";encTime (micro seconds);" + cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);

		}

		/***********************************************************************************************************/
		/***********************************************************************************************************/
		/***********************************************************************************************************/
		// oBIX from EXI schema informed
		CsvCreator.instance.writeLine("From EXI over XML (schema informed)");

		try {
			payload = ExiUtil.getInstance().encodeEXI(ObixEncoder.toString(bool), true);
		} catch (EXIOptionsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransmogrifierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
				try {
					Obj fromBytes = ObixDecoder.fromString(ExiUtil.getInstance().decodeEXI(payload, true));
				} catch (EXIOptionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
			line = "oBIX from EXI (schema); bool; startTime;" + startTime + ";endTime;" + endTime
					+ ";encTime (micro seconds);" + cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);

		}

		try {
			payload = ExiUtil.getInstance().encodeEXI(ObixEncoder.toString(i), true);
		} catch (EXIOptionsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransmogrifierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
				try {
					Obj fromBytes = ObixDecoder.fromString(ExiUtil.getInstance().decodeEXI(payload, true));
				} catch (EXIOptionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
			line = "oBIX from EXI (schema); int; startTime;" + startTime + ";endTime;" + endTime
					+ ";encTime (micro seconds);" + cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);

		}

		try {
			payload = ExiUtil.getInstance().encodeEXI(ObixEncoder.toString(real), true);
		} catch (EXIOptionsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransmogrifierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
				try {
					Obj fromBytes = ObixDecoder.fromString(ExiUtil.getInstance().decodeEXI(payload, true));
				} catch (EXIOptionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
			line = "oBIX from EXI (schema); real; startTime;" + startTime + ";endTime;" + endTime
					+ ";encTime (micro seconds);" + cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);

		}

		try {
			payload = ExiUtil.getInstance().encodeEXI(ObixEncoder.toString(str), true);
		} catch (EXIOptionsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransmogrifierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
				try {
					Obj fromBytes = ObixDecoder.fromString(ExiUtil.getInstance().decodeEXI(payload, true));
				} catch (EXIOptionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
			line = "oBIX from EXI (schema); str; startTime;" + startTime + ";endTime;" + endTime
					+ ";encTime (micro seconds);" + cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);

		}

		/*****************************************************************************/
		/*****************************************************************************/
		/*****************************************************************************/
		// oBIX from EXI non-schema informed
		CsvCreator.instance.writeLine("From EXI over XML (non schema informed)");

		try {
			payload = ExiUtil.getInstance().encodeEXI(ObixEncoder.toString(bool), false);
		} catch (EXIOptionsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransmogrifierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
				try {
					Obj fromBytes = ObixDecoder.fromString(ExiUtil.getInstance().decodeEXI(payload, false));
				} catch (EXIOptionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
			line = "oBIX from EXI (non schema); bool; startTime;" + startTime + ";endTime;" + endTime
					+ ";encTime (micro seconds);" + cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);

		}

		try {
			payload = ExiUtil.getInstance().encodeEXI(ObixEncoder.toString(i), false);
		} catch (EXIOptionsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransmogrifierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
				try {
					Obj fromBytes = ObixDecoder.fromString(ExiUtil.getInstance().decodeEXI(payload, false));
				} catch (EXIOptionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
			line = "oBIX from EXI (non schema); int; startTime;" + startTime + ";endTime;" + endTime
					+ ";encTime (micro seconds);" + cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);

		}

		try {
			payload = ExiUtil.getInstance().encodeEXI(ObixEncoder.toString(real), false);
		} catch (EXIOptionsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransmogrifierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
				try {
					Obj fromBytes = ObixDecoder.fromString(ExiUtil.getInstance().decodeEXI(payload, false));
				} catch (EXIOptionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
			line = "oBIX from EXI (non schema); real; startTime;" + startTime + ";endTime;" + endTime
					+ ";encTime (micro seconds);" + cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);

		}

		try {
			payload = ExiUtil.getInstance().encodeEXI(ObixEncoder.toString(str), false);
		} catch (EXIOptionsException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (TransmogrifierException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC_REDUCED; k++) {
				try {
					Obj fromBytes = ObixDecoder.fromString(ExiUtil.getInstance().decodeEXI(payload, false));
				} catch (EXIOptionsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SAXException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TransformerConfigurationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC_REDUCED;
			line = "oBIX from EXI (non schema); str; startTime;" + startTime + ";endTime;" + endTime
					+ ";encTime (micro seconds);" + cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);

		}

		/********************************************************************************/
		/********************************************************************************/
		/********************************************************************************/
		// oBIX from XML
		CsvCreator.instance.writeLine("From XML");

		xml = ObixEncoder.toString(bool);

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();

			for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
				Obj obj = ObixDecoder.fromString(xml);
			}

			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
			line = "oBIX from XML; bool; startTime;" + startTime + ";endTime;" + endTime + ";encTime (micro seconds);"
					+ cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);
		}

		xml = ObixEncoder.toString(i);

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();

			for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
				Obj obj = ObixDecoder.fromString(xml);
			}

			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
			line = "oBIX from XML; int; startTime;" + startTime + ";endTime;" + endTime + ";encTime (micro seconds);"
					+ cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);
		}

		xml = ObixEncoder.toString(real);

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();

			for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
				Obj obj = ObixDecoder.fromString(xml);
			}

			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
			line = "oBIX from XML; real; startTime;" + startTime + ";endTime;" + endTime + ";encTime (micro seconds);"
					+ cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);
		}

		xml = ObixEncoder.toString(str);

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();

			for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
				Obj obj = ObixDecoder.fromString(xml);
			}

			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
			line = "oBIX from XML; str; startTime;" + startTime + ";endTime;" + endTime + ";encTime (micro seconds);"
					+ cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);
		}

		/********************************************************************************/
		/********************************************************************************/
		/********************************************************************************/
		// oBIX from JSON
		CsvCreator.instance.writeLine("From JSON");

		try {
			xml = JsonUtil.fromXMLtoJSON(ObixEncoder.toString(bool));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
				try {
					Obj obj = ObixDecoder.fromString(JsonUtil.fromJSONtoXML(xml));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
			line = "oBIX from JSON; bool; startTime;" + startTime + ";endTime;" + endTime + ";encTime (micro seconds);"
					+ cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);
		}

		try {
			xml = JsonUtil.fromXMLtoJSON(ObixEncoder.toString(i));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
				try {
					Obj obj = ObixDecoder.fromString(JsonUtil.fromJSONtoXML(xml));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
			line = "oBIX from JSON; int; startTime;" + startTime + ";endTime;" + endTime + ";encTime (micro seconds);"
					+ cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);
		}

		try {
			xml = JsonUtil.fromXMLtoJSON(ObixEncoder.toString(real));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
				try {
					Obj obj = ObixDecoder.fromString(JsonUtil.fromJSONtoXML(xml));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
			line = "oBIX from JSON; real; startTime;" + startTime + ";endTime;" + endTime + ";encTime (micro seconds);"
					+ cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);
		}

		try {
			xml = JsonUtil.fromXMLtoJSON(ObixEncoder.toString(str));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
				try {
					Obj obj = ObixDecoder.fromString(JsonUtil.fromJSONtoXML(xml));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
			line = "oBIX from JSON; str; startTime;" + startTime + ";endTime;" + endTime + ";encTime (micro seconds);"
					+ cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);
		}

		/***************************************************************************************/
		/***************************************************************************************/
		/***************************************************************************************/
		/***************************************************************************************/
		/***************************************************************************************/
		// oBIX from oBIX Binary
		CsvCreator.instance.writeLine("From oBIX Binary");
		payload = BinObixEncoder.toBytes(bool);

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
				Obj obj = BinObixDecoder.fromBytes(payload);
			}

			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
			line = "oBIX from oBIX Binary; bool; startTime;" + startTime + ";endTime;" + endTime
					+ ";encTime (micro seconds);" + cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);
		}

		payload = BinObixEncoder.toBytes(i);

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
				Obj obj = BinObixDecoder.fromBytes(payload);
			}

			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
			line = "oBIX from oBIX Binary; int; startTime;" + startTime + ";endTime;" + endTime
					+ ";encTime (micro seconds);" + cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);
		}

		payload = BinObixEncoder.toBytes(real);

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
				Obj obj = BinObixDecoder.fromBytes(payload);
			}

			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
			line = "oBIX from oBIX Binary; real; startTime;" + startTime + ";endTime;" + endTime
					+ ";encTime (micro seconds);" + cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);
		}

		payload = BinObixEncoder.toBytes(str);

		for (int x = 0; x < REPEAT; x++) {
			startTime = threadMXBean.getThreadCpuTime(mainThreadId);
			sTime = System.currentTimeMillis();
			for (int k = 0; k < RUNS_PER_OBJ_ENC; k++) {
				Obj obj = BinObixDecoder.fromBytes(payload);
			}

			endTime = threadMXBean.getThreadCpuTime(mainThreadId);
			eTime = System.currentTimeMillis();

			encTime = endTime - startTime;
			System.out.println("" + (eTime - sTime));
			cpuTime = ((double) encTime) / 1000;
			timePerRequest = cpuTime / RUNS_PER_OBJ_ENC;
			line = "oBIX from oBIX Binary; str; startTime;" + startTime + ";endTime;" + endTime
					+ ";encTime (micro seconds);" + cpuTime + ";" + timePerRequest;
			System.out.println(line);
			CsvCreator.instance.writeLine(line);
		}

	}

}
