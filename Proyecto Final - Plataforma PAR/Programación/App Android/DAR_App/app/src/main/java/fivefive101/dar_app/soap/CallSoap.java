package fivefive101.dar_app.soap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Shaiduck on 6/1/2015.
 */
public class CallSoap {
    public final String SOAP_ACTION = "http://tempuri.org/scanIPS";
    public final String OPERATION_NAME = "scanIPS";
    public final String WSDL_TARGET_NAMESPACE = "http://tempuri.org";
    public final String SOAP_ADDRESS = "http://192.168.1.67:81/Receptor.asmx";

    public CallSoap()
    {

    }
    public String Call()
    {
        SoapObject request = new SoapObject(WSDL_TARGET_NAMESPACE, OPERATION_NAME);
        PropertyInfo pi = new PropertyInfo();

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);
        HttpTransportSE httpTransport = new HttpTransportSE(SOAP_ADDRESS);
        Object response =null;

        try
        {
            httpTransport.call(SOAP_ACTION, envelope);
            response = envelope.getResponse();

        }
        catch(Exception exception)
        {
            response = exception.toString();
        }
        return response.toString();
    }
}
