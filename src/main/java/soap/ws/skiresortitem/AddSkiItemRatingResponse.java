
package soap.ws.skiresortitem;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for addSkiItemRatingResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="addSkiItemRatingResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createdRating" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "addSkiItemRatingResponse", propOrder = {
    "createdRating"
})
public class AddSkiItemRatingResponse {

    protected boolean createdRating;

    /**
     * Gets the value of the createdRating property.
     * 
     */
    public boolean isCreatedRating() {
        return createdRating;
    }

    /**
     * Sets the value of the createdRating property.
     * 
     */
    public void setCreatedRating(boolean value) {
        this.createdRating = value;
    }

}
