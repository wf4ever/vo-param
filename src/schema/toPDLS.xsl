<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xmlns:pd="http://www.ivoa.net/xml/Parameter/v0.1">
     <xsl:output method="text"/>
	<xsl:template match="/">
		<xsl:apply-templates/>
	</xsl:template>
    <xsl:template match="pd:UWS_Service">
    <xsl:text>service {</xsl:text>
           <xsl:apply-templates/>
    <xsl:text>}</xsl:text>
    </xsl:template>
    <xsl:template match="pd:ParameterList">
    <xsl:text>parameters {</xsl:text>
           <xsl:apply-templates/>
    <xsl:text>}</xsl:text>
    </xsl:template>
    <xsl:template match="pd:parameter">
    <xsl:value-of select="pd:Name|pd:ParameterType" separator=" "/>
    <xsl:text>{</xsl:text>
           <xsl:apply-templates select="* except (pd:Name|pd:ParameterType)"/>
    <xsl:text>};</xsl:text>
    </xsl:template>
    <xsl:template match="pd:Inputs">
    <xsl:text>inputs {</xsl:text>
           <xsl:apply-templates/>
    <xsl:text>}</xsl:text>
    </xsl:template>
    <xsl:template match="pd:Outputs">
    <xsl:text>outputs {</xsl:text>
           <xsl:apply-templates/>
    <xsl:text>}</xsl:text>
    </xsl:template>
    <xsl:template match="pd:ParameterGroup">
    <xsl:text>@group </xsl:text>
     <xsl:value-of select="concat(pd:Name,' {')"/>
           <xsl:apply-templates select="* except (pd:Name)"/>
    <xsl:text>}</xsl:text>
    </xsl:template>
    
    <xsl:template match="pd:ConstraintOnGroup|pd:constraint">
    <xsl:text>@constraint {</xsl:text>
           <xsl:apply-templates/>
    <xsl:text>}</xsl:text>
    </xsl:template>
    
    <xsl:template match="pd:parameterRef">
         <xsl:value-of select="concat (@parameterName,'; ')"/>
    </xsl:template>
    
	<!-- standard copy template -->
     <xsl:template match="@*">
      <xsl:value-of select="concat(name(),'==',., ' ')"/>
     </xsl:template>   

  <!-- lower the priority of this rule - otherwise specific rules for empty elements above would be lower priority-->
   <xsl:template match="*[not(*)]" priority="-2" >
      <xsl:apply-templates select="@*"/>
      <xsl:value-of select="concat(name(),'=',.,' ')"/>
   </xsl:template>   
 
  
	<xsl:template match="node()" priority="-3">
		<xsl:copy>
		<xsl:apply-templates select="@*"/>
		<xsl:apply-templates/>
		</xsl:copy>
	</xsl:template>	
</xsl:stylesheet>