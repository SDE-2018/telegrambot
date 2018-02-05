
package soap.ws.botuser;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap.ws.botuser package. 
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

    private final static QName _GetBotUserByIdResponse_QNAME = new QName("http://botuser.ws.soap/", "getBotUserByIdResponse");
    private final static QName _SetPreferences_QNAME = new QName("http://botuser.ws.soap/", "setPreferences");
    private final static QName _CreateBotUserResponse_QNAME = new QName("http://botuser.ws.soap/", "createBotUserResponse");
    private final static QName _GetBotUserById_QNAME = new QName("http://botuser.ws.soap/", "getBotUserById");
    private final static QName _SetPreferencesResponse_QNAME = new QName("http://botuser.ws.soap/", "setPreferencesResponse");
    private final static QName _CreateBotUser_QNAME = new QName("http://botuser.ws.soap/", "createBotUser");
    private final static QName _ApiException_QNAME = new QName("http://botuser.ws.soap/", "ApiException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap.ws.botuser
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SetPreferences }
     * 
     */
    public SetPreferences createSetPreferences() {
        return new SetPreferences();
    }

    /**
     * Create an instance of {@link GetBotUserByIdResponse }
     * 
     */
    public GetBotUserByIdResponse createGetBotUserByIdResponse() {
        return new GetBotUserByIdResponse();
    }

    /**
     * Create an instance of {@link CreateBotUserResponse }
     * 
     */
    public CreateBotUserResponse createCreateBotUserResponse() {
        return new CreateBotUserResponse();
    }

    /**
     * Create an instance of {@link GetBotUserById }
     * 
     */
    public GetBotUserById createGetBotUserById() {
        return new GetBotUserById();
    }

    /**
     * Create an instance of {@link CreateBotUser }
     * 
     */
    public CreateBotUser createCreateBotUser() {
        return new CreateBotUser();
    }

    /**
     * Create an instance of {@link SetPreferencesResponse }
     * 
     */
    public SetPreferencesResponse createSetPreferencesResponse() {
        return new SetPreferencesResponse();
    }

    /**
     * Create an instance of {@link ApiException }
     * 
     */
    public ApiException createApiException() {
        return new ApiException();
    }

    /**
     * Create an instance of {@link BotUser }
     * 
     */
    public BotUser createBotUser() {
        return new BotUser();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBotUserByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://botuser.ws.soap/", name = "getBotUserByIdResponse")
    public JAXBElement<GetBotUserByIdResponse> createGetBotUserByIdResponse(GetBotUserByIdResponse value) {
        return new JAXBElement<GetBotUserByIdResponse>(_GetBotUserByIdResponse_QNAME, GetBotUserByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetPreferences }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://botuser.ws.soap/", name = "setPreferences")
    public JAXBElement<SetPreferences> createSetPreferences(SetPreferences value) {
        return new JAXBElement<SetPreferences>(_SetPreferences_QNAME, SetPreferences.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateBotUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://botuser.ws.soap/", name = "createBotUserResponse")
    public JAXBElement<CreateBotUserResponse> createCreateBotUserResponse(CreateBotUserResponse value) {
        return new JAXBElement<CreateBotUserResponse>(_CreateBotUserResponse_QNAME, CreateBotUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBotUserById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://botuser.ws.soap/", name = "getBotUserById")
    public JAXBElement<GetBotUserById> createGetBotUserById(GetBotUserById value) {
        return new JAXBElement<GetBotUserById>(_GetBotUserById_QNAME, GetBotUserById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetPreferencesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://botuser.ws.soap/", name = "setPreferencesResponse")
    public JAXBElement<SetPreferencesResponse> createSetPreferencesResponse(SetPreferencesResponse value) {
        return new JAXBElement<SetPreferencesResponse>(_SetPreferencesResponse_QNAME, SetPreferencesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateBotUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://botuser.ws.soap/", name = "createBotUser")
    public JAXBElement<CreateBotUser> createCreateBotUser(CreateBotUser value) {
        return new JAXBElement<CreateBotUser>(_CreateBotUser_QNAME, CreateBotUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://botuser.ws.soap/", name = "ApiException")
    public JAXBElement<ApiException> createApiException(ApiException value) {
        return new JAXBElement<ApiException>(_ApiException_QNAME, ApiException.class, null, value);
    }

}
