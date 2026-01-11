<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html"/>
    <xsl:template match="ArrayList">
        <html>
            <body>
                <h1>Mooring permits</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Berth</th>
                            <th>Fee</th>
                            <th>Boat</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="item">
                            <tr>
                                <td><xsl:value-of select="id"/></td>
                                <td>
                                    Berth : <xsl:value-of select="berthDto/berth"/> | Pier <xsl:value-of select="berthDto/pier"/>
                                </td>
                                <td><xsl:value-of select="fee"/></td>
                                <td>
                                    <xsl:value-of select="boat/name"/> | <xsl:value-of select="boat/sailNumber"/> | owner(<xsl:value-of select="boat/owner/fullName"/>)
                                </td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
