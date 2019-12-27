/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package py.una.pol.par.util;

import javax.activation.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

/**
 *
 * @author Lore
 */
public class pool {
    public DataSource datasourse;
    public String bd = "carrito_bd";
    public String url = "jdbc:mysql://localhost/"+bd;
    public String user = "postgres";
    public String pass= "123";
    
    private void inicliazarDatSource(){
        BasicDataSource basicdatasource = new BasicDataSource();
        //BasicDataSource.setDriverClassName("org.gjt.mm.mysql.Driver");
        basicdatasource.setUsername(user);
        basicdatasource.setPassword(pass);
        basicdatasource.setUrl(url);
        basicdatasource.setMaxTotal(50);
    }
}
