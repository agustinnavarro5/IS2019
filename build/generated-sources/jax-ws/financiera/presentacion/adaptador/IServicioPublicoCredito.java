
package financiera.presentacion.adaptador;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "IServicioPublicoCredito", targetNamespace = "http://ISTP1.Service.Contracts.Service")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface IServicioPublicoCredito {


    /**
     * 
     * @param codigoFinanciera
     * @param dni
     * @return
     *     returns financiera.presentacion.adaptador.ResultadoEstadoCliente
     * @throws IServicioPublicoCreditoObtenerEstadoClienteErrorServicioFaultFaultMessage
     */
    @WebMethod(operationName = "ObtenerEstadoCliente", action = "http://ISTP1.Service.Contracts.Service/IServicioPublicoCredito/ObtenerEstadoCliente")
    @WebResult(name = "ObtenerEstadoClienteResult", targetNamespace = "http://ISTP1.Service.Contracts.Service")
    @RequestWrapper(localName = "ObtenerEstadoCliente", targetNamespace = "http://ISTP1.Service.Contracts.Service", className = "financiera.presentacion.adaptador.ObtenerEstadoCliente")
    @ResponseWrapper(localName = "ObtenerEstadoClienteResponse", targetNamespace = "http://ISTP1.Service.Contracts.Service", className = "financiera.presentacion.adaptador.ObtenerEstadoClienteResponse")
    public ResultadoEstadoCliente obtenerEstadoCliente(
        @WebParam(name = "codigoFinanciera", targetNamespace = "http://ISTP1.Service.Contracts.Service")
        String codigoFinanciera,
        @WebParam(name = "dni", targetNamespace = "http://ISTP1.Service.Contracts.Service")
        Integer dni)
        throws IServicioPublicoCreditoObtenerEstadoClienteErrorServicioFaultFaultMessage
    ;

    /**
     * 
     * @param codigoFinanciera
     * @param identificadorCredito
     * @param montoOtorgado
     * @param dni
     * @return
     *     returns financiera.presentacion.adaptador.ResultadoOperacion
     * @throws IServicioPublicoCreditoInformarCreditoOtorgadoErrorServicioFaultFaultMessage
     */
    @WebMethod(operationName = "InformarCreditoOtorgado", action = "http://ISTP1.Service.Contracts.Service/IServicioPublicoCredito/InformarCreditoOtorgado")
    @WebResult(name = "InformarCreditoOtorgadoResult", targetNamespace = "http://ISTP1.Service.Contracts.Service")
    @RequestWrapper(localName = "InformarCreditoOtorgado", targetNamespace = "http://ISTP1.Service.Contracts.Service", className = "financiera.presentacion.adaptador.InformarCreditoOtorgado")
    @ResponseWrapper(localName = "InformarCreditoOtorgadoResponse", targetNamespace = "http://ISTP1.Service.Contracts.Service", className = "financiera.presentacion.adaptador.InformarCreditoOtorgadoResponse")
    public ResultadoOperacion informarCreditoOtorgado(
        @WebParam(name = "codigoFinanciera", targetNamespace = "http://ISTP1.Service.Contracts.Service")
        String codigoFinanciera,
        @WebParam(name = "dni", targetNamespace = "http://ISTP1.Service.Contracts.Service")
        Integer dni,
        @WebParam(name = "identificadorCredito", targetNamespace = "http://ISTP1.Service.Contracts.Service")
        String identificadorCredito,
        @WebParam(name = "montoOtorgado", targetNamespace = "http://ISTP1.Service.Contracts.Service")
        Double montoOtorgado)
        throws IServicioPublicoCreditoInformarCreditoOtorgadoErrorServicioFaultFaultMessage
    ;

    /**
     * 
     * @param codigoFinanciera
     * @param identificadorCredito
     * @param dni
     * @return
     *     returns financiera.presentacion.adaptador.ResultadoOperacion
     * @throws IServicioPublicoCreditoInformarCreditoFinalizadoErrorServicioFaultFaultMessage
     */
    @WebMethod(operationName = "InformarCreditoFinalizado", action = "http://ISTP1.Service.Contracts.Service/IServicioPublicoCredito/InformarCreditoFinalizado")
    @WebResult(name = "InformarCreditoFinalizadoResult", targetNamespace = "http://ISTP1.Service.Contracts.Service")
    @RequestWrapper(localName = "InformarCreditoFinalizado", targetNamespace = "http://ISTP1.Service.Contracts.Service", className = "financiera.presentacion.adaptador.InformarCreditoFinalizado")
    @ResponseWrapper(localName = "InformarCreditoFinalizadoResponse", targetNamespace = "http://ISTP1.Service.Contracts.Service", className = "financiera.presentacion.adaptador.InformarCreditoFinalizadoResponse")
    public ResultadoOperacion informarCreditoFinalizado(
        @WebParam(name = "codigoFinanciera", targetNamespace = "http://ISTP1.Service.Contracts.Service")
        String codigoFinanciera,
        @WebParam(name = "dni", targetNamespace = "http://ISTP1.Service.Contracts.Service")
        Integer dni,
        @WebParam(name = "identificadorCredito", targetNamespace = "http://ISTP1.Service.Contracts.Service")
        String identificadorCredito)
        throws IServicioPublicoCreditoInformarCreditoFinalizadoErrorServicioFaultFaultMessage
    ;


}
