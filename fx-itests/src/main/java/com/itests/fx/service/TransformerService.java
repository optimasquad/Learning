/**
 * 
 */
package com.itests.fx.service;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.itests.fx.common.CommonUtility;
import com.itests.fx.common.ItestsEnum;
import com.itests.fx.dao.ItestsDaoImpl;

/**
 * @author jatinma
 *
 */

@Service
public class TransformerService {

	/**
	 * @param TransformerService,
	 *            this is the exact call to the service
	 */
	static final org.slf4j.Logger logger = LoggerFactory.getLogger(TransformerService.class);

	@Autowired
	ItestsDaoImpl itestsDao;

	private static final String SOURCE_FILE_DIRECTORY = "src\\main\\resources\\";

	public void xmlTransform() {
		try {
			logger.debug("TransformerService starts");
			// load all the xml files currently and replace with the keys their
			// required
			File[] files = CommonUtility
					.getFileList(SOURCE_FILE_DIRECTORY + "xml\\requests\\nonUdt\\extendedData\\Ppv\\", "xml");
			// we have the file list and replace the tags
			// Replace the files with it
			if (null != files) {
				for (File file : files) {
					Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
							.parse(new InputSource(file.getPath()));
					XPath xpath = XPathFactory.newInstance().newXPath();
					// This is for the AccountInternalId tag
					NodeList nodesAccountInternalId = (NodeList) xpath.evaluate(
							"//AccountInternalId[text()='{{AccountInternalId}}']", doc, XPathConstants.NODESET);
					NodeList nodesAccountId = (NodeList) xpath.evaluate("//AccountIn[text()='{{AccountInternalId}}']",
							doc, XPathConstants.NODESET);

					NodeList nodesAccountEqual = (NodeList) xpath.evaluate("//Equal[text()='{{AccountInternalId}}']",
							doc, XPathConstants.NODESET);

					String accountInternalvalue = itestsDao.load(ItestsEnum.AccountInternalId.value());
					if (null != accountInternalvalue) {
						for (int idx = 0; idx < nodesAccountInternalId.getLength(); idx++) {
							nodesAccountInternalId.item(idx).setTextContent(accountInternalvalue);
						}
						for (int idx = 0; idx < nodesAccountId.getLength(); idx++) {
							nodesAccountId.item(idx).setTextContent(accountInternalvalue);
						}
						for (int idx = 0; idx < nodesAccountEqual.getLength(); idx++) {
							nodesAccountEqual.item(idx).setTextContent(accountInternalvalue);
						}
					}
					// This is for the ServiceInternalId
					String serviceInternalIdValue = itestsDao.load(ItestsEnum.ServiceInternalId.value());
					NodeList nodesServiceInternalId = (NodeList) xpath.evaluate(
							"//ServiceInternalId[text()='{{ServiceInternalId}}']", doc, XPathConstants.NODESET);
					if (null != serviceInternalIdValue) {
						for (int idx = 0; idx < nodesServiceInternalId.getLength(); idx++) {
							nodesServiceInternalId.item(idx).setTextContent(serviceInternalIdValue);
						}
					}
					// This is for the serviceIternalIdResets
					NodeList nodesServiceInternalIdResets = (NodeList) xpath.evaluate(
							"//ServiceInternalIdResets[text()='{{ServiceInternalIdResets}}']", doc,
							XPathConstants.NODESET);
					String serviceInternalIdResetsValue = itestsDao.load(ItestsEnum.ServiceInternalIdResets.value());
					if (null != serviceInternalIdResetsValue) {
						for (int idx = 0; idx < nodesServiceInternalIdResets.getLength(); idx++) {
							nodesServiceInternalIdResets.item(idx).setTextContent(serviceInternalIdResetsValue);
						}
					}
					// 4- Save the result to a new XML doc
					Transformer xformer = TransformerFactory.newInstance().newTransformer();
					xformer.transform(new DOMSource(doc), new StreamResult(new File(
							SOURCE_FILE_DIRECTORY + "xml\\responses\\nonUdt\\extendedData\\Ppv\\" + file.getName())));
				}
			}
		} catch (IOException ee) {
			ee.printStackTrace();
		} catch (SAXException eer) {
			eer.printStackTrace();
		} catch (ParserConfigurationException ews) {
			ews.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("TransformerService ends");
	}

}