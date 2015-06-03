using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Plataforma_PAR.RicardosService;

namespace Plataforma_PAR
{
    public partial class puertos : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void btnPuertos_Click(object sender, EventArgs e)
        {
            // Código para escáner de puertos
            atkSoapClient serviciochiquito = new atkSoapClient();
            lblPuertos.Text = serviciochiquito.getPorts();
        }
    }
}