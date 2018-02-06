
package soap.ws.botuser;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IBotUserService", targetNamespace = "http://botuser.ws.soap/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IBotUserService {


    /**
     * 
     * @param user
     * @return
     *     returns boolean
     * @throws ApiException_Exception
     */
    @WebMethod
    @WebResult(name = "updatedBotUser", targetNamespace = "")
    @RequestWrapper(localName = "setPreferences", targetNamespace = "http://botuser.ws.soap/", className = "soap.ws.botuser.SetPreferences")
    @ResponseWrapper(localName = "setPreferencesResponse", targetNamespace = "http://botuser.ws.soap/", className = "soap.ws.botuser.SetPreferencesResponse")
    @Action(input = "http://botuser.ws.soap/IBotUserService/setPreferencesRequest", output = "http://botuser.ws.soap/IBotUserService/setPreferencesResponse", fault = {
        @FaultAction(className = ApiException_Exception.class, value = "http://botuser.ws.soap/IBotUserService/setPreferences/Fault/ApiException")
    })
    public boolean setPreferences(
        @WebParam(name = "user", targetNamespace = "")
        BotUser user)
        throws ApiException_Exception
    ;

    /**
     * 
     * @param userId
     * @return
     *     returns soap.ws.botuser.BotUser
     */
    @WebMethod
    @WebResult(name = "createdBotUserInfo", targetNamespace = "")
    @RequestWrapper(localName = "getBotUserById", targetNamespace = "http://botuser.ws.soap/", className = "soap.ws.botuser.GetBotUserById")
    @ResponseWrapper(localName = "getBotUserByIdResponse", targetNamespace = "http://botuser.ws.soap/", className = "soap.ws.botuser.GetBotUserByIdResponse")
    @Action(input = "http://botuser.ws.soap/IBotUserService/getBotUserByIdRequest", output = "http://botuser.ws.soap/IBotUserService/getBotUserByIdResponse")
    public BotUser getBotUserById(
        @WebParam(name = "userId", targetNamespace = "")
        String userId);

    /**
     * 
     * @param user
     * @return
     *     returns boolean
     * @throws ApiException_Exception
     */
    @WebMethod
    @WebResult(name = "createdBotUser", targetNamespace = "")
    @RequestWrapper(localName = "createBotUser", targetNamespace = "http://botuser.ws.soap/", className = "soap.ws.botuser.CreateBotUser")
    @ResponseWrapper(localName = "createBotUserResponse", targetNamespace = "http://botuser.ws.soap/", className = "soap.ws.botuser.CreateBotUserResponse")
    @Action(input = "http://botuser.ws.soap/IBotUserService/createBotUserRequest", output = "http://botuser.ws.soap/IBotUserService/createBotUserResponse", fault = {
        @FaultAction(className = ApiException_Exception.class, value = "http://botuser.ws.soap/IBotUserService/createBotUser/Fault/ApiException")
    })
    public boolean createBotUser(
        @WebParam(name = "user", targetNamespace = "")
        BotUser user)
        throws ApiException_Exception
    ;

}
