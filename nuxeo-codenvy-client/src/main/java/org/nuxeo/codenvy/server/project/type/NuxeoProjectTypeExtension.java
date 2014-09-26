/*******************************************************************************
 * (C) Copyright 2014 Nuxeo SA (http://nuxeo.com/) and contributors.
 *
 *       All rights reserved. This program and the accompanying materials
 *       are made available under the terms of the GNU Lesser General Public License
 *       (LGPL) version 2.1 which accompanies this distribution, and is available at
 *       http://www.gnu.org/licenses/lgpl-2.1.html
 *
 *       This library is distributed in the hope that it will be useful,
 *       but WITHOUT ANY WARRANTY; without even the implied warranty of
 *       MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *       Lesser General Public License for more details.
 *
 *       Contributors:
 *       vpasquier <vpasquier@nuxeo.com>
 *******************************************************************************/
package org.nuxeo.codenvy.server.project.type;

import com.codenvy.api.project.server.ProjectTypeDescriptionRegistry;
import com.codenvy.api.project.server.ProjectTypeExtension;
import com.codenvy.api.project.shared.Attribute;
import com.codenvy.api.project.shared.ProjectTemplateDescription;
import com.codenvy.api.project.shared.ProjectType;
import com.codenvy.ide.Constants;
import com.codenvy.ide.server.ProjectTemplateDescriptionLoader;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.nuxeo.codenvy.shared.NuxeoAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class NuxeoProjectTypeExtension implements ProjectTypeExtension {

    private final ProjectType projectType;

    private final ProjectTemplateDescriptionLoader projectTemplateDescriptionLoader;

    @Inject
    public NuxeoProjectTypeExtension(ProjectTypeDescriptionRegistry registry,
            ProjectTemplateDescriptionLoader projectTemplateDescriptionLoader) {
        this.projectTemplateDescriptionLoader =
                projectTemplateDescriptionLoader;
        this.projectType = new ProjectType(NuxeoAttributes.NUXEO_ID,
                NuxeoAttributes.WIZARD_TITLE,
                NuxeoAttributes.NUXEO_CATEGORY, null,
                NuxeoAttributes.NUXEO_DEFAULT_RUNNER);
        registry.registerProjectType(this);
    }

    @Override
    public ProjectType getProjectType() {
        return projectType;
    }

    @Override
    public List<Attribute> getPredefinedAttributes() {
        final List<Attribute> list = new ArrayList<>(1);
        list.add(new Attribute(Constants.LANGUAGE, "java"));
        return list;
    }

    @Override
    public List<ProjectTemplateDescription> getTemplates() {
        Map<String, String> params = new HashMap<>(2);
        params.put("branch", "master");
        params.put("cleanVcs", "true");
        final List<ProjectTemplateDescription> list = new ArrayList<>(1);

        list.add(new ProjectTemplateDescription(NuxeoAttributes.NUXEO_CATEGORY,
                "git",
                NuxeoAttributes.WIZARD_TITLE,
                NuxeoAttributes.WIZARD_DESC,
                NuxeoAttributes.TEMPLATE_URL,
                params,
                null,
                null,
                NuxeoAttributes.NUXEO_DEFAULT_RUNNER,
                null,
                null));

        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getIconRegistry() {
        return null;
    }
}
