<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>

<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="requestUrl" type="java.lang.String" scope="request"/>
<jsp:useBean id="buildTypeId" type="java.lang.String" scope="request"/>

<tr>
  <td colspan="2">
    <em>Publish artifacts after build to specified folder.</em>
  </td>
</tr>

<tr class="noBorder" id="NetPublisherPlugin.publishPath.container">
  <th>Paths to publish:</th>
  <td>
	<props:textProperty name="NetPublisherPlugin.publishPath" className="longField" />
    <span class="smallNote" style="margin-left: 0;">
        Path to publish<br/>
    </span>
</tr>

