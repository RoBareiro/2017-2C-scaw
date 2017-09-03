package ar.edu.unlam.diit.scaw.configs;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class HsqlDataSource {
	
	Context initCtx;
	Context envCtx;
	DataSource ds;

	public DataSource dataSource() {
		
		try {
			initCtx = new InitialContext();
			envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/EvaluacionesUnlam");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return ds;	
	}

}