using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Net.Sockets;
using System.Runtime.Serialization.Formatters.Binary;
using System.IO;
using System.ComponentModel;
using System.Runtime.InteropServices;
using System.Runtime.Serialization;
using System.Net;
using System.Text;
using System.Diagnostics;

namespace DAR_CLIENT
{
    public partial class _Default : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void envio(object sender, EventArgs e)
        {
            ATK atk = new ATK();
            atk.setType(1);
            atk.setCount(1);
            atk.setACK(1);
            atk.setMSG("Enviando");
            while(true)
            {
                TcpClient clientSocket = new TcpClient();
                clientSocket.Connect("192.168.0.19", 10000);

                NetworkStream serverStream = clientSocket.GetStream();
                serverStream.Write(atk.ObjetcToArray(), 0, atk.ObjetcToArray().Length);
                
                serverStream.Flush();
                clientSocket.Close();    

            }            
        }
        
        static string printATKFullObject(ATK a)
        {
            String res = "";
            foreach (byte element in a.ObjetcToArray())
            {
                res = res + element+(char)element;                
            }
            return res;
        }
    }
        
}
