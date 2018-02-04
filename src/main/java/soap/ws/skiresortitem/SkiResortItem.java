
package soap.ws.skiresortitem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for skiResortItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="skiResortItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lastUpdated" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="lat" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="liftCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lng" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="longestRun" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nightSkiing" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="officialWebsite" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resortId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="skiMapUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terrainPark" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="top" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="website" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "skiResortItem", propOrder = {
    "id",
    "lastUpdated",
    "lat",
    "liftCount",
    "lng",
    "longestRun",
    "name",
    "nightSkiing",
    "officialWebsite",
    "region",
    "resortId",
    "skiMapUrl",
    "terrainPark",
    "top",
    "website"
})
public class SkiResortItem {

    protected int id;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdated;
    protected String lat;
    protected int liftCount;
    protected String lng;
    protected int longestRun;
    protected String name;
    protected boolean nightSkiing;
    protected String officialWebsite;
    protected String region;
    protected int resortId;
    protected String skiMapUrl;
    protected boolean terrainPark;
    protected int top;
    protected String website;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the lastUpdated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Sets the value of the lastUpdated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdated(XMLGregorianCalendar value) {
        this.lastUpdated = value;
    }

    /**
     * Gets the value of the lat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLat() {
        return lat;
    }

    /**
     * Sets the value of the lat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLat(String value) {
        this.lat = value;
    }

    /**
     * Gets the value of the liftCount property.
     * 
     */
    public int getLiftCount() {
        return liftCount;
    }

    /**
     * Sets the value of the liftCount property.
     * 
     */
    public void setLiftCount(int value) {
        this.liftCount = value;
    }

    /**
     * Gets the value of the lng property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLng() {
        return lng;
    }

    /**
     * Sets the value of the lng property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLng(String value) {
        this.lng = value;
    }

    /**
     * Gets the value of the longestRun property.
     * 
     */
    public int getLongestRun() {
        return longestRun;
    }

    /**
     * Sets the value of the longestRun property.
     * 
     */
    public void setLongestRun(int value) {
        this.longestRun = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the nightSkiing property.
     * 
     */
    public boolean isNightSkiing() {
        return nightSkiing;
    }

    /**
     * Sets the value of the nightSkiing property.
     * 
     */
    public void setNightSkiing(boolean value) {
        this.nightSkiing = value;
    }

    /**
     * Gets the value of the officialWebsite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfficialWebsite() {
        return officialWebsite;
    }

    /**
     * Sets the value of the officialWebsite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfficialWebsite(String value) {
        this.officialWebsite = value;
    }

    /**
     * Gets the value of the region property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegion() {
        return region;
    }

    /**
     * Sets the value of the region property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegion(String value) {
        this.region = value;
    }

    /**
     * Gets the value of the resortId property.
     * 
     */
    public int getResortId() {
        return resortId;
    }

    /**
     * Sets the value of the resortId property.
     * 
     */
    public void setResortId(int value) {
        this.resortId = value;
    }

    /**
     * Gets the value of the skiMapUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSkiMapUrl() {
        return skiMapUrl;
    }

    /**
     * Sets the value of the skiMapUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSkiMapUrl(String value) {
        this.skiMapUrl = value;
    }

    /**
     * Gets the value of the terrainPark property.
     * 
     */
    public boolean isTerrainPark() {
        return terrainPark;
    }

    /**
     * Sets the value of the terrainPark property.
     * 
     */
    public void setTerrainPark(boolean value) {
        this.terrainPark = value;
    }

    /**
     * Gets the value of the top property.
     * 
     */
    public int getTop() {
        return top;
    }

    /**
     * Sets the value of the top property.
     * 
     */
    public void setTop(int value) {
        this.top = value;
    }

    /**
     * Gets the value of the website property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Sets the value of the website property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebsite(String value) {
        this.website = value;
    }

}
