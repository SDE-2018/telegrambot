
package soap.ws.skiresortitem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSkiResortItemResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSkiResortItemResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="skiResortItemById" type="{http://skiresortitem.ws.soap/}skiResortItem" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSkiResortItemResponse", propOrder = {
    "skiResortItemById"
})
public class GetSkiResortItemResponse {

    protected SkiResortItem skiResortItemById;

    /**
     * Gets the value of the skiResortItemById property.
     * 
     * @return
     *     possible object is
     *     {@link SkiResortItem }
     *     
     */
    public SkiResortItem getSkiResortItemById() {
        return skiResortItemById;
    }

    /**
     * Sets the value of the skiResortItemById property.
     * 
     * @param value
     *     allowed object is
     *     {@link SkiResortItem }
     *     
     */
    public void setSkiResortItemById(SkiResortItem value) {
        this.skiResortItemById = value;
    }

}
