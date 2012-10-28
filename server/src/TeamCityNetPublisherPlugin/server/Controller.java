package TeamCityNetPublisherPlugin.server;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.web.openapi.PluginDescriptor;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.ModelAndView;
import TeamCityNetPublisherPlugin.common.Util;

/**
 * Example custom page controller
 */
public class Controller extends BaseController {
  private PluginDescriptor myPluginDescriptor;

  public Controller(PluginDescriptor pluginDescriptor, WebControllerManager manager){
    myPluginDescriptor = pluginDescriptor;
    manager.registerController("/NetPublisherSettings.html", this);
  }

  @Override
  protected ModelAndView doHandle(@NotNull final HttpServletRequest request,@NotNull final HttpServletResponse response) throws Exception {
    ModelAndView view = new ModelAndView(myPluginDescriptor.getPluginResourcesPath("NetPublisherSettings.jsp"));
    final Map model = view.getModel();
    model.put("name", Util.NAME);
    return view;
  }
}
