package TeamCityNetPublisherPlugin.server;

import jetbrains.buildServer.log.Loggers;
import jetbrains.buildServer.serverSide.*;
import jetbrains.buildServer.serverSide.artifacts.BuildArtifacts;
import jetbrains.buildServer.serverSide.artifacts.BuildArtifactsViewMode;
import jetbrains.buildServer.util.EventDispatcher;
import org.jetbrains.annotations.NotNull;
import TeamCityNetPublisherPlugin.common.Util;

public class ServerListener extends BuildServerAdapter {
  private SBuildServer myServer;

  public ServerListener(@NotNull final EventDispatcher<BuildServerListener> dispatcher, SBuildServer server) {
    dispatcher.addListener(this);
    myServer = server;
  }

  @Override
  public void serverStartup() {
    Loggers.SERVER.info("Plugin '" + Util.NAME + "'. Is running on server version " + myServer.getFullServerVersion() + ".");
  }

  @Override
  public void buildFinished(SRunningBuild build) {
      String publishPath = build.getParametersProvider().get(Util.PUBLISH_PATH);
      String publishOn = build.getParametersProvider().get(Util.PUBLISH_ON);

      if (build.getBuildStatus().isSuccessful() || publishOn.equalsIgnoreCase("always")){

          Loggers.SERVER.info("Publishing artifacts to: '"+publishPath+"'");

          BuildArtifacts arts = build.getArtifacts(BuildArtifactsViewMode.VIEW_DEFAULT);
          arts.iterateArtifacts(new ArtifactNetPublisher(publishPath));

          Loggers.SERVER.info("All artifacts published to: '"+publishPath+"'");
      }
      else{
          Loggers.SERVER.info("Build failed - nothing to publish");
      }
  }
}
