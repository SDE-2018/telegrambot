
package soap.ws.skiresortitem;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap.ws.skiresortitem package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateSkiResortItem_QNAME = new QName("http://skiresortitem.ws.soap/", "createSkiResortItem");
    private final static QName _GetSkiResortItem_QNAME = new QName("http://skiresortitem.ws.soap/", "getSkiResortItem");
    private final static QName _CreateSkiResortItemResponse_QNAME = new QName("http://skiresortitem.ws.soap/", "createSkiResortItemResponse");
    private final static QName _GetSkiResortItemResponse_QNAME = new QName("http://skiresortitem.ws.soap/", "getSkiResortItemResponse");
    private final static QName _ApiException_QNAME = new QName("http://skiresortitem.ws.soap/", "ApiException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap.ws.skiresortitem
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateSkiResortItem }
     * 
     */
    public CreateSkiResortItem createCreateSkiResortItem() {
        return new CreateSkiResortItem();
    }

    /**
     * Create an instance of {@link GetSkiResortItem }
     * 
     */
    public GetSkiResortItem createGetSkiResortItem() {
        return new GetSkiResortItem();
    }

    /**
     * Create an instance of {@link CreateSkiResortItemResponse }
     * 
     */
    public CreateSkiResortItemResponse createCreateSkiResortItemResponse() {
        return new CreateSkiResortItemResponse();
    }

    /**
     * Create an instance of {@link GetSkiResortItemResponse }
     * 
     */
    public GetSkiResortItemResponse createGetSkiResortItemResponse() {
        return new GetSkiResortItemResponse();
    }

    /**
     * Create an instance of {@link ApiException }
     * 
     */
    public ApiException createApiException() {
        return new ApiException();
    }

    /**
     * Create an instance of {@link SkiResortItem }
     * 
     */
    public SkiResortItem createSkiResortItem() {
        return new SkiResortItem();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSkiResortItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://skiresortitem.ws.soap/", name = "createSkiResortItem")
    public JAXBElement<CreateSkiResortItem> createCreateSkiResortItem(CreateSkiResortItem value) {
        return new JAXBElement<CreateSkiResortItem>(_CreateSkiResortItem_QNAME, CreateSkiResortItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSkiResortItem }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://skiresortitem.ws.soap/", name = "getSkiResortItem")
    public JAXBElement<GetSkiResortItem> createGetSkiResortItem(GetSkiResortItem value) {
        return new JAXBElement<GetSkiResortItem>(_GetSkiResortItem_QNAME, GetSkiResortItem.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSkiResortItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://skiresortitem.ws.soap/", name = "createSkiResortItemResponse")
    public JAXBElement<CreateSkiResortItemResponse> createCreateSkiResortItemResponse(CreateSkiResortItemResponse value) {
        return new JAXBElement<CreateSkiResortItemResponse>(_CreateSkiResortItemResponse_QNAME, CreateSkiResortItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSkiResortItemResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://skiresortitem.ws.soap/", name = "getSkiResortItemResponse")
    public JAXBElement<GetSkiResortItemResponse> createGetSkiResortItemResponse(GetSkiResortItemResponse value) {
        return new JAXBElement<GetSkiResortItemResponse>(_GetSkiResortItemResponse_QNAME, GetSkiResortItemResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://skiresortitem.ws.soap/", name = "ApiException")
    public JAXBElement<ApiException> createApiException(ApiException value) {
        return new JAXBElement<ApiException>(_ApiException_QNAME, ApiException.class, null, value);
    }

}
