
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
 *         &lt;element name="InformarCreditoOtorgadoResult" type="{http://schemas.datacontract.org/2004/07/SGE.Service.Contracts.Data}ResultadoOperacion" minOccurs="0"/>
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
    "informarCreditoOtorgadoResult"
})
@XmlRootElement(name = "InformarCreditoOtorgadoResponse")
public class InformarCreditoOtorgadoResponse {

    @XmlElementRef(name = "InformarCreditoOtorgadoResult", namespace = "http://ISTP1.Service.Contracts.Service", type = JAXBElement.class, required = false)
    protected JAXBElement<ResultadoOperacion> informarCreditoOtorgadoResult;

    /**
     * Obtiene el valor de la propiedad informarCreditoOtorgadoResult.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ResultadoOperacion }{@code >}
     *     
     */
    public JAXBElement<ResultadoOperacion> getInformarCreditoOtorgadoResult() {
        return informarCreditoOtorgadoResult;
    }

    /**
     * Define el valor de la propiedad informarCreditoOtorgadoResult.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ResultadoOperacion }{@code >}
     *     
     */
    public void setInformarCreditoOtorgadoResult(JAXBElement<ResultadoOperacion> value) {
        this.informarCreditoOtorgadoResult = value;
    }

}
