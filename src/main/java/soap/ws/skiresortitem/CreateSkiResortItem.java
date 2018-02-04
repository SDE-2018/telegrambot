
package soap.ws.skiresortitem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createSkiResortItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createSkiResortItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="user" type="{http://skiresortitem.ws.soap/}skiResortItem" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createSkiResortItem", propOrder = {
    "user"
})
public class CreateSkiResortItem {

    protected SkiResortItem user;

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link SkiResortItem }
     *     
     */
    public SkiResortItem getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link SkiResortItem }
     *     
     */
    public void setUser(SkiResortItem value) {
        this.user = value;
    }

}
