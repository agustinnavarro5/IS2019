
package financiera.presentacion.adaptador;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "ErrorServicio", targetNamespace = "http://schemas.datacontract.org/2004/07/SGE.Service.Contracts.Faults")
public class IServicioPublicoCreditoInformarCreditoFinalizadoErrorServicioFaultFaultMessage
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ErrorServicio faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public IServicioPublicoCreditoInformarCreditoFinalizadoErrorServicioFaultFaultMessage(String message, ErrorServicio faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public IServicioPublicoCreditoInformarCreditoFinalizadoErrorServicioFaultFaultMessage(String message, ErrorServicio faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: financiera.presentacion.adaptador.ErrorServicio
     */
    public ErrorServicio getFaultInfo() {
        return faultInfo;
    }

}
