package ar.com.auster.wifi.portal_server.services;

import ar.com.auster.wifi.portal_server.mapper.VoucherMapper;
import ar.com.auster.wifi.portal_server.model.Voucher;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class DAOData implements IDAOData {

    private static Logger log = org.slf4j.LoggerFactory.getLogger(DAOData.class);

    @Resource(name = "DataDB")
    private DataSource dbData;

    private JdbcTemplate jdbcApi;

    @PostConstruct
    private void onInit() {
        jdbcApi = new JdbcTemplate(dbData);
    }

    public List<Voucher> getVouchers() {
        List<Voucher> resultList = new ArrayList<>();

        String sql = "SELECT * FROM VOUCHERS";
        List<Map<String, Object>> listMap = jdbcApi.queryForList(sql);
        if (listMap != null && !listMap.isEmpty()) {
            listMap.forEach(itemMap -> {
                resultList.add(VoucherMapper.buildObject(itemMap));
            });
        }
        return resultList;
    }

}
