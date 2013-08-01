
package at.ac.tuwien.auto.iotsys.clients.pdp;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.1
 * 
 */
@WebService(name = "PDP", targetNamespace = "http://pdp.smartwebgrid.auto.tuwien.ac.at/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PDP {


    /**
     * 
     * @param arg0
     * @return
     *     returns at.ac.tuwien.auto.iotsys.clients.pdp.DecisionType
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "authorize", targetNamespace = "http://pdp.smartwebgrid.auto.tuwien.ac.at/", className = "at.ac.tuwien.auto.iotsys.clients.pdp.Authorize")
    @ResponseWrapper(localName = "authorizeResponse", targetNamespace = "http://pdp.smartwebgrid.auto.tuwien.ac.at/", className = "at.ac.tuwien.auto.iotsys.clients.pdp.AuthorizeResponse")
    public DecisionType authorize(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "init", targetNamespace = "http://pdp.smartwebgrid.auto.tuwien.ac.at/", className = "at.ac.tuwien.auto.iotsys.clients.pdp.Init")
    @ResponseWrapper(localName = "initResponse", targetNamespace = "http://pdp.smartwebgrid.auto.tuwien.ac.at/", className = "at.ac.tuwien.auto.iotsys.clients.pdp.InitResponse")
    public void init();

}