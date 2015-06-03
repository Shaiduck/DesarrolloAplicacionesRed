using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
// Se agrega la referencia del servicio web local
using Servicios_Web.SumaDosNumerosReferencia;
// Se agrega la referencia del servicio web remoto
using Servicios_Web.CelsiusReferencia;

namespace Servicios_Web
{
    public partial class Home : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            lResultado.Text = "Se utilizará el servicio web local";
            lResultado2.Text = " = Se utilizará el servicio web remoto (w3schools.com)";
        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            try
            {
                // Se crea una instancia del servicio web local (el que se creó)
                SumaDosNumerosSoapClient servicioLocal = new SumaDosNumerosSoapClient();

                int a = Int32.Parse(tNumeroUno.Text);
                int b = Int32.Parse(tNumeroDos.Text);
                // Se llama al servicio web para obtener resultado en variable 'suma'
                int suma = servicioLocal.suma(a, b);
                // Se muestra resultado
                lResultado.Text = suma.ToString() + " según servicio web.";
                lErrorUno.Text = "";
            }
            catch (System.FormatException)
            {
                lErrorUno.Text = "¡Ingresa dos números primero!";
            }
        }

        protected void bTemperatura_Click1(object sender, EventArgs e)
        {
            /* 
             * Se crea una instancia del servicio web remoto)
             * Servicio web obtenido de:
             * www.w3schools.com/webservices/tempconvert.asmx
            */
            TempConvertSoapClient servicioRemoto = new TempConvertSoapClient("TempConvertSoap");
            String gradosCelsius = tCelsius.Text;
            // Se llama al servicio web remoto para obtener resultado en la variable 'resultado'
            String resultado = servicioRemoto.CelsiusToFahrenheit(gradosCelsius);
            // Si resultado == 0
            if (String.Equals(resultado, "Error"))
            {
                lErrorDos.Text = "Ingresa grados Celsius en siguiente formato: 12.20, por ejemplo.";
            }
            else
            {
                lResultado2.Text = " = " + resultado + " grados Farenheit, según servicio web remoto.";
                lErrorDos.Text = "";
            }
        }
    }
}