package ar.com.auster.wifi.portal_server.services;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class DAOData implements IDAOData {

    private static Logger log = org.slf4j.LoggerFactory.getLogger(DAOData.class);

    @Resource(name = "DataDB")
    private DataSource dbData;

    //TODO

}
