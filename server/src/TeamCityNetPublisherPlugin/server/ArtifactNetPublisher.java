package TeamCityNetPublisherPlugin.server;

import jetbrains.buildServer.log.Loggers;
import jetbrains.buildServer.serverSide.artifacts.BuildArtifact;
import jetbrains.buildServer.serverSide.artifacts.BuildArtifacts;
import org.jetbrains.annotations.NotNull;

import java.io.*;

public class ArtifactNetPublisher  implements BuildArtifacts.BuildArtifactsProcessor {
    final String pathTo;

    public  ArtifactNetPublisher(final String pathTo){
        this.pathTo = pathTo;
    }

    private File pathCombine(String path1, String path2)
    {
        File file1 = new File(path1);
        return  new File(file1, path2);
    }

    @NotNull
    public Continuation processBuildArtifact(@NotNull BuildArtifact artifact) {
        File targetFile = pathCombine(pathTo, artifact.getRelativePath());
        Loggers.SERVER.info("\tPublishing artifact '"+targetFile+"'");

        if (artifact.isDirectory()){
            targetFile.mkdirs();
        }
        else {
            try{
                InputStream inStream = artifact.getInputStream();
                OutputStream outStream = new FileOutputStream(targetFile);

                int read = 0;
                byte[] bytes = new byte[10240];

                while ((read = inStream.read(bytes)) != -1) {
                    outStream.write(bytes, 0, read);
                }

                inStream.close();
                outStream.flush();
                outStream.close();
            }
            catch (IOException ex){
                Loggers.SERVER.error(ex);
            }
        }
        return Continuation.CONTINUE;
    }
}
