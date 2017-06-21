package br.com.MyHouseTech.Filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.MyHouseTech.model.Usuario;

public class FilterAdministrativo implements Filter {

	private static final String PerfilAdministrador = "a18f9ea8068211e7bb070050568d4390";
	private static final String PerfilGestor = "a38ca58a068211e7bb070050568d4390";
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		
		Usuario obUsuario = (Usuario) session.getAttribute("objUser");
		
		if(obUsuario != null && (obUsuario.getIdPerfil().equals(PerfilAdministrador) || obUsuario.getIdPerfil().equals(PerfilGestor))){
			 chain.doFilter(req, res);
		}
		else {
			response.sendRedirect("areaRestrita.jsp");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
