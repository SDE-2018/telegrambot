
package soap.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for setPreferencesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="setPreferencesResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="updatedBotUser" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setPreferencesResponse", propOrder = {
    "updatedBotUser"
})
public class SetPreferencesResponse {

    protected boolean updatedBotUser;

    /**
     * Gets the value of the updatedBotUser property.
     * 
     */
    public boolean isUpdatedBotUser() {
        return updatedBotUser;
    }

    /**
     * Sets the value of the updatedBotUser property.
     * 
     */
    public void setUpdatedBotUser(boolean value) {
        this.updatedBotUser = value;
    }

}
