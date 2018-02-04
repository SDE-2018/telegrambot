
package soap.ws.skiresortitem;

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
@WebService(name = "ISkiResortItemService", targetNamespace = "http://skiresortitem.ws.soap/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ISkiResortItemService {


    /**
     * 
     * @param user
     * @return
     *     returns boolean
     * @throws ApiException_Exception
     */
    @WebMethod
    @WebResult(name = "createdSkiResortItem", targetNamespace = "")
    @RequestWrapper(localName = "createSkiResortItem", targetNamespace = "http://skiresortitem.ws.soap/", className = "soap.ws.skiresortitem.CreateSkiResortItem")
    @ResponseWrapper(localName = "createSkiResortItemResponse", targetNamespace = "http://skiresortitem.ws.soap/", className = "soap.ws.skiresortitem.CreateSkiResortItemResponse")
    @Action(input = "http://skiresortitem.ws.soap/ISkiResortItemService/createSkiResortItemRequest", output = "http://skiresortitem.ws.soap/ISkiResortItemService/createSkiResortItemResponse", fault = {
        @FaultAction(className = ApiException_Exception.class, value = "http://skiresortitem.ws.soap/ISkiResortItemService/createSkiResortItem/Fault/ApiException")
    })
    public boolean createSkiResortItem(
        @WebParam(name = "user", targetNamespace = "")
        SkiResortItem user)
        throws ApiException_Exception
    ;

    /**
     * 
     * @param itemId
     * @return
     *     returns soap.ws.skiresortitem.SkiResortItem
     * @throws ApiException_Exception
     */
    @WebMethod
    @WebResult(name = "skiResortItemById", targetNamespace = "")
    @RequestWrapper(localName = "getSkiResortItem", targetNamespace = "http://skiresortitem.ws.soap/", className = "soap.ws.skiresortitem.GetSkiResortItem")
    @ResponseWrapper(localName = "getSkiResortItemResponse", targetNamespace = "http://skiresortitem.ws.soap/", className = "soap.ws.skiresortitem.GetSkiResortItemResponse")
    @Action(input = "http://skiresortitem.ws.soap/ISkiResortItemService/getSkiResortItemRequest", output = "http://skiresortitem.ws.soap/ISkiResortItemService/getSkiResortItemResponse", fault = {
        @FaultAction(className = ApiException_Exception.class, value = "http://skiresortitem.ws.soap/ISkiResortItemService/getSkiResortItem/Fault/ApiException")
    })
    public SkiResortItem getSkiResortItem(
        @WebParam(name = "itemId", targetNamespace = "")
        String itemId)
        throws ApiException_Exception
    ;

}
