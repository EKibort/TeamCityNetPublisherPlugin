
package TeamCityNetPublisherPlugin.server;

import TeamCityNetPublisherPlugin.common.Util;
import jetbrains.buildServer.serverSide.SBuild;
import jetbrains.buildServer.serverSide.SBuildFeatureDescriptor;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.parameters.AbstractBuildParametersProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class NetPublisherBuildParametersProvider extends AbstractBuildParametersProvider {
    @Override
    @NotNull
    public Map<String, String> getParameters(@NotNull SBuild build, boolean emulationMode) {
        Map<String, String> parameters = super.getParameters(build,emulationMode);

                SBuildType buildType = build.getBuildType();
        if (buildType != null){
            for (SBuildFeatureDescriptor feature : buildType.getBuildFeatures()){
                if (feature.getBuildFeature().getType().equals(Util.TYPE)){
                    parameters.put(Util.PUBLISH_PATH, feature.getParameters().get(Util.PUBLISH_PATH));
                    parameters.put(Util.PUBLISH_ON, feature.getParameters().get(Util.PUBLISH_ON));
                }
            }
        }
        return parameters;
    }
}
