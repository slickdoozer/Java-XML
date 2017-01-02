<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <!-- XSLT: eXtensible Style Language Used for styling XML -->
    <!-- This sets the output type (Not Needed for HTML) -->
	<xsl:output method="html" />

    <!-- xsl:template : States that we want to match the whole document -->
    <!-- All of the rules that follow will apply to the root node -->
		<xsl:template match="/">
	
		<html>
		<head><title>TV Shows</title></head>
		<body>
			<!-- Return the XSLT Version used -->
			Version: <xsl:value-of select="system-property('xsl:version')" /><br />
			
			<!-- Return the XSLT Vendor used -->
			<!-- libxml2 : Toolkit developed for the Gnome project -->
			Vendor: <xsl:value-of select="system-property('xsl:vendor')" /><br />
			Vendor URL: <xsl:value-of select="system-property('xsl:vendor-url')" /><br />
			
			<!-- Table Of Contents : Cycles through each node in the XML file -->
			<xsl:for-each select="tvshows/show">
				<!-- xsl:sort sorts based on your rules
                        select: node to base sort on
                        order: ascending (default) or descending
                        data-type: text or number -->
				<xsl:sort select="concat(name,release)" order="ascending" data-type="text" />
				
				<!-- generate-id() returns a unique node identifier -->
				<a href="#{generate-id(name)}">
				
				<!-- Get the value of name for the node -->
				<xsl:value-of select="name" /></a>(<xsl:value-of select="release" /> - <xsl:value-of select="end_date" />)<br />
				
			</xsl:for-each>
			
			<!-- List Shows : Cycles through each node in the XML file -->
			<xsl:for-each select="tvshows/show">
				<!-- xsl:sort sorts based on your rules
                        select: node to base sort on
                        order: ascending (default) or descending
                        data-type: text or number -->
				<xsl:sort select="concat(name,release)" order="ascending" data-type="text" />
				
				<!-- Print show name with a link to the list above -->
				<h3><a name="{generate-id(name)}"><xsl:value-of select="name" />
				(<xsl:value-of select="release" /> - <xsl:value-of select="end_date" />)
				</a></h3>
				
				<!-- Create IMG tag and add attributes to it -->
				<img>
					<!-- Set the value of the SRC attribute on the IMG tag -->
					<xsl:attribute name="src">
						<!-- Set the value for the attribute -->
                        <!-- You grab the attribute of a node with @ -->
						<xsl:value-of select="poster/@href" />
					</xsl:attribute>
				</img><br />
				
				<!-- Return the current node information -->
				<xsl:value-of select="current()" />
				
				<p>The show <xsl:value-of select="name" /> was released in
				<xsl:value-of select="release" /> by
				<xsl:value-of select="network" />. It had an average
				viewership of <xsl:value-of select="viewers" /> million people.
				It was cancelled in <xsl:value-of select="end_date" />.
				</p><br />
				
			</xsl:for-each>
			
			<!-- Output information conditionally in table format -->
			<h3>US-only Network Shows</h3>
			<table border="2">
			<tr><th>Name</th><th>Network</th><th>Viewers</th></tr>
			
			<xsl:for-each select="tvshows/show">
				
				<!-- Possible conditions to use: = != > &lt; -->
				<xsl:if test="network[@country!='UK']">
				
					<!-- 
					<xsl:if test="release &lt; 2006">
						<tr>
							<td><xsl:value-of select="name" /> (<xsl:value-of select="release" /> - <xsl:value-of select="end_date" />)</td>
							<td><xsl:value-of select="network" /></td>
							<td><xsl:value-of select="viewers" /></td>
						</tr>
					</xsl:if>
					-->
					
					<xsl:choose>
						
						<xsl:when test="release = 2006">
							<tr bgcolor="yellow">
								<td><xsl:value-of select="name" /> (<xsl:value-of select="release" /> - <xsl:value-of select="end_date" />)</td>
								<td><xsl:value-of select="network" /></td>
								<td><xsl:value-of select="viewers" /></td>
							</tr>
						</xsl:when>
						
						<xsl:when test="release > 2006">
							<tr bgcolor="orange">
								<td><xsl:value-of select="name" /> (<xsl:value-of select="release" /> - <xsl:value-of select="end_date" />)</td>
								<td><xsl:value-of select="network" /></td>
								<td><xsl:value-of select="viewers" /></td>
							</tr>
						</xsl:when>
						
						<xsl:otherwise>
							<tr bgcolor="pink">
								<td><xsl:value-of select="name" /> (<xsl:value-of select="release" /> - <xsl:value-of select="end_date" />)</td>
								<td><xsl:value-of select="network" /></td>
								<td><xsl:value-of select="viewers" /></td>
							</tr>
						</xsl:otherwise>
						
					</xsl:choose>
				
				</xsl:if>
												
			</xsl:for-each>
			
			</table>
			
		
		</body>
		</html>
		
	</xsl:template>
	
</xsl:stylesheet>