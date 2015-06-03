using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WebApplication1
{
    public partial class _Default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            DAREntities x = new DAREntities();
            LOGIN y = new LOGIN();
            y.ID = TextBox1.Text;
            y.LOGIN1 = ":D";
            x.AddToLOGIN(y);
            x.SaveChanges();
            TextBox2.Text= x.LOGIN.First().LOGIN1;

        }

        protected void TextBox1_TextChanged(object sender, EventArgs e)
        {

        }

        protected void Button2_Click(object sender, EventArgs e)
        {
            DAREntities x = new DAREntities();
            var q = (from t in x.LOGIN
                        where t.ID.Equals("10")        
                      select t).ToList();
            
            var q1 = x.LOGIN.Where(y => y.ID.Equals("10")).First();
            
            var q3 = x.LOGIN.First();
            q3.LOGIN1 = ":(";


            x.SaveChanges();
            GridView1.DataSource = q;
            GridView1.DataBind();
        }
    }
}
