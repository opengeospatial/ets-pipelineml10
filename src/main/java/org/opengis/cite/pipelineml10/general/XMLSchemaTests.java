package org.opengis.cite.pipelineml10.general;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.validation.Schema;

import org.opengis.cite.pipelineml10.ErrorMessage;
import org.opengis.cite.pipelineml10.ErrorMessageKeys;
import org.opengis.cite.pipelineml10.SuiteAttribute;
import org.opengis.cite.validation.ValidationErrorHandler;
import org.opengis.cite.validation.XmlSchemaCompiler;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

public class XMLSchemaTests {
	
	/**
     * Root test suite package (absolute path).
     */
    public static final String ROOT_PKG_PATH = "/org/opengis/cite/pipelineml10/";
    
    /**
     * [{@code @Test}] Verifies the validity of the PML application schema XML
     * document with respect to the W3C XML Schema specification. It must
     * satisfy all relevant constraints imposed by the XML Schema specification.
     * 
     * <p>
     * If the schema is successfully compiled with no errors it is added to the
     * ISuite context as the value of the {@link SuiteAttribute#SCHEMA schema}
     * attribute. The resulting (immutable, thread-safe) Schema object may then
     * be used to construct a Validator.
     * </p>
     * 
     * 
     * @see "ISO 19136:2007, cl. A.1.1.4 (Valid XML Schema)"
     * 
     * @param testContext
     *            The test (group) context.
     * @throws SAXException
     *             If a schema cannot be read.
     * @throws IOException
     *             If a schema resource cannot be accessed for any reason.
     * @throws URISyntaxException 
     *             If a string could not be parsed as a URI reference
     */
    @Test
    public void compileXMLSchema(ITestContext testContext) throws SAXException,
            IOException, URISyntaxException {
    	URL entityCatalog = this.getClass().getResource(
    			ROOT_PKG_PATH + "components.xsd");
        XmlSchemaCompiler xsdCompiler = new XmlSchemaCompiler(entityCatalog);
        Schema schema = xsdCompiler.compileXmlSchema(entityCatalog.toURI());
        ValidationErrorHandler errHandler = xsdCompiler.getErrorHandler();
        Assert.assertFalse(
                errHandler.errorsDetected(),
                ErrorMessage.format(ErrorMessageKeys.NOT_SCHEMA_VALID,
                        errHandler.getErrorCount(), errHandler.toString()));
        if (null != schema) {
            testContext.getSuite().setAttribute(
                    SuiteAttribute.SCHEMA.getName(), schema);
        }
    }
}
