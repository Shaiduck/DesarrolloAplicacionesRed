<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Home.aspx.cs" Inherits="Servicios_Web.Home" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="utf-8" />
    <title>IS3 Consumir servicios</title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <div>
            <asp:Label ID="lTitulo" runat="server" Text="Servicio web propio" Font-Size="14"></asp:Label>  
        </div>
        <div style="margin-top: 10px">
        <asp:TextBox ID="tNumeroUno" runat="server" ForeColor="Silver">Número 1</asp:TextBox>
        <asp:Label ID="lSuma" runat="server" Font-Bold="True" Font-Underline="False" Text=" + "></asp:Label>
        <asp:TextBox ID="tNumeroDos" runat="server" ForeColor="Silver">Número 2</asp:TextBox>
        <asp:Label ID="lIgual" runat="server" Text="  =  "></asp:Label>
        <asp:Label ID="lResultado" runat="server"></asp:Label>
        </div>
        <div>
            <asp:Button ID="Button1" runat="server" OnClick="Button1_Click" Text="Sumar" Width="105px" />
            <asp:Label ID="lErrorUno" runat="server" Font-Bold="True" Font-Overline="False" Font-Size="Large" ForeColor="Red"></asp:Label>
        </div>
    </div>
    <div style="margin-top: 50px">
        <div>
            <asp:Label ID="lTitulo2" runat="server" Text="Servicio web ajeno" Font-Size="14pt"></asp:Label>
        </div>
        <div style="margin-top: 10px">
            <asp:TextBox ID="tCelsius" runat="server" ForeColor="Silver">Grados Celsius</asp:TextBox>
            <asp:Label ID="lResultado2" runat="server"></asp:Label>
        </div>
    </div>
        <asp:Button ID="bTemperatura" runat="server" Text="Convertir" OnClick="bTemperatura_Click1" />
            <asp:Label ID="lErrorDos" runat="server" Font-Bold="True" Font-Overline="False" Font-Size="Large" ForeColor="Red"></asp:Label>
    </form>
</body>
</html>
