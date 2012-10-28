TeamCityNetPublisherPlugin
==========================

This plugin helps with publishing artifacts to net share.

Build
-----
You should have installed TeamCity, JAVA, ANT
run ant in root directory
get compiled TeamCityNetPublisherPlugin.zip from dist folder


Instalation
------------
copy TeamCityNetPublisherPlugin.zip to plugins directory (for windows %USERPROFILE%\.BuildServer\plugins) and resart TeamCity server

Using
-----
Add "Publish artifacts to specific path" build feature and specify path to publishing like this "\\SERVER\SHARE\BUILDS\%env.BUILD_NUMBER%'" or "C:\PATH\MY BUILDS\%env.BUILD_NUMBER%"
Artifacts will published only for success builds.
TemCity build server acount should have permissions to specified publishing path.


If you have any questions feel free to ask me  

    Eduard Kibort
    http://twitter.com/advard



