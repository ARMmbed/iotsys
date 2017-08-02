package at.ac.tuwien.auto.iotsys.gateway.connectors.rfid.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public final class RfidSerialCom implements Runnable, SerialPortEventListener {
	private static final Logger log = Logger.getLogger(RfidSerialCom.class.getName());

	private static final int SERIAL_TIMEOUT = 2000;
	private static final int BAUD_RATE = 9600;

	private SerialPort serialPort;
	private Thread readThread;
	private InputStream inputStream;
	private CommPort commPort;
	RfidFrame frame;

	public RfidSerialCom(String portName) {
		log.info("RfidSerialCom loaded ...");

		CommPortIdentifier portIdentifier;
		try {
			portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
			commPort = portIdentifier.open(portIdentifier.getName(), SERIAL_TIMEOUT);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		if (commPort instanceof SerialPort) {
			try {
				serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams(BAUD_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);
				inputStream = serialPort.getInputStream();
				serialPort.addEventListener(this);
				serialPort.notifyOnDataAvailable(true);
			} catch (Exception e) {
				e.printStackTrace();
			}

			log.info("Start listening on USB (/ttyUSBX) port ...");

			readThread = new Thread(this);
			readThread.start();
		}
	}

	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void serialEvent(SerialPortEvent event) {
		log.info("Serial Event recognized.");
		switch (event.getEventType()) {
		case SerialPortEvent.BI:
		case SerialPortEvent.OE:
		case SerialPortEvent.FE:
		case SerialPortEvent.PE:
		case SerialPortEvent.CD:
		case SerialPortEvent.CTS:
		case SerialPortEvent.DSR:
		case SerialPortEvent.RI:
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:
			byte[] buffer = new byte[4096];
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			try {
				int numBytes = 0;

				while (inputStream.available() > 0) {
					numBytes += inputStream.read(buffer);
				}
				StringBuffer hexString = new StringBuffer();
				for (int i = 0; i < buffer.length && i < numBytes; i++) {
					String hex = Integer.toHexString(0xFF & buffer[i]);
					if (hex.length() == 1)
						hexString.append('0');

					hexString.append(hex + " ");
				}
				// System.out.println(hexString.toString());

				frame = new RfidFrame(buffer, numBytes);
				frame.readPacket();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void closePort() {
		serialPort.close();
	}
}
