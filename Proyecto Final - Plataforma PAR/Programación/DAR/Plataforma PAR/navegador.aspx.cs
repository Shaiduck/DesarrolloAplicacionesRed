using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Plataforma_PAR.RicardosService;

namespace Plataforma_PAR
{
    public partial class navegador : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
        }

        protected void btnObtener_Click(object sender, EventArgs e)
        {
            atkSoapClient servicio = new atkSoapClient();
            // lblPagina.Text = servicio.getHTML();
        }
    }
}