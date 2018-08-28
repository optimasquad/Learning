/**
 * 
 */
package com.itests.fx.service;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.itests.fx.common.JsonConfigFileReaderUtil;
import com.itests.fx.common.JsonToJavaObjectConverter;
import com.itests.fx.dao.ItestsDaoImpl;
import com.itests.fx.model.Account;

/**
 * @author jatinma
 *
 */
@Service
@Transactional
public class RestService {

	InputStream is = null;
	Properties prop = null;

	@Autowired
	ItestsDaoImpl itestsDao;

	/**
	 * @param restTemplate,
	 *            this is the exact call to the service
	 */
	static final org.slf4j.Logger logger = LoggerFactory.getLogger(RestService.class);

	@Transactional
	public String consumeService() {
		try {
			logger.info("Entering RestService to retrieve data");
			RestTemplate restTemplate = new RestTemplate();
			
			
			// Calling the service to retrieve the detail
			HttpHeaders headers = new HttpHeaders();
			headers.add("Accept", "application/json");
			headers.add("tenantId", "2");
			headers.add("operatorName", "arborsv");
			headers.set("Authorization", "Basic");
			String bodyParameters = JsonConfigFileReaderUtil.loadRequestFromStream("NCA.json");
			HttpEntity<String> entity = new HttpEntity<String>(bodyParameters, headers);
			String response = restTemplate.postForObject(
					"http://10.234.209.38:8080/fx_rest/rest/CustomerUdts/request?bridgeEndpoint=true", entity,
					String.class);
			System.out.println("The response is " + response);
			// post the json to the database or to the load the data from the
			// text file
			// There is the wrapper needed to call any of the Object8
			// Converstion....We have the data now being filtered,so can be
			// cascaded for the further use

			CopyOnWriteArrayList<Object> listOfObject = JsonToJavaObjectConverter
					.processData("com.amdocs.fx.model.Account", response, "Account");

			if (null != listOfObject && !listOfObject.isEmpty() && null != listOfObject.get(0)) {

				if (listOfObject.get(0) instanceof Account) {

					Account account = (Account) listOfObject.get(0);
					account.setAccountInternalId(account.getKey());
					System.out.println("The AcccountStatus is-" + account.getAccountStatus());
					System.out.println("The AccountInternalId is-" + account.getAccountInternalId());
				}

			}

			// We have the res entity that need to be cascaded for further
			// processing.....

			// There will be the response that will be cascaded
			// load the file from the config for the code
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return "jatin";

		// return response;
	}

}
