
package financiera.presentacion.adaptador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ObtenerEstadoClienteResult" type="{http://schemas.datacontract.org/2004/07/SGE.Service.Contracts.Data}ResultadoEstadoCliente" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "obtenerEstadoClienteResult"
})
@XmlRootElement(name = "ObtenerEstadoClienteResponse")
public class ObtenerEstadoClienteResponse {

    @XmlElementRef(name = "ObtenerEstadoClienteResult", namespace = "http://ISTP1.Service.Contracts.Service", type = JAXBElement.class, required = false)
    protected JAXBElement<ResultadoEstadoCliente> obtenerEstadoClienteResult;

    /**
     * Obtiene el valor de la propiedad obtenerEstadoClienteResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ResultadoEstadoCliente }{@code >}
     *     
     */
    public JAXBElement<ResultadoEstadoCliente> getObtenerEstadoClienteResult() {
        return obtenerEstadoClienteResult;
    }

    /**
     * Define el valor de la propiedad obtenerEstadoClienteResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ResultadoEstadoCliente }{@code >}
     *     
     */
    public void setObtenerEstadoClienteResult(JAXBElement<ResultadoEstadoCliente> value) {
        this.obtenerEstadoClienteResult = value;
    }

}
