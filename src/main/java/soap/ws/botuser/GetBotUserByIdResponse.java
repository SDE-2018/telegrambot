
package soap.ws.botuser;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getBotUserByIdResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getBotUserByIdResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createdBotUserInfo" type="{http://botuser.ws.soap/}botUser" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBotUserByIdResponse", propOrder = {
    "createdBotUserInfo"
})
public class GetBotUserByIdResponse {

    protected BotUser createdBotUserInfo;

    /**
     * Gets the value of the createdBotUserInfo property.
     * 
     * @return
     *     possible object is
     *     {@link BotUser }
     *     
     */
    public BotUser getCreatedBotUserInfo() {
        return createdBotUserInfo;
    }

    /**
     * Sets the value of the createdBotUserInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BotUser }
     *     
     */
    public void setCreatedBotUserInfo(BotUser value) {
        this.createdBotUserInfo = value;
    }

}
