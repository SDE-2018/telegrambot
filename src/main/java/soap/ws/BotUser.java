
package soap.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for botUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="botUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="budget" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="chatId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="expertLevel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nearTrento" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="occupation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferredSkiType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="registerDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "botUser", propOrder = {
    "age",
    "budget",
    "chatId",
    "expertLevel",
    "name",
    "nearTrento",
    "occupation",
    "preferredSkiType",
    "registerDate"
})
public class BotUser {

    protected int age;
    protected int budget;
    protected int chatId;
    protected int expertLevel;
    protected String name;
    protected boolean nearTrento;
    protected String occupation;
    protected String preferredSkiType;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar registerDate;

    /**
     * Gets the value of the age property.
     * 
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the value of the age property.
     * 
     */
    public void setAge(int value) {
        this.age = value;
    }

    /**
     * Gets the value of the budget property.
     * 
     */
    public int getBudget() {
        return budget;
    }

    /**
     * Sets the value of the budget property.
     * 
     */
    public void setBudget(int value) {
        this.budget = value;
    }

    /**
     * Gets the value of the chatId property.
     * 
     */
    public int getChatId() {
        return chatId;
    }

    /**
     * Sets the value of the chatId property.
     * 
     */
    public void setChatId(int value) {
        this.chatId = value;
    }

    /**
     * Gets the value of the expertLevel property.
     * 
     */
    public int getExpertLevel() {
        return expertLevel;
    }

    /**
     * Sets the value of the expertLevel property.
     * 
     */
    public void setExpertLevel(int value) {
        this.expertLevel = value;
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
     * Gets the value of the nearTrento property.
     * 
     */
    public boolean isNearTrento() {
        return nearTrento;
    }

    /**
     * Sets the value of the nearTrento property.
     * 
     */
    public void setNearTrento(boolean value) {
        this.nearTrento = value;
    }

    /**
     * Gets the value of the occupation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOccupation() {
        return occupation;
    }

    /**
     * Sets the value of the occupation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOccupation(String value) {
        this.occupation = value;
    }

    /**
     * Gets the value of the preferredSkiType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferredSkiType() {
        return preferredSkiType;
    }

    /**
     * Sets the value of the preferredSkiType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferredSkiType(String value) {
        this.preferredSkiType = value;
    }

    /**
     * Gets the value of the registerDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRegisterDate() {
        return registerDate;
    }

    /**
     * Sets the value of the registerDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRegisterDate(XMLGregorianCalendar value) {
        this.registerDate = value;
    }

}
