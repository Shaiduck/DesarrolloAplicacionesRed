﻿<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="puertos.aspx.cs" Inherits="Plataforma_PAR.puertos" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Plataforma de Auditoría de Red</title>
<link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
<link href="default.css" rel="stylesheet" type="text/css" media="all" />
<link href="fonts.css" rel="stylesheet" type="text/css" media="all" />

<!--[if IE 6]><link href="default_ie6.css" rel="stylesheet" type="text/css" /><![endif]-->

</head>
<body>
    <form id="form1" runat="server">
<div id="header-wrapper">
	<div id="header" class="container">
		<div id="logo">
			<span class="icon icon-group"></span>
			<h1><a href="#">PAR</a></h1>
			<span>Plataforma de Auditoría de Red   <a href="http://templated.co" rel="nofollow">   DAR</a></span>
		</div>
		<div id="triangle-up"></div>
	</div>
</div>
<div id="menu-wrapper">
		<div id="menu">
			<ul>
				<li><a href="index.aspx">PAR</a></li>
				<li class="current_page_item"><a href="#">PUERTOS</a></li>
				<li><a href="ip.aspx">IP</a></li>
				<li><a href="navegador.aspx">NAVEGADOR</a></li>
				<li><a href="ataque.aspx">ATAQUE SSH</a></li>
			</ul>
		</div>
</div>
<div id="wrapper">
	<div id="featured-wrapper">
	
		<div class="extra2 container">
			<div class="ebox1">
				<h1>Scanner de Puertos</h1>
                 <p>Clic en el siguiente botón para iniciar el escáner de puertos. Se mostrará en la siguiente <b>lista</b> los puertos de la red.</p>
                <p>
                    <asp:Button ID="btnPuertos" runat="server" OnClick="btnPuertos_Click" Text="Escanear" />
                    <asp:TextBox ID="txtPuertos" runat="server" Width="232px"></asp:TextBox>
                </p>
                <p>
                    <asp:Label ID="lblPuertos" runat="server"></asp:Label>
                </p>
			</div>			
		</div>	
	</div>
</div>
<div id="stamp" class="container">
	<div class="hexagon"><span class="icon icon-wrench"></span></div>
</div>
<div id="copyright" class="container">
	<p>&copy; PAR. All rights reserved. | Photos by <a href="http://fotogrph.com/">Fotogrph</a> | Design by <a href="http://templated.co" rel="nofollow">TEMPLATED</a>.</p>
</div>
    </form>
</body>
</html>
