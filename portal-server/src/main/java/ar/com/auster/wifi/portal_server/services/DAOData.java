package ar.com.auster.wifi.portal_server.services;

import ar.com.auster.wifi.portal_server.mapper.*;
import ar.com.auster.wifi.portal_server.model.Client;
import ar.com.auster.wifi.portal_server.model.ClientVoucher;
import ar.com.auster.wifi.portal_server.model.Device;
import ar.com.auster.wifi.portal_server.model.Voucher;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional(value = "DataTx", isolation = Isolation.READ_UNCOMMITTED)
public class DAOData implements IDAOData {

    private static Logger log = org.slf4j.LoggerFactory.getLogger(DAOData.class);

    @Resource(name = "DataJdbc")
    private JdbcTemplate jdbcApi;

    @Override
    public List<Voucher> getVouchersAvailables() {
        List<Voucher> resultList = new ArrayList<>();

        final String sql = "SELECT * FROM VOUCHERS WHERE STATUS = 'READY'";
        List<Map<String, Object>> listMap = jdbcApi.queryForList(sql);
        if (listMap != null && !listMap.isEmpty()) {
            listMap.forEach(itemMap -> {
                resultList.add(VoucherMapper.buildObject(itemMap));
            });
        }
        return resultList;
    }

    @Override
    public List<ClientVoucher> getClienVouchers(Client client, String mac) {
        List<ClientVoucher> resultList = new ArrayList<>();
        if (client != null && client.getId() != null && mac != null) {
            final String sql = "SELECT * FROM CLIENT_VOUCHERS WHERE CLIENT_ID = ? AND DEVICE_MAC = ?";
            List<Map<String, Object>> listMap = jdbcApi.queryForList(sql, new Object[]{client.getId(), mac});
            if (listMap != null && !listMap.isEmpty()) {
                listMap.forEach(itemMap -> {
                    ClientVoucher cv = ClientVoucherMapper.buildObject(itemMap);
                    cv.setClient(client);
                    resultList.add(cv);
                });
            }
        }
        return resultList;
    }

    @Override
    public Device getDeviceByMac(String mac) {
        Device result = null;
        if (mac != null) {
            final String sql = "SELECT * FROM DEVICES WHERE STATUS <> 'DELETED' AND MAC = ? ORDER BY  LAST_OPERATION DESC LIMIT 1";
            Map<String, Object> mapItem = jdbcApi.queryForMap(sql, new Object[]{mac.trim()});
            result = DeviceMapper.buildObject(mapItem);
        }
        return result;
    }

    @Override
    public boolean addNewDevice(Device device) {
        boolean result = false;
        final String sql = "INSERT INTO DEVICES (MAC, IP, STATUS) VALUES (?, ?, ?)";
        result = jdbcApi.update(sql, new Object[]{device.getMac(), device.getIp(), device.getStatus()}) == 1 ? true : false;
        return result;
    }

    @Override
    public Voucher getVoucherById(Long id) {
        Voucher result = null;
        if (id != null) {
            final String sql = "SELECT * FROM VOUCHERS WHERE ID = ? LIMIT 1";
            Map<String, Object> mapItem = jdbcApi.queryForMap(sql, new Object[] {id});
            if (mapItem != null) {
                result = VoucherMapper.buildObject(mapItem);
            }
        }
        return result;
    }

    @Override
    public Client getClienById(Long id) {
        Client result = null;
        if (id != null) {
            final String sql = "SELECT * FROM CLIENTS WHERE ID = ? LIMIT 1";
            Map<String, Object> mapItem = jdbcApi.queryForMap(sql, new Object[] {id});
            if (mapItem != null) {
                result = ClientMapper.buildObject(mapItem);
            }
        }
        return result;
    }

}
