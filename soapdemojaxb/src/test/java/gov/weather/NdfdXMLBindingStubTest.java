package gov.weather;

import org.apache.log4j.Logger;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

/**
 * Created by Punitha Anandan on 2/24/2017.
 */
public class NdfdXMLBindingStubTest {
    private final Logger log = Logger.getLogger(this.getClass());

    @Test
    public void latLonListZipCode() throws Exception {
        NdfdXMLBindingStub bindingStub = (NdfdXMLBindingStub) new NdfdXMLLocator().getndfdXMLPort();
        String response = bindingStub.latLonListZipCode("53711");
        String latlong = unmarshallResult(response);
        assertEquals("43.0798,-89.3875", latlong);
    }

    private String unmarshallResult(String response) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(DwmlType.class);
        try {
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            DwmlType dwml = (DwmlType) jaxbUnmarshaller.unmarshal(new StringReader(response));
            return dwml.getLatLonList();
        } catch (JAXBException jaxbException) {
            log.error("This is the jaxbException", jaxbException);
        }
        return null;
    }

}