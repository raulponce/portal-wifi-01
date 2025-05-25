package ar.com.auster.wifi.portal_server.services;

import ar.com.auster.wifi.portal_server.model.Client;
import ar.com.auster.wifi.portal_server.model.ClientVoucher;
import ar.com.auster.wifi.portal_server.model.Device;
import ar.com.auster.wifi.portal_server.model.Voucher;

import java.util.List;

public interface IDAOData {

    List<Voucher> getVouchersAvailables();

    List<ClientVoucher> getClienVouchers(Client client, String mac);

    Device getDeviceByMac(String mac);

    boolean addNewDevice(Device device);

    Voucher<?> getVoucherById(Long id);

    Client getClienById(Long id);
}
