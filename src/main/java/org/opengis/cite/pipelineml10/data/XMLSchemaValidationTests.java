package org.opengis.cite.pipelineml10.data;

import java.io.IOException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;

import org.opengis.cite.pipelineml10.CommonFixture;
import org.opengis.cite.pipelineml10.ErrorMessage;
import org.opengis.cite.pipelineml10.ErrorMessageKeys;
import org.opengis.cite.pipelineml10.SuiteAttribute;
import org.opengis.cite.validation.ValidationErrorHandler;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

/**
 * Includes various tests of capability 1.
 */
public class XMLSchemaValidationTests extends CommonFixture {

    /**
	 * A PML application schema.
	 */
	private Schema appSchema;
    
	/**
	 * Obtains the PML application schema from the ISuite context. The value of
	 * the  attribute is
	 * expected to be a Schema object.
	 * 
	 * @param testContext
	 *            The test (group) context.
	 */
	@BeforeClass
	public void getXMLSchema(ITestContext testContext) {
		this.appSchema = (Schema) testContext.getSuite().getAttribute(
				SuiteAttribute.SCHEMA.getName());
	}

	/**
	 * [{@code Test}] Verifies that a PML instance is valid with respect to its
	 * application schema.
	 * 
	 * <p style="margin-bottom: 0.5em">
	 * <strong>Sources</strong>
	 * </p>
	 * <ul>
	 * <li>Valid XML</li>
	 * </ul>
	 * 
	 * @throws SAXException
	 *             If a fatal error occurs (e.g. instance is not well-formed).
	 */
	@Test
	public void isXMLSchemaValid() throws SAXException {
		Validator validator = this.appSchema.newValidator();
		ValidationErrorHandler errHandler = new ValidationErrorHandler();
		validator.setErrorHandler(errHandler);
		try {
			validator.validate(new StreamSource(this.dataFile));
		} catch (IOException e) {
			// ignore--not processing a SAXSource here (see API documentation)
		}
		Assert.assertFalse(errHandler.errorsDetected(), ErrorMessage.format(
				ErrorMessageKeys.NOT_SCHEMA_VALID, errHandler.getErrorCount(),
				errHandler.toString()));
	}
}
