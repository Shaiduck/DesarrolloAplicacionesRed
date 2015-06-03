using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;

namespace Servicios_Web
{
    /// <summary>
    /// Descripción breve de SumaDosNumeros
    /// </summary>
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // Para permitir que se llame a este servicio web desde un script, usando ASP.NET AJAX, quite la marca de comentario de la línea siguiente. 
    // [System.Web.Script.Services.ScriptService]
    public class SumaDosNumeros : System.Web.Services.WebService
    {

        [WebMethod]
        public int suma(int a, int b)
        {
            int interno = a+b;
            // Regresa la suma de los dos números que le pasan como parámetro.
            Console.WriteLine("Llega request. La suma es: "+interno);
            return interno;
        }
    }
}
