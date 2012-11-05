/*
 * Copyright 2000-2012 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package TeamCityNetPublisherPlugin.server;

import TeamCityNetPublisherPlugin.common.Util;
import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.serverSide.BuildFeature;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class NetPublisherBuildFeature extends BuildFeature {
    private final String myEditUrl;

    public NetPublisherBuildFeature(@NotNull final PluginDescriptor descriptor,
                              @NotNull final WebControllerManager web) {
        final String jsp = descriptor.getPluginResourcesPath("NetPublisherSettings.jsp");
        final String html = descriptor.getPluginResourcesPath("NetPublisherSettings.html");

        web.registerController(html, new BaseController() {
            @Override
            protected ModelAndView doHandle(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
                final ModelAndView mv = new ModelAndView(jsp);
                mv.getModel().put("requestUrl", html);
                mv.getModel().put("buildTypeId", getBuildTypeIdParameter(request));
                return mv;
            }
        });

        myEditUrl = html;
    }

    private String getBuildTypeIdParameter(final HttpServletRequest request) {
        return request.getParameter("id");
    }

    @NotNull
    @Override
    public String getType() {
        return Util.TYPE;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Publish artifacts to specific path";
    }

    @Override
    public String getEditParametersUrl() {
        return myEditUrl;
    }

    @Override
    public boolean isMultipleFeaturesPerBuildTypeAllowed() {
        return false;
    }

    @NotNull
    @Override
    public String describeParameters(@NotNull final Map<String, String> params) {
        StringBuilder result = new StringBuilder();
        if (params.containsKey(Util.PUBLISH_PATH) && !params.get(Util.PUBLISH_PATH).isEmpty())
        {
            result.append("Publishing artifacts to '"+params.get(Util.PUBLISH_PATH)+"' - "+params.get(Util.PUBLISH_ON));
        }
        else
        {
            result.append("Path for artifact publishing not specified.");
        }
        return result.toString();
    }

    @Override
    public Map<String, String> getDefaultParameters() {
        final Map<String, String> defaults = new HashMap<String, String>(1);
        defaults.put(Util.PUBLISH_PATH, "");
        defaults.put(Util.PUBLISH_ON, "always");
        return defaults;
    }

}
