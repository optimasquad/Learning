/**
 * 
 */
package com.itests.fx.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.itests.fx.ppv.itests.PpvAccountQdeCheckTest;
import com.itests.fx.ppv.itests.PpvOfferValidateTest;
import com.itests.fx.ppv.itests.PpvOrderItemAssembleTest;
import com.itests.fx.ppv.itests.PpvOutletFindByAccountTest;
import com.itests.fx.ppv.itests.PpvOutletFindByServiceTest;
import com.itests.fx.ppv.itests.PpvOutletFindTest;
import com.itests.fx.ppv.itests.PpvReferenceDataGetTest;

/**
 * @author jatinma
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ PpvAccountQdeCheckTest.class, PpvOfferValidateTest.class, PpvOrderItemAssembleTest.class,
		PpvOutletFindByAccountTest.class, PpvOutletFindTest.class, PpvReferenceDataGetTest.class,
		PpvOutletFindByServiceTest.class })
public class PpvTestSuite {

}
