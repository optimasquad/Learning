package com.itests.fx.service;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.itests.fx.common.CommonUtility;
import com.itests.fx.common.ItestsEnum;
import com.itests.fx.common.JsonConfigFileReaderUtil;
import com.itests.fx.common.JsonToJavaObjectConverter;
import com.itests.fx.common.PropertyLoader;
import com.itests.fx.common.ReportGenerator;
import com.itests.fx.dao.ItestsDao;
import com.itests.fx.entities.ITESTS_REF;
import com.itests.fx.model.CustomerService;

/**
 * 
 * 
 * @author jatinma
 * 
 *         This is the NCA service created for calling the Dao integration
 *
 */
@Service("ncaService")
public class NCAService {

	/**
	 * @param restTemplate,
	 *            this is the exact call to the service
	 */
	static final org.slf4j.Logger logger = LoggerFactory.getLogger(NCAService.class);

	InputStream is = null;
	Properties prop = null;

	@Autowired
	ItestsDao itestsDao;

	@Autowired
	TransformerService transformerService;

	@Autowired
	RestTemplate restTemplate;

	/**
	 * 
	 * This is the generic method for creating the account
	 * 
	 * @return
	 */
	public String accountCreate() {
		try {
			HttpHeaders headers = CommonUtility.mapHttpHeaders();
			String bodyParameters = JsonConfigFileReaderUtil.loadRequestFromStream("NCA.json");
			HttpEntity<String> entity = new HttpEntity<String>(bodyParameters, headers);
			String response = restTemplate.postForObject(PropertyLoader.getPropValue("serviceurl")
					+ "/fx_rest/rest/CustomerUdts/request?bridgeEndpoint=true", entity, String.class);
			// map the response for the service Object
			CopyOnWriteArrayList<Object> serviceList = JsonToJavaObjectConverter
					.processData("com.amdocs.fx.model.CustomerService", response, "Service");
			System.out.println("The response is " + response);

			if (null != serviceList && !serviceList.isEmpty()) {
				// delete the table
				if (serviceList.get(0) instanceof CustomerService) {
					CustomerService customerService = (CustomerService) serviceList.get(0);
					if (null != customerService.getParentAccountInternalId()
							&& null != customerService.getServiceInternalId()
							&& null != customerService.getServiceInternalIdResets()) {
						itestsDao.delete();
						ITESTS_REF itestsRefAccount = new ITESTS_REF();
						itestsRefAccount.setItests_key(ItestsEnum.AccountInternalId.value());
						itestsRefAccount.setItests_value(customerService.getParentAccountInternalId());
						itestsDao.saveItests(itestsRefAccount);

						ITESTS_REF itestsRefInternalId = new ITESTS_REF();
						itestsRefInternalId.setItests_key(ItestsEnum.ServiceInternalId.value());
						itestsRefInternalId.setItests_value(customerService.getParentAccountInternalId());
						itestsDao.saveItests(itestsRefInternalId);

						ITESTS_REF itestsRefResets = new ITESTS_REF();
						itestsRefResets.setItests_key(ItestsEnum.ServiceInternalIdResets.value());
						itestsRefResets.setItests_value(customerService.getParentAccountInternalId());
						itestsDao.saveItests(itestsRefResets);
					}
				}
			}
			// check the account is successfully created and replace the tabs
			// their
			transformerService.xmlTransform();
			ReportGenerator.reportGenerate();
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return null;

	}

	/**
	 * This is the generic class to be called of retrieving the values from the
	 * database
	 * 
	 * @return
	 */
	public String retrieveServiceValue() {

		return null;

	}

}
