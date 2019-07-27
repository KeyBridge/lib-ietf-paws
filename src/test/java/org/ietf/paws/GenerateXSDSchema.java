/*
 * Copyright 2016 Key Bridge.
 *
 * All rights reserved. Use is subject to license terms.
 * This software is protected by copyright.
 *
 * See the License for specific language governing permissions and
 * limitations under the License.
 */
package org.ietf.paws;

import ch.keybridge.lib.paws.PawsChannel;
import ch.keybridge.lib.paws.PawsInfo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import org.ietf.lib.paws.Error;
import org.ietf.lib.paws.*;
import org.ietf.lib.paws.message.*;
import org.junit.Test;

/**
 * Using JAXBContext to Generate an XML Schema from
 * http://wiki.eclipse.org/EclipseLink/Examples/MOXy/JAXB/GenerateSchema
 *
 * @author jesse
 */
public class GenerateXSDSchema {

  /**
   * Generate a XML Schema and store it in the docs/xsd directory.
   *
   * @throws JAXBException
   * @throws IOException
   */
  @Test
  public void testGenerateSchema() throws JAXBException, IOException {

    System.out.println("Generate a XML Schema and store it in the docs/xsd directory");

    List<Class> classes = new ArrayList<>();
    // message classes
    classes.add(InitializationRequest.class);
    classes.add(InititializationResponse.class);
    classes.add(RegistrationRequest.class);
    classes.add(RegistrationResponse.class);
    classes.add(AvailableSpectrumRequest.class);
    classes.add(AvailableSpectrumResponse.class);

    // object classes
    classes.add(AntennaCharacteristics.class);
    classes.add(DatabaseSpec.class);
    classes.add(DbUpdateSpec.class);
    classes.add(DeviceCapabilities.class);

    classes.add(DeviceDescriptor.class);
    classes.add(DeviceDescriptorEtsi.class);
    classes.add(DeviceDescriptorFcc.class);
    classes.add(DeviceDescriptorIsed.class);

    classes.add(DeviceOwner.class);
    classes.add(DeviceValidity.class);
    classes.add(Error.class);
    classes.add(EventTime.class);
    classes.add(FrequencyRange.class);
    classes.add(GeoLocation.class);
    classes.add(GeoSpectrumSpec.class);
    classes.add(RulesetInfo.class);
    classes.add(Spectrum.class);
    classes.add(SpectrumProfile.class);
    classes.add(SpectrumProfilePoint.class);
    classes.add(SpectrumSchedule.class);
    classes.add(SpectrumSpec.class);

    // key bridge proprietary
    classes.add(PawsChannel.class);
    classes.add(PawsInfo.class);

    JAXBContext jaxb = JAXBContext.newInstance(classes.toArray(new Class[classes.size()]));
    SchemaOutputResolver resolver = new MySchemaOutputResolver();
    jaxb.generateSchema(resolver);

  }

  /**
   * First you must create a class that extends
   * javax.xml.bind.SchemaOutputResolver.
   */
  private class MySchemaOutputResolver extends SchemaOutputResolver {

    @Override
    public Result createOutput(String namespaceURI, String suggestedFileName) throws IOException {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      /**
       * suggestedFileName is "schema1.xsd"
       */
      java.nio.file.Path path = Paths.get("docs", "xsd", "lib-ietf-paws." + sdf.format(new Date()) + "." + suggestedFileName);// + ".xsd");
      File file = path.toFile();
      System.out.println("  Writing XSD Schema file to " + file);
      StreamResult result = new StreamResult(file);
      result.setSystemId(file.toURI().toURL().toString());
      return result;
    }

  }

}
