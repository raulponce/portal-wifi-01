package ar.com.auster.wifi.portal_server.mapper;

import ar.com.auster.wifi.portal_server.model.*;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.Map;

@Repository
@Transactional(value = "DataTx", isolation = Isolation.READ_UNCOMMITTED)
public class Tests {

    @Resource(name = "DataJdbc")
    private JdbcTemplate jdbcApi;

//    @Transactional(value = "DataTx", isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRES_NEW)
    public void testVoucherMapper() {
        Voucher item = new Voucher();
        item.setId(null);
        item.setName("voucherA01");
        item.setType(VoucherType.BY_TIME);
        item.setStatus(VoucherStatus.READY);
        VoucherPrice price = new VoucherPrice();
        price.setCurrency(Currency.USD);
        price.setCost(12.56);
        item.setPrice(price);
        item.setUnit(VoucherUnit.HOUR);
        item.setDataInt(3);
        item.setCreationTime(null);
        item.setModificationTime(null);
        item.setDeletionTime(null);
        //save
        String sqlInsert = "INSERT INTO VOUCHERS (" +
                "NAME, " +
                "TYPE, " +
                "STATUS, " +
                "CURRENCY, " +
                "PRICE, " +
                "UNIT, " +
                "DATA_INT) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        Object insertValues[] = {
                item.getName(),
                item.getType().name(),
                item.getStatus().name(),
                item.getPrice().getCurrency().name(),
                item.getPrice().getCost(),
                item.getUnit() != null ? item.getUnit().name() : null,
                item.getDataInt()
        };
        int insertTypes[] = {
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.VARCHAR,
                Types.DECIMAL,
                Types.VARCHAR,
                Types.INTEGER
        };


        KeyHolder kh = new GeneratedKeyHolder();
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                String[] key = new String[] { "ID" };
                PreparedStatement ps = connection.prepareStatement(sqlInsert, key);
                for (int idx=0; idx<insertValues.length; idx++) {
                    if (insertValues[idx] != null) {
                        ps.setObject(idx + 1, insertValues[idx], insertTypes[idx]);
                    } else {
                        ps.setNull(idx + 1, insertTypes[idx]);
                    }
                }

                return ps;
            }
        };

        //int insertRet = jdbcApi.update(sqlInsert, insertValues, insertTypes);
        int insertRet = jdbcApi.update(psc, kh);
        if (insertRet != 1) {
            System.err.println(String.format("Update insert ret != 0: %d", insertRet));
        }

        Number itemKey = kh.getKey();
        if (itemKey == null) {
            System.err.println(String.format("Update insert key is NULL"));
        }

        String sqlSelect = "SELECT * FROM VOUCHERS WHERE ID = ?";
        Map<String, Object> mapItem = jdbcApi.queryForMap(sqlSelect,
                new Object[] { itemKey.intValue() },
                new int[] { Types.INTEGER });

        Voucher savedItem = VoucherMapper.buildObject(mapItem);
        if (savedItem == null) {
            System.err.println(String.format("Select mapper NULL"));
        } else {
            if (savedItem.getId().intValue() != itemKey.intValue()) {
                System.err.println(String.format("ID key mismatch"));
            }
            if (!item.getName().equals(savedItem.getName())) {
                System.err.println(String.format("Name mismatch"));
            }
            if (savedItem.getCreationTime() == null) {
                System.err.println(String.format("CreationTime is NULL"));
            } else {
                System.out.println("BD    TIME: "+savedItem.getCreationTime());
                System.out.println("LOCAL TIME: "+savedItem.getCreationTime().toLocalDateTime());
            }
        }
        //get
    }

}
