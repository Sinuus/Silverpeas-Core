/*
 * Copyright (C) 2000 - 2017 Silverpeas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * As a special exception to the terms and conditions of version 3.0 of
 * the GPL, you may redistribute this Program in connection with Free/Libre
 * Open Source Software ("FLOSS") applications as described in Silverpeas's
 * FLOSS exception.  You should have received a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "https://www.silverpeas.org/legal/floss_exception.html"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.silverpeas.core.contribution.template.publication;

import org.junit.Assert;
import org.silverpeas.core.contribution.content.form.Field;
import org.silverpeas.core.contribution.content.form.FieldTemplate;
import org.silverpeas.core.contribution.content.form.RecordTemplate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.silverpeas.core.test.rule.CommonAPI4Test;
import org.silverpeas.core.admin.component.model.GlobalContext;

import java.io.File;

import static org.silverpeas.core.contribution.template.publication.Assertion.assertEquals;
import static java.io.File.separatorChar;
import static org.apache.commons.io.FileUtils.getFile;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author ehugonnet
 */
public class PublicationTemplateImplTest {
  private static final char SEPARATOR = separatorChar;

  private File TEMPLATES_PATH;

  @Rule
  public CommonAPI4Test commonAPI4Test = new CommonAPI4Test();

  @Before
  public void setUpClass() throws Exception {
    final File targetDir = getFile(
        PublicationTemplateImplTest.class.getProtectionDomain().getCodeSource().getLocation()
            .getFile());
    TEMPLATES_PATH = getFile(targetDir, "templateRepository");

    PublicationTemplateManager.templateDir = TEMPLATES_PATH.getPath();
  }

  @Test
  public void testGetRecordTemplateSimple() throws Exception {
    String xmlFileName = "template" + SEPARATOR + "data.xml";
    // Pay attention to not declare org.silverpeas.core.contribution.content.form.displayers.PdcPositionsFieldDisplayer
    // inside types.properties cause this class is not available inside silverpeas-core project
    PublicationTemplateImpl instance = new PublicationTemplateImpl();
    instance.setDataFileName(xmlFileName);
    instance.setFileName("personne.xml");
    RecordTemplate result = instance.getRecordTemplate();
    FieldTemplate fieldTemplate = result.getFieldTemplate("civilite");
    Field field = fieldTemplate.getEmptyField();
    assertThat(field, is(notNullValue()));
    Assert.assertEquals("Civilité", fieldTemplate.getLabel("fr"));
    Assert.assertEquals(2, fieldTemplate.getParametersObj().size());
  }

  @Test
  @Ignore
  public void testTemplateVisibilityOnApplications() throws Exception {
    // template.xml is only applicable to component kmelia
    GlobalContext globalContext = new GlobalContext("WA1");
    globalContext.setComponentName("kmelia");
    PublicationTemplateManager manager = PublicationTemplateManager.getInstance();

    // template is visible to all kmelia instances
    assertThat(manager.isPublicationTemplateVisible("template.xml", globalContext), is(true));

    globalContext.setComponentName("webPages");
    // template is not visible to other components
    assertThat(manager.isPublicationTemplateVisible("template.xml", globalContext), is(false));
  }

  @Test
  @Ignore
  public void testTemplateVisibilityOnInstances() throws Exception {
    // template.xml is only applicable to only both instances
    GlobalContext globalContext = new GlobalContext("WA1");
    globalContext.setComponentName("webPages");
    PublicationTemplateManager manager = PublicationTemplateManager.getInstance();

    // template is not visible to all webPages instances
    assertThat(manager.isPublicationTemplateVisible("sandbox.xml", globalContext), is(false));

    globalContext.setComponentName("kmelia");
    // template is not visible to other components
    assertThat(manager.isPublicationTemplateVisible("sandbox.xml", globalContext), is(false));

    globalContext.setComponentName(null);
    globalContext.setComponentId("kmelia123");
    assertThat(manager.isPublicationTemplateVisible("sandbox.xml", globalContext), is(false));

    globalContext.setComponentId("kmelia12");
    assertThat(manager.isPublicationTemplateVisible("sandbox.xml", globalContext), is(true));
  }
}
