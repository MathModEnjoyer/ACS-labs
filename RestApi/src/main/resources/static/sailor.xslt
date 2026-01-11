<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html"/>
    <xsl:template match="ArrayList">
        <html>
            <body>
                <h1>Sailors</h1>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Full name</th>
                            <th>Birth date</th>
                            <th>Country</th>
                            <th>Club</th>
                        </tr>
                    </thead>
                    <tbody>
                        <xsl:for-each select="item">
                            <tr>
                                <td><xsl:value-of select="id"/></td>
                                <td>
                                    <xsl:value-of select="fullName"/>
                                </td>
                                <td><xsl:value-of select="birthDate"/></td>
                                <td><xsl:value-of select="country"/></td>
                                <td><xsl:value-of select="club"/></td>
                            </tr>
                        </xsl:for-each>
                    </tbody>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
