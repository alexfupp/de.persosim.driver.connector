package de.persosim.driver.connector.ui;

import java.io.IOException;
import java.net.UnknownHostException;

import de.persosim.driver.connector.UnsignedInteger;
import de.persosim.driver.connector.VirtualDriverComm;
import de.persosim.driver.connector.exceptions.IfdCreationException;
import de.persosim.driver.connector.features.DefaultListener;
import de.persosim.driver.connector.features.MctReaderDirect;
import de.persosim.driver.connector.features.MctUniversal;
import de.persosim.driver.connector.features.ModifyPinDirect;
import de.persosim.driver.connector.features.PersoSimPcscProcessor;
import de.persosim.driver.connector.features.VerifyPinDirect;
import de.persosim.driver.connector.service.IfdConnector;
import de.persosim.driver.connector.service.IfdConnectorImpl;

/**
 * This class serves as a simple console interface to the native driver
 * connector.
 * 
 * @author mboonk
 *
 */
public class VirtualDriverConnectorConsole {

	public static void main(String[] args) throws UnknownHostException,
			IOException, IfdCreationException {
		IfdConnector connector = new IfdConnectorImpl();
		connector.addUi(new ConsoleUi());
		connector.addListener(new DefaultListener());
		connector.addListener(new VerifyPinDirect(new UnsignedInteger(0x42000DB2)));
		connector.addListener(new ModifyPinDirect(new UnsignedInteger(0x42000DB3)));
		connector.addListener(new MctReaderDirect(new UnsignedInteger(0x42000DB4)));
		connector.addListener(new MctUniversal(new UnsignedInteger(0x42000DB5)));
		connector.addListener(new PersoSimPcscProcessor(new UnsignedInteger(0x42000DCC)));
		connector.connect(new VirtualDriverComm("localhost", 5678));

	}

}

