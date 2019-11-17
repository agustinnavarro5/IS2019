
package financiera.presentacion.adaptador;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ResultadoEstadoCliente complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ResultadoEstadoCliente">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CantidadCreditosActivos" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="ConsultaValida" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Error" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TieneDeudas" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResultadoEstadoCliente", namespace = "http://schemas.datacontract.org/2004/07/SGE.Service.Contracts.Data", propOrder = {
    "cantidadCreditosActivos",
    "consultaValida",
    "error",
    "tieneDeudas"
})
public class ResultadoEstadoCliente {

    @XmlElement(name = "CantidadCreditosActivos")
    protected Integer cantidadCreditosActivos;
    @XmlElement(name = "ConsultaValida")
    protected Boolean consultaValida;
    @XmlElementRef(name = "Error", namespace = "http://schemas.datacontract.org/2004/07/SGE.Service.Contracts.Data", type = JAXBElement.class, required = false)
    protected JAXBElement<String> error;
    @XmlElement(name = "TieneDeudas")
    protected Boolean tieneDeudas;

    /**
     * Obtiene el valor de la propiedad cantidadCreditosActivos.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantidadCreditosActivos() {
        return cantidadCreditosActivos;
    }

    /**
     * Define el valor de la propiedad cantidadCreditosActivos.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantidadCreditosActivos(Integer value) {
        this.cantidadCreditosActivos = value;
    }

    /**
     * Obtiene el valor de la propiedad consultaValida.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isConsultaValida() {
        return consultaValida;
    }

    /**
     * Define el valor de la propiedad consultaValida.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setConsultaValida(Boolean value) {
        this.consultaValida = value;
    }

    /**
     * Obtiene el valor de la propiedad error.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getError() {
        return error;
    }

    /**
     * Define el valor de la propiedad error.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setError(JAXBElement<String> value) {
        this.error = value;
    }

    /**
     * Obtiene el valor de la propiedad tieneDeudas.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTieneDeudas() {
        return tieneDeudas;
    }

    /**
     * Define el valor de la propiedad tieneDeudas.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTieneDeudas(Boolean value) {
        this.tieneDeudas = value;
    }

}
