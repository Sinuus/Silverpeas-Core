/*
 * Copyright (C) 2000 - 2013 Silverpeas
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * As a special exception to the terms and conditions of version 3.0 of
 * the GPL, you may redistribute this Program in connection with Free/Libre
 * Open Source Software ("FLOSS") applications as described in Silverpeas's
 * FLOSS exception.  You should have recieved a copy of the text describing
 * the FLOSS exception, and it is also available here:
 * "http://www.silverpeas.org/docs/core/legal/floss_exception.html"
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.silverpeas.subscribe.mock;

import com.stratelia.webactiv.beans.admin.ComponentInstLight;
import com.stratelia.webactiv.beans.admin.DefaultOrganizationController;
import com.stratelia.webactiv.beans.admin.OrganizationController;
import com.stratelia.webactiv.beans.admin.UserDetail;

import javax.inject.Named;

import java.util.List;

import static org.mockito.Mockito.mock;

/**
 * @author Yohann Chastagnier
 */
@Named("organizationController")
public class OrganizationControllerMock extends DefaultOrganizationController {
  private static final long serialVersionUID = -8307476470533272352L;

  private final DefaultOrganizationController mock = mock(DefaultOrganizationController.class);

  private OrganizationController getMock() {
    return mock;
  }

  @Override
  public ComponentInstLight getComponentInstLight(final String sComponentId) {
    return getMock().getComponentInstLight(sComponentId);
  }

  @Override
  public UserDetail[] getAllUsersOfGroup(final String groupId) {
    return getMock().getAllUsersOfGroup(groupId);
  }

  @Override
  public String[] getAllGroupIdsOfUser(final String userId) {
    return getMock().getAllGroupIdsOfUser(userId);
  }
}