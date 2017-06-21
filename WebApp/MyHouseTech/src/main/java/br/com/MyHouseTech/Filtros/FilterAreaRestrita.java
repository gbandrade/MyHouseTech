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

public class FilterAreaRestrita implements Filter {

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		
		Usuario obUsuario = (Usuario) session.getAttribute("objUser");
		
		if(obUsuario != null){
			 chain.doFilter(req, res);
		}
		else {
			response.sendRedirect("logar.jsp");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
