
package soap.ws.skiresortitem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createSkiResortItemResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createSkiResortItemResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createdSkiResortItem" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createSkiResortItemResponse", propOrder = {
    "createdSkiResortItem"
})
public class CreateSkiResortItemResponse {

    protected boolean createdSkiResortItem;

    /**
     * Gets the value of the createdSkiResortItem property.
     * 
     */
    public boolean isCreatedSkiResortItem() {
        return createdSkiResortItem;
    }

    /**
     * Sets the value of the createdSkiResortItem property.
     * 
     */
    public void setCreatedSkiResortItem(boolean value) {
        this.createdSkiResortItem = value;
    }

}
