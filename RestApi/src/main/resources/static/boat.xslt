<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html"/>
    <xsl:template match="ArrayList">
        <html>
            <body>
                <h1>Boats</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Owner</th>
                            <th>Name</th>
                            <th>Sail number</th>
                            <th>Class</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="item">
                            <tr>
                                <td><xsl:value-of select="id"/></td>
                                <td>
                                    <xsl:value-of select="owner/fullName"/>
                                </td>
                                <td><xsl:value-of select="name"/></td>
                                <td><xsl:value-of select="sailNumber"/></td>
                                <td><xsl:value-of select="className"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
